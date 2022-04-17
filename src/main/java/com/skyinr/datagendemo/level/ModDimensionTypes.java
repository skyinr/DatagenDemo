package com.skyinr.datagendemo.level;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.OptionalLong;

public class ModDimensionTypes {
    public static final DimensionType dimensionType = DimensionType.create(
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
            256,
            256,
            BlockTags.INFINIBURN_OVERWORLD,
            DimensionType.OVERWORLD_EFFECTS,
            0.0F
    );
}
