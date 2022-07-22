package com.skyinr.datagendemo.item;

import com.mojang.math.Vector3d;
import com.mojang.math.Vector3f;
import com.skyinr.datagendemo.DataGenDemo;
import com.skyinr.datagendemo.block.ModBlocks;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DataGenDemo.MODID);
    public static final RegistryObject<Item> ITEM_DEMO = ITEMS.register("item_demo", () -> new Item(getDefaultProperties()));

//    public static final RegistryObject<Item> ITEM_DEMO_1 = ITEMS.register("item_demo_1", () -> new Item(getDefaultProperties()) {
//        @Override
//        public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
//            //实例化一个投掷物瓶子
//            ThrownPotion thrownPotion = new ThrownPotion(pLevel, pPlayer);
//            //设置投掷物瓶子的位置
//            thrownPotion.setPos(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ());
//
//            //抬头高度
//            Vec3 upVector = pPlayer.getUpVector(1.0F);
//            //方向
//            Vec3 look = pPlayer.getViewVector(1.0F);
//            Vector3f vector3f = new Vector3f(look);
//            vector3f.transform(new Vector3f(upVector).rotationDegrees(0));
//            //设置投掷物瓶子的方向与速度
//            thrownPotion.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 1.5F, 1.0F);
//            //往世界中添加投掷物瓶子
//            pLevel.addFreshEntity(thrownPotion);
//
//            return super.use(pLevel, pPlayer, pUsedHand);
//        }
//    });

    public static final RegistryObject<Item> BLOCK_DEMO = ITEMS.register("block_demo", () -> new BlockItem(ModBlocks.BLOCK_DEMO.get(), getDefaultProperties()));

    public static Item.Properties getDefaultProperties() {
        return new Item.Properties().tab(ModTab);
    }

    public static CreativeModeTab ModTab = new CreativeModeTab(DataGenDemo.MODID) {
        @Override
        @NotNull
        public ItemStack makeIcon() {
            return new ItemStack(ITEM_DEMO.get());
        }
    };


}
