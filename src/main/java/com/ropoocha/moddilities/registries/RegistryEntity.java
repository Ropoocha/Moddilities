package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.entities.EntityPLFlag;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryEntity {

  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, Moddilities.MODID);

  public static final String POLISH_FLAG_NAME = "polish_flag";
  public static final RegistryObject<EntityType<EntityPLFlag>> POLISH_FLAG_ENTITY =
      ENTITY_TYPES.register(POLISH_FLAG_NAME,
          () -> EntityType.Builder
              .create(EntityPLFlag::new, EntityClassification.CREATURE)
              .size(1, 2)
              .setShouldReceiveVelocityUpdates(false)
              .build(""));


}
