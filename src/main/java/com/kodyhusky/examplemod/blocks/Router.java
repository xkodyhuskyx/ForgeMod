package com.kodyhusky.examplemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class Router extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(2, 0, 12, 14, 2, 14), Block.makeCuboidShape(11, 0, 2, 14, 2, 4),
            Block.makeCuboidShape(2, 0, 2, 5, 2, 4), Block.makeCuboidShape(5, 0, 2.2, 11, 1.7999999999999998, 4),
            Block.makeCuboidShape(12, 0, 4, 13.8, 1.7999999999999998, 12), Block.makeCuboidShape(2.1999999999999993, 0, 4, 4, 1.7999999999999998, 12),
            Block.makeCuboidShape(3, 1.7999999999999998, 3, 13, 2.2, 13), Block.makeCuboidShape(4.199999999999999, 1, 14, 4.699999999999999, 1.5, 14.5),
            Block.makeCuboidShape(11.2, 1, 14, 11.7, 1.5, 14.5), Block.makeCuboidShape(11, 1, 14.5, 12, 6, 15.5),
            Block.makeCuboidShape(11.1, 6, 14.5, 11.9, 9, 15.5), Block.makeCuboidShape(4.1, 6, 14.5, 4.9, 9, 15.5),
            Block.makeCuboidShape(4, 1, 14.5, 5, 6, 15.5), Block.makeCuboidShape(9.3, 1.8100000000000005, 2.8, 9.5, 2.01, 3),
            Block.makeCuboidShape(8.3, 1.8100000000000005, 2.8, 8.5, 2.01, 3), Block.makeCuboidShape(7.300000000000001, 1.8100000000000005, 2.8, 7.5, 2.01, 3),
            Block.makeCuboidShape(6.300000000000001, 1.8100000000000005, 2.8, 6.5, 2.01, 3), Block.makeCuboidShape(2, -0.009999999999999787, 2, 14, 0, 14)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(2, 0, 2, 4, 2, 14), Block.makeCuboidShape(12, 0, 11, 14, 2, 14),
            Block.makeCuboidShape(12, 0, 2, 14, 2, 5), Block.makeCuboidShape(12, 0, 5, 13.8, 1.7999999999999998, 11),
            Block.makeCuboidShape(4, 0, 12, 12, 1.7999999999999998, 13.8), Block.makeCuboidShape(4, 0, 2.1999999999999993, 12, 1.7999999999999998, 4),
            Block.makeCuboidShape(3, 1.7999999999999998, 3, 13, 2.2, 13), Block.makeCuboidShape(1.5, 1, 4.199999999999999, 2, 1.5, 4.699999999999999),
            Block.makeCuboidShape(1.5, 1, 11.2, 2, 1.5, 11.7), Block.makeCuboidShape(0.5, 1, 11, 1.5, 6, 12),
            Block.makeCuboidShape(0.5, 6, 11.1, 1.5, 9, 11.9), Block.makeCuboidShape(0.5, 6, 4.1, 1.5, 9, 4.9),
            Block.makeCuboidShape(0.5, 1, 4, 1.5, 6, 5), Block.makeCuboidShape(13, 1.8100000000000005, 9.3, 13.2, 2.01, 9.5),
            Block.makeCuboidShape(13, 1.8100000000000005, 8.3, 13.2, 2.01, 8.5), Block.makeCuboidShape(13, 1.8100000000000005, 7.300000000000001, 13.2, 2.01, 7.5),
            Block.makeCuboidShape(13, 1.8100000000000005, 6.300000000000001, 13.2, 2.01, 6.5), Block.makeCuboidShape(2, -0.009999999999999787, 2, 14, 0, 14)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(12, 0, 2, 14, 2, 14), Block.makeCuboidShape(2, 0, 2, 4, 2, 5),
            Block.makeCuboidShape(2, 0, 11, 4, 2, 14), Block.makeCuboidShape(2.1999999999999993, 0, 5, 4, 1.7999999999999998, 11),
            Block.makeCuboidShape(4, 0, 2.1999999999999993, 12, 1.7999999999999998, 4), Block.makeCuboidShape(4, 0, 12, 12, 1.7999999999999998, 13.8),
            Block.makeCuboidShape(3, 1.7999999999999998, 3, 13, 2.2, 13), Block.makeCuboidShape(14, 1, 11.3, 14.5, 1.5, 11.8),
            Block.makeCuboidShape(14, 1, 4.300000000000001, 14.5, 1.5, 4.800000000000001), Block.makeCuboidShape(14.5, 1, 4, 15.5, 6, 5),
            Block.makeCuboidShape(14.5, 6, 4.1, 15.5, 9, 4.9), Block.makeCuboidShape(14.5, 6, 11.1, 15.5, 9, 11.9),
            Block.makeCuboidShape(14.5, 1, 11, 15.5, 6, 12), Block.makeCuboidShape(2.8000000000000007, 1.8100000000000005, 6.5, 3, 2.01, 6.699999999999999),
            Block.makeCuboidShape(2.8000000000000007, 1.8100000000000005, 7.5, 3, 2.01, 7.699999999999999), Block.makeCuboidShape(2.8000000000000007, 1.8100000000000005, 8.5, 3, 2.01, 8.7),
            Block.makeCuboidShape(2.8000000000000007, 1.8100000000000005, 9.5, 3, 2.01, 9.7), Block.makeCuboidShape(2, -0.009999999999999787, 2, 14, 0, 14)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(2, 0, 2, 14, 2, 4), Block.makeCuboidShape(2, 0, 12, 5, 2, 14),
            Block.makeCuboidShape(11, 0, 12, 14, 2, 14), Block.makeCuboidShape(5, 0, 12, 11, 1.7999999999999998, 13.8),
            Block.makeCuboidShape(2.1999999999999993, 0, 4, 4, 1.7999999999999998, 12), Block.makeCuboidShape(12, 0, 4, 13.8, 1.7999999999999998, 12),
            Block.makeCuboidShape(3, 1.7999999999999998, 3, 13, 2.2, 13), Block.makeCuboidShape(11.3, 1, 1.5, 11.8, 1.5, 2),
            Block.makeCuboidShape(4.300000000000001, 1, 1.5, 4.800000000000001, 1.5, 2), Block.makeCuboidShape(4, 1, 0.5, 5, 6, 1.5),
            Block.makeCuboidShape(4.1, 6, 0.5, 4.9, 9, 1.5), Block.makeCuboidShape(11.1, 6, 0.5, 11.9, 9, 1.5),
            Block.makeCuboidShape(11, 1, 0.5, 12, 6, 1.5), Block.makeCuboidShape(6.5, 1.8100000000000005, 13, 6.699999999999999, 2.01, 13.2),
            Block.makeCuboidShape(7.5, 1.8100000000000005, 13, 7.699999999999999, 2.01, 13.2), Block.makeCuboidShape(8.5, 1.8100000000000005, 13, 8.7, 2.01, 13.2),
            Block.makeCuboidShape(9.5, 1.8100000000000005, 13, 9.7, 2.01, 13.2), Block.makeCuboidShape(2, -0.009999999999999787, 2, 14, 0, 14)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    public Router() {
        super(Block.Properties.create(Material.ROCK)
                .hardnessAndResistance(1, 1)
                .sound(SoundType.STONE)
                .setLightLevel(value -> 3));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            case SOUTH:
                return SHAPE_S;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.6f;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
