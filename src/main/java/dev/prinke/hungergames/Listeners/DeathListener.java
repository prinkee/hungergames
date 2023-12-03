package dev.prinke.hungergames.Listeners;

import dev.prinke.hungergames.HungerGames;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {

    private final HungerGames plugin;

    public DeathListener(HungerGames plugin) {
        this.plugin = plugin;
    }

    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        // Set the player to spectator
        plugin.spectators.add(p);
        p.setGameMode(GameMode.ADVENTURE);
        p.setAllowFlight(true);

        // clear the player's inventory
        p.getInventory().clear();

        // disallow player pickup
        p.setCanPickupItems(false);

        // give the player a compass called "Teleporter"
        ItemStack compass = new ItemStack(Material.COMPASS);
        compass.getItemMeta().setDisplayName(ChatColor.YELLOW + "Teleporter");
        p.getInventory().addItem(compass);

        // disable player collision
        p.setCollidable(false);

        // make player invisible
        p.setInvisible(true);

        // give player infinite saturation
        p.setSaturation(20);

        // make player invulnerable
        p.setInvulnerable(true);

        // set player's displayname and nametag to red
        p.setDisplayName(ChatColor.RED + p.getName());
        p.setPlayerListName(ChatColor.RED + p.getName());

        // store the player's inventory
        plugin.inventories.put(p, p.getInventory());
        Player killer = e.getEntity().getKiller();

        // check if the player already has kills
        if (killer != null) {
            if (plugin.kills.containsKey(killer)) {
                // add 1 to the player's kills
                plugin.kills.put(killer, plugin.kills.get(killer) + 1);
            } else {
                // set the player's kills to 1
                plugin.kills.put(killer, 1);
            }
        }

        // Set death message to red
        e.setDeathMessage(ChatColor.RED + e.getDeathMessage());
    }

}

