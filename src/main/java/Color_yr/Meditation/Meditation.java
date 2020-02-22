package Color_yr.Meditation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Meditation extends JavaPlugin {

    public static Plugin Meditation;
    public static Logger log;
    public static final String version = "1.0.0";
    public static ConfigOBJ MainConfig;
    public static Dz DzT = new Dz();

    public static final String DZ = "DZ";

    @Override
    public void onEnable() {
        Meditation = this;
        log = getLogger();
        log.info("§d[Meditation]§e正在启动，感谢使用，本插件交流群：571239090");
        new Config();
        DzT.start();
        Bukkit.getPluginCommand("dz").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new Event(), this);

        log.info("§d[Meditation]§e已启动-" + version);
    }

    @Override
    public void onDisable() {
        try {
            if (DzT != null && DzT.isAlive())
                DzT.stop();
        } finally {

        }
        log.info("§d[Meditation]§e已停止，感谢使用");
    }
}
