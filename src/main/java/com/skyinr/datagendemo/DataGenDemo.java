package com.skyinr.datagendemo;

import com.skyinr.datagendemo.block.ModBlocks;
import com.skyinr.datagendemo.datagen.ModWorldgenProvider;
import com.skyinr.datagendemo.item.ModItems;
import com.skyinr.datagendemo.level.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
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
        registerLevel();
    }

    private static void registerLevel(){
        ModNoisesParameters.NOISE_PARAMETERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModStructureSets.STRUCTURE_SETS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModNoiseGeneratorSettings.NOISE_GENERATOR_SETTINGS.register(FMLJavaModLoadingContext.get().getModEventBus());

//        ModNoiseBasedChunkGenerators.NOISE_BASED_CHUNK_GENERATORS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static ResourceLocation modLoc(String path){
        return new ResourceLocation(DataGenDemo.MODID, path);
    }
}
