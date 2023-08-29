package com.skyinr.datagendemo.item;

import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = DataGenDemo.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DataGenDemo.MODID);
    public static final RegistryObject<Item> ITEM_DEMO = ITEMS.register("item_demo", () -> new Item(getDefaultProperties()));
    public static final RegistryObject<Item> BLOCK_DEMO = ITEMS.register("block_demo", () -> new BlockItem(ModBlocks.BLOCK_DEMO.get(), getDefaultProperties()));

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModTabs.DATAGEN_TAB.get()) {
            event.acceptAll(ITEMS.getEntries()
                    .stream()
                    .map(itemRegistryObject -> itemRegistryObject.get().getDefaultInstance())
                    .collect(Collectors.toSet()));
        }
    }

    public static Item.Properties getDefaultProperties() {
        return new Item.Properties();
    }


    public static class ModTabs {
        public static final String TAB_KEY = "itemGroup.datagen_tab";
        public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DataGenDemo.MODID);
        public static final RegistryObject<CreativeModeTab> DATAGEN_TAB = TABS.register("datagen_tab", () -> CreativeModeTab.builder()
                .icon(() -> ITEM_DEMO.get().getDefaultInstance())
                .title(Component.translatable(TAB_KEY))
                .build());
    }
}
