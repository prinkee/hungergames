package dev.prinke.hungergames.Commands;

import dev.prinke.hungergames.HungerGames;
import dev.prinke.hungergames.Commands.SubCommands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    HungerGames plugin;

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(HungerGames plugin) {
        this.plugin = plugin;
        // register subcommands
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (!(commandSender instanceof Player p)) {
            commandSender.sendMessage("Only players can use this command!");
            return true;
        }

        if (strings.length > 0) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (strings[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    getSubCommands().get(i).perform(p, strings);
                }
            }
        } else {
            p.sendMessage(ChatColor.GOLD + "HungerGames by prinke" + ChatColor.GRAY + ".dev");
            p.sendMessage(ChatColor.RESET + "");
            for (int i = 0; i < getSubCommands().size(); i++) {
                p.sendMessage(ChatColor.YELLOW + getSubCommands().get(i).getSyntax() + ChatColor.WHITE + " - " + getSubCommands().get(i).getDescription());
            }
        }

        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    // create tab complete with subcommands
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> completions = new ArrayList<>();

        // If the player is typing the first argument
        if (strings.length == 1) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                completions.add(getSubCommands().get(i).getName());
            }
        }

        // If the player is typing the second argument, autofill with players
        if (strings.length >= 2) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                completions.add(player.getName());
            }
        }

        return completions;
    }

}