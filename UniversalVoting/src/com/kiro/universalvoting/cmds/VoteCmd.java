package com.kiro.universalvoting.cmds;

import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.chosencraft.purefocus.Chat;
import com.kiro.universalvoting.UniversalVotingMain;


public class VoteCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		
			s.sendMessage(Chat.fill(ChatColor.GOLD + "~"));
			
			FileConfiguration conf = UniversalVotingMain.getPlugin().getConfig();
			
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) conf.getList("list");
			
			Iterator<String> it = list.iterator();
			while (it.hasNext()) {
				String str = it.next();
				s.sendMessage(str);
			}
			
			/*s.sendMessage(ChatColor.GOLD + ">>>>>>>>>>>>>>>>>>___Click The Links!___<<<<<<<<<<<<<<<<<<");
			s.sendMessage(ChatColor.BLUE +  " Server-Pact >  " +ChatColor.YELLOW + "www.serverpact.com/vote-24877");
			s.sendMessage(ChatColor.BLUE +  " MC Server Status > " + ChatColor.YELLOW + "https://mcserverstatus.com/vote/5621");
			s.sendMessage(ChatColor.BLUE + " FunMinecraftServers >   " + ChatColor.YELLOW + "funminecraftservers.com/s/1162/vote");
			s.sendMessage(ChatColor.BLUE + "  Minecraft-MP >  " + ChatColor.YELLOW + "minecraft-mp.com/server/82982/vote/");
			s.sendMessage(ChatColor.BLUE + " Minecraft-Servers >  " +ChatColor.YELLOW + "minecraftservers.org/vote/233035");
			s.sendMessage(ChatColor.BLUE + " Planet-Minecraft vvv  " + ChatColor.YELLOW + "www.planetminecraft.com/server/chosencraft-3317449/vote/");
			s.sendMessage(ChatColor.GOLD + ">>>>>>>>>>>>>>>>>>___Click The Links!___<<<<<<<<<<<<<<<<<<");
			*/
			s.sendMessage(Chat.fill(ChatColor.GOLD + "~"));
			
		return true;
	}
	
	public String colorTranslator(String s) {
		if (s.startsWith("&")) {
			s.replace("&1", "");
			System.out.println("translated");
			return s;
		}
		else {
			System.out.println("non");
			return s;
		}
		
	}

}
