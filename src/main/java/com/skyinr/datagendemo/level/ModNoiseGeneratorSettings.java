package com.skyinr.datagendemo.level;

import com.mojang.serialization.Lifecycle;
import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import com.skyinr.datagendemo.datagen.ModWorldgenProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.TerrainShaper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlider;

public class ModNoiseGeneratorSettings {
    public static final Holder<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS_DEMO_HOLDER;
    public static final WritableRegistry<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS_REGISTRY = ModWorldgenProvider.registrable.ownedWritableRegistryOrThrow(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY);
    public static final NoiseGeneratorSettings NOISE_GENERATOR_SETTINGS_DEMO = new NoiseGeneratorSettings(
            MODNoiseSettings.getNoiseSettings(),
            ModBlocks.BLOCK_DEMO.get().defaultBlockState(),
            Blocks.WATER.defaultBlockState(),
            //使用了AT
            ModNoiseRouterWithOnlyNoises.NOISE_ROUTER_WITH_ONLY_NOISES_DEMO,
            SurfaceRuleData.overworld(),
            64,
            true,
            true,
            true,
            false);
    public static final ResourceKey<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS_DEMO_KEY = ResourceKey.create(Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, DataGenDemo.modLoc("noise_generator_settings_demo"));

    static {
        NOISE_GENERATOR_SETTINGS_DEMO_HOLDER = NOISE_GENERATOR_SETTINGS_REGISTRY.register(NOISE_GENERATOR_SETTINGS_DEMO_KEY, NOISE_GENERATOR_SETTINGS_DEMO, Lifecycle.stable());
    }

    private static class MODNoiseSettings {

        private MODNoiseSettings() {

        }

        public static NoiseSettings getNoiseSettings() {
            return NoiseSettings.create(
                    -64,
                    128,
                    new NoiseSamplingSettings(1.0D, 3.0D, 80.0D, 60.0D),
                    new NoiseSlider(0.9375D, 3, 0),
                    new NoiseSlider(2.5D, 4, -1),
                    1,
                    2,
                    TerrainShaper.overworld(true)
            );
        }
    }
}
