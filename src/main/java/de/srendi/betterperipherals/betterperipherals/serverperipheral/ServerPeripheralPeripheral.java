package de.srendi.betterperipherals.betterperipherals.serverperipheral;

import com.mojang.brigadier.ParseResults;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Our peripheral class, this is the class where we will register functions for our block.
 */
public class ServerPeripheralPeripheral implements IPeripheral {

    /**
     * A list of all our connected computers. We need this for event usages.
     */
    private final List<IComputerAccess> connectedComputers = new ArrayList<>();

    /**
     * This is our tile entity, we set the tile entity when we create a new peripheral. We use this tile entity to access the block or the world
     */
    private final ServerPeripheral_TileEntity tileEntity;

    /**
     * @param tileEntity the tile entity of this peripheral
     */
    public ServerPeripheralPeripheral(ServerPeripheral_TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    /**
     * We use getType to set the name for our peripheral. A modem would wrap our block as "test_n"
     *
     * @return the name of our peripheral
     */
    @Nonnull
    @Override
    public String getType() {
        return "server_peripheral";
    }

    /**
     * CC use this method to check, if the peripheral in front of the modem is our peripheral
     */
    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        return this == iPeripheral;
    }

    /**
     * Will be called when a computer disconnects from our block
     */
    @Override
    public void detach(@Nonnull IComputerAccess computer) {
        connectedComputers.remove(computer);
    }

    /**
     * Will be called when a computer connects to our block
     */
    @Override
    public void attach(@Nonnull IComputerAccess computer) {
        connectedComputers.add(computer);
    }

    public ServerPeripheral_TileEntity getTileEntity() {
        return tileEntity;
    }

    @LuaFunction(mainThread = true)
    public final boolean isFlightAllowed() {return getTileEntity().getLevel().getServer().isFlightAllowed();}

    @LuaFunction(mainThread = true)
    public final boolean isSingleplayer() {return getTileEntity().getLevel().getServer().isSingleplayer();}

    @LuaFunction(mainThread = true)
    public final boolean isHardcore() {return getTileEntity().getLevel().getServer().isHardcore();}

    /** this is worthless!**/
    @LuaFunction(mainThread = true)
    public final boolean isDemo() {return getTileEntity().getLevel().getServer().isDemo();}

    @LuaFunction(mainThread = true)
    public final boolean isCommandBlockEnabled() {return getTileEntity().getLevel().getServer().isCommandBlockEnabled();}

    @LuaFunction(mainThread = true)
    public final boolean isNetherEnabled() {return getTileEntity().getLevel().getServer().isNetherEnabled();}

    @LuaFunction(mainThread = true)
    public final boolean isPvpAllowed() {return getTileEntity().getLevel().getServer().isPvpAllowed();}

    @LuaFunction(mainThread = true)
    public final int getMaxPlayers() {return getTileEntity().getLevel().getServer().getMaxPlayers();}

    @LuaFunction(mainThread = true)
    public final String getLocalIP() {return getTileEntity().getLevel().getServer().getLocalIp();}

    @LuaFunction(mainThread = true)
    public final String getServerVers() {return getTileEntity().getLevel().getServer().getServerVersion();}

    @LuaFunction(mainThread = true)
    public final boolean areNPCsEnabled() {return getTileEntity().getLevel().getServer().areNpcsEnabled();}

    /** this is pointless **/
    @LuaFunction(mainThread = false)
    public final String getModLoader() {return "Forge";}

    @LuaFunction
    public void runCommand(String cmd) {
        MinecraftServer server = getTileEntity().getLevel().getServer();
        server.getCommands().performPrefixedCommand(server.createCommandSourceStack(),cmd);
    }
}