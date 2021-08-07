package com.liamq12.examplemod.common.blocks;
import java.util.Random;

import com.liamq12.examplemod.common.potions.EffectHolder;
import com.liamq12.examplemod.core.util.EffectUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SpecialBlock extends Block {

	private String name;
	private int blockID;
    public EffectHolder rightEffect;
    private String rightEID;
    private String rightEntity;
    
    public EffectHolder colideEffect;
    private String colideEID;
    private String colideEntity;
	
	public SpecialBlock(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(colideEffect != null && entityIn instanceof PlayerEntity) {
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(colideEffect.getEffect(), colideEffect.getDuration(), colideEffect.getAmplifier()));
			}
		      //System.out.println("magic92 " + targetEID);
		      if(colideEID != null) {
		    	  if(colideEID.equals("lightning")) {
		    		 EffectUtil.spawnLightning((LivingEntity) entityIn);
		    	  }
		    	  if(colideEID.equals("ender_chest") && entityIn instanceof PlayerEntity) {
		    		  EffectUtil.openEnderChest((PlayerEntity) entityIn);
		    	  }
		    	  if(colideEID.equals("jump")) {
		    		  if(((PlayerEntity) entityIn).isOnGround()) {
		    		  EffectUtil.jump((LivingEntity) entityIn, 1, 0, 0);
		    		  }
		    	  }
		    	  if(colideEID.equals("yeet")) {
		    		  if(((LivingEntity) entityIn).isOnGround()) {
		    		  Random rand = new Random();
		    		  int left = rand.nextInt(7)-3;
		    		  int right = rand.nextInt(7)-3;
		    		  int jump = rand.nextInt(3)+4;
		    		  EffectUtil.jump((LivingEntity) entityIn, jump, left, right);
		    		  }
		    	  }
		    	  if(colideEID.equals("double_jump")) {
		    		  EffectUtil.jump((LivingEntity) entityIn, 1, 0, 0);
		    	  }
		    	  if(colideEID.equals("saddam")) {
		    		  EffectUtil.saddam((LivingEntity) entityIn, worldIn);
		    	  }
		    	  if(colideEID.equals("hole")) {
		    		  EffectUtil.makeHole(16, worldIn, (LivingEntity) entityIn);
		    	  }
		    	  if(colideEID.equals("clear_inventory") && entityIn instanceof PlayerEntity) {
		    		  EffectUtil.clearInventory((PlayerEntity) entityIn);
		    	  }
			}
		      if(colideEntity != null && entityIn instanceof PlayerEntity) {
		    	  EffectUtil.spawnEntityFromString((PlayerEntity) entityIn, rightEntity, worldIn);
		      }
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity playerIn) {
		// TODO Auto-generated method stub
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
		super.onBlockHarvested(worldIn, pos, state, playerIn);
	}

	public void addBreakEffect(String id, int duration, int amplifier) {
		String[] parts = id.split("-");
		Effect effect = null;
		if(parts[0].equals("1")) {
			effect = com.liamq12.examplemod.core.util.EffectUtil.getVanillaEffect(parts[1]);
		}else if(parts[0].equals("0")){
			rightEID = parts[1];
		}else {
			rightEntity = parts[1];
		}
		
		if(effect != null) {
		this.rightEffect = new EffectHolder(effect, duration, amplifier);
		}
	}
	
	public void addHitEffect(String id, int duration, int amplifier) {
		String[] parts = id.split("-");
		Effect effect = null;
		if(parts[0].equals("1")) {
			effect = com.liamq12.examplemod.core.util.EffectUtil.getVanillaEffect(parts[1]);
		}else if(parts[0].equals("0")){
			colideEID = parts[1];
		}else {
			colideEntity = parts[1];
		}
		
		if(effect != null) {
		this.colideEffect = new EffectHolder(effect, duration, amplifier);
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setID(int id) {
		blockID = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return blockID;
	}
	
}