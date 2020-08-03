package com.ropoocha.moddilities.blocks;

import com.ropoocha.moddilities.tileentities.TileDiamondGenerator;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockDiamondGenerator extends Block {

  public BlockDiamondGenerator() {
    super(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f));
    this.setDefaultState(
        this.getStateContainer().getBaseState().with(BlockStateProperties.FACING, Direction.SOUTH));
  }

  @Override
  protected void fillStateContainer(Builder<Block, BlockState> builder) {
    builder.add(BlockStateProperties.FACING);
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockItemUseContext context) {
    return this.getDefaultState()
        .with(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

  @Nullable
  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new TileDiamondGenerator();
  }
}
