package com.liamq12.examplemod.common.items;

import java.util.function.Supplier;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

//public enum ItemToolTiers implements IItemTier {
//    // Harvest-level, durability, efficiency, attackdamage, enchantability, repair material
//    MYPICKAXE(4, 3031, 9.0F, 5.0F, 15, () -> {
//        return Ingredient.EMPTY;
//
//    //You may also return Items.EMPTY if it has no material repair type
//    });

public class ItemToolTiers implements IItemTier {
  
    //Private values to store
    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    //This sets and/or gets the values we specific for the material
    public ItemToolTiers(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability,
                         Supplier<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

	public int getMaxUses() {
		// TODO Auto-generated method stub
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		// TODO Auto-generated method stub
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		// TODO Auto-generated method stub
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		// TODO Auto-generated method stub
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		// TODO Auto-generated method stub
		return this.repairMaterial.getValue();
	}
}
