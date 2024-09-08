package boxxed.create_trimmings.mixin;

import boxxed.create_trimmings.Trimmings;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.foundation.mixin.accessor.HumanoidArmorLayerAccessor;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
@OnlyIn(Dist.CLIENT)
public class HumanoidArmorLayerMixin {
    @Unique
    private static LivingEntity trimmings$livingEntity;

    @Inject(method = "renderArmorPiece", at = @At("HEAD"), remap = false)
    private void trimmings$getLivingEntity(PoseStack pPoseStack, MultiBufferSource pBuffer, LivingEntity pLivingEntity, EquipmentSlot pSlot, int pPackedLight, HumanoidModel pModel, CallbackInfo ci){
        trimmings$livingEntity = pLivingEntity;
    }
    @Inject(method = "setPartVisibility", at = @At("TAIL"), remap = false)
    private void trimmings$hideArmsWhenBacktank(HumanoidModel pModel, EquipmentSlot pSlot, CallbackInfo ci) {
        if (trimmings$livingEntity != null) {
            ItemStack stack = trimmings$livingEntity.getItemBySlot(pSlot);
            Item item = stack.getItem();
            if (item instanceof BacktankItem) {
                if (pSlot == EquipmentSlot.CHEST) {
                    pModel.rightArm.visible = false;
                    pModel.leftArm.visible = false;
                }
            }
        }
    }

}
