package fr.papon.squidgame.Features.commands;

import fr.papon.squidgame.Features.inventory.StartInventory;
import fr.papon.squidgame.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StartCommand extends Main.Command {
    @Override
    protected void myOnCommand(CommandSender sender, Command command, String label, String[] split) {
        if (split.length == 0){
            if (sender instanceof Player){
                if (sender.isOp()){
                    Player p = (Player) sender;
                    p.openInventory(StartInventory.getStart(p));
                }else {
                    sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande.");
                }
            }
        }
    }

    @Override
    protected List<String> myOnTabComplete(CommandSender sender, Command command, String label, String[] split) {
        return null;
    }

    @Override
    protected boolean isOpOnly(){
        return false;
    }
}
