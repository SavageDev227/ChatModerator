package com.savage.chatmoderator.command;

import com.savage.chatmoderator.ChatModerator;
import com.savage.chatmoderator.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {

    ChatModerator plugin = ChatModerator.getPlugin();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("chatmoderator")) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(args.length == 1 && args[0].equals("reload") && p.hasPermission("chatmoderator.admin")) {
                    try {
                        plugin.reloadConfig();
                        p.sendMessage(ColorUtils.translateColorCodes("&aConfig saved and reloaded successfully!"));
                    } catch (Exception e) {
                        sender.sendMessage(ColorUtils.translateColorCodes("&cAn error occurred while reloading the config!"));
                        e.printStackTrace();
                    }
                    return true;
                } else if(args.length == 1 && args[0].equals("disable") && p.hasPermission("chatmoderator.admin")) {
                    p.sendMessage(ColorUtils.translateColorCodes("&c&lWARNING: &r&cOnce disabled ALL moderation features until server is restarted! To confirm do ./chatmoderator disable confirm."));
                } else if(args.length == 2 && args[0].equals("disable") && args[1].equals("confirm") && p.hasPermission("chatmoderator.admin")) {
                    p.sendMessage(ColorUtils.translateColorCodes("&cDisabling moderation features"));
                    plugin.getPluginLoader().disablePlugin(ChatModerator.getPlugin());
                }
            }
        }
        return true;
    }
}
