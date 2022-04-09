package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, DataGenDemo.MODID);

    public static final RegistryObject<Biome> DEMO_BIOME = BIOMES.register("demo_biome", () -> new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.NONE)
            .biomeCategory(Biome.BiomeCategory.JUNGLE)
            .temperature(0.8F)
            .downfall(0.4F)
            .specialEffects(new BiomeSpecialEffects.Builder()
                    .skyColor(0x32CD32)
                    .fogColor(0x32CD32)
                    .waterColor(0x32CD32)
                    .waterFogColor(0x32CD32)
                    .build())
            .mobSpawnSettings(new MobSpawnSettings.Builder()
                    .build())
            .generationSettings(new BiomeGenerationSettings.Builder()
                    .build())
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .build());
}
