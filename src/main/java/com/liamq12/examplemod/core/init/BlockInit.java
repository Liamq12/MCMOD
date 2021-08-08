package com.liamq12.examplemod.core.init;

import java.util.ArrayList;
import java.util.HashMap;

import com.liamq12.examplemod.ExampleMod;
import com.liamq12.examplemod.common.blocks.LuckyBlock;
import com.liamq12.examplemod.common.blocks.SpecialBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);
	
//	public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("aquamarine_ore", 
//			() -> new Block(AbstractBlock.Properties
//					.create(Material.IRON, MaterialColor.BLUE).hardnessAndResistance(15f, 30f)
//					.harvestTool(ToolType.PICKAXE)
//					.harvestLevel(-1)));

	//static ArrayList<RegistryObject<Block>> BLOCK_LIST = new ArrayList<>();
	//public static ArrayList<String> BLOCK_NAMES = new ArrayList<>();
	public static HashMap<String, RegistryObject<Block>> BLOCK_MAP = new HashMap<String, RegistryObject<Block>>();
	public static HashMap<String, SpecialBlock> BLOCK_CLASS_MAP = new HashMap<String, SpecialBlock>();
	public static ArrayList<String> blockNames = new ArrayList<>();
	
	public static void setBlock(String blockName, float hardness, float resistance, ToolType harvestTool, int harvestLevel) {
	    //System.out.println("magic34");
		SpecialBlock block = new SpecialBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(hardness, resistance).harvestTool(harvestTool).harvestLevel(harvestLevel));
		block.setName(blockName);
		BLOCK_CLASS_MAP.put(blockName, block);
		blockNames.add(blockName);
		block.setID(blockName.length()-1);
		//BLOCK_MAP.put(blockName, BLOCKS.register(blockName, () -> block));
		//BLOCK_NAMES.add(blockName);
		//BLOCK_LIST.add(BLOCKS.register(blockName, () -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(15f, 30f).harvestTool(ToolType.PICKAXE).harvestLevel(-1))));
	}
	
	public static HashMap<String, RegistryObject<Block>> LUCKY_BLOCK_MAP = new HashMap<String, RegistryObject<Block>>();
	public static HashMap<String, LuckyBlock> LUCKY_BLOCK_CLASS_MAP = new HashMap<String, LuckyBlock>();
	public static ArrayList<String> luckyBlocksNames = new ArrayList<>();
	
	public static void setLuckyBlock(String blockName, float hardness, float resistance, ToolType harvestTool, int harvestLevel) {
	    System.out.println("magic344");
		LUCKY_BLOCK_CLASS_MAP.put(blockName, new LuckyBlock(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(hardness, resistance).harvestTool(harvestTool).harvestLevel(harvestLevel)));
		luckyBlocksNames.add(blockName);
		//BLOCK_MAP.put(blockName, BLOCKS.register(blockName, () -> LUCKY_BLOCK_CLASS_MAP.get(blockName)));
	}
	
	public static void registerLuckyBlocks() {
		for(int i = 0; i < luckyBlocksNames.size(); i++) {
			String name = luckyBlocksNames.get(i);
		    BLOCK_MAP.put(luckyBlocksNames.get(i), BLOCKS.register(luckyBlocksNames.get(i), () -> LUCKY_BLOCK_CLASS_MAP.get(name)));
		}
	}
	
	public static void registerBlocks() {
		for(int i = 0; i < blockNames.size(); i++) {
			String name = blockNames.get(i);
		    BLOCK_MAP.put(blockNames.get(i), BLOCKS.register(blockNames.get(i), () -> BLOCK_CLASS_MAP.get(name)));
		}
	}
	
}
