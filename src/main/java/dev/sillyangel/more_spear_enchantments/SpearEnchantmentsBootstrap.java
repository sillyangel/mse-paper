package dev.sillyangel.more_spear_enchantments;

import dev.sillyangel.more_spear_enchantments.enchantment.SpearEnchantments;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.registry.event.RegistryEvents;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class SpearEnchantmentsBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        context.getLogger().info("MoreSpearEnchantments bootstrap starting...");
        LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();

        // Register enchantments during the registry compose event
        manager.registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event -> {
            context.getLogger().info("Registering custom enchantments...");
            SpearEnchantments.registerEnchantments(event.registry());
        }));

        context.getLogger().info("MoreSpearEnchantments bootstrap complete!");
    }
}
