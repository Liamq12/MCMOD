package com.liamq12.examplemod.common.items;

import java.util.List;

import com.liamq12.examplemod.common.potions.EffectHolder;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpecialItem extends Item{
	
	private EffectHolder effect;
	private String name;
	
	public SpecialItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		// TODO Auto-generated method stub
		if(effect != null) {
		playerIn.addPotionEffect(new EffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier()));
		}
		//ZombieEntity entity = new ZombieEntity(worldIn);
		//entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
		//worldIn.addEntity(entity);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
	
	public void addEffect(Effect effect, int duration, int amplifier) {
		this.effect = new EffectHolder(effect, duration, amplifier);
		System.out.println("kangaroo340");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getItemName() {
		return name;
	}
}