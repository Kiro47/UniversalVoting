package com.kiro.universalvoting.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kiro.universalvoting.methods.Methods;
import com.kiro.universalvoting.methods.VotingMethod;

public class debugCmd implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		Player p = (Player) s;
		if (!(p.getPlayer().getUniqueId().toString().equalsIgnoreCase("814d3666-47af-4089-881e-b8259d50be86"))) return true;
		VotingMethod.registerVote(p, Methods.selectEnum(), "DEBUG");
		return true;
	}
}
