package rainbow.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import rainbow.utils.Utils;

public class Events extends Utils implements Listener {
    public SpigotPlugin plugin;

    public Events(SpigotPlugin plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        InventoryType.SlotType slot = e.getSlotType();
        Player p = (Player) e.getWhoClicked();
        if (slot.equals(InventoryType.SlotType.ARMOR) && contains(p)) {
            e.setCancelled(true);
        }
    }
}