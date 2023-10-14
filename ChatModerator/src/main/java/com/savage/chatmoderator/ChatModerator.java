package com.savage.chatmoderator;

import com.savage.chatmoderator.command.ChatCommand;
import com.savage.chatmoderator.command.ChatTabCompleter;
import com.savage.chatmoderator.utils.ColorUtils;
import org.bukkit.Color;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;
import java.util.logging.Logger;

public final class ChatModerator extends JavaPlugin {

    private static ChatModerator plugin;

    Logger log = getServer().getLogger();
    @Override
    public void onEnable() {

        plugin = this;
        String prefix = ColorUtils.translateColorCodes("&a&l[ChatModerator]: &r");
        // Plugin startup logic
        log.info(ColorUtils.translateColorCodes("&a--------------------------------"));
        log.info(ColorUtils.translateColorCodes(prefix + "&ahas been enabled"));
        log.info(ColorUtils.translateColorCodes(prefix + "&arunning server version: &a&l" + getServer().getVersion()));
        log.info(ColorUtils.translateColorCodes("&a---------------------------------"));

        //Load the config
        getConfig().options().copyDefaults(true);
        saveConfig();

        getServer().getPluginManager().registerEvents(new WatchChat(), this);
        getCommand("chatmoderator").setExecutor(new ChatCommand());
        getCommand("chatmoderator").setTabCompleter(new ChatTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        String prefix = ColorUtils.translateColorCodes("&a&l[ChatModerator]: &r");
        // Plugin startup logic
        log.info(ColorUtils.translateColorCodes("&a--------------------------------"));
        log.info(ColorUtils.translateColorCodes(prefix + "&ahas been enabled"));
        log.info(ColorUtils.translateColorCodes(prefix + "&arunning server version: &a&l" + getServer().getVersion()));
        log.info(ColorUtils.translateColorCodes("&a---------------------------------"));

    }

    public static ChatModerator getPlugin() {
        return plugin;
    }
}
