package Color_yr.Meditation;

import com.google.gson.Gson;
import org.bukkit.Material;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Config {
    public Config() {
        try {
            File file = new File(Meditation.Meditation.getDataFolder().getParent() + "/Meditation/config.json");
            if (!Meditation.Meditation.getDataFolder().exists())
                Meditation.Meditation.getDataFolder().mkdirs();
            if (!file.exists()) {
                try (InputStream in = Meditation.Meditation.getResource("config.json")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    Meditation.log.warning("§d[Meditation]§c配置文件 config.json 创建失败：" + e);
                }
            }
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bf = new BufferedReader(reader);
            Meditation.MainConfig = new Gson().fromJson(bf, ConfigOBJ.class);
            if (Meditation.MainConfig == null) {
                Meditation.MainConfig = new ConfigOBJ();
            }
        } catch (Exception e) {
            Meditation.log.warning("§d[Meditation]§c配置文件 config.json 读取失败：" + e);
        }
    }
}
