package com.rinko1231.armordamagelimit.config;

import com.rinko1231.armordamagelimit.ArmorDamageLimit;
import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;

public class ArmorProtectionConfig
{
    public static ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec.DoubleValue maxArmorDurabilityLossPercent;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> itemProtectionBlacklist;

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
        ForgeConfigRegistry.INSTANCE.register(ArmorDamageLimit.MOD_ID, ModConfig.Type.COMMON, SPEC);
    }
}