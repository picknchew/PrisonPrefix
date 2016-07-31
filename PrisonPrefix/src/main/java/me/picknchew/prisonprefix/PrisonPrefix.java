package me.picknchew.prisonprefix;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PrisonPrefix extends JavaPlugin implements Listener {
	private final Set<Identifier> identifiers = new HashSet<Identifier>();

	@Override
	public void onEnable() {
		saveDefaultConfig();

		FileConfiguration config = getConfig();

		for (String identifier : config.getKeys(false)) {
			SortedSet<Prefix> prefixes = new TreeSet<Prefix>();

			for (String prefix : config.getConfigurationSection(identifier).getKeys(false)) {
				ConfigurationSection section = config.getConfigurationSection(identifier + "." + prefix);

				prefixes.add(new Prefix(ChatColor.translateAlternateColorCodes('&', section.getString("prefix")), section.getString("permission"), section.getInt("priority")));
			}

			identifiers.add(new Identifier("{" + identifier + "}", prefixes));
		}

		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		for (Identifier identifier : identifiers) {
			event.setFormat(event.getFormat().replace(identifier.getIdentifier(), identifier.getPrefix(event.getPlayer()).getPrefix()));
		}
	}
}