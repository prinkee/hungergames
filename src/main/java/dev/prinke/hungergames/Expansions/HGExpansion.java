package dev.prinke.hungergames.Expansions;

import dev.prinke.hungergames.HungerGames;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class HGExpansion extends PlaceholderExpansion {

    private HungerGames plugin;

    public HGExpansion(HungerGames plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "prinke";
    }

    @Override
    public String getIdentifier() {
        return "hg";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("kills")){
            return plugin.kills.get(player.getUniqueId()).toString();
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
