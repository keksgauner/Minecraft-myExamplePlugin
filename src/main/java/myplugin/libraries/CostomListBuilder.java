package myplugin.libraries;

import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import showcase.utils.ItemManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is not a Offical api
 * @author KeksGauner
 */
public class CostomListBuilder {
    public static void sendList(Player player, int seite, int length, String commandPrefix) {
        int end = (int) length / 9;
        if(seite >= end) {
            seite = end;
        }

        if(9 >= length) {
            player.spigot().sendMessage(TextComponentBuilder.create(
                    "&8&m-------------&r ",
                    new TextComponentBuilder("&7«").addHover("&7Keine Seite vorhanden").build(),
                    " &8| &eSeite &e" + seite +"&7/&e" + Integer.valueOf(end) + " &8| ",
                    new TextComponentBuilder("&7»").addHover("&7Keine Seite vorhanden").build(),
                    " &8&m---------------"
            ));
        } else

        if(seite <= 0) {
            player.spigot().sendMessage(TextComponentBuilder.create(
                    "&8&m-------------&r ",
                    new TextComponentBuilder("&7«").addHover("&7Keine Seite vorhanden").build(),
                    " &8| &eSeite &e" + Integer.valueOf(seite) +"&7/&e" + Integer.valueOf(end) + " &8| ",
                    new TextComponentBuilder("&a»").addHover("&e» Nächste Seite").addClickEvent(ClickEvent.Action.RUN_COMMAND, commandPrefix + " " + Integer.valueOf(seite +1)).build(),
                    " &8&m---------------"
            ));
        } else

        if(seite != end) {
            player.spigot().sendMessage(TextComponentBuilder.create(
                    "&8&m-------------&r ",
                    new TextComponentBuilder("&a«").addHover("&a» Vorherige Seite").addClickEvent(ClickEvent.Action.RUN_COMMAND, commandPrefix + " " + Integer.valueOf(seite -1)).build(),
                    " &8| &eSeite &e" + Integer.valueOf(seite) +"&7/&e" + Integer.valueOf(end) + " &8| ",
                    new TextComponentBuilder("&a»").addHover("&e» Nächste Seite").addClickEvent(ClickEvent.Action.RUN_COMMAND, commandPrefix + " " + Integer.valueOf(seite +1)).build(),
                    " &8&m---------------"
            ));
        } else


        if(seite >= end) {
            seite = end;
            player.spigot().sendMessage(TextComponentBuilder.create(
                    "&8&m-------------&r ",
                    new TextComponentBuilder("&a«").addHover("&a» Vorherige Seite").addClickEvent(ClickEvent.Action.RUN_COMMAND, commandPrefix + " " + Integer.valueOf(seite -1)).build(),
                    " &8| &eSeite &e" + Integer.valueOf(seite) +"&7/&e" + Integer.valueOf(end) + " &8| ",
                    new TextComponentBuilder("&7»").addHover("&7Keine Seite vorhanden").build(),
                    " &8&m---------------"
            ));
        }
    }
    public static void setInvList(Inventory inv, int seite, int length) {
        // end ist seiten ende
        // seite ist die aufgerufene seite

        int end = (int) length / 9;
        if(seite >= end) { // Falls die zahl größer als die eingegebene zahl ist
            seite = end;
        }

        if(9 >= length) {
            inv.setItem(10 - 1, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1).setName("§7Keine Seite vorhanden"));
            inv.setItem(9 * 2 - 1, ItemManager.createItem(Material.BLACK_STAINED_GLASS_PANE, "§7Keine Seite vorhanden"));
        } else

        if(seite <= 0) {
            inv.setItem(10 - 1, ItemManager.createItem(Material.BLACK_STAINED_GLASS_PANE, "§7Keine Seite vorhanden"));
            inv.setItem(9 * 2 - 1, ItemManager.createItemWithLore(Material.RED_STAINED_GLASS_PANE, "§e» Nächste Seite", new ArrayList<String>(List.of("Seite " + Integer.valueOf(seite + 1)))));
        } else

        if(seite != end) {
            inv.setItem(10 - 1, ItemManager.createItemWithLore(Material.RED_STAINED_GLASS_PANE, "§a» Vorherige Seite", new ArrayList<String>(List.of("Seite " + Integer.valueOf(seite - 1)))));
            inv.setItem(9 * 2 - 1, ItemManager.createItemWithLore(Material.RED_STAINED_GLASS_PANE, "§e» Nächste Seite", new ArrayList<String>(List.of("Seite " + Integer.valueOf(seite + 1)))));
        } else


        if(seite >= end) {
            seite = end;
            inv.setItem(10 - 1, ItemManager.createItemWithLore(Material.RED_STAINED_GLASS_PANE, "§a» Vorherige Seite", new ArrayList<String>(List.of("Seite " + Integer.valueOf(seite - 1)))));
            inv.setItem(9 * 2 - 1, ItemManager.createItem(Material.BLACK_STAINED_GLASS_PANE, "§7Keine Seite vorhanden"));
        }
    }
    public static List<Integer> getList(int seite, int length, int max) {
        int st = 0;
        int sp = 0;
        if(9 <= length) {
            st = seite * max;
            sp = (seite + 1) * max;
            if(sp >= length) {
                sp = length;
            }
        } else {
            st = 0;
            sp = length;
        }
        return Arrays.asList(st, sp);
    }
}
