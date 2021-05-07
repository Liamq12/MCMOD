package com.liamq12.examplemod.core.init;

import java.util.ArrayList;
import java.util.HashMap;

import com.liamq12.examplemod.ExampleMod;

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
	
	public static void registerBlock(String blockName, float hardness, float resistance, ToolType harvestTool, int harvestLevel) {
		BLOCK_MAP.put(blockName, BLOCKS.register(blockName, () -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(hardness, resistance).harvestTool(harvestTool).harvestLevel(harvestLevel))));
		//BLOCK_NAMES.add(blockName);
		//BLOCK_LIST.add(BLOCKS.register(blockName, () -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(15f, 30f).harvestTool(ToolType.PICKAXE).harvestLevel(-1))));
	}
}
