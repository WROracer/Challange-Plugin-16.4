package de.wroracer.challange.config;

import de.wroracer.challange.Constands;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChallangeConfig {
    private final static String filePath = Constands.CONFIG_FOLDER;
    private final static String fileName = "challanges.yml";

    private final File file;
    private final File folder;
    private final YamlConfiguration cfg;

    public static ChallangeConfig loadConfig(){
        return new ChallangeConfig();
    }
    private ChallangeConfig(){
        folder = new File(filePath);
        file = new File(filePath, fileName);
        cfg = YamlConfiguration.loadConfiguration(file);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            try {
                cfg.set("Challange.NoDead.Enabled",true);
                cfg.set("Challange.Timer.Enabled",true);
                cfg.set("Challange.DamageAnounce.Enable",true);
                cfg.save(file);
            } catch (IOException ignored) {
            }
        }
    }


    public boolean isNoDeadEnabled(){
        return cfg.getBoolean("Challange.NoDead.Enabled");
    }
    public void setNoDeadEnabled(boolean enabled){
        cfg.set("Challange.NoDead.Enabled",enabled);
        save();
    }
    public boolean isTimerEnabled(){
        return cfg.getBoolean("Challange.Timer.Enabled");
    }
    public void setTimerEnabled(boolean enabled){
        cfg.set("Challange.Timer.Enabled",enabled);
        save();
    }
    public boolean isDamageAnounceEnabled(){
        return cfg.getBoolean("Challange.DamageAnounce.Enable");
    }
    public void setDamageAnounceEnabled(boolean enabled){
        cfg.set("Challange.DamageAnounce.Enable",enabled);
        save();
    }





    private void save(){
        try {
            cfg.save(file);
        } catch (IOException e) {
            System.err.println("Speicherung in file "+file.getName()+" nicht möglich:");
            e.printStackTrace();
        }
    }


}
