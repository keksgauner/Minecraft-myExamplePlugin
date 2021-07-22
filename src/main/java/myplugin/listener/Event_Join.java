package myplugin.listener;

import myplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * This is a example of a event
 *
 * @author KeksGauner
 */
public class Event_Join implements Listener {
    @EventHandler
    public void onEvent(PlayerJoinEvent event)
    {
       Player p = event.getPlayer();

        AttributeInstance attribute = p.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        attribute.setBaseValue(6);

        p.setHealth(6);
        p.setLevel(2021);

        // stay = ticks || 20 ticks = 1 sec || 5 sec = 100 ticks
        p.sendTitle("§cWillkommen §a" + p.getName(), "", 1, 100, 1);
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 100, 0);


        BossBar bossBar = Bukkit.createBossBar(p.getName()+ " willkommen", BarColor.BLUE, BarStyle.SOLID);
        bossBar.addPlayer(p);

        // Example of a scheduke task
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                // Example of a try and catch
                try {
                    bossBar.removeAll();
                } catch (Exception e) {}
            }
        }, 100);

        p.sendMessage("");
        p.sendMessage("Folge uns auf onlyfans mit /onlyfans");
        p.sendMessage("");

    }
}
