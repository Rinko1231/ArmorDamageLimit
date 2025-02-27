package com.rinko1231.armordamagelimit.config;

import com.rinko1231.armordamagelimit.ArmorDamageLimit;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;


public class ArmorProtectionConfig
{
    public static ModConfigSpec SPEC;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static ModConfigSpec.DoubleValue maxArmorDurabilityLossPercent;
    public static ModConfigSpec.ConfigValue<List<? extends String>> itemProtectionBlacklist;

    static
    {
        BUILDER.push("Config");

        maxArmorDurabilityLossPercent = BUILDER
                .defineInRange("Max Armor Durability Loss Percentage", 0.2,0.01,1);

        itemProtectionBlacklist = BUILDER
                .comment("Armor items that will not be protected")
                .defineList("Item Protection Blacklist", List.of("modA:armorB"),
                        element -> element instanceof String);

        SPEC = BUILDER.build();
    }

    public static void setup()
    {
        NeoForgeConfigRegistry.INSTANCE.register(ArmorDamageLimit.MOD_ID, ModConfig.Type.COMMON, SPEC);
    }
}