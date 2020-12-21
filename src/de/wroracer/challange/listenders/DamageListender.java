package de.wroracer.challange.listenders;

import de.wroracer.challange.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListender implements Listener {

    @EventHandler
    public void onPlayerDamageTake(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            if (Main.getMain().getChallangeConfig().isDamageAnounceEnabled()) {
                Bukkit.broadcastMessage("§4Der Spieler §6" + ((Player) event.getEntity()).getDisplayName() + "§4 hat §6" + event.getDamage() + " hp §4Damage genommen. \n Ursache: §6" + event.getCause());
            }
        }
    }
}
