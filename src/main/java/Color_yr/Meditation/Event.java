package Color_yr.Meditation;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class Event implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (Dz.DzList.containsKey(e.getPlayer())) {
            Dz.Remove(e.getPlayer());
        }
    }

    @EventHandler
    public void onDismount(EntityDismountEvent event) {
        if (event.getDismounted() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getDismounted();
            if (armorStand.hasMetadata(Meditation.DZ)) {
                for (Entity p : armorStand.getPassengers()) {
                    if (p instanceof Player) {
                        Dz.Remove((Player) p);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (Dz.DzList.containsKey(event.getPlayer())) {
            Dz.Remove(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (Dz.DzList.containsKey(event.getEntity())) {
            Dz.Remove(event.getEntity());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (Dz.DzList.containsKey(event.getPlayer())) {
            Dz.Remove(event.getPlayer());
        }
    }
}
