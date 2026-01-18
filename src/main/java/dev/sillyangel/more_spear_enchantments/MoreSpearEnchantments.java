package dev.sillyangel.more_spear_enchantments;

import dev.sillyangel.more_spear_enchantments.listeners.EnchantmentListener;
import org.bukkit.Bukkit;
import org.bukkit. event.Listener;
import org.bukkit.plugin.java. JavaPlugin;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

public class MoreSpearEnchantments extends JavaPlugin implements Listener {
    public static final String PLUGIN_ID = "more_spear_enchantments";
    public static final Logger LOGGER = LogUtils.getLogger();
    private boolean shouldDisable = false;

    @Override
    public void onLoad() {
        // Check platform compatibility
        if (!CompatibilityCheck.isCompatible()) {
            shouldDisable = true;
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        LOGGER.info("Platform check passed!  Loading plugin...");
    }

    @Override
    public void onEnable() {
        // Safeguard:  Don't enable if we flagged for disable
        if (shouldDisable) {
            LOGGER.warn("Plugin was marked for disable - skipping enable");
            return;
        }

        // Register listeners
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new EnchantmentListener(), this);

        LOGGER.info("More Spear Enchantments enabled!");
    }

    @Override
    public void onDisable() {
        // Safeguard: Don't run shutdown logic if we never fully enabled
        if (shouldDisable) {
            return;
        }

        LOGGER.info("More Spear Enchantments disabled!");
    }

}