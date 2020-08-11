package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.entities.EntityWeirdMob;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryEntity {

  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, Moddilities.MODID);

  public static final String WEIRD_MOB_NAME = "weird_mob";
  public static final RegistryObject<EntityType<EntityWeirdMob>> WEIRD_MOB_ENTITY =
      ENTITY_TYPES.register(WEIRD_MOB_NAME,
          () -> EntityType.Builder
              .create(EntityWeirdMob::new, EntityClassification.CREATURE)
              .size(1, 2)
              .setUpdateInterval(3)
              .setTrackingRange(16)
              .setShouldReceiveVelocityUpdates(false)
              .build(""));


}
