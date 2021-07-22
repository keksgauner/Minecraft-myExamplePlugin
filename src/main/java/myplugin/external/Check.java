package myplugin.external;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author KeksGauner
 */
public class Check {
    public static boolean isPlayer(CommandSender sender) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command is only for Player!");
            return true;
        }
        return false;
    }

    public static boolean hasPermission(Player player, String permission, String message) {
        if(!player.hasPermission(permission)) {
            player.sendMessage(message);
            return true;
        }
        return false;
    }
}
