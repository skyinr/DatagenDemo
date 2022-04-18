package com.skyinr.datagendemo.level;

import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomes {
    public static final ResourceKey<Biome> BIOME_DEMO_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, DataGenDemo.modLoc("biome_demo"));
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, DataGenDemo.MODID);

    public static final RegistryObject<Biome> BIOME_DEMO = BIOMES.register("biome_demo", () -> new Biome.BiomeBuilder()
            .precipitation(Biome.Precipitation.NONE)
            .biomeCategory(Biome.BiomeCategory.JUNGLE)
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
            .generationSettings(new BiomeGenerationSettings.Builder()
                    .build())
            .temperatureAdjustment(Biome.TemperatureModifier.NONE)
            .build());

    public static final BiomeDictionary.Type DATA_GEN_DEMO = BiomeDictionary.Type.getType("DATA_GEN_DEMO");

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(BIOME_DEMO.getKey(), DATA_GEN_DEMO, BiomeDictionary.Type.OVERWORLD);
    }
}
