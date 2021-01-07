package rainbow.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import rainbow.main.SpigotPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Utils {
    public List<Player> rainbow = new ArrayList<Player>();
    public HashMap<Player, Integer> number = new HashMap<>();
    public HashMap<Integer, Color> colors = build_map();
    public SpigotPlugin plugin;

    public Utils(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean contains(Player p) {
        if (rainbow.contains(p)) {
            return true;
        } else {
            return false;
        }
    }

    enum ColorsRainbow {
        RED(Color.RED), ORANGE(Color.ORANGE), YELLOW(Color.YELLOW), GREEN(Color.GREEN), AQUA(Color.AQUA), BLUE(Color.BLUE), FUCHSIA(Color.FUCHSIA);

        Color color;
        ColorsRainbow(Color color) {
            this.color = color;
        }
    }

    public HashMap<Integer, Color> build_map() {
        HashMap<Integer, Color> i = new HashMap<>();
        int count = 0;
        for (ColorsRainbow color : ColorsRainbow.values()) {
            i.put(count, color.color);
            count++;
        }
        return i;
    }

    public void add(Player p) {
        rainbow.add(p);
    }

    public void remove(Player p, boolean isMap) {
        if (!isMap) {
            rainbow.remove(p);
        } else {
            number.remove(p);
        }
    }

    public void put(Player p, int num) {
        number.put(p, num);
    }

    public void replace(Player p, int sum) {
        number.replace(p, sum);
    }

    public int get(Player p) {
        return number.get(p);
    }

    public int getBalance(Player p) {
        return (int) plugin.e.getBalance(p);
    }

    public void takePlayer(Player p, int sum) {
        plugin.e.withdrawPlayer(p, sum);
    }

    public ItemStack leather_meta(ItemStack stack, int data) {
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();
        meta.setColor(colors.get(data));
        stack.setItemMeta(meta);
        return stack;
    }

    public ItemStack item(String name, List<String> lore, Material material, int data) {
        ItemStack i = new ItemStack(material, 1, (short) data);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}