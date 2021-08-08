package com.liamq12.examplemod.core.util;

import net.minecraft.client.renderer.entity.GiantZombieRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.GiantEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.MuleEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Effect {
	
//	//Potion Effects
//	SPEED(new EffectInstance(effect.getEffect(), rightEffect.getDuration(), rightEffect.getAmplifier())),
//	SLOWNESS(),
//	HASTE(),
//	MINING_FATIGUE(),
//	STRENGTH(),
//	INSTANT_HEALTH(),
//	INSTANT_DAMAGE(),
//	JUMP_BOOST(),
//	NAUSEA(),
//	REGENERATION(),
//	RESISTANCE(),
//	FIRE_RESISTANCE(),
//	WATER_BREATHING(),
//	INVISIBILLITY(),
//	BLINDNESS(),
//	NIGHT_VISION(),
//	HUNGER(),
//	WEAKNESS(),
//	POISON(),
//	WITHER(),
//	HEALTH_BOOST(),
//	ABSORPTION(),
//	SATURATION(),
//	GLOWING(),
//	LEVITATION(),
//	LUCK(),
//	UNLUCK(),
//	SLOW_FALLING(),
//	CONDUIT_POWER(),
//	DOLPHINS_GRACE(),
//	BAD_OMEN(),
//	HERO_OF_THE_VILLAGE(),
	
//	//Custom Effects
//	LIGHTNING(),
//	ENDER_CHEST(),
//	BEAM(),
//	SPAWN(),
//	ACCELERATE(),
//	REPLACE(),
//	TELEPORT_TO_SPAWN();
	
//Codes
// 0 = custom effect (effect functions in EffectUtil)
// 1 = potion effect
// 2 = animal spawns
	private Effect(){
		
	}
	
	public static String enderChest() {
		return "0-ender_chest";
	}
	
	public static String lightning() {
		return "0-lightning";
	}
	
	public static String spawn() {
		return "0-spawn";
	}
	
	public static String jump() {
		return "0-jump";
	}
	
	public static String yeet() {
		return "0-yeet";
	}
	
	public static String InfiniteJump() {
		return "0-double_jump";
	}
	
	public static String shootArrow() {
		return "0-shoot_arrow";
	}
	
	public static String shootFireball() {
		return "0-shoot_fireball";
	}
	
	public static String saddamHussein() {
		return "0-saddam";
	}
	
	public static String hole() {
		return "0-hole";
	}
	
	public static String clearInventory() {
		return "0-clear_inventory";
	}
	
	public static String beam() {
		return "0-beam";
	}
	
	public static String speed() {
		return "1-speed";
	}
	
	public static String slowness() {
		return "1-slowness";
	}
	
	public static String haste() {
		return "1-haste";
	}
	
	public static String miningFatigue() {
		return "1-mining_fatigue";
	}
	
	public static String strength() {
		return "1-strength";
	}
	
	public static String instantHealth() {
		return "1-instant_health";
	}
	
	public static String instantDamage() {
		return "1-instant_damage";
	}
	
	public static String jumpBoost() {
		return "1-jump_boost";
	}
	
	public static String nausea() {
		return "1-nauesa";
	}
	
	public static String regeneration() {
		return "1-regeneration";
	}
	
	public static String resistance() {
		return "1-resistance";
	}
	
	public static String fireResistance() {
		return "1-fire_resistance";
	}
	
	public static String waterBreathing() {
		return "1-water_breathing";
	}
	
	public static String invisibillity() {
		return "1-invisibillity";
	}
	
	public static String blindness() {
		return "1-blindness";
	}
	
	public static String nightVision() {
		return "1-night_vision";
	}
	
	public static String hunger() {
		return "1-hunger";
	}
	
	public static String weakness() {
		return "1-weakness";
	}
	
	public static String poison() {
		return "1-posion";
	}
	
	public static String wither() {
		return "1-wither";
	}
	
	public static String healthBoost() {
		return "1-health_boost";
	}
	
	public static String absorption() {
		return "1-absorption";
	}
	
	public static String saturation() {
		return "1-saturation";
	}
	
	public static String glowing() {
		return "1-glowing";
	}
	
	public static String levitation() {
		return "1-levitation";
	}
	
	public static String luck() {
		return "1-luck";
	}
	
	public static String unluck() {
		return "1-unluck";
	}
	
	public static String slowFalling() {
		return "1-slow_falling";
	}

	public static String conduitPower() {
		return "1-conduit_power";
	}
	
	public static String dolphinsGrace() {
		return "1-dolphins_grace";
	}
	
	public static String badOmen() {
		return "1-bad_omen";
	}
	
	public static String heroOfTheVillage() {
		return "1-hero_of_the_village";
	}
	
	public static String spawnPig() {
		return "2-pig";
	}
	
	public static String spawnCow() {
		return "2-cow";
	}
	
	public static String spawnChicken() {
		return "2-chicken";
	}
	
	public static String spawnSheep() {
		return "2-sheep";
	}

	public static String spawnZombie() {
		return "2-zombie";
	}
	
	public static String spawnSkeleton() {
		return "2-skeleton";
	}
	
	public static String spawnCreeper() {
		return "2-creeper";
	}
	
	public static String spawnVillager() {
		return "2-villager";
	}
	
	public static String spawnWolf() {
		return "2-wolf";
	}
	
	public static String spawnOcelot() {
		return "2-ocelot";
	}
	
	public static String spawnWitch() {
		return "2-witch";
	}
	
	public static String spawnHorse() {
		return "2-horse";
	}
	
	public static String spawnSquid() {
		return "2-squid";
	}
	
	public static String spawnRabbit() {
		return "2-rabbit";
	}
	
	public static String spawnParrot() {
		return "2-parrot";
	}
	
	public static String spawnMule() {
		return "2-mule";
	}
	
	public static String spawnMooshroom() {
		return "2-mooshroom";
	}
	
	public static String spawnLlama() {
		return "2-llama";
	}
	
	public static String spawnDonkey() {
		return "2-donkey";
	}
	
	public static String spawnBat() {
		return "2-bat";
	}
	
	public static String spawnWitherSkeleton() {
		return "2-wither_skeleton";
	}
	
	public static String spawnWither() {
		return "2-wither";
	}
	
	public static String spawnVindicator() {
		return "2-vindicator";
	}
	
	public static String spawnVex() {
		return "2-vex";
	}
	
	public static String spawnStray() {
		return "2-stray";
	}
	
	public static String spawnSpider() {
		return "2-spider";
	}
	
	public static String spawnSnowman() {
		return "2-snowman";
	}
	
	public static String spawnSlime() {
		return "2-slime";
	}
	
	public static String spawnSilverfish() {
		return "2-silverfish";
	}
	
	public static String spawnShulker() {
		return "2-shulker";
	}
	
	public static String spawnPolarBear() {
		return "2-polar_bear";
	}
	
	public static String spawnPiglin() {
		return "2-piglin";
	}
	
	public static String spawnMagmacube() {
		return "2-magmacube";
	}
	
	public static String spawnGolem() {
		return "2-golem";
	}
	
	public static String spawnHusk() {
		return "2-husk";
	}
	
	public static String spawnGuardian() {
		return "2-guardian";
	}
	
	public static String spawnGiantZombie() {
		return "2-giant_zombie";
	}
	
	public static String spawnGhast() {
		return "2-ghast";
	}
	
	public static String spawnEndermite() {
		return "2-endermite";
	}
	
	public static String spawnEnderman() {
		return "2-enderman";
	}
	
	public static String spawnCaveSpider() {
		return "2-cave_spider";
	}
	
	public static String spawnBlaze() {
		return "2-blaze";
	}
	
}
