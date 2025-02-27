package com.rinko1231.armordamagelimit.mixin;

import com.rinko1231.armordamagelimit.config.ArmorProtectionConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class ArmorDurabilityMixin extends LivingEntity {
    protected ArmorDurabilityMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "hurtArmor", at = @At("HEAD"), cancellable = true)
    private void modifyHurtArmor(DamageSource damageSource, float damage, CallbackInfo ci) {
        if (!(damage <= 0.0F)) {

            EquipmentSlot[] slots = {EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD};
            for (EquipmentSlot slot : slots) {
                ItemStack itemStack = this.getItemBySlot(slot);
                String itemId = BuiltInRegistries.ITEM.getKey(itemStack.getItem()).toString();
                if (!itemStack.isEmpty()  && !ArmorProtectionConfig.itemProtectionBlacklist.get().contains(itemId)) {
                    int maxDurability = itemStack.getMaxDamage();
                    float cappedDamage = Math.min(damage, maxDurability * (float) ArmorProtectionConfig.maxArmorDurabilityLossPercent.getAsDouble() * 4) ;


                    this.doHurtEquipment(damageSource, cappedDamage, slot);
                }
            }
        }
        ci.cancel();
    }
}