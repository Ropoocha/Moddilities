package com.ropoocha.moddilities.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.ropoocha.moddilities.Moddilities;
import com.ropoocha.moddilities.containters.ContainerDiamondGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ScreenDiamondGenerator extends ContainerScreen<ContainerDiamondGenerator> {

  private final ResourceLocation GUI =
      new ResourceLocation(Moddilities.MODID, "textures/gui/diamond_generator_gui.png");

  public ScreenDiamondGenerator(
      ContainerDiamondGenerator screenContainer,
      PlayerInventory inv,
      ITextComponent titleIn) {
    super(screenContainer, inv, titleIn);
  }

  @Override
  public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    this.renderBackground(matrixStack);
    super.render(matrixStack, mouseX, mouseY, partialTicks);
    this.func_230459_a_(matrixStack, mouseX, mouseY);
  }

  @Override
  protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
    drawString(matrixStack,
        Minecraft.getInstance().fontRenderer,
        new TranslationTextComponent("gui.diamond_generator.energy").getString() +
            container.getEnergy(),
        10,
        10,
        0xffffff);
  }

  @Override
  protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x,
      int y) {
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    getMinecraft().getTextureManager().bindTexture(GUI);
    int relX = (this.width - this.xSize) / 2;
    int relY = (this.height - this.ySize) / 2;
    this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
  }
}
