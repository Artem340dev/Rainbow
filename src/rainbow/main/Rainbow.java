package rainbow.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import rainbow.utils.Utils;

public class Rainbow extends Utils implements CommandExecutor {
    private SpigotPlugin plugin;

    public Rainbow(SpigotPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (!contains(p)) {
                    int balance = getBalance(p);
                    if (balance < 1000) {
                        p.sendMessage(ChatColor.RED + "У вас не хватает денег! Нужно 1000 вирт!");
                        return true;
                    } else {
                        takePlayer(p, 1000);
                        p.sendMessage(ChatColor.GREEN + "Успешно!");
                        add(p);
                        put(p, 0);
                        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
                            if (contains(p)) {
                                ItemStack helmet = item("Кепка", null, Material.LEATHER_HELMET, 0);
                                ItemStack chestplate = item("Майка", null, Material.LEATHER_CHESTPLATE, 0);
                                ItemStack leggings = item("Джинсы", null, Material.LEATHER_LEGGINGS, 0);
                                ItemStack boots = item("Кроссовки", null, Material.LEATHER_BOOTS, 0);
                                p.getInventory().setHelmet(leather_meta(helmet, get(p)));
                                p.getInventory().setChestplate(leather_meta(chestplate, get(p)));
                                p.getInventory().setLeggings(leather_meta(leggings, get(p)));
                                p.getInventory().setBoots(leather_meta(boots, get(p)));
                                if (get(p)+1 <= 6) {
                                    replace(p, get(p)+1);
                                } else {
                                    replace(p, 0);
                                }
                            }
                        }, 20L, 20L);
                        return true;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Вы уже надели радужный лут!");
                    return true;
                }
            } else if (args.length == 1) {
                if (args[0].equals("off")) {
                    if (contains(p)) {
                        remove(p, false);
                        remove(p, true);
                        p.getInventory().setHelmet(null);
                        p.getInventory().setChestplate(null);
                        p.getInventory().setLeggings(null);
                        p.getInventory().setBoots(null);
                        p.sendMessage(ChatColor.GREEN + "Успешно!");
                        return true;
                    } else {
                        p.sendMessage(ChatColor.RED + "Вы не одевали лут!");
                        return true;
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Вы ввели команду не по форме!");
                    return true;
                }
            }
            p.sendMessage(ChatColor.RED + "Вы ввели команду не по форме!");
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "У вас нет прав!");
            return true;
        }
    }
}