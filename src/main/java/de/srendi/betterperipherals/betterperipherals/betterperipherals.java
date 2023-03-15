package de.srendi.betterperipherals.betterperipherals;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("betterperipherals")
public class betterperipherals {

    // Our mod id
    public static final String MODID = "betterperipherals";

    public betterperipherals() {
        Registration.register();
        // Register ourselves for server and other game events we are interested in. Currently, we do not use any events
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final CreativeModeTab BP_TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.time_detector.get());
        }
    };
}
