package com.liamq12.examplemod.common.biomes;

import java.util.ArrayList;

import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.StructureFeatures;

public class CustomBiome {
	
	private String name = "test_biome";
	private ArrayList<StructureFeature> structures = new ArrayList<>();
	private ArrayList<EntityType> entities = new ArrayList<>();
	private Biome.Category category = Biome.Category.FOREST;
	private Biome.TemperatureModifier temperatureModifier = Biome.TemperatureModifier.NONE;
	private Biome.RainType rainType = Biome.RainType.NONE;
	private float height = 1f;
	private float depth = 1f;
	private float downfall = 1f;
	private float temperature = 0.8f;
	private int grassColor = 0x75CC1C;
	private int fogColor = 0x0FDC8F;
	private int waterColor = 0x12A8EE;
	private int waterFogColor = 0x468EAF;
	private int skyColor = 0x154EE5;
	private int foliageColor = 0x3C9B51;
			
	public CustomBiome(String biomeName) {
		name = biomeName;
		entities.add(EntityType.PIG);
		entities.add(EntityType.COW);
		entities.add(EntityType.SHEEP);
		entities.add(EntityType.CHICKEN);
		entities.add(EntityType.WOLF);
		
		structures.add(StructureFeatures.RUINED_PORTAL);
		structures.add(StructureFeatures.STRONGHOLD);
		structures.add(StructureFeatures.VILLAGE_PLAINS);
		structures.add(StructureFeatures.VILLAGE_DESERT);
		structures.add(StructureFeatures.VILLAGE_SNOWY);
	}
	
	public void setHeight(float biomeHeight) {
		height = biomeHeight;
	}
	
	public void setDepth(float biomeDepth) {
		depth = biomeDepth;
	}
	
	public void setDownfall(float biomeDownfall) {
		downfall = biomeDownfall;
	}
	
	public void setGrassColor(int biomeGrassColor) {
		grassColor = biomeGrassColor;
	}
	
	public void setFogColor(int biomeFogColor) {
		fogColor = biomeFogColor;
	}
	
	public void setWaterColor(int biomeWaterColor) {
		waterColor = biomeWaterColor;
	}
	
	public void setWaterFogColor(int biomeWaterFogColor) {
		waterFogColor = biomeWaterFogColor;
	}
	
	public void setSkyColor(int biomeSkyColor) {
		skyColor = biomeSkyColor;
	}
	
	public void setFoliageColor(int biomeFoliageColor) {
		foliageColor = biomeFoliageColor;
	}
	
	public void setCategory(Biome.Category biomeCategory) {
		category = biomeCategory;
	}
	
	public void setTemperatureModifier(Biome.TemperatureModifier biomeTemperature) {
		temperatureModifier = biomeTemperature;
	}
	
	public void setRainType(Biome.RainType biomeRainType) {
		rainType = biomeRainType;
	}
	
	public void setTemperature(float biomeTemperature) {
		temperature = biomeTemperature;
	}
	
	public void addStructure(StructureFeature structureFeature) {
		structures.remove(0);
		structures.add(structureFeature);
	}
	
	public void addEntity(EntityType entityType) {
		entities.remove(0);
		entities.add(entityType);
	}
	
	
	
	
	
	public String getName() {
		return name;
	}
	
	public ArrayList<StructureFeature> getStructures(){
		return structures;
	}
	
	public ArrayList<EntityType> getEntities(){
		return entities;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getDepth() {
		return depth;
	}
	
	public float getDownfall() {
		return downfall;
	}
	
	public int getGrassColor() {
		return grassColor;
	}
	
	public int getFogColor() {
		return fogColor;
	}
	
	public int getWaterColor() {
		return waterColor;
	}
	
	public int getWaterFogColor() {
		return waterFogColor;
	}
	
	public int getSkyColor() {
		return skyColor;
	}
	
	public int getFoliageColor() {
		return foliageColor;
	}
	
	public Biome.Category getCategory(){
		return category;
	}
	
	public Biome.TemperatureModifier getTemperatureModifier(){
		return temperatureModifier;
	}
	
	public Biome.RainType getRainType(){
		return rainType;
	}
	
	public float getTemperature() {
		return temperature;
	}
}
