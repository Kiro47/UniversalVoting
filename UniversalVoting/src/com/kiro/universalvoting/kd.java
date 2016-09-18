package com.kiro.universalvoting;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class kd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		
		if (!(s.isOp())) return true;
		
		s.sendMessage("name" + Bukkit.getServer().getName());
		s.sendMessage("servername" + Bukkit.getServer().getServerName());
		return true;
	}

}
