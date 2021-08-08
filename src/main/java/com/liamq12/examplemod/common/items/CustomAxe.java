package com.liamq12.examplemod.common.items;

import java.util.UUID;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.liamq12.examplemod.common.potions.EffectHolder;
import com.liamq12.examplemod.core.util.EffectUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CustomAxe extends AxeItem{
    private final float attackDamage;
    private final float attackSpeed;
    public Effect effect;
    private String name;
    public EffectHolder rightEffect;
    public EffectHolder targetEffect;
    private String rightEID;
    private String rightEntity;
    private String targetEID;
    private String targetEntity;
    private final Multimap<Attribute, AttributeModifier> attribute;
    public CustomAxe(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.attackSpeed = attackSpeedIn;
        this.attackDamage = attackDamageIn;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.<Attribute, AttributeModifier>builder();
        attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        attributeBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
        this.attribute = attributeBuilder.build();
    }
    
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		// TODO Auto-generated method stub
		EffectUtil.onRightEventAction(worldIn, playerIn, handIn, rightEffect, targetEffect, rightEID, rightEntity, targetEID);
		//ZombieEntity entity = new ZombieEntity(worldIn);
		//entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
		//worldIn.addEntity(entity);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		// TODO Auto-generated method stub
		EffectUtil.onHitEventAction(target.getEntityWorld(), target, targetEffect, targetEffect, targetEID, targetEntity, targetEID);
		return super.hitEntity(stack, target, attacker);
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
