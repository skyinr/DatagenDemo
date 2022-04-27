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
                    ModNoisesParameters.NOISE_PARAMETERS_REGISTRY,
                    new FixedBiomeSource(ModBiomes.BIOME_HOLDER),
                    0L,
                    ModNoiseGeneratorSettings.NOISE_GENERATOR_SETTINGS_DEMO_HOLDER
            );
}
