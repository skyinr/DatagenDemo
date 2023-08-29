package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;

public class ModLevelStem {
    public static ResourceKey<LevelStem> LEVEL_STEM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            DataGenDemo.modLoc("level_stem_demo"));

    public static void bootstrap(BootstapContext<LevelStem> context) {
        context.register(LEVEL_STEM_KEY, new LevelStem(
                context.lookup(Registries.DIMENSION_TYPE)
                        .getOrThrow(ModDimensionTypes.DIMENSION_TYPE_KEY),
                ModNoiseBasedChunkGenerator.createDemoChunkGen(
                        new FixedBiomeSource(context.lookup(Registries.BIOME)
                                .getOrThrow(ModBiomes.BIOME_KEY)),
                        context.lookup(Registries.NOISE_SETTINGS)
                                .getOrThrow(ModNoiseGeneratorSettings.NOISE_GENERATOR_SETTINGS_DEMO_KEY))
        ));
    }
}
