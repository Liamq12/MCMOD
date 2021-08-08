package com.liamq12.examplemod.core.init;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.base.Supplier;
import com.liamq12.examplemod.ExampleMod;
import com.liamq12.examplemod.common.items.CustomAxe;
import com.liamq12.examplemod.common.items.CustomHoe;
import com.liamq12.examplemod.common.items.CustomPickaxe;
import com.liamq12.examplemod.common.items.CustomShovel;
import com.liamq12.examplemod.common.items.CustomSword;
import com.liamq12.examplemod.common.items.ItemToolTiers;
import com.liamq12.examplemod.common.items.ModArmorMaterial;
import com.liamq12.examplemod.common.items.SpecialItem;
import com.liamq12.examplemod.common.items.CustomDisk;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ToolItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);
	
	//public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("test_item", () -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	
	//Block Items
	//public static final RegistryObject<BlockItem> EXAMPLE_BLOCK = ITEMS.register("aquamarine_ore", () -> new BlockItem(BlockInit.EXAMPLE_BLOCK.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
	
	public static ArrayList<RegistryObject<Item>> ITEM_LIST = new ArrayList<>();
	public static HashMap<String, Item> ITEM_CLASS_MAP = new HashMap<String, Item>();
	public static HashMap<String, RegistryObject<Item>> ITEM_MAP = new HashMap<String, RegistryObject<Item>>();
	
	public static void registerItem(String itemName) {
		
		RegistryObject<Item> active = ITEMS.register(itemName, () -> {
			System.out.println("running");
			Item newItem = new Item(new Item.Properties().group(ItemGroup.MISC));
			ITEM_CLASS_MAP.put(itemName, newItem);
			return newItem;
		});
		ITEM_LIST.add(active);
		ITEM_MAP.put(itemName, active);
		
	}
	
	//static ArrayList<RegistryObject<BlockItem>> BLOCK_LIST = new ArrayList<>();
	
	public static HashMap<String, RegistryObject<Item>> BLOCK_ITEM_MAP = new HashMap<String, RegistryObject<Item>>();
	public static void registerBlockItem(String blockName) {
		
		BLOCK_ITEM_MAP.put(blockName, ITEMS.register(blockName, () -> {
			//lambda lore: is spent 2 hours getting this to work. deferred execution...
			Item newItem = new BlockItem(BlockInit.BLOCK_MAP.get(blockName).get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS));
			ITEM_CLASS_MAP.put(blockName, newItem);
			return newItem;
		}));
	}
	//public static final RegistryObject<SpecialItem> SPECIAL_ITEM = ITEMS.register("special_item", () -> new SpecialItem(new Item.Properties().group(ItemGroup.MISC), Effects.GLOWING));
	
	public static ArrayList<String> SPECIAL_ITEM_LIST = new ArrayList<>();
	public static HashMap<String, SpecialItem> SPECIAL_ITEM_MAP = new HashMap<String, SpecialItem>();
	
	public static void registerSpecialItem() {
		for(int i = 0; i < SPECIAL_ITEM_LIST.size(); i++) {
			String specialItemName = SPECIAL_ITEM_LIST.get(i);
			RegistryObject<Item> active = ITEMS.register(specialItemName, () -> {
				SpecialItem newItem = SPECIAL_ITEM_MAP.get(specialItemName);
//				Item newItem = new SpecialItem(new Item.Properties().group(ItemGroup.MISC));
//				ITEM_CLASS_MAP.put(specialItemName, newItem);
				return newItem;
			});
		}

	}
	
	public static void setSpecialItem(String specialItemName) {
		SpecialItem newItem = new SpecialItem(new Item.Properties().group(ItemGroup.MISC));
		newItem.setName(specialItemName);
		SPECIAL_ITEM_MAP.put(specialItemName, newItem);
		SPECIAL_ITEM_LIST.add(specialItemName);
	}
	
	//public static final RegistryObject<Item> EXAMPLE_FOOD = ITEMS.register("example_food", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().effect(() -> new EffectInstance(Effects.SPEED, 200, 2), 1.0f).hunger(2).saturation(1.0f))));
	
	public static HashMap<String, RegistryObject<Item>> FOOD_ITEM_MAP = new HashMap<String, RegistryObject<Item>>();
	public static void registerFoodItem(String foodName, Effect effect, int time, int amplifier, float chance, int regen, float saturation) {
		FOOD_ITEM_MAP.put(foodName, ITEMS.register(foodName, () -> {
			Item newItem = new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().effect(() -> new EffectInstance(effect, time, amplifier), chance).hunger(regen).saturation(saturation).build()));
			ITEM_CLASS_MAP.put(foodName, newItem);
			return newItem;
		}));
	}
	
	public static void registerDisk(String name) {
		final RegistryObject<Item> foreverDisk = ITEMS.register(name, () -> new CustomDisk(1,
	            SoundInit.SOUND_LAZY.get(name).get(), new Item.Properties().maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));

	}
	
	static ArrayList<CustomPickaxe> pickaxeItems = new ArrayList<>();
	public static HashMap<String, CustomPickaxe> PICKAXE_ITEMS = new HashMap<String, CustomPickaxe>();
	public static void registerPickaxeCustomItem(String name, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		CustomPickaxe cp = new CustomPickaxe(new ItemToolTiers(harvestLevel, maxUses, efficiency, attackdamage, enchantabillity, () -> {return Ingredient.EMPTY;}), (int) attackdamage, attackSpeed,
	            new Item.Properties().group(ItemGroup.TOOLS));
		//cp.effect = Effects.BLINDNESS;
		cp.setName(name);
		PICKAXE_ITEMS.put(name, cp);
		pickaxeItems.add(cp);
	}
	
	static ArrayList<CustomSword> swordItems = new ArrayList<>();
	public static HashMap<String, CustomSword> SWORD_ITEMS = new HashMap<String, CustomSword>();
	public static void registerSwordCustomItem(String name, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		CustomSword cs = new CustomSword(new ItemToolTiers(harvestLevel, maxUses, efficiency, attackdamage, enchantabillity, () -> {return Ingredient.EMPTY;}), (int) attackdamage, attackSpeed,
	            new Item.Properties().group(ItemGroup.COMBAT));
		//cp.effect = Effects.BLINDNESS;
		cs.setName(name);
		SWORD_ITEMS.put(name, cs);
		swordItems.add(cs);
	}
	
	static ArrayList<CustomAxe> axeItems = new ArrayList<>();
	public static HashMap<String, CustomAxe> AXE_ITEMS = new HashMap<String, CustomAxe>();
	public static void registerAxeCustomItem(String name, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		CustomAxe ca = new CustomAxe(new ItemToolTiers(harvestLevel, maxUses, efficiency, attackdamage, enchantabillity, () -> {return Ingredient.EMPTY;}), (int) attackdamage, attackSpeed,
	            new Item.Properties().group(ItemGroup.TOOLS));
		//cp.effect = Effects.BLINDNESS;
		ca.setName(name);
		AXE_ITEMS.put(name, ca);
		axeItems.add(ca);
	}
	
	static ArrayList<CustomShovel> shovelItems = new ArrayList<>();
	public static HashMap<String, CustomShovel> SHOVEL_ITEMS = new HashMap<String, CustomShovel>();
	public static void registerShovelCustomItem(String name, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		CustomShovel cs = new CustomShovel(new ItemToolTiers(harvestLevel, maxUses, efficiency, attackdamage, enchantabillity, () -> {return Ingredient.EMPTY;}), (int) attackdamage, attackSpeed,
	            new Item.Properties().group(ItemGroup.TOOLS));
		//cp.effect = Effects.BLINDNESS;
		cs.setName(name);
		SHOVEL_ITEMS.put(name, cs);
		shovelItems.add(cs);
	}
	
	static ArrayList<CustomHoe> hoeItems = new ArrayList<>();
	public static HashMap<String, CustomHoe> HOE_ITEMS = new HashMap<String, CustomHoe>();
	public static void registerHoeCustomItem(String name, int harvestLevel, int maxUses, float efficiency, float attackdamage, int enchantabillity, float attackSpeed) {
		CustomHoe ch = new CustomHoe(new ItemToolTiers(harvestLevel, maxUses, efficiency, attackdamage, enchantabillity, () -> {return Ingredient.EMPTY;}), (int) attackdamage, attackSpeed,
	            new Item.Properties().group(ItemGroup.TOOLS));
		//cp.effect = Effects.BLINDNESS;
		ch.setName(name);
		HOE_ITEMS.put(name, ch);
		hoeItems.add(ch);
	}
	
	public static void registerTools() {
		registerPickaxeItems();
		registerSwordItems();
		registerShovelItems();
		registerHoeItems();
		registerAxeItems();
	}
	
	public static void registerItems() {
		registerSpecialItem();
	}
	
	public static void registerPickaxeItems() {
		for(int i = 0; i < pickaxeItems.size(); i++) {
			CustomPickaxe cp = pickaxeItems.remove(0);
			final RegistryObject<CustomPickaxe> myPickaxe = ITEMS.register(cp.getToolName(), () -> cp);
		}
	}
	
	public static void registerSwordItems() {
		for(int i = 0; i < swordItems.size(); i++) {
			CustomSword cs = swordItems.remove(0);
			final RegistryObject<CustomSword> mySword = ITEMS.register(cs.getToolName(), () -> cs);
		}
	}
	
	public static void registerHoeItems() {
		for(int i = 0; i < hoeItems.size(); i++) {
			CustomHoe ch = hoeItems.remove(0);
			final RegistryObject<ToolItem> myHoe = ITEMS.register(ch.getToolName(), () -> ch);
		}
	}
	
	public static void registerShovelItems() {
		for(int i = 0; i < shovelItems.size(); i++) {
			CustomShovel cs = shovelItems.remove(0);
			final RegistryObject<ToolItem> myShovel = ITEMS.register(cs.getToolName(), () -> cs);
		}
	}
	
	public static void registerAxeItems() {
		for(int i = 0; i < axeItems.size(); i++) {
			CustomAxe ca = axeItems.remove(0);
			final RegistryObject<ToolItem> myAxe = ITEMS.register(ca.getToolName(), () -> ca);
		}
	}
	
	
	public static void registerHelmet(String name, String model, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {		
		ArmorItem ai = new ArmorItem(new ModArmorMaterial(ExampleMod.MOD_ID + ":" + model, maxDamageFactor, damageReductionAmount, enchantabillity, SoundEvents.ENTITY_IRON_GOLEM_DEATH, toughness, knockbackResistance), EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT));
		ITEMS.register(name, () -> ai);
	}
	
	public static void registerChestplate(String name, String model, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		ArmorItem ai = new ArmorItem(new ModArmorMaterial(ExampleMod.MOD_ID + ":" + model, maxDamageFactor, damageReductionAmount, enchantabillity, SoundEvents.ENTITY_IRON_GOLEM_DEATH, toughness, knockbackResistance), EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT));
		ITEMS.register(name, () -> ai);
	}
	
	public static void registerLeggings(String name, String model, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		ArmorItem ai = new ArmorItem(new ModArmorMaterial(ExampleMod.MOD_ID + ":" + model, maxDamageFactor, damageReductionAmount, enchantabillity, SoundEvents.ENTITY_IRON_GOLEM_DEATH, toughness, knockbackResistance), EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT));
		ITEMS.register(name, () -> ai);
	}
	
	public static void registerBoots(String name, String model, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, float toughness, float knockbackResistance) {
		ArmorItem ai = new ArmorItem(new ModArmorMaterial(ExampleMod.MOD_ID + ":" + model, maxDamageFactor, damageReductionAmount, enchantabillity, SoundEvents.ENTITY_IRON_GOLEM_DEATH, toughness, knockbackResistance), EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT));
		ITEMS.register(name, () -> ai);
	}
}
