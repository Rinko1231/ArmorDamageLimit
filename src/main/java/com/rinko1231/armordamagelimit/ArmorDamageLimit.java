package com.rinko1231.armordamagelimit;

import com.rinko1231.armordamagelimit.config.ArmorProtectionConfig;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public class ArmorDamageLimit implements ModInitializer {
    public static final String MOD_ID = "armordamagelimit";

    @Override
    public void onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.register(MOD_ID, ModConfig.Type.COMMON, ArmorProtectionConfig.SPEC);
    }
}