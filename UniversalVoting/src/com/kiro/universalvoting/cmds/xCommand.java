package com.kiro.universalvoting.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kiro.universalvoting.UniversalVotingMain;

public class xCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		else {
			 final Player p = (Player) sender;
			final float xp = p.getExp();
			final float xpToN = p.getExpToLevel();
			final int lv = p.getLevel();
			if (args.length == 0) {
				p.sendMessage("current XP: " + xp);
				p.sendMessage("xpToNext: " +xpToN);
				p.sendMessage("lv: " + lv);
				return true;
			}
			 if (args[0].equalsIgnoreCase("lv")) {
				int amt = Integer.parseInt(args[1]);
				p.giveExpLevels(amt);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(UniversalVotingMain.getPlugin(), new Runnable() {
					public void run() {
						p.sendMessage("current XP: " + xp);
						p.sendMessage("xpToNext: " +xpToN);
						p.sendMessage("lv: " + lv);
					}
				}, 10L);
				return true;
			}
			if (args[0].equalsIgnoreCase("xp")) {
				int amt = Integer.parseInt(args[1]);
				p.giveExp(amt);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(UniversalVotingMain.getPlugin(), new Runnable() {
					public void run() {
						
						p.sendMessage("current XP: " + xp);
						p.sendMessage("xpToNext: " +xpToN);
						p.sendMessage("lv: " + lv);
					}
				}, 10L);
				return true;
			}
			
		}
		return true;
	}


	
	
}
