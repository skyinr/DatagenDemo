package com.skyinr.datagendemo.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.JsonOps;
import com.skyinr.datagendemo.DataGenDemo;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModWorldgenProvider implements DataProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    public static final Map<DeferredRegister<?>, Codec<?>> deferredRegisters = new HashMap<>();
    private final DataGenerator generator;

    public ModWorldgenProvider(DataGenerator dataGenerator) {
        generator = dataGenerator;
    }

    @Override
    public void run(HashCache cache) {

        Path path = this.generator.getOutputFolder();
        RegistryAccess registryaccess = RegistryAccess.BUILTIN.get();
        DynamicOps<JsonElement> dynamicops = RegistryOps.create(JsonOps.INSTANCE, registryaccess);
        deferredRegisters.forEach((deferredRegister, codec) -> dumpRegistry(path, cache, dynamicops, deferredRegister, codec));


    }

    @SuppressWarnings("all")
    protected static <T> void dumpRegistry(Path path, HashCache cache, DynamicOps<JsonElement> jsonElementDynamicOps, DeferredRegister<T> deferredRegister, Encoder encoder) {
        try {
            for (RegistryObject<T> entry : deferredRegister.getEntries()) {
                Path newPath = createPath(path, deferredRegister.getRegistryName(), entry.getId());
                dumpValue(newPath, cache, jsonElementDynamicOps, encoder, entry.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return path.resolve("reports").resolve("worldgen");
    }

    @Override
    public String getName() {
        return "Worldgen:" + DataGenDemo.MODID;
    }
}
