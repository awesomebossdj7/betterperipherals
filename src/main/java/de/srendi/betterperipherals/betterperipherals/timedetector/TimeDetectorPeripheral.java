package de.srendi.betterperipherals.betterperipherals.timedetector;

import com.sun.jna.WString;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import de.srendi.betterperipherals.betterperipherals.timedetector.TimeDetector_TileEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Our peripheral class, this is the class where we will register functions for our block.
 */
public class TimeDetectorPeripheral implements IPeripheral {

    /**
     * A list of all our connected computers. We need this for event usages.
     */
    private final List<IComputerAccess> connectedComputers = new ArrayList<>();

    /**
     * This is our tile entity, we set the tile entity when we create a new peripheral. We use this tile entity to access the block or the world
     */
    private final TimeDetector_TileEntity tileEntity;

    /**
     * @param tileEntity the tile entity of this peripheral
     */
    public TimeDetectorPeripheral(TimeDetector_TileEntity tileEntity) {
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
        return "time_detector";
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

    public TimeDetector_TileEntity getTileEntity() {
        return tileEntity;
    }

    @LuaFunction(mainThread = true)
    public final boolean isDay() {return getTileEntity().getLevel().isDay();}

    @LuaFunction(mainThread = true)
    public final boolean isNight() {return getTileEntity().getLevel().isNight();}

    @LuaFunction(mainThread = true)
    public final int getGameTime() {return (int) getTileEntity().getLevel().getGameTime();}

    @LuaFunction(mainThread = true)
    public final int getTime() {return (int) getTileEntity().getLevel().getDayTime();}

    }
