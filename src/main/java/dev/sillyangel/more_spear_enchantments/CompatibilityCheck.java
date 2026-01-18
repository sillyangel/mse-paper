package dev.sillyangel.more_spear_enchantments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompatibilityCheck {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompatibilityCheck.class);

    private static Platform platform;
    private static boolean runsAsync = false;

    public enum Platform {
        BUKKIT,
        SPIGOT,
        PAPER,
        FOLIA,
        PURPUR
    }

    /**
     * Gets the detected platform, performing detection on first call
     */
    public static Platform getPlatform() {
        if (platform == null) {
            platform = checkPlatform();
            LOGGER.info("Detected platform: {}", platform);
        }
        return platform;
    }

    public static boolean isRunsAsync() {
        return runsAsync;
    }

    /**
     * Checks which platform the plugin is running on by attempting to load
     * platform-specific classes.  Order matters - checks from most specific to least specific.
     */
    private static Platform checkPlatform() {
        // Check for Folia (Paper fork with regionized threading)
        if (classExists("io.papermc.paper.threadedregions.RegionizedServer")) {
            return Platform.FOLIA;
        }

        // Check for Purpur (Paper fork with extra features)
        if (classExists("org.purpurmc.purpur.event.player.PlayerBookTooLargeEvent")) {
            return Platform.PURPUR;
        }

        // Check for Paper (Spigot fork)
        if (classExists("io.papermc.paper.event.player.AbstractChatEvent")) {
            return Platform.PAPER;
        }

        // Check for Spigot (Bukkit fork)
        if (classExists("org.spigotmc.CustomTimingsHandler")) {
            return Platform.SPIGOT;
        }

        // Check for vanilla Bukkit
        if (classExists("org.bukkit.Bukkit")) {
            return Platform.BUKKIT;
        }

        throw new IllegalStateException("Unknown server platform - no recognized classes found!");
    }

    /**
     * Helper method to safely check if a class exists
     */
    private static boolean classExists(String className) {
        try {
            Class. forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Determines if the plugin should continue loading based on platform compatibility.
     * @return true if compatible, false if the plugin should disable itself
     */
    public static boolean isCompatible() {
        Platform currentPlatform = getPlatform();

        switch (currentPlatform) {
            case BUKKIT:
            case SPIGOT:
                LOGGER. error("MoreSpearEnchantments has been loaded on an installation of {}!", currentPlatform);
                LOGGER.error("MoreSpearEnchantments does not support anything other than Paper and derivatives!");
                LOGGER.error("Spigot is considered legacy, and you should definitely consider upgrading!");
                LOGGER.error("For further information, see https://docs.papermc.io/paper/migration");
                LOGGER.error("MoreSpearEnchantments will shut down now...");
                return false;

            case PAPER:
            case PURPUR:
                return true;

            case FOLIA:
                LOGGER.info("MoreSpearEnchantments running in Folia-compatible mode, please report any issues!");
                runsAsync = true;
                return true;

            default:
                return false;
        }
    }
}