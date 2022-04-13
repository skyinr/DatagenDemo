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
        ModConfiguredStructureFeatures.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModDimensionTypes.DEFERREDS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModNoisesParameters.NOISE_PARAMETERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModStructureSets.STRUCTURE_SETS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLevelStems.LEVEL_STEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModNoiseGeneratorSettings.NOISE_GENERATOR_SETTINGS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLevels.LEVELS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModWorldgenProvider.deferredRegisters.put(ModBiomes.BIOMES, Biome.DIRECT_CODEC);
        ModWorldgenProvider.deferredRegisters.put(ModConfiguredStructureFeatures.FEATURES, ConfiguredStructureFeature.DIRECT_CODEC);
        ModWorldgenProvider.deferredRegisters.put(ModDimensionTypes.DEFERREDS, DimensionType.DIRECT_CODEC);
        ModWorldgenProvider.deferredRegisters.put(ModNoisesParameters.NOISE_PARAMETERS, NormalNoise.NoiseParameters.DIRECT_CODEC);
        ModWorldgenProvider.deferredRegisters.put(ModStructureSets.STRUCTURE_SETS, StructureSet.DIRECT_CODEC);
//        ModWorldgenProvider.deferredRegisters.put(ModLevelStems.LEVEL_STEMS, LevelStem.CODEC);
        ModWorldgenProvider.deferredRegisters.put(ModNoiseGeneratorSettings.NOISE_GENERATOR_SETTINGS, NoiseGeneratorSettings.DIRECT_CODEC);
//        ModWorldgenProvider.deferredRegisters.put(ModLevels.LEVELS, Level.RESOURCE_KEY_CODEC);

//        ModNoiseBasedChunkGenerators.NOISE_BASED_CHUNK_GENERATORS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static ResourceLocation modLoc(String path){
        return new ResourceLocation(DataGenDemo.MODID, path);
    }
}
