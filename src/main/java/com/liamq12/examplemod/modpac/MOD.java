package com.liamq12.examplemod.modpac;

import com.liamq12.examplemod.core.init.BlockInit;
import com.liamq12.examplemod.core.init.ItemInit;
import com.liamq12.examplemod.core.init.SoundInit;
import com.liamq12.examplemod.world.BiomeInit;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.datafix.fixes.ItemIntIDToString;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.rmi.registry.Registry;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;
import com.liamq12.examplemod.ExampleMod;
import com.liamq12.examplemod.common.biomes.CustomBiome;
import com.liamq12.examplemod.common.biomes.SpecialBiome;
import com.liamq12.examplemod.common.blocks.LuckyBlock;
import com.liamq12.examplemod.common.blocks.PreInitBlock;
import com.liamq12.examplemod.common.blocks.SpecialBlock;
import com.liamq12.examplemod.common.blocks.SpecialOre;
import com.liamq12.examplemod.common.entities.EntityList;
import com.liamq12.examplemod.common.items.CustomAxe;
import com.liamq12.examplemod.common.items.CustomHoe;
import com.liamq12.examplemod.common.items.CustomPickaxe;
import com.liamq12.examplemod.common.items.CustomShovel;
import com.liamq12.examplemod.common.items.CustomSword;
import com.liamq12.examplemod.common.items.SpecialItem;

public class MOD {
	static ArrayList<String> itemKeys = new ArrayList<>();
	static ArrayList<String> blockKeys = new ArrayList<>();
	static ArrayList<String> specialItemKeys = new ArrayList<>();
	static ArrayList<String> foodItemKeys = new ArrayList<>();
	static ArrayList<String> luckyBlocks = new ArrayList<>();
	static ArrayList<String> toolKeys = new ArrayList<>();
	static ArrayList<CustomBiome> customBiomes = new ArrayList<>();
	static ArrayList<String> discList = new ArrayList<>();
	public static ArrayList<SpecialOre> oreGenerationKeys = new ArrayList<>();

	public static HashMap<String, Boolean> HAS_DROP = new HashMap<String, Boolean>();
	public static HashMap<String, ArrayList<String>> MASTER_LUCKY_BLOCKS_ENTITIES = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, ArrayList<String>> MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, ArrayList<Item>> MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS = new HashMap<String, ArrayList<Item>>();
	public static HashMap<String, CustomBiome> CUSTOM_BIOME_KEYS = new HashMap<String, CustomBiome>();
	public static HashMap<String, PreInitBlock> PREINIT_BLOCKS = new HashMap<String, PreInitBlock>();
	
	private static String discJson = "{\r\n";

	//TODO 
	//Fix armor lang generation
	//Make all items "special" and deprecate .addRightClickEffect
	//Continuity errors between MOD.item/MOD.block and string equivelents
	//Fix hoeing on snow
	//Fix Effect.beam() crashes
	
	//Tons of other stuff (very useful description on a TODO)
	
	
	public MOD() {

	}

	@SuppressWarnings("unchecked")
	public static void addItem(String name) {
		// ItemInit.registerItem(name);
		itemKeys.add(name);
		specialItemKeys.add(name);
		writeItemJson(name);
		ItemInit.setSpecialItem(name);
	}

	@SuppressWarnings("unchecked")
	public static void addBlock(String name, float hardness, float resistance, ToolType harvestTool, int harvestLevel) {
		// ItemInit.registerBlockItem(name);
		// BlockInit.registerBlock(name, hardness, resistance, harvestTool,
		// harvestLevel);
		blockKeys.add(name);
		writeBlockJson(name);
		BlockInit.setBlock(name, hardness, resistance, harvestTool, harvestLevel);
		PREINIT_BLOCKS.put(name, new PreInitBlock(name, hardness, resistance, harvestTool, harvestLevel));
	}

	public static void addDrop(SpecialBlock blockNamee, SpecialItem itemNamee) {
		String itemName = itemNamee.getItemName();
		String blockName = blockNamee.getName();
		HAS_DROP.put(blockName, true);
		String base = "{\"type\": \"minecraft:block\",\"pools\": [{\"rolls\": 1,\"entries\": [{\"type\": \"minecraft:alternatives\",\"children\": [{\"type\": \"minecraft:item\",\"name\": \""
				+ ExampleMod.MOD_ID + ":" + blockName
				+ "\",\"conditions\": [{\"condition\": \"minecraft:match_tool\",\"predicate\": {\"enchantments\": [{\"enchantment\": \"minecraft:silk_touch\",\"levels\": {\"min\": 1}}]}}]},{\"type\": \"minecraft:item\",\"name\": \""
				+ ExampleMod.MOD_ID + ":" + itemName
				+ "\",\"functions\": [{\"function\": \"minecraft:apply_bonus\",\"enchantment\": \"minecraft:fortune\",\"formula\": \"minecraft:ore_drops\"},{\"function\": \"minecraft:explosion_decay\"}]}]}]}]}";
		File lootTable = new File(
				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
		try {
			lootTable.createNewFile();
			FileWriter w = new FileWriter(ExampleMod.PATH
					+ "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
			w.write(base);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Re-add these when all blocks are specialblocks
//	
//	public static void addDrop(Block blockNamee, SpecialItem itemNamee) {
//        String[] str = blockNamee.getRegistryName().toString().split(":");
//		String itemName = itemNamee.getItemName();
//        String blockName = str[1];
//		HAS_DROP.put(blockName, true);
//		String base = "{\"type\": \"minecraft:block\",\"pools\": [{\"rolls\": 1,\"entries\": [{\"type\": \"minecraft:alternatives\",\"children\": [{\"type\": \"minecraft:item\",\"name\": \""
//				+ ExampleMod.MOD_ID + ":" + blockName
//				+ "\",\"conditions\": [{\"condition\": \"minecraft:match_tool\",\"predicate\": {\"enchantments\": [{\"enchantment\": \"minecraft:silk_touch\",\"levels\": {\"min\": 1}}]}}]},{\"type\": \"minecraft:item\",\"name\": \""
//				+ ExampleMod.MOD_ID + ":" + itemName
//				+ "\",\"functions\": [{\"function\": \"minecraft:apply_bonus\",\"enchantment\": \"minecraft:fortune\",\"formula\": \"minecraft:ore_drops\"},{\"function\": \"minecraft:explosion_decay\"}]}]}]}]}";
//		File lootTable = new File(
//				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
//		try {
//			lootTable.createNewFile();
//			FileWriter w = new FileWriter(ExampleMod.PATH
//					+ "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
//			w.write(base);
//			w.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static void addDrop(Block blockNamee, Item itemNamee) {
//        String[] str = blockNamee.getRegistryName().toString().split(":");
//        String[] strr = itemNamee.getRegistryName().toString().split(":");
//        String blockName = str[1];
//        String itemName = strr[1];
//		HAS_DROP.put(blockName, true);
//		String base = "{\"type\": \"minecraft:block\",\"pools\": [{\"rolls\": 1,\"entries\": [{\"type\": \"minecraft:alternatives\",\"children\": [{\"type\": \"minecraft:item\",\"name\": \""
//				+ ExampleMod.MOD_ID + ":" + blockName
//				+ "\",\"conditions\": [{\"condition\": \"minecraft:match_tool\",\"predicate\": {\"enchantments\": [{\"enchantment\": \"minecraft:silk_touch\",\"levels\": {\"min\": 1}}]}}]},{\"type\": \"minecraft:item\",\"name\": \""
//				+ ExampleMod.MOD_ID + ":" + itemName
//				+ "\",\"functions\": [{\"function\": \"minecraft:apply_bonus\",\"enchantment\": \"minecraft:fortune\",\"formula\": \"minecraft:ore_drops\"},{\"function\": \"minecraft:explosion_decay\"}]}]}]}]}";
//		File lootTable = new File(
//				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
//		try {
//			lootTable.createNewFile();
//			FileWriter w = new FileWriter(ExampleMod.PATH
//					+ "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
//			w.write(base);
//			w.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	//Add custom block instance
	
	public static void addDrop(SpecialBlock blockNamee, Item itemNamee) {
        String[] strr = itemNamee.getRegistryName().toString().split(":");
        String itemName = strr[1];
        System.out.println(blockNamee + " ||&&");
		String blockName = blockNamee.getName();
		HAS_DROP.put(blockName, true);
		String base = "{\"type\": \"minecraft:block\",\"pools\": [{\"rolls\": 1,\"entries\": [{\"type\": \"minecraft:alternatives\",\"children\": [{\"type\": \"minecraft:item\",\"name\": \""
				+ ExampleMod.MOD_ID + ":" + blockName
				+ "\",\"conditions\": [{\"condition\": \"minecraft:match_tool\",\"predicate\": {\"enchantments\": [{\"enchantment\": \"minecraft:silk_touch\",\"levels\": {\"min\": 1}}]}}]},{\"type\": \"minecraft:item\",\"name\": \""
				+ "minecraft:" + itemName
				+ "\",\"functions\": [{\"function\": \"minecraft:apply_bonus\",\"enchantment\": \"minecraft:fortune\",\"formula\": \"minecraft:ore_drops\"},{\"function\": \"minecraft:explosion_decay\"}]}]}]}]}";
		File lootTable = new File(
				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
		try {
			lootTable.createNewFile();
			FileWriter w = new FileWriter(ExampleMod.PATH
					+ "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + blockName + ".json");
			w.write(base);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Finish migrating all items to special items

//	public static void addRightClickEffect(String itemName) {
//		itemKeys.add(itemName);
//		specialItemKeys.add(itemName);
//		ItemInit.registerSpecialItem(itemName);
//		writeItemJson(itemName);
//	}
	
	public static void addPickaxe(String name, SpecialItem materiall, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		String material = materiall.getItemName();
		toolKeys.add(name);
		generateToolJson(name);
		ItemInit.registerPickaxeCustomItem(name, harvestLevel, maxUses, efficiency, (attackdamage-1f)/2f, enchantabillity, attackSpeed);
		generateToolCraftingJson(name, material, "###", " * ", " * ");
	}
	
	public static void addAxe(String name, SpecialItem materiall, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		String material = materiall.getItemName();
		toolKeys.add(name);
		generateToolJson(name);
		ItemInit.registerAxeCustomItem(name, harvestLevel, maxUses, efficiency, (attackdamage-1f)/2f, enchantabillity, attackSpeed);
		generateToolCraftingJson(name, material, " ##", " *#", " * ");
	}
	
	public static void addHoe(String name, SpecialItem materiall, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		String material = materiall.getItemName();
		toolKeys.add(name);
		generateToolJson(name);
		ItemInit.registerHoeCustomItem(name, harvestLevel, maxUses, efficiency, (attackdamage-1f)/2f, enchantabillity, attackSpeed);
		generateToolCraftingJson(name, material, "## ", " * ", " * ");
	}
	
	public static void addShovel(String name, SpecialItem materiall, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		String material = materiall.getItemName();
		toolKeys.add(name);
		generateToolJson(name);
		ItemInit.registerShovelCustomItem(name, harvestLevel, maxUses, efficiency, (attackdamage-1f)/2f, enchantabillity, attackSpeed);
		generateToolCraftingJson(name, material, " # ", " * ", " * ");
	}
	
	public static void addSword(String name, SpecialItem materiall, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		String material = materiall.getItemName();
		toolKeys.add(name);
		generateToolJson(name);
		ItemInit.registerSwordCustomItem(name, harvestLevel, maxUses, efficiency, (attackdamage-1f)/2f, enchantabillity, attackSpeed);
		generateToolCraftingJson(name, material, " # ", " # ", " * ");
	}
	
	public static CustomPickaxe pickaxe(String name) {
		return ItemInit.PICKAXE_ITEMS.get(name);
	}
	
	public static CustomAxe axe(String name) {
		return ItemInit.AXE_ITEMS.get(name);
	}
	
	public static CustomHoe hoe(String name) {
		return ItemInit.HOE_ITEMS.get(name);
	}
	
	public static CustomShovel shovel(String name) {
		return ItemInit.SHOVEL_ITEMS.get(name);
	}
	
	public static CustomSword sword(String name) {
		return ItemInit.SWORD_ITEMS.get(name);
	}

	public static void addHelmet(String name, SpecialItem itemFrom, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		String[] parts = name.split("_", 2);
		ItemInit.registerHelmet(name, parts[0], maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
		writeItemJson(name);
		setCraftingRecipe(itemFrom, name, "###", "# #", "   ", 1);
	}
	
	public static void addChestplate(String name, SpecialItem itemFrom, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		String[] parts = name.split("_", 2);
		ItemInit.registerChestplate(name, parts[0], maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
		writeItemJson(name);
		setCraftingRecipe(itemFrom, name, "# #", "###", "###", 1);
	}
	
	public static void addLeggings(String name, SpecialItem itemFrom, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		String[] parts = name.split("_", 2);
		ItemInit.registerLeggings(name, parts[0], maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
		writeItemJson(name);
		setCraftingRecipe(itemFrom, name, "###", "# #", "# #", 1);
	}
	
	public static void addBoots(String name, SpecialItem itemFrom, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		String[] parts = name.split("_", 2);
		ItemInit.registerBoots(name, parts[0], maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
		writeItemJson(name);
		setCraftingRecipe(itemFrom, name, "# #", "# #", "   ", 1);
	}
	
	public static void addArmorSet(String name, SpecialItem itemFrom, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		addHelmet(name + "_hel", itemFrom, maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
		addChestplate(name + "_che", itemFrom, maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
		addLeggings(name + "_leg", itemFrom, maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
		addBoots(name + "_bot", itemFrom, maxDamageFactor, damageReductionAmount, enchantabillity, toughness, knockbackResistance);
	}
	
	private static void generateToolCraftingJson(String swordName, String material, String topPattern, String middlePattern, String bottomPattern) {
		String base = "{\r\n" + 
				"    \"type\": \"minecraft:crafting_shaped\",\r\n" + 
				"    \"pattern\": [\r\n" + 
				"        \"" + topPattern + "\",\r\n" + 
				"        \"" + middlePattern + "\",\r\n" + 
				"        \"" + bottomPattern + "\"\r\n" + 
				"    ],\r\n" + 
				"    \"key\": {\r\n" + 
				"        \"#\": {\r\n" + 
				"            \"item\": \"example:" + material + "\"\r\n" + 
				"        },\r\n" + 
				"        \"*\": {\r\n" + 
				"            \"item\": \"minecraft:stick\"\r\n" + 
				"        }\r\n" + 
				"    },\r\n" + 
				"    \"result\": {\r\n" + 
				"        \"item\": \"example:" + swordName + "\",\r\n" + 
				"        \"count\": 1\r\n" + 
				"    }\r\n" + 
				"}";
		File recipeJson = new File(
				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes\\"
						+ material + "_to_" + swordName + ".json");
		try {
			recipeJson.createNewFile();
			FileWriter w = new FileWriter(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes\\"
					+ material + "_to_" + swordName + ".json");
			w.write(base);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static SpecialItem item(String name) {
		return ItemInit.SPECIAL_ITEM_MAP.get(name);
	}
	
	//this code works dont @ me
	public static SpecialBlock block(String name) {
		return BlockInit.BLOCK_CLASS_MAP.get(name);
		//return name;
	}

	public static void addFood(String name, Effect effect, int time, int amplifier, float chance, int regen,
			float saturation) {
		ItemInit.registerFoodItem(name, effect, time, amplifier, chance, regen, saturation);
		itemKeys.add(name);
		specialItemKeys.add(name);
		writeItemJson(name);
	}

	public static void generateOre(SpecialBlock blockNamee, int veinSize, int minHeight, int maxHeight, int amount) {
		String name = blockNamee.getName();
		SpecialOre so = new SpecialOre(name, veinSize, minHeight, maxHeight, amount);
		oreGenerationKeys.add(so);
	}

	// Crafting Overloads
	
	public static void setCraftingRecipe(String itemFrom, String itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		craftingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, ExampleMod.MOD_ID + ":" + itemTo, itemTo,
				topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setCraftingRecipe(Item itemFrom, String itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		craftingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), ExampleMod.MOD_ID + ":" + itemTo,
				itemTo, topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setCraftingRecipe(String itemFrom, Item itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		craftingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, "minecraft:" + itemTo.toString(),
				itemTo.toString(), topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setSmeltingRecipe(String itemFrom, Item itemTo, double xp, int time) {
		smeltingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, "minecraft:" + itemTo.toString(),
				itemTo.toString(), xp, time);
	}

	public static void setSmeltingRecipe(Item itemFrom, String itemTo, double xp, int time) {
		smeltingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), "minecraft:" + itemTo.toString(),
				itemTo.toString(), xp, time);
	}

	public static void setSmeltingRecipe(String itemFrom, String itemTo, double xp, int time) {
		smeltingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, ExampleMod.MOD_ID + ":" + itemTo, itemTo,
				xp, time);
	}

	public static void setCraftingRecipe(SpecialItem itemFromm, SpecialItem itemToo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		String itemFrom = itemFromm.getItemName();
		String itemTo = itemToo.getItemName();
		craftingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, ExampleMod.MOD_ID + ":" + itemTo, itemTo,
				topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setCraftingRecipe(Item itemFrom, SpecialItem itemToo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		String itemTo = itemToo.getItemName();
		craftingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), ExampleMod.MOD_ID + ":" + itemTo,
				itemTo, topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setCraftingRecipe(SpecialItem itemFromm, Item itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		String itemFrom = itemFromm.getItemName();
		craftingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, "minecraft:" + itemTo.toString(),
				itemTo.toString(), topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setCraftingRecipe(Item itemFrom, Item itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		craftingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), "minecraft:" + itemTo.toString(),
				itemTo.toString(), topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setSmeltingRecipe(Item itemFrom, Item itemTo, double xp, int time) {
		smeltingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), "minecraft:" + itemTo.toString(),
				itemTo.toString(), xp, time);
	}

	public static void setSmeltingRecipe(SpecialItem itemFromm, Item itemTo, double xp, int time) {
		String itemFrom = itemFromm.getItemName();
		smeltingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, "minecraft:" + itemTo.toString(),
				itemTo.toString(), xp, time);
	}

	public static void setSmeltingRecipe(Item itemFrom, SpecialItem itemToo, double xp, int time) {
		String itemTo = itemToo.getItemName();
		smeltingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), ExampleMod.MOD_ID + ":" + itemTo.toString(),
				itemTo.toString(), xp, time);
	}

	public static void setSmeltingRecipe(SpecialItem itemFromm, SpecialItem itemToo, double xp, int time) {
		String itemFrom = itemFromm.getItemName();
		String itemTo = itemToo.getItemName();
		smeltingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, ExampleMod.MOD_ID + ":" + itemTo, itemTo,
				xp, time);
	}
	
	public static void setCraftingRecipe(SpecialItem itemFrom, String itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		craftingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom.getItemName(), itemFrom.getItemName(), ExampleMod.MOD_ID + ":" + itemTo, itemTo,
				topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setCraftingRecipe(String itemFrom, SpecialItem itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		craftingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, ExampleMod.MOD_ID + ":" + itemTo.getItemName(),
				itemTo.getItemName(), topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void setSmeltingRecipe(SpecialItem itemFrom, String itemTo, double xp, int time) {
		smeltingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom.getItemName(), itemFrom.getItemName(), ExampleMod.MOD_ID + ":" + itemTo,
				itemTo, xp, time);
	}

	public static void setSmeltingRecipe(String itemFrom, SpecialItem itemTo, double xp, int time) {
		smeltingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFrom, itemFrom, ExampleMod.MOD_ID + ":" + itemTo.getItemName(),
				itemTo.getItemName(), xp, time);
	}
	
	public static void setSmeltingRecipe(SpecialBlock itemFromm, SpecialItem itemTo, double xp, int time) {
		String itemFrom = itemTo.getItemName();
		smeltingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFromm.getName(), itemFromm.getName(), ExampleMod.MOD_ID + ":" + itemTo.getItemName(),
				itemTo.getItemName(), xp, time);
	}
	
	public static void setCraftingRecipe(SpecialBlock itemFromm, SpecialItem itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		String itemFrom = itemTo.getItemName();
		craftingRecipeJsonGen(ExampleMod.MOD_ID + ":" + itemFromm.getName(), itemFromm.getName(), ExampleMod.MOD_ID + ":" + itemTo.getItemName(),
				itemTo.getItemName(), topPattern, middlePattern, bottomPattern, resultCount);
	}

	public static void addBiome(String name) {
		CUSTOM_BIOME_KEYS.put(name, new CustomBiome(name));
		customBiomes.add(CUSTOM_BIOME_KEYS.get(name));
		generateBiomeJson(name);
	}
	
	public static CustomBiome biome(String name) {
		return CUSTOM_BIOME_KEYS.get(name);
	}
	
	public static void addDisc(String name) {
		writeItemJson(name);
		SoundInit.registerSound(name);
		ItemInit.registerDisk(name);
		generateSoundJson(name);
		discList.add(name);
	}
	
	public static void makeLuckyBlock(SpecialBlock block) {
		String name = block.getName();
		// blockKeys.add(name);
		luckyBlocks.add(name);
		BlockInit.setLuckyBlock(name, PREINIT_BLOCKS.get(name).getHardness(), PREINIT_BLOCKS.get(name).getResistance(), PREINIT_BLOCKS.get(name).getHarvestTool(), PREINIT_BLOCKS.get(name).getHarvestLevel());
		// ItemInit.registerBlockItem(name);
		// BlockInit.registerBlock(name);
		// BlockInit.registerLuckyBlock(name, hardness, resistance, harvestTool,
		// harvestLevel);
		writeBlockJson(name);
		System.out.println("Good12 " + BlockInit.blockNames.remove(name));
		MASTER_LUCKY_BLOCKS_ENTITIES.put(name, new ArrayList<String>());
		MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.put(name, new ArrayList<String>());
		MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS.put(name, new ArrayList<Item>());
	}
	
	public static LuckyBlock luckyBlock(SpecialBlock block) {
		String name = block.getName();
		return BlockInit.LUCKY_BLOCK_CLASS_MAP.get(name);
	}

//	public static void addLuckyBlockOutcome(String blockName, String entity) {
//		if (EntityList.permittedEntities.contains(entity)) {
//			MASTER_LUCKY_BLOCKS_ENTITIES.get(blockName).add(entity);
//		} else {
//			MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.get(blockName).add(entity);
//		}
//
//	}

	public static void addLuckyBlockOutcome(String blockName, Item item) {
		MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS.get(blockName).add(item);
	}

	// UTILITY FUNCTIONS
	private static void writeItemJson(String itemName) {
		JSONObject jso = new JSONObject();
		jso.put("parent", "item/generated");
		Map<String, String> m = new LinkedHashMap<String, String>(1);
		m.put("layer0", ExampleMod.MOD_ID + ":items/" + itemName);
		jso.put("textures", m);

		PrintWriter pw;
		try {
			pw = new PrintWriter(
					ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\item\\" + itemName + ".json");
			pw.write(jso.toJSONString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void writeBlockJson(String name) {
		// ItemInit.registerBlockItem(name);
		// BlockInit.registerBlock(name);
		// BlockInit.registerBlock(name, hardness, resistance, harvestTool,
		// harvestLevel);
		// blockKeys.add(name);
		JSONObject jso = new JSONObject();
		Map l = new LinkedHashMap(1);
		Map m = new LinkedHashMap(1);
		l.put("model", ExampleMod.MOD_ID + ":block/" + name);
		m.put("", l);
		jso.put("variants", m);

		PrintWriter pw;
		try {
			pw = new PrintWriter(
					ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\blockstates\\" + name + ".json");
			pw.write(jso.toJSONString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsoo = new JSONObject();
		jsoo.put("parent", "block/cube_all");
		Map<String, String> mm = new LinkedHashMap<String, String>(1);
		mm.put("all", ExampleMod.MOD_ID + ":blocks/" + name);
		jsoo.put("textures", mm);

		PrintWriter pww;
		try {
			pww = new PrintWriter(
					ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\block\\" + name + ".json");
			pww.write(jsoo.toJSONString());
			pww.flush();
			pww.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsooo = new JSONObject();
		jsooo.put("parent", ExampleMod.MOD_ID + ":block/" + name);
		PrintWriter pwww;
		try {
			pwww = new PrintWriter(
					ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\item\\" + name + ".json");
			pwww.write(jsooo.toJSONString());
			pwww.flush();
			pwww.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void generateSoundJson(String name) {
		discJson = discJson + "    \"" + name + "\":{\r\n" + 
				"       \"category\":\"item\",\r\n" + 
				"       \"subtitle\":\"" + name + "\",\r\n" + 
				"       \"sounds\":[\r\n" + 
				"          {\r\n" + 
				"             \"name\":\"example:" + name + "\",\r\n" + 
				"             \"stream\":true\r\n" + 
				"          }\r\n" + 
				"       ]\r\n" + 
				"    },\r\n";
		System.out.println("magic34 " + discJson);
	}
	
	private static void finalizeDiscJson() {
		String result = discJson.substring(0, discJson.length()-3);
		String base = result + "\r\n}";
		System.out.println("magic35 " + base);
		File recipeJson = new File(
				ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\sounds.json");
		try { 
			recipeJson.createNewFile();
			FileWriter w = new FileWriter(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\sounds.json");
			w.write(base);
			w.close();
			System.out.println("magic36");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void craftingRecipeJsonGen(String itemFrom, String itemFromName, String itemTo, String itemToName,
			String topPattern, String middlePattern, String bottomPattern, int resultCount) {
		String base = "{\r\n" + "    \"type\": \"minecraft:crafting_shaped\",\r\n" + "    \"pattern\": [\r\n"
				+ "        \"" + topPattern + "\",\r\n" + "        \"" + middlePattern + "\",\r\n" + "        \""
				+ bottomPattern + "\"\r\n" + "    ],\r\n" + "    \"key\": {\r\n" + "        \"#\": {\r\n"
				+ "            \"item\": \"" + itemFrom + "\"\r\n" + "        }\r\n" + "    },\r\n"
				+ "    \"result\": {\r\n" + "        \"item\": \"" + itemTo + "\",\r\n" + "        \"count\": "
				+ resultCount + "\r\n" + "    }\r\n" + "}";
		File recipeJson = new File(
				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes\\"
						+ itemFromName + "_to_" + itemToName + ".json");
		try {
			recipeJson.createNewFile();
			FileWriter w = new FileWriter(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes\\"
					+ itemFromName + "_to_" + itemToName + ".json");
			w.write(base);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void smeltingRecipeJsonGen(String itemFrom, String itemFromName, String itemTo, String itemToName,
			double xp, int time) {
		String base = "{\r\n" + "    \"type\": \"minecraft:smelting\",\r\n" + "    \"ingredient\": {\r\n"
				+ "        \"item\": \"" + itemFrom + "\"\r\n" + "    },\r\n" + "    \"result\": \"" + itemTo
				+ "\",\r\n" + "    \"experience\": " + xp + ",\r\n" + "    \"cookingtime\": " + time + "\r\n" + "}";
		File recipeJson = new File(
				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes\\"
						+ itemFromName + "_to_" + itemToName + ".json");
		try {
			recipeJson.createNewFile();
			FileWriter w = new FileWriter(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes\\"
					+ itemFromName + "_to_" + itemToName + ".json");
			w.write(base);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void generateBiomeJson(String name) {
		String base = "{\"surface_builder\":\"minecraft:grass\",\"depth\":0.125,\"scale\":0.05,\"temperature\":0.8,\"downfall\":0.4,\"precipitation\":\"rain\",\"category\":\"plains\",\"effects\":{\"sky_color\":16711680,\"fog_color\":16711795,\"water_color\":16711680,\"water_fog_color\":255,\"grass_color\":65322,\"foliage_color\":65356},\"starts\":[],\"spawners\":{},\"spawn_costs\":{},\"carvers\":{},\"features\":[]}";
		File recipeJson = new File(
				ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\worldgen\\"
						+ name + ".json");
		try {
			recipeJson.createNewFile();
			FileWriter w = new FileWriter(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\worldgen\\"
					+ name + ".json");
			w.write(base);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void generateToolJson(String itemName) {
		JSONObject jso = new JSONObject();
		jso.put("parent", "item/handheld");
		Map<String, String> m = new LinkedHashMap<String, String>(1);
		m.put("layer0", ExampleMod.MOD_ID + ":items/" + itemName);
		jso.put("textures", m);

		PrintWriter pw;
		try {
			pw = new PrintWriter(
					ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\item\\" + itemName + ".json");
			pw.write(jso.toJSONString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void endLang() {
		ItemInit.registerTools();
		ItemInit.registerItems();
		BlockInit.registerLuckyBlocks();
		BlockInit.registerBlocks();
		finalizeDiscJson();
		JSONObject jso = new JSONObject();
		for(int i = 0; i < discList.size(); i++) {
			String activeName = discList.remove(0);
			jso.put("item." + ExampleMod.MOD_ID + activeName, activeName);
			jso.put("item." + ExampleMod.MOD_ID + activeName + ".desc", ExampleMod.NAME + "'s cool disk");
		}
		for(int i = 0; i < toolKeys.size(); i++) {
			String activeName = toolKeys.remove(0);
			jso.put("item." + ExampleMod.MOD_ID + "." + activeName, activeName);
			}
		for (int i = 0; i < itemKeys.size() + 1; i++) { // Why does this +1 fix it? idk, it should not be needed but
			// here we are
			String activeName = itemKeys.remove(0);
			if (!specialItemKeys.contains(activeName)) {
				ItemInit.registerItem(activeName);
			}
			jso.put("item." + ExampleMod.MOD_ID + "." + activeName, activeName);
		}
		for (int i = 0; i < blockKeys.size() + 1; i++) {
			String activeName = blockKeys.remove(0);
			if (HAS_DROP.get(activeName) == null) {
				String base = "{\r\n" + "  \"type\": \"minecraft:block\",\r\n" + "  \"pools\": [\r\n" + "    {\r\n"
						+ "      \"rolls\": 1,\r\n" + "      \"entries\": [\r\n" + "        {\r\n"
						+ "          \"type\": \"minecraft:item\",\r\n" + "          \"name\": \"example:" + activeName
						+ "\"\r\n" + "        }\r\n" + "      ]\r\n" + "    }\r\n" + "  ]\r\n" + "}";
				File lootTable = new File(ExampleMod.PATH
						+ "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + activeName + ".json");
				try {
					lootTable.createNewFile();
					FileWriter w = new FileWriter(ExampleMod.PATH
							+ "\\src\\main\\resources\\data\\example\\loot_tables\\blocks\\" + activeName + ".json");
					w.write(base);
					w.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			jso.put("block." + ExampleMod.MOD_ID + "." + activeName, activeName);
			if (luckyBlocks.contains(activeName)) {
				System.out.println("magic3444");
				PreInitBlock preBlock = PREINIT_BLOCKS.get(activeName);
				//BlockInit.registerLuckyBlock(activeName, preBlock.getHardness(), preBlock.getResistance(),
				//		preBlock.getHarvestTool(), preBlock.getHarvestLevel());
				ItemInit.registerBlockItem(activeName);
			} else {
				System.out.println("magic34445");
				PreInitBlock preBlock = PREINIT_BLOCKS.get(activeName);
				//BlockInit.registerBlock(activeName, preBlock.getHardness(), preBlock.getResistance(),
				//		preBlock.getHarvestTool(), preBlock.getHarvestLevel());
				ItemInit.registerBlockItem(activeName);
			}
		}
		PrintWriter pw;
		try {
			pw = new PrintWriter(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\lang\\en_us.json");
			pw.write(jso.toJSONString());
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Add luck block outcomes
//		for (int i = 0; i < luckyBlocks.size(); i++) {
//			for (int j = 0; j < MASTER_LUCKY_BLOCKS_ENTITIES.get(luckyBlocks.get(i)).size(); j++) {
//				BlockInit.LUCKY_BLOCK_CLASS_MAP.get(luckyBlocks.get(i))
//						.setMobOutcome(MASTER_LUCKY_BLOCKS_ENTITIES.get(luckyBlocks.get(i)).get(j));
//			}
//			System.out.println("size is " + ItemInit.ITEM_CLASS_MAP.size());
//			for(int j = 0; j < MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.get(luckyBlocks.get(i)).size(); j++){
//				BlockInit.LUCKY_BLOCK_CLASS_MAP.get(luckyBlocks.get(i))
//						.setModdedItemOutcome(MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.get(luckyBlocks.get(i)).get(j));
//			}
//			for(int j = 0; j < MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS.get(luckyBlocks.get(i)).size(); j++) {
//				BlockInit.LUCKY_BLOCK_CLASS_MAP.get(luckyBlocks.get(i))
//						.setVanilliaItemOutcome(MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS.get(luckyBlocks.get(i)).get(j));
//			}
//		}
		for(int i = 0; i < customBiomes.size()+1; i++) {
		CustomBiome cb = customBiomes.remove(0);
		BiomeInit.registerBiome(cb);
		SpecialBiome.registerBiome(cb.getName());
		}
	}
	

	public static void readyLang() {
		//Folders
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\item"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\blockstates"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\block"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\loot_tables\\blocks"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\\resources\\data\\example\\worldgen"));
		
		//Files
		File sounds = new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\sounds.json");
		sounds.delete();
		File myJSON = new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\lang\\en_us.json");
		myJSON.delete();
	}

	private static void deleteFolderContents(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolderContents(f);
				} else {
					f.delete();
				}
			}
		}
		// folder.delete();
	}
}
