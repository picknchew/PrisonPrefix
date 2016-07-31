package me.picknchew.prisonprefix;

public class Prefix implements Comparable<Prefix> {
	private final String prefix;
	private final String permission;

	private final int priority;

	public Prefix(String prefix, String permission, int priority) {
		this.prefix = prefix;
		this.permission = permission;
		this.priority = priority;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getPermission() {
		return permission;
	}

	public int getPriority() {
		return priority;
	}

	@Override
	public int compareTo(Prefix prefix) {
		return prefix.getPriority() - priority;
	}
}
