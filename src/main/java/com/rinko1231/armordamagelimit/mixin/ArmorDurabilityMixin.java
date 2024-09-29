package com.rinko1231.armordamagelimit.mixin;

import com.rinko1231.armordamagelimit.config.ArmorProtectionConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public class ArmorDurabilityMixin {


    @Shadow @Final public NonNullList<ItemStack> armor;


    @Inject(method = "hurtArmor", at = @At("HEAD"), cancellable = true)
    private void modifyArmorDurability(DamageSource source, float amount, int[] slots, CallbackInfo ci) {
        if (!(amount <= 0.0F))
          {amount /= 4.0F;
            for (int i : slots) {
                // 获取护甲物品栈

                ItemStack armorItem = armor.get(i);
                if ((!source.is(DamageTypeTags.IS_FIRE) || !armorItem.getItem().isFireResistant()) && armorItem.getItem() instanceof ArmorItem)  {
                    String itemId = ForgeRegistries.ITEMS.getKey(armorItem.getItem()).toString();
                    if(!ArmorProtectionConfig.itemProtectionBlacklist.get().contains(itemId))
                    {
                      int maxDurability = armorItem.getMaxDamage();
                      float maxAllowedDamage = (float) (maxDurability * ArmorProtectionConfig.maxArmorDurabilityLossPercent.get());
                      amount = Math.min(amount, maxAllowedDamage);// 确保耐久损失不超过设定的最大值
                    }
                    if (amount < 1.0F)  {amount = 1.0F;}
                    armorItem.hurtAndBreak((int) amount, ((Inventory) (Object) this).player, (ThePlayer) ->
                            ThePlayer.broadcastBreakEvent(EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i)));
                }
              }
          }
        ci.cancel();
    }
}