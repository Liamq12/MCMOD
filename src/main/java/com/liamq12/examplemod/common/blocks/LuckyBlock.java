package com.liamq12.examplemod.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.liamq12.examplemod.common.entities.EntityList;
import com.liamq12.examplemod.common.entities.Results;
import com.liamq12.examplemod.common.potions.EffectHolder;
import com.liamq12.examplemod.core.init.ItemInit;
import com.liamq12.examplemod.core.util.EffectUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyBlock extends Block {
	public ArrayList<String> ENTITY_LIST = new ArrayList<String>();
	public ArrayList<String> CEFFECT_LIST = new ArrayList<String>();
	public ArrayList<EffectHolder> EFFECT_LIST = new ArrayList<EffectHolder>();
	public ArrayList<ArrayList> LISTOLISTS = new ArrayList<ArrayList>();
	public ArrayList<ArrayList> GOODLISTS = new ArrayList<ArrayList>();

	public Results r;
	
    public EffectHolder rightEffect;
    private String rightEID;
    private String rightEntity;

	public LuckyBlock(Properties properties) {
		super(properties);
		LISTOLISTS.add(ENTITY_LIST);
		LISTOLISTS.add(CEFFECT_LIST);
		LISTOLISTS.add(EFFECT_LIST);
		
//		ENTITY_LIST.add(com.liamq12.examplemod.core.util.Effect.spawnCow());
//		ENTITY_LIST.add(com.liamq12.examplemod.core.util.Effect.spawnPig());
//		
//		EFFECT_LIST.add(new EffectHolder(EffectUtil.getVanillaEffect(com.liamq12.examplemod.core.util.Effect.blindness()), 200, 5));
//		EFFECT_LIST.add(new EffectHolder(EffectUtil.getVanillaEffect(com.liamq12.examplemod.core.util.Effect.glowing()), 200, 5));
//		
//		CEFFECT_LIST.add(com.liamq12.examplemod.core.util.Effect.lightning());
//		CEFFECT_LIST.add(com.liamq12.examplemod.core.util.Effect.enderChest());
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		
	      rightEffect = null;
	      rightEID = null;
	      rightEntity = null;
		// TODO Auto-generated method stub
		super.onBlockHarvested(worldIn, pos, state, playerIn);
		
		Random rand = new Random();
		
		for(int i = 0; i < LISTOLISTS.size(); i++) {
			if(LISTOLISTS.get(i).size() > 0) {
				GOODLISTS.add(LISTOLISTS.get(i));
			}
		}
		
		int randNum = rand.nextInt(GOODLISTS.size());
		ArrayList activeList = GOODLISTS.get(randNum);
		int randFromList = rand.nextInt(activeList.size());
		
		if (activeList.get(0).getClass().getSimpleName().equals("EffectHolder")) {
			rightEffect = (EffectHolder) activeList.get(randFromList);
		}else {
			String[] parts = ((String) activeList.get(randFromList)).split("-");
			if(parts[0].equals("1")) {
				//wont run
				System.out.println("FIRST IF FAILED");
			}else if(parts[0].equals("0")){
				rightEID = parts[1];
				}else {
					rightEntity = parts[1];
					System.out.println("magic43 " + parts[1]);
				}
		}
		
		//EffectUtil.onBreakEventAction(worldIn, playerIn, rightEffect, targetEffect, rightEID, rightEntity, targetEID);
		
		if(rightEffect != null) {
			playerIn.addPotionEffect(new EffectInstance(rightEffect.getEffect(), rightEffect.getDuration(), rightEffect.getAmplifier()));
			}else if(rightEID != null) {
		      //System.out.println("magic92 " + targetEID);
		      if(rightEID != null) {
		    	  if(rightEID.equals("lightning")) {
		    		 EffectUtil.spawnLightning(playerIn);
		    	  }
		    	  if(rightEID.equals("ender_chest")) {
		    		  EffectUtil.openEnderChest(playerIn);
		    	  }
		    	  if(rightEID.equals("jump")) {
		    		  if(playerIn.isOnGround()) {
		    		  EffectUtil.jump(playerIn, 1, 0, 0);
		    		  }
		    	  }
		    	  if(rightEID.equals("yeet")) {
		    		  if(playerIn.isOnGround()) {
		    		  int left = rand.nextInt(7)-3;
		    		  int right = rand.nextInt(7)-3;
		    		  int jump = rand.nextInt(3)+4;
		    		  EffectUtil.jump(playerIn, jump, left, right);
		    		  }
		    	  }
		    	  if(rightEID.equals("double_jump")) {
		    		  EffectUtil.jump(playerIn, 1, 0, 0);
		    	  }
		    	  if(rightEID.equals("saddam")) {
		    		  EffectUtil.saddam(playerIn, worldIn);
		    	  }
		    	  if(rightEID.equals("hole")) {
		    		  EffectUtil.makeHole(16, worldIn, playerIn);
		    	  }
		    	  if(rightEID.equals("clear_inventory")) {
		    		  EffectUtil.clearInventory(playerIn);
		    	  }
		    	  if(rightEID.equals("beam")) {
		    		  EffectUtil.beam(playerIn, worldIn, 12, 500);
		    	  }
		      }
			}else {
		      if(rightEntity != null) {
		    	  EffectUtil.spawnEntityFromString(playerIn, rightEntity, worldIn);
		      }
			}
//		Results r = new Results();
//		r.setWorld(worldIn);
//		Random rand = new Random();
//		boolean run = true;
//		while (run == true) {
//			int path = rand.nextInt(3);
//			if (path == 0) {
//				System.out.println(MODDED_ITEMS + " this is items");
//				if (!(VANILLIA_ITEMS.size() <= 0)) {
//					// if (ITEMS.size() >= 1) {
//					int random;
//					if (VANILLIA_ITEMS.size() <= 1) {+
//						random = 0;
//					} else {
//						random = rand.nextInt(VANILLIA_ITEMS.size());
//					}
//
//					ItemEntity item = new ItemEntity(worldIn, playerIn.getPosX(), playerIn.getPosY(),
//							playerIn.getPosZ(), new ItemStack(VANILLIA_ITEMS.get(random)));
//					System.out.println("magic34good " + worldIn.addEntity(item));
//					run = false;
//				}
//
////		int randomNumber = rand.nextInt(ENTITY_LIST.size());
////		Entity entity = EntityList.getEntity(r, ENTITY_LIST.get(randomNumber));
////		if (entity != null) {
////		    entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
////		    worldIn.addEntity(entity);
////		}
//			} else if (path == 1) {
//				if (!(ENTITY_LIST.size() <= 0)) {
//					int randomNumber = rand.nextInt(ENTITY_LIST.size());
//					Entity entity = EntityList.getEntity(r, ENTITY_LIST.get(randomNumber));
//					if (entity != null) {
//						entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
//						worldIn.addEntity(entity);
//						run = false;
//					}
//				}
//			} else {
//				if (!(MODDED_ITEMS.size() <= 0)) {
//					int random = rand.nextInt(MODDED_ITEMS.size());
//					ItemEntity item = new ItemEntity(worldIn, playerIn.getPosX(), playerIn.getPosY(),
//							playerIn.getPosZ(), new ItemStack(ItemInit.ITEM_CLASS_MAP.get(MODDED_ITEMS.get(random))));
//					System.out.println("magic34good " + worldIn.addEntity(item));
//					run = false;
//				}
//			}
//		}
		      rightEffect = null;
		      rightEID = null;
		      rightEntity = null;
	}
	
	public void addVanillaEffect(String id, int duration, int amplifier) {
		String[] updated = id.split("-");
		System.out.println("Coolio " + updated[1]);
		EFFECT_LIST.add(new EffectHolder(EffectUtil.getVanillaEffect(updated[1]), duration, amplifier));
		}
	
	public void addCustomEffect(String id) {
		CEFFECT_LIST.add(id);
	}
	
	public void addMobSpawn(String id) {
		ENTITY_LIST.add(id);
	}
}