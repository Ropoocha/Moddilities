package com.ropoocha.moddilities.registries;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.containters.ContainerDiamondGenerator;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryContainer {

  public static final DeferredRegister<ContainerType<?>> CONTAINERS =
      DeferredRegister.create(ForgeRegistries.CONTAINERS, Moddilities.MODID);

  public static final RegistryObject<ContainerType<ContainerDiamondGenerator>> DIAMOND_GENERATOR_CONTAINER =
      CONTAINERS.register("diamond_generator",
          () -> IForgeContainerType.create(
              (windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new ContainerDiamondGenerator(windowId, pos, world, inv);
              }));
}
