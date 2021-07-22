package myplugin.command;

import myplugin.external.Check;
import myplugin.external.TextComponentBuilder;
import myplugin.utils.Data;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

/**
 * This is a example of a command
 *
 * @author KeksGauner
 */
public class Command_KeksGauner extends BukkitCommand {

    public Command_KeksGauner(String name) {
        super(name);
        //this.setDescription("Is a KeksGauner Command");
        //this.setPermission("keksgauner.use");
        //this.setUsage("/keksgauner");
        //this.setAliases(Arrays.asList("keksi", "mykeks","superkeks"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        // Check if commandsender a Player
        if(Check.isPlayer(sender)) return true;
        Player p = (Player) sender;
        // Check player permission
        if(Check.hasPermission(p, "keksgauner.use", "§4Du bist nicht cool genug")) return true;
        p.sendMessage(Data.getConfig().getConfig().getString("example.keksgauner_example"));
        // Example of TextCpmponent
        p.spigot().sendMessage(TextComponentBuilder.create(">> ", new TextComponentBuilder("Bin ich COOL?").addHover(" >> Coolnismode").addClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "Nein bist du nicht!").build(), " <<"));
        return false;
    }
}
