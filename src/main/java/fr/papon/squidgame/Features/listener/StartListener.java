package fr.papon.squidgame.Features.listener;

import fr.papon.squidgame.Features.Feature;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class StartListener extends Feature {

    public double x, y, z;

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if (e.getClickedInventory() != null && e.getView().getTitle().startsWith("§3Squid Game")){
            if (e.getWhoClicked() instanceof Player){
                Player p = (Player) e.getWhoClicked();
                Inventory inv = e.getClickedInventory();
                e.setCancelled(true);
                int slot = e.getSlot();
                if (slot == 10 && inv.getItem(slot) != null){
                    x = 30.5;
                    y = 65;
                    z = 1.5;

                    Location location = new Location(p.getWorld(), x, y, z);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()){
                        player.teleport(location);
                    }
                }
                if (slot == 11 && inv.getItem(slot) != null){
                    x = 32.5;
                    y = 65;
                    z = 1.5;

                    Location location = new Location(p.getWorld(), x, y, z);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()){
                        player.teleport(location);
                    }
                }
                if (slot == 12 && inv.getItem(slot) != null){
                    x = 34.5;
                    y = 65;
                    z = 1.5;

                    Location location = new Location(p.getWorld(), x, y, z);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()){
                        player.teleport(location);
                    }
                }
                if (slot == 13 && inv.getItem(slot) != null){
                    x = 36.5;
                    y = 65;
                    z = 1.5;

                    Location location = new Location(p.getWorld(), x, y, z);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()){
                        player.teleport(location);
                    }
                }
                if (slot == 14 && inv.getItem(slot) != null){
                    x = 38.5;
                    y = 65;
                    z = 1.5;

                    Location location = new Location(p.getWorld(), x, y, z);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()){
                        player.teleport(location);
                    }
                }
                if (slot == 15 && inv.getItem(slot) != null){
                    x = 40.5;
                    y = 65;
                    z = 1.5;

                    Location location = new Location(p.getWorld(), x, y, z);
                    for (Player player : Bukkit.getServer().getOnlinePlayers()){
                        player.teleport(location);
                    }
                }
                if (slot == 16 && inv.getItem(slot) != null){
                    p.closeInventory();
                }
            }
        }
    }
}
