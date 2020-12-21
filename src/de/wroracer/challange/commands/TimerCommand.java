package de.wroracer.challange.commands;

import de.wroracer.challange.Main;
import de.wroracer.challange.config.ChallangeConfig;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {

    private String active;
    private String color;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()){
            if (strings.length == 0){
                commandSender.sendMessage("§aAlle Timer Befehle:\n§6resume\n- pause\n- reset\n- enable");
            }else {
                switch (strings[0]){
                    case "resume":{
                        Main.getMain().timer.startTimer();
                        break;
                    }
                    case "pause":{
                        Main.getMain().timer.stopTimer();
                        break;
                    }
                    case "reset":{
                        Main.getMain().timer.stopTimer();
                        Main.getMain().timer.resetTimer();
                        break;
                    }
                    case "enable":{
                        ChallangeConfig config = Main.getMain().getChallangeConfig();
                        config.setTimerEnabled(!config.isTimerEnabled());

                        active = "Deaktiviert";
                        color = "§c";

                        if (config.isTimerEnabled()){
                            active = "Aktiviert";
                            color = "§a";
                        }
                        commandSender.sendMessage(color+"Der Timer wurde "+active);
                        Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(color+"Der Timer wurde "+active)));
                        break;
                    }
                    default:{
                        commandSender.sendMessage("$aAlle Timer Befehle:\n§6- resume\n- pause\n- reset\n- enable");
                    }
                }
            }
        }else {
            commandSender.sendMessage("§cDu hast keine Rechte diese Challange zu bearbeiten");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> subcommands = new ArrayList<>();
        subcommands.add("resume");
        subcommands.add("pause");
        subcommands.add("reset");
        subcommands.add("enable");
        return subcommands;
    }
}
