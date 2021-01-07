package rainbow.main;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlugin extends JavaPlugin {
    public Economy e;

    public void init() {
        RegisteredServiceProvider<Economy> reg = Bukkit.getServicesManager().getRegistration(Economy.class);
        e = reg.getProvider();
    }

    public void onEnable() {
        init();
        getCommand("rainbow").setExecutor(new Rainbow(this));
        Bukkit.getPluginManager().registerEvents(new Events(this), this);
    }
}