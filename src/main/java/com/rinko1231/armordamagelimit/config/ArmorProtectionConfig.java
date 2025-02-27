package com.rinko1231.armordamagelimit.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class ArmorProtectionConfig
{
    public static final ModConfigSpec SPEC;
    public static ModConfigSpec.DoubleValue maxArmorDurabilityLossPercent;
    public static ModConfigSpec.ConfigValue<List<? extends String>> itemProtectionBlacklist;

    static
    {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        BUILDER.push("Armor Damage Limit Config");

        maxArmorDurabilityLossPercent = BUILDER
                .defineInRange("Max Armor Durability Loss Percentage", 0.2,0.01,1.0);

        itemProtectionBlacklist = BUILDER
                .comment("Armor items that will not be protected")
                .defineList("Item Protection Blacklist", List.of(
                        "modA:armorB",
                        "modC:armorD"), () -> "", o -> (o instanceof String));

        SPEC = BUILDER.build();
    }
}

