package de.wroracer.challange;

import de.wroracer.challange.commands.NoDeadCommand;
import de.wroracer.challange.config.ChallangeConfig;
import de.wroracer.challange.listenders.DeadListender;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    ChallangeConfig challangeConfig;

    @Override
    public void onEnable() {
        System.out.println("Challange  Plugin by WRORacer");
        System.out.println("Loading Configs");
        loadConfigs();
        System.out.println("Register Listenders");
        registerListenders();
        System.out.println("Register Commands");
        registerCommands();

        plugin = this;

        System.out.println("Challange Plugin Started");
    }

    @Override
    public void onDisable() {

    }

    private void registerListenders(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new DeadListender(), this);
    }

    private void registerCommands(){
        getCommand("nodead").setExecutor(new NoDeadCommand());
    }
    private void loadConfigs(){
        challangeConfig = ChallangeConfig.loadConfig();
    }

    public static Main getMain(){return plugin;}

    public ChallangeConfig getChallangeConfig(){return challangeConfig;}

}
