package boxxed.create_trimmings.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.equipment.armor.BacktankArmorLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BacktankArmorLayer.class)
public class BacktankArmorLayerMixin {
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",at=@At("TAIL"),remap = false)
    private void trimmings$renderTrim(PoseStack ms, MultiBufferSource buffer, int light, LivingEntity entity, float yaw,
                                      float pitch, float pt, float p_225628_8_, float p_225628_9_, float p_225628_10_,
                                      CallbackInfo ci) {

    }
}
