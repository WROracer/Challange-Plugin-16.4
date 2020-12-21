package de.wroracer.challange.timer;

import de.wroracer.challange.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

public class Timer {

    private boolean isEnabled;
    private int timeSecond;
    private int timeMinute;
    private int timeHoure;
    private int timeDays;
    private String time;

    private boolean timerStopt;


    private Thread timerThread;

    public Timer(){

        timerStopt = true;
        isEnabled = Main.getMain().getChallangeConfig().isTimerEnabled();

        timerThread = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000);
                    timeSecond++;
                    if (timeSecond >= 60){
                        timeSecond = 0;
                        timeMinute++;
                        if (timeMinute >=60){
                            timeMinute = 0;
                            timeHoure++;
                            if (timeHoure >=24){
                                timeHoure = 0;
                                timeDays++;
                            }
                        }
                    }
                    //dd:hh:mm:ss
                    generateTimeString();
                    Bukkit.getOnlinePlayers().forEach(player -> {player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(time, ChatColor.DARK_GREEN));});
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        });

    }
    public void resetTimer(){
        isEnabled = Main.getMain().getChallangeConfig().isTimerEnabled();

        if (!timerStopt){
            stopTimer();
        }

        timeDays = 0;
        timeHoure = 0;
        timeMinute = 0;
        timeSecond = 0;
        Bukkit.broadcastMessage("§2Timer wurde zurück gesetzt");
    }
    public void stopTimer(){
        if (!timerStopt){
            isEnabled = Main.getMain().getChallangeConfig().isTimerEnabled();

            timerThread.stop();
            generateTimeString();
            Bukkit.broadcastMessage("§2Timer bei §6"+time+" §2gestoppt");
            timerStopt = true;
        }
    }
    public void startTimer(){

        isEnabled = Main.getMain().getChallangeConfig().isTimerEnabled();
        if (isEnabled) {
            timerStopt = false;
            timerThread = new Thread(()->{
                while (true){
                    try {
                        Thread.sleep(1000);
                        timeSecond++;
                        if (timeSecond >= 60){
                            timeSecond = 0;
                            timeMinute++;
                            if (timeMinute >=60){
                                timeMinute = 0;
                                timeHoure++;
                                if (timeHoure >=24){
                                    timeHoure = 0;
                                    timeDays++;
                                }
                            }
                        }
                        //dd:hh:mm:ss
                        generateTimeString();
                        Bukkit.getOnlinePlayers().forEach(player -> {player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(time, ChatColor.DARK_GREEN));});
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
            });
            timerThread.start();
            Bukkit.broadcastMessage("§2Timer gestarted");
        }else {
            stopTimer();
        }
    }


    private void generateTimeString(){
        if (timeDays <=9){
            time = "§a0"+timeDays;
        }else {
            time = ""+timeDays;
        }
        if (timeHoure <=9){
            time = time+":0"+timeHoure;
        }else {
            time = time+":"+timeHoure;
        }
        if (timeMinute <=9){
            time = time+":0"+timeMinute;
        }else {
            time = time+":"+timeMinute;
        }
        if (timeSecond <=9){
            time = time+":0"+timeSecond;
        }else {
            time = time+":"+timeSecond;
        }
    }


}
