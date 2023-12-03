package dev.prinke.hungergames.Commands.SubCommands;

import dev.prinke.hungergames.Commands.SubCommand;
import dev.prinke.hungergames.HungerGames;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class removeStaffCommand extends SubCommand {

    HungerGames plugin;

    public removeStaffCommand(HungerGames plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "removestaff";
    }

    @Override
    public String getDescription() {
        return "Remove a player from the staff list";
    }

    @Override
    public String getSyntax() {
        return "/hg removestaff <player>";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (p.hasPermission("hg.removestaff")) {
            if (args.length > 1) {
                // remove the player from the staff list
                p.sendMessage(ChatColor.RED + "Removed " + ChatColor.YELLOW + args[1] + ChatColor.RED + " from the staff list.");
                // get the player
                Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    plugin.staff.remove(target);
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
