package com.liamq12.examplemod.common.potions;

import net.minecraft.potion.Effect;

public class EffectHolder {
	private Effect effect;
	private int duration;
	private int amplifier;
	public EffectHolder(Effect effect, int duration, int amplifier) {
		this.effect = effect;
		this.duration = duration;
		this.amplifier = amplifier;
	}
	
	public Effect getEffect() {
		return effect;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getAmplifier() {
		return amplifier;
	}
}
