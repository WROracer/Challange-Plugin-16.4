package de.wroracer.challange.commands;

import de.wroracer.challange.Main;
import de.wroracer.challange.config.ChallangeConfig;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DamageCommand implements CommandExecutor {

    private String active;
    private String color;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()){
            ChallangeConfig config = Main.getMain().getChallangeConfig();
            config.setDamageAnounceEnabled(!config.isDamageAnounceEnabled());
            active = "Deaktiviert";
            color = "§c";

            if (config.isDamageAnounceEnabled()){
                active = "Aktiviert";
                color = "§a";
            }
            commandSender.sendMessage(color+"Damage Anounce wurde "+active);
            Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color+"Damage Anounce wurde "+active)));
        }else {
            commandSender.sendMessage("§cDu hast keine Rechte diese Challange zu bearbeiten");
        }
        return false;
    }
}
