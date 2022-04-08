package com.skyinr.datagendemo;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("DatagenDemo")
public class DatagenDemo {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public DatagenDemo() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
