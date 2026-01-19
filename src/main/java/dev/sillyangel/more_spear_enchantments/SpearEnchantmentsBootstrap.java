package dev.sillyangel.more_spear_enchantments;

import dev.sillyangel.more_spear_enchantments.enchantment.SpearEnchantments;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.event.RegistryEvents;
import io.papermc.paper.registry.keys.tags.EnchantmentTagKeys;
import io.papermc.paper.tag.PostFlattenTagRegistrar;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class SpearEnchantmentsBootstrap implements PluginBootstrap {

    // Define all enchantments in one place for easy management
    private static final Set<TypedKey<Enchantment>> ALL_ENCHANTMENTS = Set.of(
            SpearEnchantments.VAMPIRIC,
            SpearEnchantments.EXPLOSIVE,
            SpearEnchantments.LIGHTNING,
            SpearEnchantments.CRIPPLING,
            SpearEnchantments.POISON,
            SpearEnchantments.WITHERING
    );

    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        context.getLogger().info("MoreSpearEnchantments bootstrap starting...");
        LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();

        // Register enchantments during the registry compose event
        manager.registerEventHandler(RegistryEvents.ENCHANTMENT.compose().newHandler(event -> {
            context.getLogger().info("Registering custom enchantments...");
            SpearEnchantments.registerEnchantments(event.registry());
        }));

        // Add all enchantments to the appropriate tags
        manager.registerEventHandler(LifecycleEvents.TAGS.postFlatten(RegistryKey.ENCHANTMENT), event -> {
            final PostFlattenTagRegistrar<Enchantment> registrar = event.registrar();
            registrar.addToTag(EnchantmentTagKeys.TRADEABLE, ALL_ENCHANTMENTS);
            registrar.addToTag(EnchantmentTagKeys.NON_TREASURE, ALL_ENCHANTMENTS);
            registrar.addToTag(EnchantmentTagKeys.IN_ENCHANTING_TABLE, ALL_ENCHANTMENTS);
        });

        context.getLogger().info("MoreSpearEnchantments bootstrap complete!");
    }
}
