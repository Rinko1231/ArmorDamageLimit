package com.rinko1231.armordamagelimit;

import com.rinko1231.armordamagelimit.config.ArmorProtectionConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(ArmorDamageLimit.MOD_ID)
public class ArmorDamageLimit {
    public static final String MOD_ID = "armordamagelimit";

    public ArmorDamageLimit(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ArmorProtectionConfig.SPEC,"ArmorDamageLimit.toml");
    }

}