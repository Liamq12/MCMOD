package com.liamq12.examplemod.modpac;

import com.liamq12.examplemod.core.init.BlockInit;
import com.liamq12.examplemod.core.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
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
import com.liamq12.examplemod.common.blocks.PreInitBlock;
import com.liamq12.examplemod.common.blocks.SpecialOre;
import com.liamq12.examplemod.common.entities.EntityList;

public class MOD {
	static ArrayList<String> itemKeys = new ArrayList<>();
	static ArrayList<String> blockKeys = new ArrayList<>();
	static ArrayList<String> specialItemKeys = new ArrayList<>();
	static ArrayList<String> foodItemKeys = new ArrayList<>();
	static ArrayList<String> luckyBlocks = new ArrayList<>();
	public static ArrayList<SpecialOre> oreGenerationKeys = new ArrayList<>();

	public static HashMap<String, Boolean> HAS_DROP = new HashMap<String, Boolean>();
	public static HashMap<String, ArrayList<String>> MASTER_LUCKY_BLOCKS_ENTITIES = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, ArrayList<String>> MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS = new HashMap<String, ArrayList<String>>();
	public static HashMap<String, ArrayList<Item>> MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS = new HashMap<String, ArrayList<Item>>();
	public static HashMap<String, PreInitBlock> PREINIT_BLOCKS = new HashMap<String, PreInitBlock>();

	public MOD() {

	}

	@SuppressWarnings("unchecked")
	public static void addItem(String name) {
		// ItemInit.registerItem(name);
		itemKeys.add(name);
		writeItemJson(name);
	}

	@SuppressWarnings("unchecked")
	public static void addBlock(String name, float hardness, float resistance, ToolType harvestTool, int harvestLevel) {
		// ItemInit.registerBlockItem(name);
		// BlockInit.registerBlock(name, hardness, resistance, harvestTool,
		// harvestLevel);
		blockKeys.add(name);
		writeBlockJson(name);
		PREINIT_BLOCKS.put(name, new PreInitBlock(name, hardness, resistance, harvestTool, harvestLevel));
//	PREINIT_BLOCKS.put(name, new PreInitBlock(name, hardness, resistance, harvestTool, harvestLevel));
//	JSONObject jso = new JSONObject();
//	Map l = new LinkedHashMap(1);
//	Map m = new LinkedHashMap(1);
//	l.put("model", ExampleMod.MOD_ID + ":block/" + name);
//	m.put("", l);
//	jso.put("variants", m);
//
//	PrintWriter pw;
//	try {
//	    pw = new PrintWriter(
//		    ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\blockstates\\" + name + ".json");
//	    pw.write(jso.toJSONString());
//	    pw.flush();
//	    pw.close();
//	} catch (FileNotFoundException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
//	JSONObject jsoo = new JSONObject();
//	jsoo.put("parent", "block/cube_all");
//	Map<String, String> mm = new LinkedHashMap<String, String>(1);
//	mm.put("all", ExampleMod.MOD_ID + ":blocks/" + name);
//	jsoo.put("textures", mm);
//
//	PrintWriter pww;
//	try {
//	    pww = new PrintWriter(
//		    ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\block\\" + name + ".json");
//	    pww.write(jsoo.toJSONString());
//	    pww.flush();
//	    pww.close();
//	} catch (FileNotFoundException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
//	JSONObject jsooo = new JSONObject();
//	jsooo.put("parent", ExampleMod.MOD_ID + ":block/" + name);
//	PrintWriter pwww;
//	try {
//	    pwww = new PrintWriter(
//		    ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\item\\" + name + ".json");
//	    pwww.write(jsooo.toJSONString());
//	    pwww.flush();
//	    pwww.close();
//	} catch (FileNotFoundException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}

	}

	public static void addDrop(String blockName, String itemName) {
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

	public static void addPotionRightClickEffect(String itemName, Effect effect) {
		itemKeys.add(itemName);
		specialItemKeys.add(itemName);
		ItemInit.registerSpecialItem(itemName, effect);
		writeItemJson(itemName);
	}

	public static RegistryObject<Item> item(String name) {
		if (ItemInit.ITEM_MAP.get(name) != null) {
			return ItemInit.ITEM_MAP.get(name);
		} else if (ItemInit.BLOCK_ITEM_MAP.get(name) != null) {
			return ItemInit.BLOCK_ITEM_MAP.get(name);
		} else if (ItemInit.SPECIAL_ITEM_MAP.get(name) != null) {
			return ItemInit.SPECIAL_ITEM_MAP.get(name);
		} else if (ItemInit.FOOD_ITEM_MAP.get(name) != null) {
			return ItemInit.FOOD_ITEM_MAP.get(name);
		}
		return null;
	}

	public static RegistryObject<Block> block(String name) {
		return BlockInit.BLOCK_MAP.get(name);
	}

	public static void makeFood(String name, Effect effect, int time, int amplifier, float chance, int regen,
			float saturation) {
		ItemInit.registerFoodItem(name, effect, time, amplifier, chance, regen, saturation);
		itemKeys.add(name);
		specialItemKeys.add(name);
	}

	public static void generateOre(String name, int veinSize, int minHeight, int maxHeight, int amount) {
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

	public static void setCraftingRecipe(Item itemFrom, Item itemTo, String topPattern, String middlePattern,
			String bottomPattern, int resultCount) {
		craftingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), "minecraft:" + itemTo.toString(),
				itemTo.toString(), topPattern, middlePattern, bottomPattern, resultCount);
	}

	// Smelting Overloads
	public static void setSmeltingRecipe(Item itemFrom, Item itemTo, double xp, int time) {
		smeltingRecipeJsonGen("minecraft:" + itemFrom.toString(), itemFrom.toString(), "minecraft:" + itemTo.toString(),
				itemTo.toString(), xp, time);
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

	public static void makeLuckyBlock(String name) {
		// blockKeys.add(name);
		luckyBlocks.add(name);
		// ItemInit.registerBlockItem(name);
		// BlockInit.registerBlock(name);
		// BlockInit.registerLuckyBlock(name, hardness, resistance, harvestTool,
		// harvestLevel);
		writeBlockJson(name);
		MASTER_LUCKY_BLOCKS_ENTITIES.put(name, new ArrayList<String>());
		MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.put(name, new ArrayList<String>());
		MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS.put(name, new ArrayList<Item>());
	}

	public static void addLuckyBlockOutcome(String blockName, String entity) {
		if (EntityList.permittedEntities.contains(entity)) {
			MASTER_LUCKY_BLOCKS_ENTITIES.get(blockName).add(entity);
		} else {
			MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.get(blockName).add(entity);
		}

	}

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

	private static void craftingRecipeJsonGen(String itemFrom, String itemFromName, String itemTo, String itemToName,
			String topPattern, String middlePattern, String bottomPattern, int resultCount) {
		String base = "{\r\n" + "    \"type\": \"minecraft:crafting_shaped\",\r\n" + "    \"pattern\": [\r\n"
				+ "        \"" + topPattern + "\",\r\n" + "        \"" + middlePattern + "\",\r\n" + "        \""
				+ bottomPattern + "\"\r\n" + "    ],\r\n" + "    \"key\": {\r\n" + "        \"#\": {\r\n"
				+ "            \"item\": \"" + itemFrom + "\"\r\n" + "        }\r\n" + "    },\r\n"
				+ "    \"result\": {\r\n" + "        \"item\": \"" + itemTo + "\",\r\n" + "        \"count\": "
				+ resultCount + "\r\n" + "    }\r\n" + "}";
		File recipeJson = new File(
				"C:\\Users\\liamh\\OneDrive\\Desktop\\MCMOD\\src\\main\\resources\\data\\example\\recipes\\"
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
				"C:\\Users\\liamh\\OneDrive\\Desktop\\MCMOD\\src\\main\\resources\\data\\example\\recipes\\"
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

	@SuppressWarnings("unchecked")
	public static void endLang() {
		JSONObject jso = new JSONObject();
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
				BlockInit.registerLuckyBlock(activeName, preBlock.getHardness(), preBlock.getResistance(),
						preBlock.getHarvestTool(), preBlock.getHarvestLevel());
				ItemInit.registerBlockItem(activeName);
			} else {
				System.out.println("magic34445");
				PreInitBlock preBlock = PREINIT_BLOCKS.get(activeName);
				BlockInit.registerBlock(activeName, preBlock.getHardness(), preBlock.getResistance(),
						preBlock.getHarvestTool(), preBlock.getHarvestLevel());
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
		for (int i = 0; i < luckyBlocks.size(); i++) {
			for (int j = 0; j < MASTER_LUCKY_BLOCKS_ENTITIES.get(luckyBlocks.get(i)).size(); j++) {
				BlockInit.LUCKY_BLOCK_CLASS_MAP.get(luckyBlocks.get(i))
						.setMobOutcome(MASTER_LUCKY_BLOCKS_ENTITIES.get(luckyBlocks.get(i)).get(j));
			}
			System.out.println("size is " + ItemInit.ITEM_CLASS_MAP.size());
			for(int j = 0; j < MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.get(luckyBlocks.get(i)).size(); j++){
				BlockInit.LUCKY_BLOCK_CLASS_MAP.get(luckyBlocks.get(i))
						.setModdedItemOutcome(MASTER_LUCKY_BLOCKS_CUSTOM_ITEMS.get(luckyBlocks.get(i)).get(j));
			}
			for(int j = 0; j < MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS.get(luckyBlocks.get(i)).size(); j++) {
				BlockInit.LUCKY_BLOCK_CLASS_MAP.get(luckyBlocks.get(i))
						.setVanilliaItemOutcome(MASTER_LUCKY_BLOCKS_VANILLIA_ITEMS.get(luckyBlocks.get(i)).get(j));
			}
		}
	}
	

	public static void readyLang() {
		File myJSON = new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\lang\\en_us.json");
		myJSON.delete();
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\item"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\blockstates"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\assets\\example\\models\\block"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\loot_tables\\blocks"));
		deleteFolderContents(new File(ExampleMod.PATH + "\\src\\main\\resources\\data\\example\\recipes"));
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
