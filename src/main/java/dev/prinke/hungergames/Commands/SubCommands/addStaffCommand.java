package dev.prinke.hungergames.Commands.SubCommands;

import dev.prinke.hungergames.Commands.SubCommand;
import dev.prinke.hungergames.HungerGames;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class addStaffCommand extends SubCommand {

    HungerGames plugin;

    public addStaffCommand(HungerGames plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "addstaff";
    }

    @Override
    public String getDescription() {
        return "Add a player to the staff list";
    }

    @Override
    public String getSyntax() {
        return "/hg addstaff <player>";
    }

    @Override
    public void perform(Player p, String[] args) {
        if (p.hasPermission("hg.addstaff")) {
            if (args.length > 1) {
                // add the player to the staff list
                p.sendMessage(ChatColor.GREEN + "Added " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + " to the staff list.");
                Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    plugin.staff.add(target);
                } else {
                    p.sendMessage(ChatColor.RED + "Player not found!");
                }
            } else {
                p.sendMessage(ChatColor.RED + "Invalid syntax! Usage: " + getSyntax());
            }
        } else {
            p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        }
    }

}
