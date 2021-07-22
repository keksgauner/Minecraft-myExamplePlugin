package myplugin.external;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

/**
 * @author KeksGauner
 */
public class InitializeManager {

    public static void registerCommand(String commandPrefix, Object register) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(commandPrefix, (Command) register);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void registerEvent(Plugin plugin, Listener register) {
        try {
            plugin.getServer().getPluginManager().registerEvents(register, plugin);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
