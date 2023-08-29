package com.skyinr.datagendemo.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.Lifecycle;
import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.level.*;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

public class ModWorldgenProvider implements DataProvider {
    public static final RegistryAccess.Writable registrable = RegistryAccess.builtinCopy();
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    public ModWorldgenProvider(DataGenerator dataGenerator) {
        generator = dataGenerator;
    }

    @SuppressWarnings("all")
    protected static <E, T extends Registry<E>> void dumpRegistry(Path path, HashCache cache, DynamicOps<JsonElement> jsonElementDynamicOps, ResourceKey<? extends T> key, T registry, Encoder<E> encoder) {
        for (Map.Entry<ResourceKey<E>, E> entry : registry.entrySet()) {
            if (entry.getKey().location().getNamespace().equals(DataGenDemo.MODID)) {
                Path otherPath = createPath(path, key.location(), entry.getKey().location());
                dumpValue(otherPath, cache, jsonElementDynamicOps, encoder, entry.getValue());
            }
        }
    }

//    private static <T> void dumpRegistryCap(HashCache cache, Path path, DynamicOps<JsonElement> dynamicops, RegistryAccess.RegistryData<T> registryData) {
//        dumpRegistry(path, cache, dynamicops, registryData.key(), ModWorldgenProvider.registrable.ownedRegistryOrThrow(registryData.key()), registryData.codec());
//    }

    private static <T> void dumpValue(Path path, HashCache cache, DynamicOps<JsonElement> jsonElementDynamicOps, Encoder<T> encoder, T value) {
        try {
            Optional<JsonElement> optional = encoder.encodeStart(jsonElementDynamicOps, value).resultOrPartial((string) -> {
                LOGGER.error("Couldn't serialize element {}: {}", path, string);
            });
            if (optional.isPresent()) {
                DataProvider.save(GSON, cache, optional.get(), path);
            }
        } catch (IOException ioexception) {
            LOGGER.error("Couldn't save element {}", path, ioexception);
        }

    }

    private static Path createPath(Path path, ResourceLocation location, ResourceLocation value) {
        return resolveTopPath(path).resolve(value.getNamespace()).resolve(location.getPath()).resolve(value.getPath() + ".json");
    }

    protected static Path resolveTopPath(Path path) {
        return path.resolve("data");
    }

    @Override
    public void run(HashCache cache) {
        Path path = this.generator.getOutputFolder();
        DynamicOps<JsonElement> dynamicops = RegistryOps.create(JsonOps.INSTANCE, registrable);
        Registry<LevelStem> dimensionRegistry = this.registryDimension();
        dumpRegistry(path, cache, dynamicops, Registry.DIMENSION_TYPE_REGISTRY, ModDimensionTypes.DIMENSION_TYPE_REGISTRY, DimensionType.DIRECT_CODEC);
        dumpRegistry(path, cache, dynamicops, Registry.BIOME_REGISTRY, ModBiomes.BIOME_REGISTRY, Biome.DIRECT_CODEC);
        dumpRegistry(path, cache, dynamicops, Registry.NOISE_REGISTRY, ModNoisesParameters.NOISE_PARAMETERS_REGISTRY, NormalNoise.NoiseParameters.DIRECT_CODEC);
        dumpRegistry(path, cache, dynamicops, Registry.NOISE_GENERATOR_SETTINGS_REGISTRY, ModNoiseGeneratorSettings.NOISE_GENERATOR_SETTINGS_REGISTRY, NoiseGeneratorSettings.DIRECT_CODEC);
        dumpRegistry(path, cache, dynamicops, Registry.LEVEL_STEM_REGISTRY, dimensionRegistry, LevelStem.CODEC);
//        RegistryAccess.knownRegistries().forEach((registryData) -> dumpRegistryCap(cache, path, dynamicops, registryData));

    }

    private Registry<LevelStem> registryDimension() {
        WritableRegistry<LevelStem> writableregistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.stable(), null);

        writableregistry.register(ResourceKey.create(Registry.LEVEL_STEM_REGISTRY,
                        DataGenDemo.modLoc("level_stem_demo")),
                new LevelStem(
                        ModDimensionTypes.DIMENSION_TYPE_HOLDER,
                        ModNoiseBasedChunkGenerator.forestChunkGen,
                        true)
                , Lifecycle.stable());
        return writableregistry;
    }

    @Override
    public String getName() {
        return "Worldgen:" + DataGenDemo.MODID;
    }
}
