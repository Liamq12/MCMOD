package com.liamq12.examplemod.common.items;

import com.liamq12.examplemod.*;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.LazyValue;
import net.minecraft.item.crafting.Ingredient;
import com.google.common.base.Supplier;
import com.liamq12.examplemod.core.init.ItemInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Editing this class in enum form will crash your computer, or at the very least slow down eclipse

//public enum ModArmorMaterial implements IArmorMaterial{
//	TEST(ExampleMod.MOD_ID + ":test", 5, new int[] {7, 9, 11, 7}, 420, SoundEvents.AMBIENT_CAVE, 6.9f, () -> {
//		return Ingredient.fromItems(ItemInit.ITEM_CLASS_MAP.get("aquamarine"));
//	});
	
public class ModArmorMaterial implements IArmorMaterial{
	
	private static final int[] MAX_DAMAGE_ARRAY = new int[] {16, 16, 16, 16};
	private final String name;
	private final int maxDamangeFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantabillity;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairMaterial;
	
	public ModArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmount, int enchantabillity, SoundEvent soundEvent, float toughness, float knockbackResistance) {//, Supplier<Ingredient> repairMaterial) {
		this.name = name;
		this.maxDamangeFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmount;
		this.enchantabillity = enchantabillity;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairMaterial = Ingredient.EMPTY;
	}


	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return this.enchantabillity;
	}

	@Override
	public SoundEvent getSoundEvent() {
		// TODO Auto-generated method stub
		return this.soundEvent;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return this.repairMaterial;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public float getToughness() {
		// TODO Auto-generated method stub
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		// TODO Auto-generated method stub
		return this.knockbackResistance;
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		// TODO Auto-generated method stub
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamangeFactor;
	}
}
