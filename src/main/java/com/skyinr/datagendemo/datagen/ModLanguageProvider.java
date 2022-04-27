package com.skyinr.datagendemo.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    protected String modID;

    public ModLanguageProvider(DataGenerator gen, String modid, String locale) {
        super(gen, modid, locale);
        this.modID = modid;
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.datagendemo", "DataGen Demo");
        try {
            Registry.BLOCK.stream()
                    .filter(block -> modID.equals(Registry.BLOCK.getKey(block).getNamespace()))
                    .forEach(block -> addBlock(() -> block, block.getRegistryName().getPath()));
            Registry.ITEM.stream()
                    .filter(item -> modID.equals(Registry.ITEM.getKey(item).getNamespace()) && !(item instanceof BlockItem))
                    .forEach(item -> addItem(() -> item, item.getRegistryName().getPath()));
            Registry.ENCHANTMENT.stream()
                    .filter(enchantment -> modID.equals(Registry.ENCHANTMENT.getKey(enchantment).getNamespace()))
                    .forEach(enchantment -> addEnchantment(() -> enchantment, enchantment.getRegistryName().getPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
