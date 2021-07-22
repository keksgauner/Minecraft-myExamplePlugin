package myplugin.command;

import myplugin.external.Check;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a example of a command with tabolator
 *
 * @author KeksGauner
 */
public class Command_Tabolator extends BukkitCommand {

    public Command_Tabolator(String name) {
        super(name);
        //this.setDescription("Is a Tabolator Command");
        //this.setPermission("keksgauner.use.tabolator");
        this.setUsage("/tabolator <send> <player> <message>");
        //this.setAliases(Arrays.asList("keksi", "mykeks","superkeks"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        // Check if commandsender a Player
        if(Check.isPlayer(sender)) return true;
        Player p = (Player) sender;
        // Check player permission
        if(Check.hasPermission(p, "keksgauner.use.tabolator", "§4Du bist nicht cool genug")) return true;

        if(args.length > 3) { // i use this because i cannot use < in switch
            Player targetPlayer = Bukkit.getPlayer(args[1]);

            if(targetPlayer == null)
                p.sendMessage("Player not Found");
            else {
                // Example of get multible messages
                String msg = ""; int startBy = 2;
                for (int argCurrent = startBy; argCurrent < args.length; argCurrent++) {
                    msg = msg + args[argCurrent] + " ";
                }
                p.sendMessage("Sended to " + targetPlayer.getName() + ": " + msg);
                targetPlayer.sendMessage(msg);
            }
            return false; // if false they stop. If true they continue
        }

        switch (args.length) {
            default:
                p.sendMessage(getUsage());
                break;
            case 2: // If the args equal a length of 2
            //case 3: // This is how to add something
                p.sendMessage("Why you dont use: " + getUsage());
                break;
        }
        return false;
    }

    public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) {
        if (args.length == 1){ // first args
            List<String> arguments = new ArrayList<>();
            arguments.add("send");
            return arguments; // give a list back
        }
        if (args.length == 3){ // third args
            List<String> arguments = new ArrayList<>();
            arguments.add("I am at X:" + location.getBlockX()+ " Y:" +location.getBlockY()+ " Z:" +location.getBlockZ());
            return arguments; // give a list back
        }

        // A other default return
        List<String> playerNames = new ArrayList<>();
        for (Player all : Bukkit.getOnlinePlayers()){
            playerNames.add(all.getName());
        }
        return playerNames;

        //return null; // default return
    }
}
