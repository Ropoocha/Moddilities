package com.ropoocha.moddilities.geometries;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.ropoocha.moddilities.Moddilities;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.IModelTransform;
import net.minecraft.client.renderer.model.IUnbakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.model.ItemTransformVec3f;
import net.minecraft.client.renderer.model.ModelBakery;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.resources.IResourceManager;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

public class GeometryMimic implements IModelGeometry<GeometryMimic> {

  @Override
  public IBakedModel bake(IModelConfiguration owner, ModelBakery bakery,
      Function<RenderMaterial, TextureAtlasSprite> spriteGetter, IModelTransform modelTransform,
      ItemOverrideList overrides, ResourceLocation modelLocation) {
    return new BakedModel();
  }

  @Override
  public Collection<RenderMaterial> getTextures(IModelConfiguration owner,
      Function<ResourceLocation, IUnbakedModel> modelGetter,
      Set<Pair<String, String>> missingTextureErrors) {
    return Collections.singletonList(new RenderMaterial(AtlasTexture.LOCATION_BLOCKS_TEXTURE, GeometryMimic.BakedModel.TEXTURE));
  }

  public static class Loader implements IModelLoader<GeometryMimic> {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {

    }

    @Override
    public GeometryMimic read(JsonDeserializationContext deserializationContext,
        JsonObject modelContents) {
      return new GeometryMimic();
    }
  }

  public static class BakedModel implements IDynamicBakedModel {

    public static final ResourceLocation TEXTURE = new ResourceLocation("block/yellow_concrete");

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side,
        @Nonnull Random rand, @Nonnull IModelData extraData) {

      RenderType layer = MinecraftForgeClient.getRenderLayer();

      if (side != null) {
        return Collections.emptyList();
      }

      List<BakedQuad> quads = new ArrayList<>();
      TextureAtlasSprite texture = getTexture();

      double s = 3.0/16.0;
      double e = 13.0/16.0;
      double c = 13.0/32.0;
      double r = 19.0/32.0;

      // left segment - 5 quads
      quads.add(createQuad(v(s, c, c), v(s, c, r), v(s, r, r), v(s, r, c), texture, 4, 4, 12, 12)); // left
      quads.add(createQuad(v(s, c, c), v(s, r, c), v(c, r, c), v(c, c, c), texture, 4, 4, 12, 15)); // front
      quads.add(createQuad(v(s, c, r), v(c, c, r), v(c, r, r), v(s, r, r), texture, 4, 4, 12, 15)); // back
      quads.add(createQuad(v(s, r, c), v(s, r, r), v(c, r, r), v(c, r, c), texture, 4, 4, 12, 15)); // top
      quads.add(createQuad(v(s, c, c), v(c, c, c), v(c, c, r), v(s, c, r), texture, 4, 4, 12, 15)); // bottom

      // right segment - 5 quads
      quads.add(createQuad(v(e, c, c), v(e, r, c), v(e, r, r), v(e, c, r), texture, 4, 4, 12, 12)); // right
      quads.add(createQuad(v(r, c, c), v(r, r, c), v(e, r, c), v(e, c, c), texture, 4, 4, 12, 15)); // front
      quads.add(createQuad(v(r, c, r), v(e, c, r), v(e, r, r), v(r, r, r), texture, 4, 4, 12, 15)); // back
      quads.add(createQuad(v(r, r, c), v(r, r, r), v(e, r, r), v(e, r, c), texture, 4, 4, 12, 15)); // top
      quads.add(createQuad(v(r, c, c), v(e, c, c), v(e, c, r), v(r, c, r), texture, 4, 4, 12, 15)); // bottom

      // top segment - 5 quads
      quads.add(createQuad(v(r, r, c), v(r, e, c), v(r, e, r), v(r, r, r), texture, 4, 4, 12, 15)); // right
      quads.add(createQuad(v(c, r, c), v(c, r, r), v(c, e, r), v(c, e, c), texture, 4, 4, 12, 15)); // left
      quads.add(createQuad(v(c, r, c), v(c, e, c), v(r, e, c), v(r, r, c), texture, 4, 4, 12, 15)); // front
      quads.add(createQuad(v(c, r, r), v(r, r, r), v(r, e, r), v(c, e, r), texture, 4, 4, 12, 15)); // back
      quads.add(createQuad(v(c, e, c), v(c, e, r), v(r, e, r), v(r, e, c), texture, 4, 4, 12, 12)); // top

      // bottom segment - 5 quads
      quads.add(createQuad(v(r, c, c), v(r, c, r), v(r, s, r), v(r, s, c), texture, 4, 4, 12, 15)); // right
      quads.add(createQuad(v(c, c, c), v(c, s, c), v(c, s, r), v(c, c, r), texture, 4, 4, 12, 15)); // left
      quads.add(createQuad(v(c, c, c), v(r, c, c), v(r, s, c), v(c, s, c), texture, 4, 4, 12, 15)); // front
      quads.add(createQuad(v(c, c, r), v(c, s, r), v(r, s, r), v(r, c, r), texture, 4, 4, 12, 15)); // back
      quads.add(createQuad(v(c, s, c), v(r, s, c), v(r, s, r), v(c, s, r), texture, 4, 4, 12, 12)); // bottom

      // front segment - 5 quads
      quads.add(createQuad(v(r, c, c), v(r, c, s), v(r, r, s), v(r, r, c), texture, 4, 4, 12, 15)); // right
      quads.add(createQuad(v(c, c, c), v(c, r, c), v(c, r, s), v(c, c, s), texture, 4, 4, 12, 15)); // left
      quads.add(createQuad(v(c, c, s), v(c, r, s), v(r, r, s), v(r, c, s), texture, 4, 4, 12, 12)); // front
      quads.add(createQuad(v(c, r, s), v(c, r, c), v(r, r, c), v(r, r, s), texture, 4, 4, 12, 15)); // top
      quads.add(createQuad(v(c, c, s), v(r, c, s), v(r, c, c), v(c, c, c), texture, 4, 4, 12, 15)); // bottom

      // back segment - 5 quads
      quads.add(createQuad(v(r, c, r), v(r, r, r), v(r, r, e), v(r, c, e), texture, 4, 4, 12, 15)); // right
      quads.add(createQuad(v(c, r, r), v(c, c, r), v(c, c, e), v(c, r, e), texture, 4, 4, 12, 15)); // left
      quads.add(createQuad(v(c, r, e), v(c, c, e), v(r, c, e), v(r, r, e), texture, 4, 4, 12, 12)); // back
      quads.add(createQuad(v(r, r, r), v(c, r, r), v(c, r, e), v(r, r, e), texture, 4, 4, 12, 15)); // top
      quads.add(createQuad(v(c, c, r), v(r, c, r), v(r, c, e), v(c, c, e), texture, 4, 4, 12, 15)); // bottom

      return quads;
    }

    private TextureAtlasSprite getTexture() {
      return Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(TEXTURE);
    }

    private void putVertex(BakedQuadBuilder builder, Vector3d normal, Vector3d vertex, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {

      ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
      for (int j = 0; j < elements.size(); j++) {
        VertexFormatElement element = elements.get(j);

        switch (element.getUsage()) {
          case POSITION:
            builder.put(j, (float) vertex.x, (float) vertex.y, (float) vertex.z, 1.0f);
            break;
          case COLOR:
            builder.put(j, r, g, b, 1.0f);
            break;
          case NORMAL:
            builder.put(j, (float) normal.x, (float) normal.y, (float) normal.z, 1.0f);
            break;
          case UV:
            switch (element.getIndex()) {
              case 0:
                float iu = sprite.getInterpolatedU(u);
                float iv = sprite.getInterpolatedV(v);
                builder.put(j, iu, iv);
                break;
              case 2:
                builder.put(j, (short) 0, (short) 0);
                break;
              default:
                builder.put(j);
                break;
            }
            break;
          default:
            builder.put(j);
            break;
        }
      }
    }

    private Vector3d v(double x, double y, double z) {
      return new Vector3d(x, y, z);
    }

    private BakedQuad createQuad(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite) {
      int tw = sprite.getWidth();
      int th = sprite.getHeight();

      return this.createQuad(v1, v2, v3, v4, sprite, 0, 0, tw, th);
    }

    private BakedQuad createQuad(Vector3d v1, Vector3d v2, Vector3d v3, Vector3d v4, TextureAtlasSprite sprite, int startX, int startY, int endX, int endY) {
      BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
      Vector3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();

      builder.setQuadOrientation(Direction.getFacingFromVector(normal.x, normal.y, normal.z));
      putVertex(builder, normal, v1, startX, startY, sprite, 1.0f, 1.0f, 1.0f);
      putVertex(builder, normal, v2, startX, endY, sprite, 1.0f, 1.0f, 1.0f);
      putVertex(builder, normal, v3, endX, endY, sprite, 1.0f, 1.0f, 1.0f);
      putVertex(builder, normal, v4, endX, startY, sprite, 1.0f, 1.0f, 1.0f);

      return builder.build();
    }

    @Override
    public boolean isAmbientOcclusion() {
      return true;
    }

    @Override
    public boolean isGui3d() {
      return false;
    }

    @Override
    public boolean func_230044_c_() {
      return false;
    }

    @Override
    public boolean isBuiltInRenderer() {
      return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
      return getTexture();
    }

    @Override
    public ItemOverrideList getOverrides() {
      return ItemOverrideList.EMPTY;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
      ItemTransformVec3f tpLeft = this.getTransform(TransformType.THIRD_PERSON_LEFT_HAND);
      ItemTransformVec3f tpRight = this.getTransform(TransformType.THIRD_PERSON_RIGHT_HAND);
      ItemTransformVec3f fpLeft = this.getTransform(TransformType.FIRST_PERSON_LEFT_HAND);
      ItemTransformVec3f fpRight = this.getTransform(TransformType.FIRST_PERSON_RIGHT_HAND);
      ItemTransformVec3f head = this.getTransform(TransformType.HEAD);
      ItemTransformVec3f gui = this.getTransform(TransformType.GUI);
      ItemTransformVec3f ground = this.getTransform(TransformType.GROUND);
      ItemTransformVec3f fixed = this.getTransform(TransformType.FIXED);
      return new ItemCameraTransforms(tpLeft, tpRight, fpLeft, fpRight, head, gui, ground, fixed);
    }

    private ItemTransformVec3f getTransform(ItemCameraTransforms.TransformType type) {
      if (type.equals(TransformType.GUI)) {
        return new ItemTransformVec3f(new Vector3f(30, 225 ,0), new Vector3f(), new Vector3f(1, 1, 1));
      } else if (type.equals(TransformType.GROUND)) {
        return new ItemTransformVec3f(new Vector3f(), new Vector3f(0, 3.0f/16f, 0), new Vector3f(0.4f, 0.4f, 0.4f));
      } else if (type.equals(TransformType.FIXED)) {
        return new ItemTransformVec3f(new Vector3f(), new Vector3f(), new Vector3f(0.8f, 0.8f, 0.8f));
      } else if (type.equals(TransformType.THIRD_PERSON_RIGHT_HAND)) {
        return new ItemTransformVec3f(new Vector3f(75, 45, 0), new Vector3f(0, 2.5f/16f, 0), new Vector3f(0.6f, 0.6f, 0.6f));
      } else if (type.equals(TransformType.FIRST_PERSON_RIGHT_HAND)) {
        return new ItemTransformVec3f(new Vector3f(0, 45, 0), new Vector3f(), new Vector3f(0.64f, 0.64f, 0.64f));
      } else if (type.equals(TransformType.FIRST_PERSON_LEFT_HAND)) {
        return new ItemTransformVec3f(new Vector3f(0, 225, 0), new Vector3f(), new Vector3f(0.64f, 0.64f, 0.64f));
      }
      return ItemTransformVec3f.DEFAULT;
    }


  }
}
