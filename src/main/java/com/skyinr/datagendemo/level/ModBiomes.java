package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class ModBiomes {
    public static final ResourceKey<Biome> BIOME_KEY = ResourceKey.create(Registries.BIOME, DataGenDemo.modLoc("biome_demo"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(BIOME_KEY, new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.8F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .skyColor(0xFFC0CB)
                        .fogColor(0xFFC0CB)
                        .waterColor(0xFFC0CB)
                        .waterFogColor(0xFFC0CB)
                        .build())
                .mobSpawnSettings(new MobSpawnSettings.Builder()
                        .build())
                .generationSettings(new BiomeGenerationSettings.PlainBuilder()
                        .build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build());
    }
}
