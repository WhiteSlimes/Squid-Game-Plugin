package fr.papon.squidgame.Features.commands;

import fr.papon.squidgame.Features.utils.ItemUtil;
import fr.papon.squidgame.Features.utils.Nickname;
import fr.papon.squidgame.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class NickCommand extends Main.Command {
    @Override
    protected void myOnCommand(CommandSender sender, Command command, String label, String[] split) {
        if(sender instanceof Player){
            if (!sender.isOp()){
                return;
            }
        }
        if (split.length < 2){
            sender.sendMessage("§7/nick [Player] [Nickname]");
            return;
        }
        Player p = Bukkit.getPlayer(split[0]);
        if (p == null){
            sender.sendMessage("§7Le joueur n'est pas connecté.");
            return;
        }

        String line = "";
        for (int i = 1; i != split.length; i++ ){
            line = line.concat(split[i]+" ");
        }
        line = line.substring(0, line.length()-1);
        line = ItemUtil.translateHexCodes(line);
        line = line.replace("&","§");
        line += "§r";

        Nickname.setNick(sender, p, line);
    }

    @Override
    protected List<String> myOnTabComplete(CommandSender sender, Command command, String label, String[] split) {
        List<String> completion = new ArrayList<>();
        if (split.length == 1) for (Player p : Bukkit.getOnlinePlayers()) complete(completion, p.getName(), split[0]);

        return completion;
    }

    @Override
    protected boolean isOpOnly(){
        return false;
    }

}
