package com.skyinr.datagendemo;

import com.skyinr.datagendemo.block.ModBlocks;
import com.skyinr.datagendemo.item.ModItems;
import com.skyinr.datagendemo.level.ModBiomes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("datagendemo")
public class DataGenDemo {
    public static final String MODID = "datagendemo";

    public DataGenDemo() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBiomes.BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static ResourceLocation modLoc(String path) {
        return new ResourceLocation(DataGenDemo.MODID, path);
    }
}
