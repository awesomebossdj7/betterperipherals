package de.srendi.betterperipherals.betterperipherals.playerinteractionblock;

import de.srendi.betterperipherals.betterperipherals.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;


import javax.annotation.Nullable;


/**
 * This is our block. To tell minecraft that this block has a block entity, we need to implement {@link EntityBlock}
 */
public class PIB extends Block implements EntityBlock {


    public PIB() {
        super(Properties.copy(Blocks.NETHERITE_BLOCK));
    }



    /**
     * This is the method from {@link EntityBlock} to create a new block entity for our block
     *
     * @return A new block entity from our registry object
     */
    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return Registration.PIB_TileEntity.get().create(pos, state);
    }
}
