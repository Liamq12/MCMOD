package com.liamq12.examplemod.core.init;

import java.util.ArrayList;
import java.util.HashMap;

import com.liamq12.examplemod.ExampleMod;
import com.liamq12.examplemod.common.items.SpecialItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.item.Item.Properties;
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
	
	public static ArrayList<RegistryObject<Item>> SPECIAL_ITEM_LIST = new ArrayList<>();
	public static HashMap<String, RegistryObject<Item>> SPECIAL_ITEM_MAP = new HashMap<String, RegistryObject<Item>>();
	
	public static void registerSpecialItem(String specialItemName, Effect effect) {
		RegistryObject<Item> active = ITEMS.register(specialItemName, () -> {
			Item newItem = new SpecialItem(new Item.Properties().group(ItemGroup.MISC), effect);
			ITEM_CLASS_MAP.put(specialItemName, newItem);
			return newItem;
		});
		SPECIAL_ITEM_LIST.add(active);
		SPECIAL_ITEM_MAP.put(specialItemName, active);
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
}
