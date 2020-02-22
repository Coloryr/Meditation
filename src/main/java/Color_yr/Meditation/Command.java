package Color_yr.Meditation;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


public class Command implements CommandExecutor {

    private boolean isConsole(CommandSender sender) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("§d[Meditation]§c控制台不能使用这条命令");
            return true;
        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("dz")) {
            if(args.length == 0)
            {
                if(isConsole(sender)) {
                    sender.sendMessage("§d[Meditation]§c你没有权限使用该指令");
                    return true;
                }
                Dz.dz((Player) sender);
                return true;
            }
            else if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("reload"))
                {
                    if (!sender.hasPermission("Meditation.admin")) {
                        sender.sendMessage("§d[Meditation]§c你没有权限使用该指令");
                        return true;
                    }
                }
                new Config();
                sender.sendMessage("§d[Meditation]§b已重读");
                return true;
            }
        }
        return false;
    }
}
