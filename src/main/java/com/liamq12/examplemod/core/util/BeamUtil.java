package com.liamq12.examplemod.core.util;

//MB Liam Homburger

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class BeamUtil {
	private int iteration;
	private int beamLength;
	private double adjuster;
	private double posX;
	private double posY;
	private double posZ;
	private String direction;
	private PlayerEntity player;
	private World world;
	
	public BeamUtil(int iterationIn, int beamLengthIn, double adjusterIn, double posX, double posY, double posZ, String directionIn, PlayerEntity playerIn, World worldIn) {
		this.iteration = iterationIn;
		this.beamLength = beamLengthIn;
		this.adjuster = adjusterIn;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.direction = directionIn;
		this.player = playerIn;
		this.world = worldIn;
	}
	
	public int getIteration() {
		return iteration;
	}
	
	public int getBeamLength() {
		return beamLength;
	}
	
	public double getAdjuster() {
		return adjuster;
	}
	
	public double getPlayerX() {
		return posX;
	}
	
	public double getPlayerY() {
		return posY;
	}
	
	public double getPlayerZ() {
		return posZ;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public PlayerEntity getPlayer() {
		return player;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void iterateIterator() {
		iteration += 1;
	}
	
	public void diterateIterator() {
		iteration -= 1;
	}
	
}
