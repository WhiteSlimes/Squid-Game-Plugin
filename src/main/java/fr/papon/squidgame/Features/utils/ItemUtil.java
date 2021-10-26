package fr.papon.squidgame.Features.utils;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemUtil {

    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
    public static String translateHexCodes(String str)
    {
        Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', str));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find())
            matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());

        return matcher.appendTail(buffer).toString();
    }

    public static ItemStack createItemStack(Material type, int amount){
        return new ItemStack(type, amount);
    }


    public static ItemStack createItemStack(Material type, String name){
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }

    public static ItemStack createItemStack(Material type, int amount, String name){
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            meta.setDisplayName(name);
            item.setItemMeta(meta);
            item.setAmount(amount);
        }

        return item;
    }

    public static ItemStack createItemStack(Material type, int amount, String name, List<String> lore){
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }

    public static ItemStack createItemStack(Material type, int amount, String name, List<String> lore, String keytag, String valuetag){
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null){
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setString(keytag, valuetag);
        stack.setTag(tag);
        item = CraftItemStack.asBukkitCopy(stack);
        return item;
    }

    public static ItemStack addTag(ItemStack item, String keytag, String valuetag) {
        net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = stack.getTag() != null ? stack.getTag() : new NBTTagCompound();
        tag.setString(keytag, valuetag);
        stack.setTag(tag);
        item = CraftItemStack.asBukkitCopy(stack);
        return item;
    }

    public static boolean hasTag(ItemStack item, String keytag, String valuetag) {
        boolean test = false;
        net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(item);
        if(stack.hasTag() && stack.getTag() != null && stack.getTag().hasKey(keytag) && stack.getTag().getString(keytag).equals(valuetag)) {
            test = true;
        }
        return test;
    }

    public static ItemStack createRenamedWool(DyeColor color, int amount, String name){
        Wool wool = new Wool(color);
        ItemStack item = wool.toItemStack(amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
    }

    public static String convertItemStackToJsonRegular(ItemStack itemStack) {
        // First we convert the item stack into an NMS itemstack
        net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        net.minecraft.nbt.NBTTagCompound compound = new NBTTagCompound();

        return nmsItemStack.save(compound).toString();
    }

    /**
     * Converts the player inventory to a String array of Base64 strings. First string is the content and second string is the armor.
     *
     * @param playerInventory to turn into an array of strings.
     * @return Array of strings: [ main content, armor content ]
     * @throws IllegalStateException
     */
    public static String[] playerInventoryToBase64(PlayerInventory playerInventory) throws IllegalStateException {
        //get the main content part, this doesn't return the armor
        String content = toBase64(playerInventory);
        String armor = itemStackArrayToBase64(playerInventory.getArmorContents());

        return new String[] { content, armor };
    }

    /**
     *
     * A method to serialize an {@link ItemStack} array to Base64 String.
     *
     * <p />
     *
     * Based off of {@link #toBase64(Inventory)}.
     *
     * @param items to turn into a Base64 String.
     * @return Base64 string of the items.
     * @throws IllegalStateException
     */
    public static String itemStackArrayToBase64(ItemStack[] items) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(items.length);

            // Save every element in the list
            for (int i = 0; i < items.length; i++) {
                dataOutput.writeObject(items[i]);
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    /**
     * A method to serialize an inventory to Base64 string.
     *
     * <p />
     *
     * Special thanks to Comphenix in the Bukkit forums or also known
     * as aadnk on GitHub.
     *
     * <a href="https://gist.github.com/aadnk/8138186">Original Source</a>
     *
     * @param inventory to serialize
     * @return Base64 string of the provided inventory
     * @throws IllegalStateException
     */
    public static String toBase64(Inventory inventory) throws IllegalStateException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(inventory.getSize());

            // Save every element in the list
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    /**
     *
     * A method to get an {@link Inventory} from an encoded, Base64, string.
     *
     * <p />
     *
     * Special thanks to Comphenix in the Bukkit forums or also known
     * as aadnk on GitHub.
     *
     * <a href="https://gist.github.com/aadnk/8138186">Original Source</a>
     *
     * @param data Base64 string of data containing an inventory.
     * @return Inventory created from the Base64 string.
     * @throws IOException
     */
    public static Inventory fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());

            // Read the serialized inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }

            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }

    /**
     * Gets an array of ItemStacks from Base64 string.
     *
     * <p />
     *
     * Base off of {@link #fromBase64(String)}.
     *
     * @param data Base64 string to convert to ItemStack array.
     * @return ItemStack array created from the Base64 string.
     * @throws IOException
     */
    public static ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            // Read the serialized inventory
            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }

            dataInput.close();
            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}
