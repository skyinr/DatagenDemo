package com.skyinr.datagendemo.level;

import com.mojang.serialization.Lifecycle;
import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.datagen.ModWorldgenProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
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
    public static final Holder<Biome> BIOME_HOLDER;
    public static final WritableRegistry<Biome> BIOME_REGISTRY = ModWorldgenProvider.registrable.ownedWritableRegistryOrThrow(Registry.BIOME_REGISTRY);
    public static final Biome BIOME_DEMO_GEN = new Biome.BiomeBuilder()
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
            .build();

    public static final ResourceKey<Biome> BIOME_DEMO_KEY = ResourceKey.create(Registry.BIOME_REGISTRY, DataGenDemo.modLoc("biome_demo"));
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, DataGenDemo.MODID);

    public static final RegistryObject<Biome> BIOME_DEMO = BIOMES.register("biome_demo", () -> BIOME_DEMO_GEN);

    public static final BiomeDictionary.Type DATA_DEMO_TYPE = BiomeDictionary.Type.getType("data_demo_type");

    static {
        addBiomeTypes();
        BIOME_HOLDER = BIOME_REGISTRY.register(BIOME_DEMO_KEY, BIOME_DEMO_GEN, Lifecycle.stable());
    }

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(BIOME_DEMO.getKey(), DATA_DEMO_TYPE, BiomeDictionary.Type.OVERWORLD);
    }
}
