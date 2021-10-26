package fr.papon.squidgame.Features.commands;

import fr.papon.squidgame.Features.Feature;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Commands extends Feature {
    public static ArrayList<String> param = new ArrayList<>();
    public static HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
    public static Random random;

    @Override
    protected void doRegister(){
        random = new Random(System.nanoTime());
        new NickCommand().register();
        new StartCommand().register();

        param.add("add");
        param.add("remove");
    }

    // Commande qui permet de générer un nombre aléatoire
    static int getRandom(int lower, int upper) {
        return (random.nextInt((upper - lower) + 1) + lower);
    }
}
