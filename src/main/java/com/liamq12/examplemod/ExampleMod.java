package com.liamq12.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
//import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.liamq12.examplemod.modpac.MOD;
import com.liamq12.examplemod.world.BiomeInit;
import com.liamq12.examplemod.common.entities.EntityList;
import com.liamq12.examplemod.common.items.CraftingItems;
import com.liamq12.examplemod.core.init.BlockInit;
import com.liamq12.examplemod.core.init.ItemInit;
import com.liamq12.examplemod.core.world.OreGeneration;
import com.liamq12.examplemod.core.util.*;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MOD_ID)
public class ExampleMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "example"; //needs to stay as example for now
    public static final String PATH = "C:\\Users\\liamh\\OneDrive\\Desktop\\MCMOD"; // Path to MCMOD Folder 
    public static final String NAME = "Liam"; // Your Name
    
    public ExampleMod() {
    	init();
    	
    	//Your Code Below
    	//V V V V V V V V// // // //
        MOD.addItem("aquamarine");
        MOD.addItem("burger");
        MOD.addBlock("neat_ore", 15f, 15f, ToolType.PICKAXE, 1);
        MOD.addBlock("aquamarine_ore", 15f, 30f, ToolType.PICKAXE, -1);
        MOD.addBlock("aqua", 1f, 3f, ToolType.PICKAXE, 2);
        MOD.addDrop(MOD.block("aquamarine_ore"), Items.ACACIA_BOAT);
        MOD.item("burger").addEffect(Effects.BLINDNESS, 100, 5);
        MOD.addFood("taco", Effects.SPEED, 200, 2, 1.0f, 1, 1.0f);
        MOD.generateOre(MOD.block("aquamarine_ore"), 10, 15, 50, 20);
        MOD.generateOre(MOD.block("aqua"), 3, 20, 60, 20);
        MOD.setCraftingRecipe(MOD.item("aquamarine"), Items.GOLD_BLOCK, "# #", " # ", "# #", 5);
        MOD.setSmeltingRecipe(MOD.block("aqua"), MOD.item("aquamarine"), 0.2, 100);
        MOD.makeLuckyBlock(MOD.block("aqua"));
        MOD.luckyBlock(MOD.block("aqua")).addCustomEffect(Effect.lightning());
        MOD.luckyBlock(MOD.block("aqua")).addCustomEffect(Effect.enderChest());
        MOD.luckyBlock(MOD.block("aqua")).addVanillaEffect(Effect.blindness(), 100, 5);
        MOD.luckyBlock(MOD.block("aqua")).addVanillaEffect(Effect.speed(), 100, 5);
        MOD.luckyBlock(MOD.block("aqua")).addMobSpawn(Effect.spawnCow());
        MOD.luckyBlock(MOD.block("aqua")).addMobSpawn(Effect.spawnPig());
        MOD.addBiome("test_biome");
        MOD.biome("test_biome").setGrassColor(0xEB372A);
        MOD.addBiome("my_biome");
        MOD.biome("my_biome").setFogColor(0xEB372A);
        MOD.addPickaxe("mypickaxe", MOD.item("aquamarine"), 4, 1000, 9.0f, 22f, 15, 2f);
        MOD.addAxe("myaxe", MOD.item("aquamarine") , 4, 1000, 9.0f, 22f, 15, 2f);
        MOD.addShovel("myshovel", MOD.item("aquamarine"), 4, 1000, 9.0f, 22f, 15, 2f);
        MOD.addHoe("myhoe", MOD.item("aquamarine"), 4, 1000, 9.0f, 22f, 15, 2f);
        MOD.addSword("mysword", MOD.item("aquamarine"), 4, 1000, 9.0f, 5f, 15, 2f);
        MOD.pickaxe("mypickaxe").addRightClickEffect(Effect.lightning(), 200, 10);
        MOD.sword("mysword").addTargetEffect(Effect.yeet(), 200, 100);
        MOD.addDisc("cooldisk");
        MOD.addDisc("sus");
        MOD.addDisc("foreverdisk");
        MOD.block("neat_ore").addHitEffect(Effect.lightning(), 1, 1);
        MOD.addHelmet("test_hel", MOD.item("aquamarine"), 12, new int[] {7, 9, 11, 7}, 30, 100f, 2f);
        MOD.addChestplate("test_che", MOD.item("aquamarine"), 12, new int[] {7, 9, 11, 7}, 30, 100f, 2f);
        MOD.addLeggings("test_leg", MOD.item("aquamarine"), 12, new int[] {7, 9, 11, 7}, 30, 100f, 2f);
        MOD.addBoots("test_bot", MOD.item("aquamarine"), 12, new int[] {7, 9, 11, 7}, 30, 100f, 2f);
        //MOD.addArmorSet("test", MOD.item("aquamarine"), 12, new int[] {7, 9, 11, 7}, 30, 100f, 2f); //untested
        
        
        //^ ^ ^ ^ ^ ^ ^ ^// // // //
        //Your Code Above
        
        //Examples
        //MOD.addItem([itemName]); //Replace [itemName] with the name of your item in the textures folder minus the ".png"
        //MOD.addBlock([blockName]); //Replace [blockName] with the name of your block in the textures folder minus the ".png"
        //MOD.addDrop(MOD.block([blockName), MOD.item([itemName])); //Replace [blockName] with the name of the block that will now drop [itemName]
        //MOD.addDrop(MOD.block([blockName), Items.[vanillaItemName]); //Replace [blockName] with the name of the block that will now drop [vanillaItemName]
        
        
        enit();
    }
    
    private void setup(final FMLCommonSetupEvent event){
    	
    }
    private void init() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        
        //Registry Registration
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        BiomeInit.BIOMES.register(bus);
        
        
        //MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
        MinecraftForge.EVENT_BUS.register(this);
        
        MOD.readyLang();
    }
    private void enit() {
    	MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
    	MOD.endLang();
    }
}
