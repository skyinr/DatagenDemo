package com.skyinr.datagendemo.level;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class ModNoiseBasedChunkGenerator {
    public static NoiseBasedChunkGenerator createDemoChunkGen(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> holder) {
        return new NoiseBasedChunkGenerator(
                biomeSource,
                holder);
    }
}
