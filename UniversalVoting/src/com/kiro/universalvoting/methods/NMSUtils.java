package com.kiro.universalvoting.methods;

import org.bukkit.Bukkit;

public class NMSUtils {

	
	 public static Class<?> getNMSClass(String name) {
	        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	        try {
	            return Class.forName("net.minecraft.server." + version + "." + name);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
}
