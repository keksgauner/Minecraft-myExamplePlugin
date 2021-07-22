package myplugin;

import myplugin.command.Command_Tabolator;
import myplugin.command.Command_KeksGauner;
import myplugin.external.InitializeManager;
import myplugin.listener.Event_Join;
import myplugin.utils.Data;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the plugin
 *
 * @author KeksGauner
 */
public final class Main extends JavaPlugin {
    private static Main instance;
    private static JavaPlugin plugin;

    public static JavaPlugin getPlugin() { return plugin; }
    public static Main getInstance() { return instance; }

    @Override
    public void onEnable() {
        instance = this; plugin = this;
        Data.init();

        InitializeManager im = new InitializeManager();
        // Register a Command
        String pluginPrefix = "keksgauner";
        im.registerCommand(pluginPrefix, new Command_KeksGauner("keksgauner"));
        im.registerCommand(pluginPrefix, new Command_Tabolator("tabolator"));

        // Register a Event
        im.registerEvent(this, new Event_Join());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
