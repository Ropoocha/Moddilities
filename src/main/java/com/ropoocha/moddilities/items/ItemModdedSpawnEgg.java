package com.ropoocha.moddilities.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ItemModdedSpawnEgg extends SpawnEggItem {

  protected static final List<ItemModdedSpawnEgg> UNADDED_EGGS = new ArrayList<>();
  private final Lazy<? extends EntityType<?>> entityTypeSupplier;

  public ItemModdedSpawnEgg(NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn, Properties builder) {
    super(null, primaryColorIn, secondaryColorIn, builder);

    this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
    UNADDED_EGGS.add(this);
  }

  public ItemModdedSpawnEgg(RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn, Properties builder) {
    super(null, primaryColorIn, secondaryColorIn, builder);

    this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
    UNADDED_EGGS.add(this);
  }

  public static void initUnaddedEggs() {
    final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "EGGS");
    DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior() {
      @Override
      protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
        Direction direction = source.getBlockState().get(DispenserBlock.FACING);
        EntityType<?> entityType = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
        entityType.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
        stack.shrink(1);
        return stack;
      }
    };

    for (final SpawnEggItem egg : UNADDED_EGGS) {
      EGGS.put(egg.getType(null), egg);
      DispenserBlock.registerDispenseBehavior(egg, defaultDispenseItemBehavior);
    }
    UNADDED_EGGS.clear();
  }

  @Override
  public EntityType<?> getType(@Nullable CompoundNBT p_208076_1_) {
    return entityTypeSupplier.get();
  }
}
