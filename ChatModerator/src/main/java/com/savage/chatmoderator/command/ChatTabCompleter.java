package com.savage.chatmoderator.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChatTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            // return a list of possible subcommands
            return Arrays.asList("reload", "disable");
        } else if(args.length == 2) {
            return Arrays.asList("confirm");
        }
        // return an empty list if no suggestions are available
        return Collections.emptyList();
    }
}
