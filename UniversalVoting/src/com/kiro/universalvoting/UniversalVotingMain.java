package com.kiro.universalvoting;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.kiro.universalvoting.cmds.VoteCmd;
import com.kiro.universalvoting.cmds.debugCmd;
import com.kiro.universalvoting.methods.SettingsManager;

public class UniversalVotingMain extends JavaPlugin{


	public void onEnable() {
		saveDefaultConfig();
		SettingsManager.getInstance().setup(getPlugin());
		getCommand("vote").setExecutor(new VoteCmd());
		getCommand("kdebug").setExecutor(new debugCmd());
		//getCommand("x").setExecutor(new xCommand());
		

		
		Bukkit.getServer().getPluginManager().registerEvents(new VoteListener() , this);
	}
	
	public void onDisable() {
		SettingsManager.getInstance().deleteData();
	}
	
	public static Plugin getPlugin() {
		return Bukkit.getServer().getPluginManager().getPlugin("UniversalVoting");
	}

		
}
