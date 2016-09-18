package com.kiro.universalvoting.methods;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class SettingsManager {
	 
    private SettingsManager() { }
   
    static SettingsManager instance = new SettingsManager();
   
    public static SettingsManager getInstance() {
            return instance;
    }
   
    Plugin p;
   
    FileConfiguration data;
    File dfile;
    
    public void setup(Plugin p) {
    	dfile = new File(p.getDataFolder(), "data.yml");
    	
    	if (!(p.getDataFolder().exists())) {
    		p.getDataFolder().mkdir();
    	}
    	
    		if (!dfile.exists()) {
    			try{
    			dfile.createNewFile();
    		}
    		catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    		data = YamlConfiguration.loadConfiguration(dfile);	
    }
    
    public FileConfiguration getData() {
        return data;
}
    
    public void saveData() {
        try {
                data.save(dfile);
        }
        catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
        }
}   
    public void deleteData() {
    	try {
    		Files.deleteIfExists(dfile.toPath());
    	}
    	catch(DirectoryNotEmptyException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
			e.printStackTrace();
		} 
    }
}