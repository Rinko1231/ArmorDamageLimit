package com.rinko1231.armordamagelimit;

import fuzs.forgeconfigscreens.client.helper.ConfigScreenHelper;
import net.fabricmc.api.ClientModInitializer;

public class ArmorDamageLimitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ConfigScreenHelper.createConfigScreen(ArmorDamageLimit.MOD_ID);
    }
}
