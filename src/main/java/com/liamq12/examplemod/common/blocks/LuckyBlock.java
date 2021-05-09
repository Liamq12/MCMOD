package com.liamq12.examplemod.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.liamq12.examplemod.common.entities.EntityList;
import com.liamq12.examplemod.common.entities.Results;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyBlock extends Block {
    public ArrayList<String> ENTITY_LIST = new ArrayList<>();
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
	int randomNumber = rand.nextInt(ENTITY_LIST.size());
	Entity entity = EntityList.getEntity(r, ENTITY_LIST.get(randomNumber));
	if (entity != null) {
	    entity.setPosition(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
	    worldIn.addEntity(entity);
	}
    }

    public void setMobOutcome(String elist) {
	ENTITY_LIST.add(elist);
    }
}