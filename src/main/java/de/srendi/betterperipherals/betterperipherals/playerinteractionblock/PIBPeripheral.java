package de.srendi.betterperipherals.betterperipherals.playerinteractionblock;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.server.ServerLifecycleHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Our peripheral class, this is the class where we will register functions for our block.
 */
public class PIBPeripheral implements IPeripheral {

    /**
     * A list of all our connected computers. We need this for event usages.
     */
    private final List<IComputerAccess> connectedComputers = new ArrayList<>();

    /**
     * This is our tile entity, we set the tile entity when we create a new peripheral. We use this tile entity to access the block or the world
     */
    private final PIB_TileEntity tileEntity;

    /**
     * @param tileEntity the tile entity of this peripheral
     */
    public PIBPeripheral(PIB_TileEntity tileEntity) {
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
        return "player_interaction_block";
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

    public PIB_TileEntity getTileEntity() {
        return tileEntity;
    }

    /**
     * To register functions for our block, wee need to create final methods with the {@link LuaFunction} annotation
     * This function will send a message to every player on the Server
     *
     * @return
     */


    @LuaFunction(mainThread = true)
    public final void boost(String plr, int x, int y, int z) {
        ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(plr).hurtMarked = true;
        ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByName(plr).setDeltaMovement(x,y,z);
    }
}
