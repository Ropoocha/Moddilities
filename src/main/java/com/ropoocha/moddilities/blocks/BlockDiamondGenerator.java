package com.ropoocha.moddilities.blocks;

import com.ropoocha.moddilities.registries.RegistryBlock;
import com.ropoocha.moddilities.tileentities.TileDiamondGenerator;
import java.util.List;
import java.util.function.ToIntFunction;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class BlockDiamondGenerator extends Block {

  public BlockDiamondGenerator() {
    super(Properties.create(Material.IRON)
        .setRequiresTool()
        .hardnessAndResistance(5.0F, 6.0F)
        .sound(SoundType.METAL)
        .setLightLevel(b -> b.get(BlockStateProperties.POWERED) ? 14 : 0));

    this.setDefaultState(this.getStateContainer()
        .getBaseState()
        .with(BlockStateProperties.FACING, Direction.SOUTH)
        .with(BlockStateProperties.POWERED, false));
  }

  @Override
  protected void fillStateContainer(Builder<Block, BlockState> builder) {
    builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
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

  @Override
  public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
      PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
    if (!worldIn.isRemote) {
      TileEntity tileEntity = worldIn.getTileEntity(pos);
      if (tileEntity instanceof INamedContainerProvider) {
        NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity,
            tileEntity.getPos());
      }
    }
    return ActionResultType.SUCCESS;
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn,
      List<ITextComponent> tooltip, ITooltipFlag flagIn) {
    super.addInformation(stack, worldIn, tooltip, flagIn);

    CompoundNBT tag = stack.getChildTag("BlockEntityTag");
    if (tag != null) {
      if (tag.contains("energy")) {
        tooltip.add(new TranslationTextComponent("gui.diamond_generator.energy")
            .appendString(String.valueOf(tag.getInt("energy"))));
      }
      if (tag.contains("inv")) {
        ItemStackHandler handler = new ItemStackHandler();
        handler.deserializeNBT(tag.getCompound("inv"));
        tooltip.add(new TranslationTextComponent("tooltip.diamond_generator.left").appendString(String.valueOf(handler.getStackInSlot(0).getCount())));
      }
    }
  }
}
