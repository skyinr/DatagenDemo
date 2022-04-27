package com.skyinr.datagendemo.level;

import com.mojang.serialization.Lifecycle;
import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.datagen.ModWorldgenProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class ModDimensionTypes {
    public static final Holder<DimensionType> DIMENSION_TYPE_HOLDER;
    public static final WritableRegistry<DimensionType> DIMENSION_TYPE_REGISTRY = ModWorldgenProvider.registrable.ownedWritableRegistryOrThrow(Registry.DIMENSION_TYPE_REGISTRY);
    public static final ResourceKey<DimensionType> DIMENSION_TYPE_KEY = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, DataGenDemo.modLoc("skyinr_dimension"));
    public static final DimensionType DIMENSION_TYPE = DimensionType.create(
            OptionalLong.empty(),
            true,
            false,
            false,
            true,
            1.0D,
            false,
            true,
            false,
            true,
            true,
            -64,
            384,
            384,
            BlockTags.INFINIBURN_OVERWORLD,
            DimensionType.OVERWORLD_EFFECTS,
            0.0F
    );

    static {
        DIMENSION_TYPE_HOLDER = DIMENSION_TYPE_REGISTRY.register(DIMENSION_TYPE_KEY, DIMENSION_TYPE, Lifecycle.stable());
    }
}
