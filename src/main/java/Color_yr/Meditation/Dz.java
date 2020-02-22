package Color_yr.Meditation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dz extends Thread {

    public static final Map<Player, Integer> DzList = new HashMap<>();
    public static final Map<Player, ArmorStand> ASList = new HashMap<>();

    public static void dz(Player player) {
        if (DzList.containsKey(player)) {
            player.sendMessage("§b你已经在打坐了");
            return;
        }
        Vector vector = player.getBoundingBox().getCenter().clone();
        Location loc = vector.setY(player.getBoundingBox().getMaxY()).toLocation(player.getWorld()).clone();
        loc.setY(loc.getY() - 2);
        ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class, (e) -> {
            e.setVisible(false);
            e.setCanPickupItems(false);
            e.setBasePlate(false);
            e.setArms(false);
            e.setMarker(true);
            e.setInvulnerable(true);
            e.setGravity(false);
        });
        armorStand.setMetadata(Meditation.DZ, new FixedMetadataValue(Meditation.Meditation, true));
        if (!armorStand.addPassenger(player)) {
            armorStand.remove();
            player.sendMessage("§c这个位置不能打坐");
            return;
        }
        DzList.put(player, 0);
        ASList.put(player, armorStand);
        player.sendMessage("§b你开始了打坐");
    }

    private static Lock lock = new ReentrantLock();

    public static void Check() {
        lock.lock();
        try {
            Player player;
            int time;
            boolean Op;
            for (Map.Entry<Player, Integer> item : DzList.entrySet()) {
                player = item.getKey();
                time = item.getValue();
                time++;
                DzList.put(item.getKey(), time);
                SetOBJ Set = Meditation.MainConfig.check(time);
                if (Set != null) {
                    List<String> commands = Set.getCommand();
                    Op = player.isOp();
                    if (Set.isOp())
                        player.setOp(true);
                    for (String command : commands) {
                        Player finalPlayer = player;
                        Bukkit.getScheduler().runTask(Meditation.Meditation, () ->
                                finalPlayer.performCommand(command.replace("%player%", finalPlayer.getName())));

                    }
                    player.setOp(Op);
                }
            }
        } catch (Exception e) {
            Meditation.log.warning("§d[Meditation]&c发生错误");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void Remove(Player player) {
        lock.lock();
        try {
            if (ASList.containsKey(player)) {
                ASList.get(player).remove();
                ASList.remove(player);
            }
            if (DzList.containsKey(player)) {
                DzList.remove(player);
                player.sendMessage("§b你停止了打坐");
            }
        } catch (Exception e) {
            Meditation.log.warning("§d[Meditation]&c发生错误");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (DzList.size() != 0) {
                    Check();
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                Meditation.log.warning("§d[Meditation]&c发生错误");
                e.printStackTrace();
            }
        }
    }
}
