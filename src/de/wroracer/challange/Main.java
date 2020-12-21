package de.wroracer.challange;

import de.wroracer.challange.commands.NoDeadCommand;
import de.wroracer.challange.commands.TimerCommand;
import de.wroracer.challange.config.ChallangeConfig;
import de.wroracer.challange.listenders.DeadListender;
import de.wroracer.challange.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {

    private static Main plugin;

    public Timer timer;

    ChallangeConfig challangeConfig;

    @Override
    public void onEnable() {
        plugin = this;

        System.out.println("Challange  Plugin by WRORacer");
        System.out.println("Loading Configs");
        loadConfigs();
        System.out.println("Register Listenders");
        registerListenders();
        System.out.println("Register Commands");
        registerCommands();

        timer = new Timer();


        System.out.println("Challange Plugin Started");
    }

    @Override
    public void onDisable() {
        timer.stopTimer();
    }

    private void registerListenders(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new DeadListender(), this);
    }

    private void registerCommands(){
        TimerCommand timerCommand = new TimerCommand();

        getCommand("nodead").setExecutor(new NoDeadCommand());
        PluginCommand timer = getCommand("timer");
        timer.setExecutor(timerCommand);
        timer.setTabCompleter(timerCommand);

    }
    private void loadConfigs(){
        challangeConfig = ChallangeConfig.loadConfig();
    }

    public static Main getMain(){return plugin;}

    public ChallangeConfig getChallangeConfig(){return challangeConfig;}



}
