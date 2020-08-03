package com.ropoocha.moddilities.blocks;

import com.ropoocha.moddilities.tileentities.TileDiamondGenerator;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockDiamondGenerator extends Block {

  public BlockDiamondGenerator() {
    super(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f));
  }

  public static Direction getFacingFromPlacer(BlockPos pos, LivingEntity placer) {
    return Direction.getFacingFromVector(
        (float) (placer.getPosX() - pos.getX()),
        (float) (placer.getPosY() - pos.getY()),
        (float) (placer.getPosZ() - pos.getZ()));
  }

  @Override
  protected void fillStateContainer(Builder<Block, BlockState> builder) {
    builder.add(BlockStateProperties.FACING);
  }

  @Override
  public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state,
      @Nullable LivingEntity placer, ItemStack stack) {
    if (placer != null) {
      worldIn.setBlockState(
          pos,
          state.with(BlockStateProperties.FACING, getFacingFromPlacer(pos, placer)),
          2);
    }
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
