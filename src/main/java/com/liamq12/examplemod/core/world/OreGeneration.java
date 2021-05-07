package com.liamq12.examplemod.core.world;

import com.liamq12.examplemod.common.blocks.SpecialOre;
import com.liamq12.examplemod.core.init.BlockInit;
import com.liamq12.examplemod.modpac.MOD;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {
	public static void generateOres(final BiomeLoadingEvent event) {
		if(MOD.oreGenerationKeys.size() > 0 && (!event.getCategory().equals(Biome.Category.NETHER) || !event.getCategory().equals(Biome.Category.THEEND))){
			System.out.println("magic34 ");// + MOD.oreGenerationKeys.size());
			for(int i = 0; i < MOD.oreGenerationKeys.size(); i++) {
				//registerGenerate(event, MOD.oreGenerationKeys.get(i));
				SpecialOre so = MOD.oreGenerationKeys.get(i);
				//SpecialOre so = new SpecialOre("aquamarine_ore", 5, 15, 50, 20);
				generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.BLOCK_MAP.get(so.getName()).get().getDefaultState(), so.getVeinSize(), so.getMinHeight(), so.getMaxHeight(), so.getAmount());
			}
		}
	}
	public static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int minHeight, int maxHeight, int amount) {
		settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize)).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight))).square().func_242731_b(amount));
	}
	public static void registerGenerate(final BiomeLoadingEvent event, SpecialOre so) {
		generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.BLOCK_MAP.get(so.getName()).get().getDefaultState(), so.getVeinSize(), so.getMinHeight(), so.getMaxHeight(), so.getAmount());
	}
}
