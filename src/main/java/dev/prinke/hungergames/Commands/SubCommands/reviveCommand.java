package dev.prinke.hungergames.Commands.SubCommands;

import dev.prinke.hungergames.Commands.SubCommand;
import dev.prinke.hungergames.HungerGames;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class reviveCommand extends SubCommand {

    HungerGames plugin;

    public reviveCommand(HungerGames plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "revive";
    }

    @Override
    public String getDescription() {
        return "Revive a player";
    }

    @Override
    public String getSyntax() {
        return "/hg revive <player>";
    }

    @Override
    public void perform(Player p, String[] args) {
        if (p.hasPermission("hg.revive")) {
            if (args.length > 1) {
                // revive the player
                p.sendMessage(ChatColor.GREEN + "Revived " + ChatColor.YELLOW + args[1] + ChatColor.GREEN + ".");
                Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {

                    // remove the player from spectator
                    plugin.spectators.remove(target);
                    target.setGameMode(GameMode.SURVIVAL);
                    target.setAllowFlight(false);

                    // allow player pickup
                    p.setCanPickupItems(true);

                    // clear the player's inventory
                    p.getInventory().clear();
                    p.getInventory().setContents(plugin.inventories.get(target).getContents());

                    // enable player collision
                    p.setCollidable(true);

                    // make player visible
                    p.setInvisible(false);

                    // reset saturation
                    p.setSaturation(20);

                    // make player vulnerable
                    p.setInvulnerable(false);

                    // set player's displayname and nametag to yellow
                    p.setDisplayName(ChatColor.YELLOW + p.getName());
                    p.setPlayerListName(ChatColor.YELLOW + p.getName());
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
