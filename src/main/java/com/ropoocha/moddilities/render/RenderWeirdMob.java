package com.ropoocha.moddilities.render;

import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.entities.EntityWeirdMob;
import com.ropoocha.moddilities.model.ModelWeirdMob;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderWeirdMob extends MobRenderer<EntityWeirdMob, ModelWeirdMob> {

  private static final ResourceLocation TEXTURE = new ResourceLocation(Moddilities.MODID,
      "textures/entity/weirdmob.png");

  public RenderWeirdMob(EntityRendererManager renderManagerIn) {
    super(renderManagerIn, new ModelWeirdMob(), 0.5f);
  }

  @Override
  public ResourceLocation getEntityTexture(EntityWeirdMob entity) {
    return TEXTURE;
  }
}
