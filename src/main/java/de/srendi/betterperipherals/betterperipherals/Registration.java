package de.srendi.betterperipherals.betterperipherals;

import com.google.common.collect.Sets;
import de.srendi.betterperipherals.betterperipherals.chatbox.Chatbox;
import de.srendi.betterperipherals.betterperipherals.chatbox.Chatbox_TileEntity;
import de.srendi.betterperipherals.betterperipherals.miner.Miner_TileEntity;
import de.srendi.betterperipherals.betterperipherals.serverperipheral.ServerPeripheral;
import de.srendi.betterperipherals.betterperipherals.serverperipheral.ServerPeripheral_TileEntity;
import de.srendi.betterperipherals.betterperipherals.timedetector.TimeDetector;
import de.srendi.betterperipherals.betterperipherals.timedetector.TimeDetector_TileEntity;
import de.srendi.betterperipherals.betterperipherals.weatherdetector.WeatherDetector;
import de.srendi.betterperipherals.betterperipherals.weatherdetector.WeatherDetector_TileEntity;
import de.srendi.betterperipherals.betterperipherals.playerinteractionblock.PIB;
import de.srendi.betterperipherals.betterperipherals.playerinteractionblock.PIB_TileEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.registries.Registries;


import java.util.function.Supplier;

public class Registration {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, betterperipherals.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, betterperipherals.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, betterperipherals.MODID);



    // Blocks
    public static final RegistryObject<Block> chat_box = register("chat_box", Chatbox::new);
    public static final RegistryObject<Block> weather_detector = register("weather_detector", WeatherDetector::new);
    public static final RegistryObject<Block> time_detector = register("time_detector", TimeDetector::new);
    public static final RegistryObject<Block> miner = register("miner", TimeDetector::new);
    public static final RegistryObject<Block> pib = register("pib", PIB::new);
    public static final RegistryObject<Block> server_peripheral = register("server_peripheral", ServerPeripheral::new);
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties()));
        return registryObject;
    }


    // Tile Entities
    public static final RegistryObject<BlockEntityType<Chatbox_TileEntity>> Chatbox_TileEntity = Registration.BLOCK_ENTITIES.register("chat_box", () -> new BlockEntityType<>(de.srendi.betterperipherals.betterperipherals.chatbox.Chatbox_TileEntity::new, Sets.newHashSet(chat_box.get()), null));
    public static final RegistryObject<BlockEntityType<WeatherDetector_TileEntity>> WeatherDetector_TileEntity = Registration.BLOCK_ENTITIES.register("weather_detector", () -> new BlockEntityType<>(de.srendi.betterperipherals.betterperipherals.weatherdetector.WeatherDetector_TileEntity::new, Sets.newHashSet(weather_detector.get()), null));
    public static final RegistryObject<BlockEntityType<TimeDetector_TileEntity>> TimeDetector_TileEntity = Registration.BLOCK_ENTITIES.register("time_detector", () -> new BlockEntityType<>(de.srendi.betterperipherals.betterperipherals.timedetector.TimeDetector_TileEntity::new, Sets.newHashSet(time_detector.get()), null));
    public static final RegistryObject<BlockEntityType<Miner_TileEntity>> Miner_TileEntity = Registration.BLOCK_ENTITIES.register("miner", () -> new BlockEntityType<>(de.srendi.betterperipherals.betterperipherals.miner.Miner_TileEntity::new, Sets.newHashSet(miner.get()), null));
    public static final RegistryObject<BlockEntityType<PIB_TileEntity>> PIB_TileEntity = Registration.BLOCK_ENTITIES.register("pib", () -> new BlockEntityType<>(de.srendi.betterperipherals.betterperipherals.playerinteractionblock.PIB_TileEntity::new, Sets.newHashSet(pib.get()), null));
    public static final RegistryObject<BlockEntityType<ServerPeripheral_TileEntity>> ServerPeripheral_TileEntity = Registration.BLOCK_ENTITIES.register("server_peripheral", () -> new BlockEntityType<>(de.srendi.betterperipherals.betterperipherals.serverperipheral.ServerPeripheral_TileEntity::new, Sets.newHashSet(server_peripheral.get()), null));




    // Register our stuff
    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
    }
}