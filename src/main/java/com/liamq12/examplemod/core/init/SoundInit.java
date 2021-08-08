package com.liamq12.examplemod.core.init;

import java.util.HashMap;

import com.liamq12.examplemod.ExampleMod;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ExampleMod.MOD_ID);
	
	public static HashMap<String, Lazy<SoundEvent>> SOUND_LAZY = new HashMap<String, Lazy<SoundEvent>>();
	public static void registerSound(String name) {
		final Lazy<SoundEvent> myDisk = Lazy.of(() -> new SoundEvent(new ResourceLocation(ExampleMod.MOD_ID, name)));
		SOUND_LAZY.put(name, myDisk);
		
	    final RegistryObject<SoundEvent> coolDisk = SOUNDS.register(name, myDisk);
	}
}