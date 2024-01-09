package com.sammy.malum.client.renderer.block;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import com.sammy.malum.client.renderer.entity.*;
import com.sammy.malum.common.block.curiosities.spirit_crucible.*;
import com.sammy.malum.common.item.catalyzer_augment.*;
import com.sammy.malum.common.item.spirit.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.phys.*;
import org.joml.*;
import team.lodestar.lodestone.systems.blockentity.*;

import java.lang.*;
import java.lang.Math;

import static net.minecraft.client.renderer.texture.OverlayTexture.*;


public class SpiritCrucibleRenderer implements BlockEntityRenderer<SpiritCrucibleCoreBlockEntity> {
    public SpiritCrucibleRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SpiritCrucibleCoreBlockEntity blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        LodestoneBlockEntityInventory inventory = blockEntityIn.spiritInventory;
        int spiritsRendered = 0;
        if (!inventory.isEmpty()) {
            for (int i = 0; i < inventory.slotCount; i++) {
                ItemStack item = inventory.getStackInSlot(i);
                if (item.getItem() instanceof SpiritShardItem shardItem) {
                    poseStack.pushPose();
                    Vector3f offset = blockEntityIn.getSpiritItemOffset(spiritsRendered++, partialTicks).toVector3f();
                    poseStack.translate(offset.x(), offset.y(), offset.z());
                    FloatingItemEntityRenderer.renderSpiritGlimmer(poseStack, shardItem.type, partialTicks);
                    poseStack.mulPose(Axis.YP.rotationDegrees(((level.getGameTime() % 360) + partialTicks) * 3));
                    poseStack.scale(0.5f, 0.5f, 0.5f);
                    itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, level, 0);
                    poseStack.popPose();
                }
            }
        }
        ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
        if (!stack.isEmpty()) {
            poseStack.pushPose();
            Vec3 offset = blockEntityIn.getCentralItemOffset();
            poseStack.translate(offset.x, offset.y, offset.z);
            poseStack.mulPose(Axis.YP.rotationDegrees(((level.getGameTime() % 360) + partialTicks) * 3));
            poseStack.scale(0.45f, 0.45f, 0.45f);
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, level, 0);
            poseStack.popPose();
        }
        final LodestoneBlockEntityInventory augmentInventory = blockEntityIn.augmentInventory;

        int augmentsRendered = 0;
        if (!augmentInventory.isEmpty()) {
            float total = augmentInventory.nonEmptyItemAmount;
            float time = 240;
            for (int i = 0; i < augmentInventory.slotCount; i++) {
                ItemStack item = augmentInventory.getStackInSlot(i);
                if (item.getItem() instanceof AbstractAugmentItem) {
                    double angle = augmentsRendered / total * (Math.PI * 2);
                    angle -= (((long) (blockEntityIn.spiritSpin + partialTicks) % time) / time) * (Math.PI * 2);
                    poseStack.pushPose();
                    Vector3f offset = blockEntityIn.getAugmentItemOffset(augmentsRendered++, partialTicks).toVector3f();
                    poseStack.translate(offset.x(), offset.y(), offset.z());
                    poseStack.mulPose(Axis.YP.rotation((float) angle-(1.57f)));
                    poseStack.scale(0.5f, 0.5f, 0.5f);
                    itemRenderer.renderStatic(item, ItemDisplayContext.FIXED, combinedLightIn, NO_OVERLAY, poseStack, bufferIn, level, 0);
                    poseStack.popPose();
                }
            }
        }
    }
}
