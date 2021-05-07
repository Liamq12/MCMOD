package com.liamq12.examplemod.common.blocks;

public class SpecialOre {
	private String name;
	private int veinSize;
	private int minHeight;
	private int maxHeight;
	private int amount;
	public SpecialOre(String name, int veinSize, int minHeight, int maxHeight, int amount) {
		this.name = name;
		this.veinSize = veinSize;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public int getVeinSize() {
		return veinSize;
	}
	public int getMinHeight() {
		return minHeight;
	}
	public int getMaxHeight() {
		return maxHeight;
	}
	public int getAmount() {
		return amount;
	}
}
