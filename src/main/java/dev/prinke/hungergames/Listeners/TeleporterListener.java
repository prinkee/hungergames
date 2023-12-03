package dev.prinke.hungergames.Listeners;

import dev.prinke.hungergames.HungerGames;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class TeleporterListener implements Listener {

    HungerGames plugin;

    public TeleporterListener(HungerGames plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Teleporter")) {
            // create a gui with all players in the game
            Inventory gui = plugin.getServer().createInventory(null, 18, ChatColor.YELLOW + "Teleporter");

            // populate the gui with the head of each player in the game
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (!plugin.spectators.contains(player)) {
                    // get the player's skull
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                    SkullMeta meta = (SkullMeta) skull.getItemMeta();
                    meta.setOwningPlayer(player);
                    meta.setDisplayName(ChatColor.YELLOW + player.getName());
                    skull.setItemMeta(meta);

                    // add the skull to the gui
                    gui.addItem(skull);
                }
            }
            p.openInventory(gui);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(ChatColor.YELLOW + "Teleporter")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Teleporter")) {
                e.setCancelled(true);
            } else {
                // teleport the player to the player they clicked
                Player target = plugin.getServer().getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
                p.teleport(target);
                p.closeInventory();
            }
        }
    }
}
