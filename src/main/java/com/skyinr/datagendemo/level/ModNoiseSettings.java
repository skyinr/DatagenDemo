package com.skyinr.datagendemo.level;

import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.world.level.levelgen.NoiseSamplingSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.NoiseSlider;

public class ModNoiseSettings {
    public static final NoiseSettings NOISE_SETTINGS_DEMO = NoiseSettings.create(
            -64,
            384,
            new NoiseSamplingSettings(1.0D, 1.0D, 80.0D, 160.0D),
            new NoiseSlider(-0.078125D, 2, 0),
            new NoiseSlider(0.4D, 3, 0),
            1,
            2,
            TerrainProvider.caves());
}
