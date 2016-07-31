package me.picknchew.prisonprefix;

import java.util.Set;
import java.util.SortedSet;

import org.bukkit.entity.Player;

public class Identifier {
	private final Prefix emptyPrefix = new Prefix("", "", 0);
	private final String identifier;

	private final SortedSet<Prefix> prefixes;

	public Identifier(String identifier, SortedSet<Prefix> prefixes) {
		this.identifier = identifier;
		this.prefixes = prefixes;
	}

	public Prefix getPrefix(Player player) {
		for (Prefix prefix : prefixes) {
			if (player.hasPermission(prefix.getPermission())) {
				return prefix;
			}
		}

		return emptyPrefix;
	}

	public Set<Prefix> getPrefixes() {	
		return prefixes;
	}

	public String getIdentifier() {
		return identifier;
	}
}
