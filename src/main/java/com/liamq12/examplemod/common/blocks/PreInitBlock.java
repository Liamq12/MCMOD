package com.liamq12.examplemod.common.blocks;

import net.minecraftforge.common.ToolType;

public class PreInitBlock {
    private String name;
    private float hardness;
    private float resistance;
    private ToolType harvestTool;
    private int harvestLevel;
    public PreInitBlock(String name, float hardness, float resistance, ToolType harvestTool, int harvestLevel) {
	this.name = name;
	this.hardness = hardness;
	this.resistance = resistance;
	this.harvestTool = harvestTool;
	this.harvestLevel = harvestLevel;
    }
    public String getName() {
	return name;
    }
    public float getHardness() {
	return hardness;
    }
    public float getResistance() {
	return resistance;
    }
    public ToolType getHarvestTool() {
	return harvestTool;
    }
    public int getHarvestLevel() {
	return harvestLevel;
    }
}
