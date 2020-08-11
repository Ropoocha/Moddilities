package com.ropoocha.moddilities.model;

import com.google.common.collect.ImmutableList;
import com.ropoocha.moddilities.entities.EntityWeirdMob;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelWeirdMob extends SegmentedModel<EntityWeirdMob> {

  public ModelRenderer stick;
  public ModelRenderer cloth;

  public ModelWeirdMob() {
    stick = new ModelRenderer(this).setTextureSize(64, 64).setTextureOffset(0, 0);
    cloth = new ModelRenderer(this).setTextureSize(64, 64).setTextureOffset(8, 0);

    stick.addBox(-10, -16, -1, 2, 32, 2);
    stick.setRotationPoint(2, 8, 0);

    cloth.addBox(-8, -12, -0.5f, 14, 8, 1);
    cloth.setRotationPoint(2, 8, 0);
  }

  @Override
  public void setRotationAngles(EntityWeirdMob entityIn, float limbSwing, float limbSwingAmount,
      float ageInTicks, float netHeadYaw, float headPitch) {

  }

  @Override
  public Iterable<ModelRenderer> getParts() {
    return ImmutableList.of(stick, cloth);
  }
}
