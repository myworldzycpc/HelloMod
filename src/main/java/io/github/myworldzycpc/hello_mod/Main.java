package io.github.myworldzycpc.hello_mod;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("hello_mod")
@Mod.EventBusSubscriber
public class Main {

    @SubscribeEvent
    public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        Level level = player.level;

        player.sendMessage(new TextComponent("Hi, " + player.getDisplayName().getString() + ". From " + (level.isClientSide() ? "CLIENT" : "SERVER") + "."), Util.NIL_UUID);
    }

    @SubscribeEvent
    public static void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getPlayer();
        player.sendMessage(new TextComponent("LeftCLickBlock is fired."), Util.NIL_UUID);
    }

    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getPlayer();
        player.sendMessage(new TextComponent("RightClickBlock is fired."), Util.NIL_UUID);
    }

    @SubscribeEvent
    public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getPlayer();
        Level level = player.level;
        if (!level.isClientSide()) {
            ItemStack itemStack = event.getItemStack();
            Item item = itemStack.getItem();
            if (item == Items.DIAMOND_SWORD){
                HitResult hitResult = player.pick(20, 0, false);
                Vec3 location = hitResult.getLocation();
                player.teleportTo(location.x, location.y, location.z);
            }
        }
    }
}
