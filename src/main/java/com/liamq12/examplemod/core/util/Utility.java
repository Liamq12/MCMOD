package com.liamq12.examplemod.core.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;

public class Utility {

	
	public static void spawnLightning(LivingEntity target) {
		  LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, target.getEntityWorld());
		  entity.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
		  target.getEntityWorld().addEntity(entity);
	}
}
