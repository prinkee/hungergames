package dev.prinke.hungergames;

import dev.prinke.hungergames.Commands.CommandManager;
import dev.prinke.hungergames.Expansions.HGExpansion;
import dev.prinke.hungergames.Listeners.DeathListener;
import dev.prinke.hungergames.Listeners.TeleporterListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class HungerGames extends JavaPlugin {

    public ArrayList<Player> spectators = new ArrayList<Player>();
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Player> staff = new ArrayList<Player>();
    public HashMap<Player, Inventory> inventories = new HashMap<Player, Inventory>();
    public HashMap<Player, Integer> kills = new HashMap<Player, Integer>();

    @Override
    public void onEnable() {
        // register papi expansion
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new HGExpansion(this).register();
        } else {
            getLogger().warning("[HUNGER GAMES] PlaceholderAPI not found!");
        }

        // register listeners
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new TeleporterListener(this), this);

        // register commands
        getCommand("hungergames").setExecutor(new CommandManager(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}