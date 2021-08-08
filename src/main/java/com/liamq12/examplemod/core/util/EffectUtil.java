package com.liamq12.examplemod.core.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.SwingUtilities;
//import java.util.Timer;
import javax.swing.Timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.liamq12.examplemod.common.biomes.CustomBiome;
import com.liamq12.examplemod.common.potions.EffectHolder;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.GiantEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
//import net.minecraft.util.Timer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class EffectUtil {

	public static void spawnLightning(LivingEntity target) {
		  LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, target.getEntityWorld());
		  entity.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
		  target.getEntityWorld().addEntity(entity);
	}
	
	public static void openEnderChest(PlayerEntity playerIn) {
		 EnderChestInventory enderchestinventory = playerIn.getInventoryEnderChest();
         playerIn.openContainer(new SimpleNamedContainerProvider((id, inventory, playerInn) -> {
             return ChestContainer.createGeneric9X3(id, inventory, enderchestinventory);
          }, new TranslationTextComponent("container.enderchest")));
	}
	
	public static void jump(LivingEntity playerIn, int jump, int right, int left) {
		playerIn.addVelocity(right, jump, left);
	}
	
	public static void spawnEntityFromString(Entity eIn, String eSpawn, World worldIn) {
   	 Entity e = EffectUtil.getEntityFromString(worldIn, eSpawn);
   	 e.setPosition(eIn.getPosX(), eIn.getPosY(), eIn.getPosZ());
   	 worldIn.addEntity(e);
	}
	
	public static void playerShootArrow(PlayerEntity playerIn, World worldIn, Hand handIn) {
		  ItemStack stack = playerIn.getHeldItem(handIn);
		  if(!worldIn.isRemote) {
			  ArrowEntity arrow = new ArrowEntity(worldIn, playerIn);
			  
			  arrow.shoot(playerIn.getLookVec().x, playerIn.getLookVec().y,playerIn.getLookVec().z, 1.5f, 1f);
			  worldIn.addEntity(arrow);
			  
			int index = playerIn.inventory.getSlotFor(new ItemStack(Items.ARROW, 1));
			  playerIn.inventory.decrStackSize(index, 1);
		  }
	}
	
	public static void playerShootFireball(PlayerEntity playerIn, World worldIn, Hand handIn) {
		  ItemStack stack = playerIn.getHeldItem(handIn);
		  if(!worldIn.isRemote) {
			  FireballEntity arrow = new FireballEntity(worldIn, playerIn, playerIn.getLookVec().x, playerIn.getLookVec().y, playerIn.getLookVec().z);
			  
			  arrow.shoot(playerIn.getLookVec().x, playerIn.getLookVec().y,playerIn.getLookVec().z, 1.5f, 1f);
			  worldIn.addEntity(arrow);
		  }
	}
	
	@SuppressWarnings("static-access")
	public static void makeHole(int radius, World worldIn, LivingEntity playerIn) {
		for(int i = (int) (playerIn.getPosX() - (radius)); i < (int) (playerIn.getPosX() + (radius)); i++) {
			for(int j =  (int) (playerIn.getPosY() - (radius)); j < (int) (playerIn.getPosY() + (radius)); j++) {
				for(int k =  (int) (playerIn.getPosZ() - (radius)); k < (int) (playerIn.getPosZ() + (radius)); k++) {
					int distance = (int) Math.round(Math.sqrt(Math.pow((i-((int)playerIn.getPosX())), 2) + Math.pow((j-((int)playerIn.getPosY())), 2) + Math.pow((k-((int)playerIn.getPosZ())), 2)));
					if(distance <= Math.sqrt(radius)+1) {
						if(!worldIn.isRemote) {
							worldIn.getBlockState(new BlockPos(i, j, k)).getBlock().spawnDrops(worldIn.getBlockState(new BlockPos(i, j, k)).getBlock().getDefaultState(), worldIn, new BlockPos(i, j, k), worldIn.getTileEntity(new BlockPos(i, j, k)));
						}
						worldIn.setBlockState(new BlockPos(i, j, k), Blocks.AIR.getDefaultState());
					}
				}
			}
		}
	}
	
	public static void clearInventory(PlayerEntity playerIn) {
		playerIn.inventory.clear();
	}
	
	public static HashMap<String, Timer> TIMERS = new HashMap<String, Timer>();
	static Timer initTimer;
	static ScheduledExecutorService executor;
	static int xIn;
	
	private static void setAir(BeamUtil bu) {
		if(bu.getDirection().equals("+/x")) {
			  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
			  System.out.println("Beam Air PRE" + bu.getIteration());
			  bu.iterateIterator();
			  System.out.println("Beam Air " + bu.getIteration());
			  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
			  if(bu.getIteration() > bu.getPlayerX() + bu.getBeamLength()) {
				  System.out.println("DONE_____________________________________");
				  TIMERS.get("+/x").stop();
			  }	
		}
		System.out.println("Loop 1");
		if(bu.getDirection().equals("+/y")) {
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
			  System.out.println("Beam Air PRE" + bu.getIteration());
			  bu.iterateIterator();
			  System.out.println("Beam Air " + bu.getIteration());
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
			  if(bu.getIteration() > bu.getPlayerY() + bu.getBeamLength()) {
				  System.out.println("DONE_____________________________________");
				  TIMERS.get("+/y").stop();
			  }	
		}
		System.out.println("Loop 2");
		if(bu.getDirection().equals("+/z")) {
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.AIR.getDefaultState());
			  System.out.println("Beam Air PRE" + bu.getIteration());
			  bu.iterateIterator();
			  System.out.println("Beam Air " + bu.getIteration());
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.RED_WOOL.getDefaultState());
			  if(bu.getIteration() > bu.getPlayerZ() + bu.getBeamLength()) {
				  System.out.println("DONE_____________________________________");
				  TIMERS.get("+/z").stop();
			  }	
		}
		System.out.println("Loop 3");
		if(bu.getDirection().equals("-/x")) {
			  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
			  System.out.println("Beam Air PRE" + bu.getIteration());
			  bu.diterateIterator();
			  System.out.println("Beam Air " + bu.getIteration());
			  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
			  if(bu.getIteration() < bu.getPlayerX() - bu.getBeamLength()) {
				  System.out.println("DONE_____________________________________");
				  TIMERS.get("-/x").stop();
			  }	
		}
		System.out.println("Loop 4");
		if(bu.getDirection().equals("-/y")) {
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
			  System.out.println("Beam Air PRE" + bu.getIteration());
			  bu.diterateIterator();
			  System.out.println("Beam Air " + bu.getIteration());
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
			  System.out.println("LEFT: " + bu.getIteration() + ". RIGHT: " + (bu.getPlayerY() - bu.getBeamLength()) + ". PLAYERY: " + bu.getPlayerY());
			  if(bu.getIteration() < bu.getPlayerY() - bu.getBeamLength()) {
				  System.out.println("DONE_____________________________________");
				  TIMERS.get("-/y").stop();
			  }	
		}
		System.out.println("Loop 5");
		if(bu.getDirection().equals("-/z")) {
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.AIR.getDefaultState());
			  System.out.println("Beam Air PRE" + bu.getIteration());
			  bu.diterateIterator();
			  System.out.println("Beam Air " + bu.getIteration());
			  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.RED_WOOL.getDefaultState());
			  if(bu.getIteration() < bu.getPlayerZ() - bu.getBeamLength()) {
				  System.out.println("DONE_____________________________________");
				  TIMERS.get("-/z").stop();
			  }
		 }
	}
	
	public static void beam(PlayerEntity playerIn, World worldIn, int beamLength, int delay) {
		double x = playerIn.getLookVec().getX();
		double y = playerIn.getLookVec().getY();
		double z = playerIn.getLookVec().getZ();
		
		HashMap<Double, String> map = new HashMap<Double, String>();
		HashMap<String, BeamUtil> beams = new HashMap<String, BeamUtil>();
		
		ArrayList<Double> list = new ArrayList<>();
		
		list.add(x);
		list.add(y);
		list.add(z);
		
		map.put(x, "x");
		map.put(y, "y");
		map.put(z, "z");
		
		Double max = Collections.max(list);
		Double min = Collections.min(list);
		
		String direction;
		if(TIMERS.isEmpty()) {
		if(max > Math.abs(min)) {
			if(map.get(max).equals("x")) {
				direction = "+/x";
				xIn = (int) playerIn.getPosX()+1;
				
				BeamUtil bu = new BeamUtil(xIn, beamLength, playerIn.getPosX(), playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), direction, playerIn, worldIn);
				beams.put(direction, bu);
				ActionListener listenerx = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						  BeamUtil bu = beams.get("+/x");
						  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
						  bu.iterateIterator();
						  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
						  if(bu.getIteration() > bu.getPlayerX() + bu.getBeamLength()) {
							  TIMERS.get("+/x").stop();
							  TIMERS.remove("+/x");
						  }	
					}
				};
				  TIMERS.put(direction, new Timer(100, listenerx));
				  TIMERS.get(direction).start();
//					  executor = Executors.newSingleThreadScheduledExecutor();
//					  Runnable setBeam = () -> SwingUtilities.invokeLater(() -> setBeam(worldIn, playerIn, xIn, beamLength));
//					  executor.schedule(setBeam, 500, TimeUnit.MILLISECONDS);
			}
			if(map.get(max).equals("y")) {
				direction = "+/y";
				xIn = (int) playerIn.getPosY()+1;
				BeamUtil bu = new BeamUtil(xIn, beamLength, playerIn.getPosY(), playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), direction, playerIn, worldIn);
				beams.put(direction, bu);
				ActionListener listenerx = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						  BeamUtil bu = beams.get("+/y");
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
						  bu.iterateIterator();
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
						  if(bu.getIteration() > bu.getPlayerY() + bu.getBeamLength()) {
							  TIMERS.get("+/y").stop();
							  TIMERS.remove("+/y");
						  }	
					}
				};
				  TIMERS.put(direction, new Timer(100, listenerx));
				  TIMERS.get(direction).start();
//				  executor = Executors.newSingleThreadScheduledExecutor();
//				  Runnable setBeam = () -> SwingUtilities.invokeLater(() -> setBeam(worldIn, playerIn, xIn, beamLength));
//				  executor.schedule(setBeam, 500, TimeUnit.MILLISECONDS);
			}
			if(map.get(max).equals("z")) {
				direction = "+/z";
				xIn = (int) playerIn.getPosZ()+1;
				BeamUtil bu = new BeamUtil(xIn, beamLength, playerIn.getPosZ(), playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), direction, playerIn, worldIn);
				beams.put(direction, bu);
				ActionListener listenerx = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						  BeamUtil bu = beams.get("+/z");
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.AIR.getDefaultState());
						  bu.iterateIterator();
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.RED_WOOL.getDefaultState());
						  if(bu.getIteration() > bu.getPlayerZ() + bu.getBeamLength()) {
							  TIMERS.get("+/z").stop();
							  TIMERS.remove("+/z");
						  }	
					}
				};
				  TIMERS.put(direction, new Timer(100, listenerx));
				  TIMERS.get(direction).start();
//				  executor = Executors.newSingleThreadScheduledExecutor();
//				  Runnable setBeam = () -> SwingUtilities.invokeLater(() -> setBeam(worldIn, playerIn, xIn, beamLength));
//				  executor.schedule(setBeam, 500, TimeUnit.MILLISECONDS);
			}
		}else {
			if(map.get(min).equals("x")) {
				direction = "-/x";
				xIn = (int) playerIn.getPosX()-1;
				BeamUtil bu = new BeamUtil(xIn, beamLength, playerIn.getPosX(), playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), direction, playerIn, worldIn);
				beams.put("-/x", bu);
				ActionListener listenerx = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						  BeamUtil bu = beams.get("-/x");
						  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
						  bu.diterateIterator();
						  bu.getWorld().setBlockState(new BlockPos(bu.getIteration(), bu.getPlayerY(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
						  if(bu.getIteration() < bu.getPlayerX() - bu.getBeamLength()) {
							  TIMERS.get("-/x").stop();
							  TIMERS.remove("-/x");
						  }	
					}
				};
				  TIMERS.put(direction, new Timer(100, listenerx));
				  TIMERS.get(direction).start();
				  //System.out.println("GOOOOOOOOOOOOOOOOO________________4_____________________");
//				  executor = Executors.newSingleThreadScheduledExecutor();
//				  Runnable setBeam = () -> SwingUtilities.invokeLater(() -> setBeam(worldIn, playerIn, xIn, beamLength));
//				  executor.schedule(setBeam, 500, TimeUnit.MILLISECONDS);
			}
			if(map.get(min).equals("y")) {
				direction = "-/y";
				xIn = (int) playerIn.getPosY()-1;
				BeamUtil bu = new BeamUtil(xIn, beamLength, playerIn.getPosY(), playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), direction, playerIn, worldIn);
				beams.put("-/y", bu);
				ActionListener listenery = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						  BeamUtil bu = beams.get("-/y");
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.AIR.getDefaultState());
						  bu.diterateIterator();
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getIteration(), bu.getPlayerZ()), Blocks.RED_WOOL.getDefaultState());
						  if(bu.getIteration() < bu.getPlayerY() - bu.getBeamLength()) {
							  TIMERS.get("-/y").stop();
							  TIMERS.remove("-/y");
						  }	
					}
				};
				  TIMERS.put(direction, new Timer(100, listenery));
				  TIMERS.get(direction).start();
				  //System.out.println("GOOOOOOOOOOOOOOOOO_______________5______________________");
//				  executor = Executors.newSingleThreadScheduledExecutor();
//				  Runnable setBeam = () -> SwingUtilities.invokeLater(() -> setBeam(worldIn, playerIn, xIn, beamLength));
//				  executor.schedule(setBeam, 500, TimeUnit.MILLISECONDS);
			}
			if(map.get(min).equals("z")) {
				direction = "-/z";
				xIn = (int) playerIn.getPosZ()-1;
				BeamUtil bu = new BeamUtil(xIn, beamLength, playerIn.getPosZ(), playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), direction, playerIn, worldIn);
				beams.put("-/z", bu);
				ActionListener listenery = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						  BeamUtil bu = beams.get("-/z");
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.AIR.getDefaultState());
						  bu.diterateIterator();
						  bu.getWorld().setBlockState(new BlockPos(bu.getPlayerX(), bu.getPlayerY(), bu.getIteration()), Blocks.RED_WOOL.getDefaultState());
						  if(bu.getIteration() < bu.getPlayerZ() - bu.getBeamLength()) {
							  TIMERS.get("-/z").stop();
							  TIMERS.remove("-/z");
						  }	
					}
				};
				  TIMERS.put(direction, new Timer(100, listenery));
				  TIMERS.get(direction).start();
//				  executor = Executors.newSingleThreadScheduledExecutor();
//				  Runnable setBeam = () -> SwingUtilities.invokeLater(() -> setBeam(worldIn, playerIn, xIn, beamLength));
//				  executor.schedule(setBeam, 500, TimeUnit.MILLISECONDS);
			}
		}
		}
	}
	
	public static void saddam(LivingEntity playerIn, World worldIn) {
		
		  Random rand = new Random();
		  int x = rand.nextInt(100);
		  int y = rand.nextInt(100);
		  playerIn.setLocationAndAngles(x, 12, y, 0, 0);
		  worldIn.setBlockState(new BlockPos(x, 13, y), Blocks.AIR.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 12, y), Blocks.AIR.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 11, y), Blocks.AIR.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x-1, 11, y), Blocks.RED_WOOL.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 11, y-1), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x+1, 11, y), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 11, y+1), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x-1, 12, y), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 12, y-1), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x+1, 12, y), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 12, y+1), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x-1, 13, y), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 13, y-1), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x+1, 13, y), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 13, y+1), Blocks.STONE.getDefaultState());
		  worldIn.setBlockState(new BlockPos(x, 10, y), Blocks.STONE.getDefaultState());
	}
	
	public static void onRightEventAction(World worldIn, PlayerEntity playerIn, Hand handIn, EffectHolder rightEffect, EffectHolder targetEffect, String rightEID, String rightEntity, String targetEID) {
		if(rightEffect != null) {
			playerIn.addPotionEffect(new EffectInstance(rightEffect.getEffect(), rightEffect.getDuration(), rightEffect.getAmplifier()));
			}
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
		    		  Random rand = new Random();
		    		  int left = rand.nextInt(7)-3;
		    		  int right = rand.nextInt(7)-3;
		    		  int jump = rand.nextInt(3)+4;
		    		  EffectUtil.jump(playerIn, jump, left, right);
		    		  }
		    	  }
		    	  if(rightEID.equals("double_jump")) {
		    		  EffectUtil.jump(playerIn, 1, 0, 0);
		    	  }
		    	  if(rightEID.equals("shoot_arrow")) {
		    		  
		    		  if(playerIn.inventory.hasItemStack(new ItemStack(Items.ARROW, 1))) {
		    			  EffectUtil.playerShootArrow(playerIn, worldIn, handIn);
		    		  }
		    	  }
		    	  if(rightEID.equals("shoot_fireball")) {
		    		  EffectUtil.playerShootFireball(playerIn, worldIn, handIn);
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
		      if(rightEntity != null) {
		    	  EffectUtil.spawnEntityFromString(playerIn, rightEntity, worldIn);
		      }
	}
	
	public static void onHitEventAction(World worldIn, LivingEntity playerIn, EffectHolder rightEffect, EffectHolder targetEffect, String rightEID, String rightEntity, String targetEID) {
		if(rightEffect != null) {
			playerIn.addPotionEffect(new EffectInstance(rightEffect.getEffect(), rightEffect.getDuration(), rightEffect.getAmplifier()));
			}
		      //System.out.println("magic92 " + targetEID);
		      if(rightEID != null) {
		    	  if(rightEID.equals("lightning")) {
		    		 EffectUtil.spawnLightning(playerIn);
		    	  }
		    	  if(rightEID.equals("jump")) {
		    		  if(playerIn.isOnGround()) {
		    		  EffectUtil.jump(playerIn, 1, 0, 0);
		    		  }
		    	  }
		    	  if(rightEID.equals("yeet")) {
		    		  if(playerIn.isOnGround()) {
		    		  Random rand = new Random();
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
		    	  if(rightEID.equals("clear_inventory") && playerIn.getClass().getSimpleName().equals("Playerentity")) {
		    		  EffectUtil.clearInventory((PlayerEntity) playerIn);
		    	  }
			}
		      if(rightEntity != null) {
		    	  EffectUtil.spawnEntityFromString(playerIn, rightEntity, worldIn);
		      }
	}
	
	public static net.minecraft.potion.Effect getVanillaEffect(String id) {
		if(id.equals("speed")) {
			return Effects.SPEED;
		}else if(id.equals("slowness")) {
			return Effects.SLOWNESS;
		}else if(id.equals("haste")) {
			return Effects.HASTE;
		}else if(id.equals("mining_fatigue")) {
			return Effects.MINING_FATIGUE;
		}else if(id.equals("strength")) {
			return Effects.STRENGTH;
		}else if(id.equals("instant_health")) {
			return Effects.INSTANT_HEALTH;
		}else if(id.equals("instant_damage")) {
			return Effects.INSTANT_DAMAGE;
		}else if(id.equals("jump_boost")) {
			return Effects.JUMP_BOOST;
		}else if(id.equals("nausea")) {
			return Effects.NAUSEA;
		}else if(id.equals("regeneration")) {
			return Effects.REGENERATION;
		}else if(id.equals("resistance")) {
			return Effects.RESISTANCE;
		}else if(id.equals("fire_resistance")) {
			return Effects.FIRE_RESISTANCE;
		}else if(id.equals("water_breathing")) {
			return Effects.WATER_BREATHING;
		}else if(id.equals("invisibillity")) {
			return Effects.INVISIBILITY;
		}else if(id.equals("blindness")) {
			return Effects.BLINDNESS;
		}else if(id.equals("night_vision")) {
			return Effects.NIGHT_VISION;
		}else if(id.equals("hunger")) {
			return Effects.HUNGER;
		}else if(id.equals("weakness")) {
			return Effects.WEAKNESS;
		}else if(id.equals("poison")) {
			return Effects.POISON;
		}else if(id.equals("wither")) {
			return Effects.WITHER;
		}else if(id.equals("health_boost")) {
			return Effects.HEALTH_BOOST;
		}else if(id.equals("absorption")) {
			return Effects.ABSORPTION;
		}else if(id.equals("saturation")) {
			return Effects.SATURATION;
		}else if(id.equals("glowing")) {
			return Effects.GLOWING;
		}else if(id.equals("levitation")) {
			return Effects.LEVITATION;
		}else if(id.equals("luck")) {
			return Effects.LUCK;
		}else if(id.equals("unluck")) {
			return Effects.UNLUCK;
		}else if(id.equals("slow_falling")) {
			return Effects.SLOW_FALLING;
		}else if(id.equals("conduit_power")) {
			return Effects.CONDUIT_POWER;
		}else if(id.equals("dolphins_grace")) {
			return Effects.DOLPHINS_GRACE;
		}else if(id.equals("bad_omen")) {
			return Effects.BAD_OMEN;
		}else if(id.equals("hero_of_the_village")) {
			return Effects.HERO_OF_THE_VILLAGE;
		}else {
			System.out.println("FAILEDED HERE");
			return Effects.SPEED;
		}
	}
	
	public static Entity getEntityFromString(World world, String entity) {
		String entityName = entity;

		LivingEntity entityliving;
		if (entityName.equals("pig")) {
			entityliving = new PigEntity(EntityType.PIG, world);
		} else if (entityName.equals("cow")) {
			entityliving = new CowEntity(EntityType.COW, world);
		} else if (entityName.equals("chicken")) {
			entityliving = new ChickenEntity(EntityType.CHICKEN, world);
		} else if (entityName.equals("sheep")) {
			entityliving = new SheepEntity(EntityType.SHEEP, world);
		} else if (entityName.equals("zombie")) {
			entityliving = new ZombieEntity(EntityType.ZOMBIE, world);
		} else if (entityName.equals("skeleton")) {
			entityliving = new SkeletonEntity(EntityType.SKELETON, world);
		} else if (entityName.equals("creeper")) {
			entityliving = new CreeperEntity(EntityType.CREEPER, world);
		} else if (entityName.equals("villager")) {
			entityliving = new VillagerEntity(EntityType.VILLAGER, world);
		} else if (entityName.equals("wolf")) {
			entityliving = new WolfEntity(EntityType.WOLF, world);
		} else if (entityName.equals("ocelot")) {
			entityliving = new OcelotEntity(EntityType.OCELOT, world);
		} else if (entityName.equals("witch")) {
			entityliving = new WitchEntity(EntityType.WITCH, world);
		} else if (entityName.equals("horse")) {
			entityliving = new HorseEntity(EntityType.HORSE, world);
		} else if (entityName.equals("squid")) {
			entityliving = new SquidEntity(EntityType.SQUID, world);
		} else if (entityName.equals("rabbit")) {
			entityliving = new RabbitEntity(EntityType.RABBIT, world);
		} else if (entityName.equals("parrot")) {
			entityliving = new ParrotEntity(EntityType.PARROT, world);
		} else if (entityName.equals("mule")) {
			entityliving = new MuleEntity(EntityType.MULE, world);
		} else if (entityName.equals("mooshroom")) {
			entityliving = new MooshroomEntity(EntityType.MOOSHROOM, world);
		} else if (entityName.equals("llama")) {
			entityliving = new LlamaEntity(EntityType.LLAMA, world);
		} else if (entityName.equals("donkey")) {
			entityliving = new DonkeyEntity(EntityType.DONKEY, world);
		} else if (entityName.equals("bat")) {
			entityliving = new BatEntity(EntityType.BAT, world);
		} else if (entityName.equals("wither_skeleton")) {
			entityliving = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world);
		} else if (entityName.equals("wither")) {
			entityliving = new WitherEntity(EntityType.WITHER, world);
		} else if (entityName.equals("vindicator")) {
			entityliving = new VindicatorEntity(EntityType.VINDICATOR, world);
		} else if (entityName.equals("vex")) {
			entityliving = new VexEntity(EntityType.VEX, world);
		} else if (entityName.equals("stray")) {
			entityliving = new StrayEntity(EntityType.STRAY, world);
		} else if (entityName.equals("spider")) {
			entityliving = new SpiderEntity(EntityType.SPIDER, world);
		} else if (entityName.equals("snowman")) {
			entityliving = new SnowGolemEntity(EntityType.SNOW_GOLEM, world);
		} else if (entityName.equals("slime")) {
			entityliving = new SlimeEntity(EntityType.SLIME, world);
		} else if (entityName.equals("silverfish")) {
			entityliving = new SilverfishEntity(EntityType.SILVERFISH, world);
		} else if (entityName.equals("shulker")) {
			entityliving = new ShulkerEntity(EntityType.SHULKER, world);
		} else if (entityName.equals("polarbear")) {
			entityliving = new PolarBearEntity(EntityType.POLAR_BEAR, world);
		} else if (entityName.equals("piglin")) {
			entityliving = new PiglinEntity(EntityType.PIGLIN, world);
		} else if (entityName.equals("magmacube")) {
			entityliving = new MagmaCubeEntity(EntityType.MAGMA_CUBE, world);
		} else if (entityName.equals("golem")) {
			entityliving = new IronGolemEntity(EntityType.IRON_GOLEM, world);
		} else if (entityName.equals("husk")) {
			entityliving = new HuskEntity(EntityType.HUSK, world);
		} else if (entityName.equals("guardian")) {
			entityliving = new GuardianEntity(EntityType.GUARDIAN, world);
		} else if (entityName.equals("giant_zombie")) {
			entityliving = new GiantEntity(EntityType.GIANT, world);
		} else if (entityName.equals("ghast")) {
			entityliving = new GhastEntity(EntityType.GHAST, world);
		} else if (entityName.equals("endermite")) {
			entityliving = new EndermiteEntity(EntityType.ENDERMITE, world);
		} else if (entityName.equals("enderman")) {
			entityliving = new EndermanEntity(EntityType.ENDERMAN, world);
		} else if (entityName.equals("cave_spider")) {
			entityliving = new CaveSpiderEntity(EntityType.CAVE_SPIDER, world);
		} else if (entityName.equals("blaze")) {
			entityliving = new BlazeEntity(EntityType.BLAZE, world);
		} else {
			entityliving = new PigEntity(EntityType.PIG, world);
		}
		return entityliving;
	}
}
