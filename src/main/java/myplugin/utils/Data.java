package myplugin.utils;

import myplugin.Main;
import myplugin.external.ConfigAccessor;

/**
 * @author KeksGauner
 */
public class Data {

    private static ConfigAccessor cfg_getConfig = new ConfigAccessor(Main.getInstance(), "" + "config.yml");
    public static ConfigAccessor getConfig() {return cfg_getConfig;}

    public static void init() {
        Data.getConfig().saveDefaultConfig();

        Data.getConfig().getConfig().options().copyDefaults(true);

        Data.getConfig().saveConfig();
    }
}
