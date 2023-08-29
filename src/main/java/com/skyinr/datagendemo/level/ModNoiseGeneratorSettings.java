package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class ModNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> NOISE_GENERATOR_SETTINGS_DEMO_KEY = ResourceKey.create(Registries.NOISE_SETTINGS, DataGenDemo.modLoc("noise_generator_settings_demo"));

    public static void bootstrap(BootstapContext<NoiseGeneratorSettings> context) {
        context.register(NOISE_GENERATOR_SETTINGS_DEMO_KEY, new NoiseGeneratorSettings(
                ModNoiseSettings.NOISE_SETTINGS_DEMO,
                ModBlocks.BLOCK_DEMO.get().defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                ModNoiseRouterWithOnlyNoises.createDemoNoiseRouter(context.lookup(Registries.NOISE)),
                SurfaceRuleData.overworld(),
                (new OverworldBiomeBuilder()).spawnTarget(),
                64,
                true,
                true,
                true,
                false)
        );
    }
}
