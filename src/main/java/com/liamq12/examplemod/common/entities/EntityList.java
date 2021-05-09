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
    public static final String spider = "pig";
    public static final String enderdragon = "pig";
    public static final String sheep = "pig";
    
    public static ArrayList<String> permittedEntities = new ArrayList<String>() {
	{
	add(EntityList.pig);
	add(EntityList.cow);
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
	}
	return null;
    }
}
