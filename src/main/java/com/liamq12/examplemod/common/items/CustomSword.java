package com.liamq12.examplemod.common.items;

import java.util.Random;
import java.util.UUID;

import org.apache.http.util.EntityUtils;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.liamq12.examplemod.common.potions.EffectHolder;
import com.liamq12.examplemod.core.util.EffectUtil;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class CustomSword extends SwordItem {
	 private final float attackDamage;
	    private final float attackSpeed;
	    public EffectHolder rightEffect;
	    public EffectHolder targetEffect;
	    private String name;
	    private String rightEID;
	    private String rightEntity;
	    private String targetEID;
	    private String targetEntity;
	    private boolean noGravity = false;
	    private final Multimap<Attribute, AttributeModifier> attribute;
	    public CustomSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
	        super(tier, attackDamageIn, attackSpeedIn, builder);
	        this.attackSpeed = attackSpeedIn;
	        this.attackDamage = attackDamageIn;
	        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.<Attribute, AttributeModifier>builder();
	        attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
	        attributeBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
	        this.attribute = attributeBuilder.build();
	    }
	    
		public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
			EffectUtil.onRightEventAction(worldIn, playerIn, handIn, rightEffect, targetEffect, rightEID, rightEntity, targetEID);
			// TODO Auto-generated method stub
//			if(rightEffect != null) {
//			playerIn.addPotionEffect(new EffectInstance(rightEffect.getEffect(), rightEffect.getDuration(), rightEffect.getAmplifier()));
//			}
//		      //System.out.println("magic92 " + targetEID);
//		      if(rightEID != null) {
//		    	  if(rightEID.equals("lightning")) {
//		    		 EffectUtil.spawnLightning(playerIn);
//		    	  }
//		    	  if(rightEID.equals("ender_chest")) {
//		    		  EffectUtil.openEnderChest(playerIn);
//		    	  }
//		    	  if(rightEID.equals("jump")) {
//		    		  if(playerIn.isOnGround()) {
//		    		  EffectUtil.jump(playerIn, 1, 0, 0);
//		    		  }
//		    	  }
//		    	  if(rightEID.equals("yeet")) {
//		    		  if(playerIn.isOnGround()) {
//		    		  Random rand = new Random();
//		    		  int left = rand.nextInt(7)-3;
//		    		  int right = rand.nextInt(7)-3;
//		    		  int jump = rand.nextInt(3)+4;
//		    		  EffectUtil.jump(playerIn, jump, left, right);
//		    		  }
//		    	  }
//		    	  if(rightEID.equals("double_jump")) {
//		    		  EffectUtil.jump(playerIn, 1, 0, 0);
//		    	  }
//		    	  if(rightEID.equals("shoot_arrow")) {
//		    		  
//		    		  if(playerIn.inventory.hasItemStack(new ItemStack(Items.ARROW, 1))) {
//		    			  EffectUtil.playerShootArrow(playerIn, worldIn, handIn);
//		    		  }
//		    	  }
//		    	  if(rightEID.equals("shoot_fireball")) {
//		    		  EffectUtil.playerShootFireball(playerIn, worldIn, handIn);
//		    	  }
//		    	  if(rightEID.equals("saddam")) {
//		    		  EffectUtil.saddam(playerIn, worldIn);
//		    	  }
//		    	  if(rightEID.equals("hole")) {
//		    		  EffectUtil.makeHole(16, worldIn, playerIn);
//		    	  }
//		    	  if(rightEID.equals("clear_inventory")) {
//		    		  EffectUtil.clearInventory(playerIn);
//		    	  }
//		    	  if(rightEID.equals("beam")) {
//		    		  EffectUtil.beam(playerIn, worldIn, 12, 500);
//		    	  }
//			//ZombieEntity entity = new ZombieEntity(worldIn);
//			//entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
//			//worldIn.addEntity(entity);
//			//playerIn.getInventoryEnderChest();
//			}
//		      if(rightEntity != null) {
//		    	  EffectUtil.spawnEntityFromString(playerIn, rightEntity, worldIn);
//		      }
			return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
		}
		
		@Override
		public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
			EffectUtil.onHitEventAction(target.getEntityWorld(), target, targetEffect, targetEffect, targetEID, targetEntity, targetEID);
//		      stack.damageItem(1, attacker, (entity) -> {
//		          entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
//		       });
//		      if(targetEffect != null) {
//		      target.addPotionEffect(new EffectInstance(targetEffect.getEffect(), targetEffect.getDuration(), targetEffect.getAmplifier()));
//		      }
//		      if(targetEID != null) {
//		    	  if(targetEID.equals("lightning")) {
//		    		 EffectUtil.spawnLightning(target);
//		    	  }
//		    	  if(targetEID.equals("accelerate")) {
//		    		  target.setVelocity(0, 2, 0);
//		    	  }
//		      }
		      //target.setNoGravity(!noGravity);
		      return true;
		}
	
		public void addRightClickEffect(String id, int duration, int amplifier) {
			String[] parts = id.split("-");
			Effect effect = null;
  		    System.out.println("magic56 "  + id);
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
		
		public void addTargetEffect(String id, int duration, int amplifier) {
			String[] parts = id.split("-");
			Effect effect = null;
			if(parts[0].equals("1")) {
				effect = com.liamq12.examplemod.core.util.EffectUtil.getVanillaEffect(parts[1]);
			}else if(parts[0].equals("0")){
				targetEID = parts[1];
			}else {
				targetEntity = parts[1];
			}
			
			if(effect != null) {
			this.targetEffect = new EffectHolder(effect, duration, amplifier);
			}
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getToolName() {
			return this.name;
		}
		
}
