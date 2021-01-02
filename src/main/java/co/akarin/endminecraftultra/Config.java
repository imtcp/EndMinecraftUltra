package co.akarin.endminecraftultra;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import co.akarin.endminecraftultra.utils.mainUtils;

public class Config {
    public static Config instance;
    public static void loadConfig() {
        try {
            instance = new Gson().fromJson(new InputStreamReader(new FileInputStream(new File("config.json"))), Config.class);
        } catch (Exception e) {
			mainUtils.log("*******************************************************");
            mainUtils.log("*错误！无法加载配置文件: " + e.getMessage());
			mainUtils.log("*请到'https://acg.kr/emuconfig'获取一个新的配置文件");
			mainUtils.log("*如果配置文件内有中文等非英文语言请务必转码成ANSI使用!");
			mainUtils.log("*******************************************************");
            instance = new Config();
        }
        if (instance.username == null || instance.username.length == 0) instance.username = new String[] {"$rnd"};
        if (instance.register == null || instance.register.length() == 0) instance.register = "/register $pwd $pwd";
        if (instance.messages == null || instance.messages.length == 0) instance.messages = new String[] {"[$rnd] 哼哼~啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊~ [$rnd]", "[$rnd] $rnd啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊~ [$rnd]"};
    }

    public String[] username;
    public String register;
    public String[] messages;

    public String getRegisterCommand(String password) {
        return register.replace("$pwd", password);
    }

    public String getRandMessage() {
        return messages[new Random().nextInt(messages.length)].replace("$rnd", mainUtils.getRandomString(1, 4));
    }

    public String getRandUsername() {
        String rawName = username[new Random().nextInt(username.length)].replace("$rnd", mainUtils.getRandomString(4, 12));
        if (rawName.length() >= 15) rawName = rawName.substring(0, 15);
        return rawName;
    }
}
