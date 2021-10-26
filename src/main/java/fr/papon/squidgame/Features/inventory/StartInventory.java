package fr.papon.squidgame.Features.inventory;

import fr.papon.squidgame.Features.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class StartInventory {
    public static Inventory getStart(Player p){
        Inventory inv = Bukkit.createInventory(p.getPlayer(), 27, "§3Squid Game");
        for (int i = 0; i < 27; i++){
            inv.setItem(i, ItemUtil.createItemStack(Material.GRAY_STAINED_GLASS_PANE, "§8-"));
            if (i > 8 && i%9 == 0 && i < 16){
                i +=7;
            }
        }

        inv.setItem(10, ItemUtil.createItemStack(Material.SUNFLOWER,1,"§eUn, Deux, Trois... Soleil !"));
        inv.setItem(11, ItemUtil.createItemStack(Material.COOKIE, 1, "§8Gâteau de sucre"));
        inv.setItem(12, ItemUtil.createItemStack(Material.LEAD, 1, "§4Tir à la corde"));
        inv.setItem(13, ItemUtil.createItemStack(Material.MAGMA_CREAM, 1, "§6Les billes"));
        inv.setItem(14, ItemUtil.createItemStack(Material.GLASS_PANE, 1, "§fLes plaques de verres"));
        inv.setItem(15, ItemUtil.createItemStack(Material.INK_SAC, 1, "§3Squid Game"));
        inv.setItem(16, ItemUtil.createItemStack(Material.BARRIER, 1, "§cLeave"));

        return inv;
    }
}
