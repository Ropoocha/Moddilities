package com.ropoocha.moddilities.blocks;

import com.ropoocha.moddilities.tileentities.TileMimic;
import javax.annotation.Nullable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockMimic extends Block {

  public BlockMimic() {
    super(AbstractBlock.Properties.create(Material.WOOD).sound(SoundType.WOOD)
        .hardnessAndResistance(0.5f).setLightLevel((blockState) -> 14).notSolid());
  }

  @Override
  public boolean hasTileEntity(BlockState state) {
    return true;
  }

  @Nullable
  @Override
  public TileEntity createTileEntity(BlockState state, IBlockReader world) {
    return new TileMimic();
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos,
      ISelectionContext context) {

    double s = 3.0;
    double e = 13.0;
    double c = 13.0/2.0;
    double r = 19.0/2.0;


    VoxelShape width = Block.makeCuboidShape(s, c, c, e, r, r);
    VoxelShape height = Block.makeCuboidShape(c, s, c, r, e, r);
    VoxelShape depth = Block.makeCuboidShape(c, c, s, r, r, e);

    return VoxelShapes.or(width, height, depth);
  }
}
