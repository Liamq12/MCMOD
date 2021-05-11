package com.liamq12.examplemod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.world.World;

public class Results {
	public Entity entity;
	public World world;
	public Results(){
		
	}
	
	public Entity getMob() {
		return entity;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public ZombieEntity zombie() {
		return new ZombieEntity(EntityType.ZOMBIE, world);
	}
	public CreeperEntity creeper() {
		return new CreeperEntity(EntityType.CREEPER, world);
	}
	public SkeletonEntity skeleton() {
		return new SkeletonEntity(EntityType.SKELETON, world);
	}
	public EnderDragonEntity enderdragon() {
		return new EnderDragonEntity(EntityType.ENDER_DRAGON, world);
	}
	public SpiderEntity spider() {
		return new SpiderEntity(EntityType.SPIDER, world);
	}
	public EndermanEntity enderman() {
		return new EndermanEntity(EntityType.ENDERMAN, world);
	}
	public ChickenEntity chicken() {
		return new ChickenEntity(EntityType.CHICKEN, world);
	}
	public CowEntity cow() {
		return new CowEntity(EntityType.COW, world);
	}
	public PigEntity pig() {
		return new PigEntity(EntityType.PIG, world);
	}
}
