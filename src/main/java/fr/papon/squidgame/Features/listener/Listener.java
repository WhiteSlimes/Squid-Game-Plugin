package fr.papon.squidgame.Features.listener;

import fr.papon.squidgame.Features.Feature;

import java.util.HashMap;
import java.util.Map;

public class Listener extends Feature {
    private static final HashMap<String, String> TABLE = new HashMap<String, String>();

    static {
        TABLE.put("0", "0");
        TABLE.put("1", "1");
        TABLE.put("2", "2");
        TABLE.put("3", "3");
        TABLE.put("4", "4");
        TABLE.put("5", "5");
        TABLE.put("6", "6");
        TABLE.put("7", "7");
        TABLE.put("8", "8");
        TABLE.put("9", "9");
        TABLE.put("a", "a");
        TABLE.put("b", "b");
        TABLE.put("c", "c");
        TABLE.put("d", "d");
        TABLE.put("e", "e");
        TABLE.put("f", "f");
        TABLE.put("black", "0");
        TABLE.put("darkblue", "1");
        TABLE.put("darkgreen", "2");
        TABLE.put("darkaqua", "3");
        TABLE.put("darkred", "4");
        TABLE.put("purple", "5");
        TABLE.put("gold", "6");
        TABLE.put("gray", "7");
        TABLE.put("darkgray", "8");
        TABLE.put("indigo", "9");
        TABLE.put("brightgreen", "a");
        TABLE.put("aqua", "b");
        TABLE.put("red", "c");
        TABLE.put("pink", "d");
        TABLE.put("yellow", "e");
        TABLE.put("white", "f");
        TABLE.put("clear", "f");

    }
    public static String getColor(String raw) {
        return TABLE.get(raw);
    }
    public static Map<String, Integer> antimaccro;

    @Override
    protected void doRegister(){
        new StartListener().register();
        antimaccro = new HashMap<>();
    }
}
