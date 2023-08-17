package de.srendi.betterperipherals.betterperipherals;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("betterperipherals")
public class betterperipherals {

    // Our mod id
    public static final String MODID = "betterperipherals";

    public betterperipherals() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.register();

        // Register ourselves for server and other game events we are interested in. Currently, we do not use any events
        MinecraftForge.EVENT_BUS.register(this);
        TabInit.CREATIVE_MODE_TABS.register(bus);

    }
}
