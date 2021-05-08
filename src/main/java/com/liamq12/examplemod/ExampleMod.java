package com.liamq12.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
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
import com.liamq12.examplemod.common.items.CraftingItems;
import com.liamq12.examplemod.core.init.BlockInit;
import com.liamq12.examplemod.core.init.ItemInit;
import com.liamq12.examplemod.core.world.OreGeneration;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MOD_ID)
public class ExampleMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "example";
    public static final String PATH = "C:\\Users\\liamh\\OneDrive\\Desktop\\MCMOD"; // Path to MCMOD Folder 
    public static final String NAME = "Liam"; // Your Name
    
    public ExampleMod() {
    	init();
    	
    	//Your Code Below
    	//V V V V V V V V// // // //
        MOD.addItem("aquamarine");
        MOD.addItem("burger");
        MOD.addItem("taco");
        MOD.addBlock("aquamarine_ore", 15f, 30f, ToolType.PICKAXE, -1);
        MOD.addBlock("aqua", 1f, 3f, ToolType.PICKAXE, 2);
        MOD.addDrop("aquamarine_ore", "aquamarine");
        MOD.addPotionRightClickEffect("burger", Effects.BLINDNESS);
        MOD.makeFood("taco", Effects.SPEED, 200, 2, 1.0f, 1, 1.0f);
        MOD.generateOre("aquamarine_ore", 10, 15, 50, 20);
        MOD.generateOre("aqua", 3, 20, 60, 20);
        MOD.setCraftingRecipe("aquamarine", Items.GOLD_BLOCK, "# #", " # ", "# #", 5);
        MOD.setSmeltingRecipe("aqua", "aquamarine", 0.2, 100);
        
        //System.out.println("magic34 " + CraftingItems.ITEM_TEST_MAGIC34.toString());
        //^ ^ ^ ^ ^ ^ ^ ^//
        //Your Code Above
        
        //Examples
        //MOD.addItem([itemName]); //Replace "[itemName]" with the name of your item in the textures folder minus the ".png"
        //MOD.addBlock([blockName]); //Replace "[blockName]" with the name of your block in the textures folder minus the ".png"
        
        enit();
    }
    
    private void setup(final FMLCommonSetupEvent event){
    	
    }
    private void init() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        
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
