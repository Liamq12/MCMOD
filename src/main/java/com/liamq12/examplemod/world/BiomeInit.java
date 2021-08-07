package com.liamq12.examplemod.world;

import java.util.HashMap;

import com.liamq12.examplemod.ExampleMod;
//import com.liamq12.examplemod.common.biomes.CustomBiome;
import com.liamq12.examplemod.common.biomes.CustomBiome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.Biome.TemperatureModifier;


public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ExampleMod.MOD_ID);
	
	public static HashMap<String, RegistryObject<Biome>> BIOME_MAP = new HashMap<String, RegistryObject<Biome>>();
	
	public static void registerBiome(CustomBiome cb) {
	  RegistryObject<Biome> TEST_BIOME = BIOMES.register(cb.getName(), () ->
      new Biome.Builder()
          .category(cb.getCategory())
          .withTemperatureModifier(cb.getTemperatureModifier())
          .withGenerationSettings(new BiomeGenerationSettings.Builder()
              .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.field_243767_a)
              .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.field_243768_b)
              .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FANCY_OAK)
              .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_COAL)
              .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_GOLD)
              .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_IRON)
              .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_DIAMOND)
              .withStructure(cb.getStructures().get(0))
              .withStructure(cb.getStructures().get(1))
              .withStructure(cb.getStructures().get(2))
              .withStructure(cb.getStructures().get(3))
              .withStructure(cb.getStructures().get(4))
              .withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244178_j)
              .build()
          )
          .withMobSpawnSettings(new MobSpawnInfo.Builder()
              .withSpawner(cb.getEntities().get(0).getClassification(), new MobSpawnInfo.Spawners(cb.getEntities().get(0), 99999, 5, 20))
              .withSpawner(cb.getEntities().get(1).getClassification(), new MobSpawnInfo.Spawners(cb.getEntities().get(1), 99999, 5, 20))
              .withSpawner(cb.getEntities().get(2).getClassification(), new MobSpawnInfo.Spawners(cb.getEntities().get(2), 99999, 5, 20))
              .withSpawner(cb.getEntities().get(3).getClassification(), new MobSpawnInfo.Spawners(cb.getEntities().get(3), 99999, 5, 20))
              .withSpawner(cb.getEntities().get(4).getClassification(), new MobSpawnInfo.Spawners(cb.getEntities().get(4), 99999, 5, 20))
              .copy()
          )
          .scale(cb.getHeight())
          .downfall(cb.getDownfall())
          .precipitation(cb.getRainType())
          .temperature(cb.getTemperature())
          .depth(cb.getDepth())
          .setEffects(new BiomeAmbience.Builder()
              .withGrassColor(cb.getGrassColor()) //0xDA67C1
              .setFogColor(cb.getFogColor())
              .setWaterColor(cb.getWaterColor())
              .setWaterFogColor(cb.getWaterFogColor())
              .withSkyColor(cb.getSkyColor())
              .withFoliageColor(cb.getFoliageColor())
              .build()
          )
          .build()
  );
	  //this.setRegistryName(RegistryEvents.Location("test_biome"));
	 
	 BIOME_MAP.put("test_biome", TEST_BIOME); 
	 
//		ResourceLocation rl = new ResourceLocation("example:test_biome");
//		RegistryKey<Biome> TEST_BIOME_KEY = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, rl);
//		BiomeDictionary.addTypes(TEST_BIOME_KEY, Type.HILLS);
//		BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(TEST_BIOME_KEY, 1000));
//		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TEST_BIOME.get()), 10);
//		BiomeDictionary.addTypes(TEST_BIOME.get(), Type.PLAINS, Type.OVERWORLD);
//	 
//		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TEST_BIOME.get(), 10));
//		
//		BiomeDictionary.addTypes(TEST_BIOME.get(), Type.PLAINS, Type.OVERWORLD);
	}
	
//	public static final RegistryObject<Biome> GREEN_FOREST_BIOME = BIOMES.register("test_biome", () -> new Biome.Builder().category(Category.FOREST)
//			.depth(1).scale(1).downfall(1).precipitation(RainType.RAIN).temperature(30).withGenerationSettings(BiomeGenerationSettings.DEFAULT_SETTINGS)
//			.withMobSpawnSettings(new MobSpawnInfo.Builder().copy()).withTemperatureModifier(TemperatureModifier.NONE)
//			.setEffects(new BiomeAmbience.Builder().setFogColor(0).setWaterColor(0).setWaterFogColor(0).withSkyColor(0).build()).build());

}


//public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, ExampleMod.MOD_ID);
//private static final RegistryObject<Biome> TEST_BIOME = BIOMES.register("test", BiomeMaker::makeTestBiome);
//private static final RegistryKey<Biome> TEST_BIOME_KEY = RegistryKey.getOrCreateKey(Registry.BIOME_KEY,
//		new ResourceLocation(ExampleMod.MOD_ID, "test"));
//
//@SubscribeEvent(priority = EventPriority.HIGH)
//public static void biomeLoading(BiomeLoadingEvent event) {
//	if (event.getName().equals(TEST_BIOME.get().getRegistryName())) {
//		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(TEST_BIOME_KEY, 6));
//		BiomeDictionary.addTypes(TEST_BIOME_KEY, Type.HILLS, Type.MOUNTAIN);
//	}
//}

//RegistryObject<Biome> TEST_BIOME = BIOMES.register("test_biome", () ->
//new Biome.Builder()
//    .category(Biome.Category.FOREST)
//    .withTemperatureModifier(Biome.TemperatureModifier.NONE)
//    .withGenerationSettings(new BiomeGenerationSettings.Builder()
//        .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.field_243767_a)
//        .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.field_243768_b)
//        .withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FANCY_OAK)
//        .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_COAL)
//        .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_GOLD)
//        .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_IRON)
//        .withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_DIAMOND)
//        .withStructure(StructureFeatures.RUINED_PORTAL)
//        .withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244178_j)
//        .build()
//    )
//    .withMobSpawnSettings(new MobSpawnInfo.Builder()
//        .withSpawner(EntityType.ENDER_DRAGON.getClassification(), new MobSpawnInfo.Spawners(EntityType.ENDER_DRAGON, 99999, 5, 20))
//        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 1, 3, 8))
//        .copy()
//    )
//    .scale(1000f)
//    .downfall(0.4f)
//    .precipitation(Biome.RainType.SNOW)
//    .temperature(0.8f)
//    .depth(0.9f)
//    .setEffects(new BiomeAmbience.Builder()
//        .withGrassColor(0xDA67C1) //0xDA67C1
//        .setFogColor(0xEEEEEE)
//        .setWaterColor(0xCF21B8)
//        .setWaterFogColor(0xCF78C5)
//        .withSkyColor(0xE83452)
//        .withFoliageColor(0xCA57C1)
//        .build()
//    )
//    .build()
//);
////this.setRegistryName(RegistryEvents.Location("test_biome"));
//
//BIOME_MAP.put("test_biome", TEST_BIOME); 
