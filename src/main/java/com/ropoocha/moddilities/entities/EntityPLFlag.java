package com.ropoocha.moddilities.entities;

import javax.annotation.Nullable;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public class EntityPLFlag extends AnimalEntity {

  public EntityPLFlag(
      EntityType<? extends AnimalEntity> type,
      World worldIn) {
    super(type, worldIn);
  }

  @Nullable
  @Override
  public AgeableEntity createChild(AgeableEntity ageable) {
    return null;
  }
}
