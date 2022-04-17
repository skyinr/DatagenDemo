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
import net.minecraft.core.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.LevelStem;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

public class ModWorldgenProvider implements DataProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;

    public ModWorldgenProvider(DataGenerator dataGenerator) {
        generator = dataGenerator;
    }

    @Override
    public void run(HashCache cache) {
        Path path = this.generator.getOutputFolder();
        RegistryAccess registryaccess = RegistryAccess.BUILTIN.get();
        DynamicOps<JsonElement> dynamicops = RegistryOps.create(JsonOps.INSTANCE, registryaccess);

        Registry<LevelStem> dimensionRegistry = this.registryDimension();
        RegistryAccess.knownRegistries().forEach((registryData) -> {
            dumpRegistryCap(cache, path, registryaccess, dynamicops, registryData);
        });
        dumpRegistry(path, cache, dynamicops, Registry.LEVEL_STEM_REGISTRY, dimensionRegistry, LevelStem.CODEC);

    }

    private static <T> void dumpRegistryCap(HashCache cache, Path path, RegistryAccess registryaccess, DynamicOps<JsonElement> dynamicops, RegistryAccess.RegistryData<T> registryData) {
        dumpRegistry(path, cache, dynamicops, registryData.key(), registryaccess.ownedRegistryOrThrow(registryData.key()), registryData.codec());
    }

    private Registry<LevelStem> registryDimension() {
        WritableRegistry<LevelStem> writableregistry = new MappedRegistry<>(Registry.LEVEL_STEM_REGISTRY, Lifecycle.experimental(), null);

        writableregistry.register(ResourceKey.create(Registry.LEVEL_STEM_REGISTRY,
                        DataGenDemo.modLoc("level_stem_demo")),
                new LevelStem(
                        Holder.direct(ModDimensionTypes.dimensionType),
                        ModNoiseBasedChunkGenerator.forestChunkGen)
                , Lifecycle.experimental());
        return writableregistry;
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

    private static Path createPath(Path path, ResourceLocation resourceLocation, ResourceLocation resourceLocation1) {
        return resolveTopPath(path).resolve(resourceLocation1.getNamespace()).resolve(resourceLocation.getPath()).resolve(resourceLocation1.getPath() + ".json");
    }

    protected static Path resolveTopPath(Path path) {
        return path.resolve("data");
    }

    @Override
    public String getName() {
        return "Worldgen:" + DataGenDemo.MODID;
    }
}
