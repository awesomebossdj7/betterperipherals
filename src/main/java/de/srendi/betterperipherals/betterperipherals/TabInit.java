package de.srendi.betterperipherals.betterperipherals;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.antlr.v4.codegen.model.ModelElement;

@Mod.EventBusSubscriber(modid = betterperipherals.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, betterperipherals.MODID);

    public static final RegistryObject<CreativeModeTab> BP_TAB = TabInit.CREATIVE_MODE_TABS.register("betterperipherals_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.betterperipherals"))
                    .icon(() -> new ItemStack(Registration.weather_detector.get()))
                    .displayItems((displayParms,output) ->{
                        output.accept(Registration.chat_box.get());
                        output.accept(Registration.weather_detector.get());
                        output.accept(Registration.time_detector.get());
                        output.accept(Registration.miner.get());
                        output.accept(Registration.pib.get());
                        output.accept(Registration.server_peripheral.get());
                    })
                    .build()
    );
}
