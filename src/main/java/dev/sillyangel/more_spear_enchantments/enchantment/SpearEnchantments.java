package dev.sillyangel.more_spear_enchantments.enchantment;

import dev.sillyangel.more_spear_enchantments.MoreSpearEnchantments;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemType;

@SuppressWarnings("UnstableApiUsage")
public class SpearEnchantments {

    // Define enchantment keys

    public static final TypedKey<Enchantment> VAMPIRIC = TypedKey.create(
            RegistryKey.ENCHANTMENT,
            Key.key(MoreSpearEnchantments.PLUGIN_ID, "vampiric")
    );

    public static final TypedKey<Enchantment> EXPLOSIVE = TypedKey.create(
            RegistryKey.ENCHANTMENT,
            Key.key(MoreSpearEnchantments.PLUGIN_ID, "explosive")
    );

    public static final TypedKey<Enchantment> LIGHTNING = TypedKey.create(
            RegistryKey.ENCHANTMENT,
            Key.key(MoreSpearEnchantments.PLUGIN_ID, "lightning")
    );

    public static final TypedKey<Enchantment> CRIPPLING = TypedKey.create(
            RegistryKey.ENCHANTMENT,
            Key.key(MoreSpearEnchantments.PLUGIN_ID, "crippling")
    );

    public static final TypedKey<Enchantment> POISON = TypedKey.create(
            RegistryKey.ENCHANTMENT,
            Key.key(MoreSpearEnchantments.PLUGIN_ID, "poison")
    );

    public static final TypedKey<Enchantment> WITHERING = TypedKey.create(
            RegistryKey.ENCHANTMENT,
            Key.key(MoreSpearEnchantments.PLUGIN_ID, "withering")
    );

    public static void registerEnchantments(io.papermc.paper.registry.event.WritableRegistry<Enchantment, EnchantmentRegistryEntry.Builder> registry) {

        // Create spear item key set with all spear types
        RegistryKeySet<ItemType> spearItems = RegistrySet.keySet(
                RegistryKey.ITEM,
                TypedKey.create(RegistryKey.ITEM, Key.key("minecraft", "wooden_spear")),
                TypedKey.create(RegistryKey.ITEM, Key.key("minecraft", "stone_spear")),
                TypedKey.create(RegistryKey.ITEM, Key.key("minecraft", "copper_spear")),
                TypedKey.create(RegistryKey.ITEM, Key.key("minecraft", "iron_spear")),
                TypedKey.create(RegistryKey.ITEM, Key.key("minecraft", "golden_spear")),
                TypedKey.create(RegistryKey.ITEM, Key.key("minecraft", "diamond_spear")),
                TypedKey.create(RegistryKey.ITEM, Key.key("minecraft", "netherite_spear"))
                );

        // Vampiric - Heals on hit
        registry.register(
                VAMPIRIC,
                builder -> builder
                        .description(Component.text("Vampiric"))
                        .maxLevel(3)
                        .weight(2)
                        .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(10, 8))
                        .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(50, 8))
                        .anvilCost(4)
                        .supportedItems(spearItems)
                        .primaryItems(spearItems)
                        .activeSlots(EquipmentSlotGroup.MAINHAND)
        );

        // Explosive - Creates explosion on hit
        registry.register(
                EXPLOSIVE,
                builder -> builder
                        .description(Component.text("Explosive"))
                        .maxLevel(3)
                        .weight(1)
                        .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(20, 10))
                        .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(70, 10))
                        .anvilCost(8)
                        .supportedItems(spearItems)
                        .primaryItems(spearItems)
                        .activeSlots(EquipmentSlotGroup.MAINHAND)
        );

        // Lightning - Strikes lightning on hit
        registry.register(
                LIGHTNING,
                builder -> builder
                        .description(Component.text("Lightning"))
                        .maxLevel(3)
                        .weight(2)
                        .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(15, 9))
                        .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(65, 9))
                        .anvilCost(4)
                        .supportedItems(spearItems)
                        .primaryItems(spearItems)
                        .activeSlots(EquipmentSlotGroup.MAINHAND)
        );

        // Crippling - Applies slowness and weakness
        registry.register(
                CRIPPLING,
                builder -> builder
                        .description(Component.text("Crippling"))
                        .maxLevel(3)
                        .weight(2)
                        .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(10, 8))
                        .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(50, 8))
                        .anvilCost(4)
                        .supportedItems(spearItems)
                        .primaryItems(spearItems)
                        .activeSlots(EquipmentSlotGroup.MAINHAND)
        );

        // Poison - Applies poison effect
        registry.register(
                POISON,
                builder -> builder
                        .description(Component.text("Poison"))
                        .maxLevel(3)
                        .weight(2)
                        .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(10, 8))
                        .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(50, 8))
                        .anvilCost(4)
                        .supportedItems(spearItems)
                        .primaryItems(spearItems)
                        .activeSlots(EquipmentSlotGroup.MAINHAND)
        );

        // Withering - Applies wither effect
        registry.register(
                WITHERING,
                builder -> builder
                        .description(Component.text("Withering"))
                        .maxLevel(3)
                        .weight(1)
                        .minimumCost(EnchantmentRegistryEntry.EnchantmentCost.of(15, 9))
                        .maximumCost(EnchantmentRegistryEntry.EnchantmentCost.of(65, 9))
                        .anvilCost(6)
                        .supportedItems(spearItems)
                        .primaryItems(spearItems)
                        .activeSlots(EquipmentSlotGroup.MAINHAND)
        );
    }
}

