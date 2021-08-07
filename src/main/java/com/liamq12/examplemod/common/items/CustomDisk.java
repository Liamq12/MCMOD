package com.liamq12.examplemod.common.items;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

public class CustomDisk extends MusicDiscItem{
	
    @SuppressWarnings("deprecation")
	public CustomDisk(int comparatorValueIn, SoundEvent soundIn, Properties builder) {
        super(comparatorValueIn, soundIn, builder);
    }

}
