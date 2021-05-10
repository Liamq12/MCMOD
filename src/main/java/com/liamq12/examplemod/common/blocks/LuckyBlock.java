package com.liamq12.examplemod.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.liamq12.examplemod.common.entities.EntityList;
import com.liamq12.examplemod.common.entities.Results;
import com.liamq12.examplemod.core.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyBlock extends Block {
	public ArrayList<String> ENTITY_LIST = new ArrayList<String>();
	public ArrayList<Item> VANILLIA_ITEMS = new ArrayList<Item>();
	public ArrayList<String> MODDED_ITEMS = new ArrayList<String>();

	public Results r;

	public LuckyBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		// TODO Auto-generated method stub
		super.onBlockHarvested(worldIn, pos, state, playerIn);
		Results r = new Results();
		r.setWorld(worldIn);
		Random rand = new Random();
		boolean run = true;
		while (run == true) {
			int path = rand.nextInt(3);
			if (path == 0) {
				System.out.println(MODDED_ITEMS + " this is items");
				if (!(VANILLIA_ITEMS.size() <= 0)) {
					// if (ITEMS.size() >= 1) {
					int random;
					if (VANILLIA_ITEMS.size() <= 1) {
						random = 0;
					} else {
						random = rand.nextInt(VANILLIA_ITEMS.size());
					}

					ItemEntity item = new ItemEntity(worldIn, playerIn.getPosX(), playerIn.getPosY(),
							playerIn.getPosZ(), new ItemStack(VANILLIA_ITEMS.get(random)));
					System.out.println("magic34good " + worldIn.addEntity(item));
					run = false;
				}

//		int randomNumber = rand.nextInt(ENTITY_LIST.size());
//		Entity entity = EntityList.getEntity(r, ENTITY_LIST.get(randomNumber));
//		if (entity != null) {
//		    entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
//		    worldIn.addEntity(entity);
//		}
			} else if (path == 1) {
				if (!(ENTITY_LIST.size() <= 0)) {
					int randomNumber = rand.nextInt(ENTITY_LIST.size());
					Entity entity = EntityList.getEntity(r, ENTITY_LIST.get(randomNumber));
					if (entity != null) {
						entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
						worldIn.addEntity(entity);
						run = false;
					}
				}
			} else {
				if (!(MODDED_ITEMS.size() <= 0)) {
					int random = rand.nextInt(MODDED_ITEMS.size());
					ItemEntity item = new ItemEntity(worldIn, playerIn.getPosX(), playerIn.getPosY(),
							playerIn.getPosZ(), new ItemStack(ItemInit.ITEM_CLASS_MAP.get(MODDED_ITEMS.get(random))));
					System.out.println("magic34good " + worldIn.addEntity(item));
					run = false;
				}
			}
		}
	}

	public void setMobOutcome(String elist) {
		ENTITY_LIST.add(elist);
	}

	public void setVanilliaItemOutcome(Item item) {
		VANILLIA_ITEMS.add(item);
	}

	public void setModdedItemOutcome(String elist) {
		MODDED_ITEMS.add(elist);
	}
}