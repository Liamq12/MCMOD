package com.liamq12.examplemod.common.biomes;

import static net.minecraftforge.common.BiomeDictionary.Type;

import com.liamq12.examplemod.world.BiomeInit;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class SpecialBiome {
	
	public static Biome test_biome;
	
	public static void registerBiome(String name) {
		ResourceLocation rl = new ResourceLocation("example:" + name);
		RegistryKey<Biome> TEST_BIOME_KEY = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, rl);
		BiomeDictionary.addTypes(TEST_BIOME_KEY, Type.HILLS);
		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(TEST_BIOME_KEY, 1000));
		
	}
}
