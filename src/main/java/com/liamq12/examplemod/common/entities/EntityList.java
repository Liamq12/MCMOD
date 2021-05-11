package com.liamq12.examplemod.common.entities;

import java.util.ArrayList;

import com.liamq12.examplemod.common.blocks.SpecialOre;

import net.minecraft.entity.Entity;

public class EntityList {
	public static final String pig = "pig";
	public static final String cow = "cow";
	public static final String zombie = "zombie";
	public static final String skeleton = "skeleton";
	public static final String creeper = "creeper";
	public static final String enderman = "enderman";
	public static final String spider = "spider";
	public static final String enderdragon = "enderdragon";
	public static final String chicken = "chicken";

	public static ArrayList<String> permittedEntities = new ArrayList<String>() {
		{
			add(EntityList.pig);
			add(EntityList.cow);
			add(EntityList.zombie);
			add(EntityList.skeleton);
			add(EntityList.creeper);
			add(EntityList.enderman);
			add(EntityList.spider);
			add(EntityList.enderdragon);
			add(EntityList.chicken);
		}
	};

	public EntityList() {

	}

	public static Entity getEntity(Results r, String input) {
		switch (input) {
		case EntityList.pig:
			return r.pig();
		case EntityList.cow:
			return r.cow();
		case EntityList.zombie:
			return r.zombie();
		case EntityList.skeleton:
			return r.skeleton();
		case EntityList.creeper:
			return r.creeper();
		case EntityList.enderman:
			return r.enderman();
		case EntityList.spider:
			return r.spider();
		case EntityList.enderdragon:
			return r.enderdragon();
		case EntityList.chicken:
			return r.chicken();
		}
		return null;
	}
}
