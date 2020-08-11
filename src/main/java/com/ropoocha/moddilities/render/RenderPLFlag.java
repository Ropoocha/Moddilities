package com.ropoocha.moddilities.render;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.entities.EntityPLFlag;
import com.ropoocha.moddilities.model.ModelPLFlag;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPLFlag extends MobRenderer<EntityPLFlag, ModelPLFlag> {

  private static final ResourceLocation TEXTURE = new ResourceLocation(Moddilities.MODID,
      "textures/entity/polish_flag.png");

  public RenderPLFlag(EntityRendererManager renderManagerIn) {
    super(renderManagerIn, new ModelPLFlag(), 0.5f);
  }

  @Override
  public ResourceLocation getEntityTexture(EntityPLFlag entity) {
    return TEXTURE;
  }
}
