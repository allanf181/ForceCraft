package mrbysco.forcecraft.blocks.infuser;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity; 
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class InfuserBlock extends Block {

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(3, 10, 3, 13, 11, 13),
            Block.makeCuboidShape(2, 0, 2, 4, 5, 4),
            Block.makeCuboidShape(12, 0, 2, 14, 5, 4),
            Block.makeCuboidShape(12, 0, 12, 14, 5, 14),
            Block.makeCuboidShape(2, 0, 12, 4, 5, 14),
            Block.makeCuboidShape(2, 5, 2, 14, 10, 14)
    ).reduce((v1, v2) -> { return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR); }).get();

    public InfuserBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new InfuserTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof InfuserTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) playerIn, (InfuserTileEntity) tileentity, pos);
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.CONSUME;
        } 
    }
}
