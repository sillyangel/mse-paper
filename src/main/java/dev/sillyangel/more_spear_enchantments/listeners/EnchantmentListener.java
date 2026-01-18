package dev.sillyangel.more_spear_enchantments.listeners;

import dev.sillyangel.more_spear_enchantments.enchantment.SpearEnchantments;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantmentListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        if (!(event.getEntity() instanceof LivingEntity target)) {
            return;
        }

        ItemStack weapon = player.getInventory().getItemInMainHand();
        if (weapon.isEmpty()) {
            return;
        }

        // Get enchantments from registry
        var registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT);

        // Vampiric enchantment
        Enchantment vampiric = registry.get(SpearEnchantments.VAMPIRIC);
        if (vampiric != null) {
            int level = weapon.getEnchantmentLevel(vampiric);
            if (level > 0) {
                handleVampiric(player, event.getFinalDamage(), level);
            }
        }

        // Explosive enchantment
        Enchantment explosive = registry.get(SpearEnchantments.EXPLOSIVE);
        if (explosive != null) {
            int level = weapon.getEnchantmentLevel(explosive);
            if (level > 0) {
                handleExplosive(target.getLocation(), level);
            }
        }

        // Lightning enchantment
        Enchantment lightning = registry.get(SpearEnchantments.LIGHTNING);
        if (lightning != null) {
            int level = weapon.getEnchantmentLevel(lightning);
            if (level > 0) {
                handleLightning(target, level);
            }
        }

        // Crippling enchantment
        Enchantment crippling = registry.get(SpearEnchantments.CRIPPLING);
        if (crippling != null) {
            int level = weapon.getEnchantmentLevel(crippling);
            if (level > 0) {
                handleCrippling(target, level);
            }
        }

        // Poison enchantment
        Enchantment poison = registry.get(SpearEnchantments.POISON);
        if (poison != null) {
            int level = weapon.getEnchantmentLevel(poison);
            if (level > 0) {
                handlePoison(target, level);
            }
        }

        // Withering enchantment
        Enchantment withering = registry.get(SpearEnchantments.WITHERING);
        if (withering != null) {
            int level = weapon.getEnchantmentLevel(withering);
            if (level > 0) {
                handleWithering(target, level);
            }
        }
    }

    private void handleVampiric(Player player, double damage, int level) {
        // Heal based on damage dealt and level
        double healAmount = damage * (0.1 * level); // 10%, 20%, 30% of damage at levels 1, 2, 3
        AttributeInstance maxHealthAttr = player.getAttribute(Attribute.MAX_HEALTH);
        if (maxHealthAttr == null) return;
        double maxHealth = maxHealthAttr.getValue();
        double newHealth = Math.min(player.getHealth() + healAmount, maxHealth);
        player.setHealth(newHealth);
    }

    private void handleExplosive(Location location, int level) {
        // Higher levels = higher chance and bigger explosion
        double chance = 0.1 * level; // 10%, 20%, 30% chance at levels 1, 2, 3
        if (Math.random() < chance) {
            float power = 1.0f + (0.5f * level); // 1.5, 2.0, 2.5 power at levels 1, 2, 3
            location.getWorld().createExplosion(location, power, false, false);
        }
    }

    private void handleLightning(LivingEntity target, int level) {
        // Strikes lightning based on level
        for (int i = 0; i < level; i++) {
            target.getWorld().strikeLightning(target.getLocation());
        }
    }

    private void handleCrippling(LivingEntity target, int level) {
        // Apply slowness and weakness
        int duration = level * 50; // 2.5s, 5s, 7.5s at levels 1, 2, 3
        target.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, level - 1, false, true));
        target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration, level - 1, false, true));
    }

    private void handlePoison(LivingEntity target, int level) {
        // Apply poison effect
        int duration = level * 50; // 2.5s, 5s, 7.5s at levels 1, 2, 3
        target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, level - 1, false, true));
    }

    private void handleWithering(LivingEntity target, int level) {
        // Apply wither effect
        int duration = level * 50; // 2s, 4s, 6s at levels 1, 2, 3
        target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, duration, level - 1, false, true));
    }
}

