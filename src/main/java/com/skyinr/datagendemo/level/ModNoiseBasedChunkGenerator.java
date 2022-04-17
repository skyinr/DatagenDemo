package com.skyinr.datagendemo.level;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

public class ModNoiseBasedChunkGenerator {
    private static final RegistryAccess registryaccess = RegistryAccess.BUILTIN.get();
    public static NoiseBasedChunkGenerator forestChunkGen =
            new NoiseBasedChunkGenerator(
                    registryaccess.registryOrThrow(Registry.STRUCTURE_SET_REGISTRY),
                    registryaccess.registryOrThrow(Registry.NOISE_REGISTRY),
                    new FixedBiomeSource(registryaccess.registryOrThrow(Registry.BIOME_REGISTRY)
                            .getHolderOrThrow(ModBiomes.BIOME_DEMO_KEY)),
                    0L,
                    registryaccess.registryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY)
                            .getOrCreateHolder(ModNoiseGeneratorSettings.DEMO)
            );
}
