package de.wroracer.challange.listenders;

import de.wroracer.challange.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class DeadListender implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent){
        if (Main.getMain().getChallangeConfig().isNoDeadEnabled()) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setGameMode(GameMode.SPECTATOR);
            });
            Bukkit.broadcastMessage("§4Challange beendet durch Spieler §6" + deathEvent.getEntity().getName() + "\n§1" + deathEvent.getDeathMessage() + "\n§4Das war doch nicht so gewollt oder ?");
            deathEvent.setDeathMessage("");
        }
    }

}
