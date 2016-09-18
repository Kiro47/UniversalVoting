package com.kiro.universalvoting;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.kiro.universalvoting.methods.Methods;
import com.kiro.universalvoting.methods.VotingMethod;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

public class VoteListener implements Listener	{
	
	@EventHandler
	public void onServerVote(VotifierEvent e) {
		
		Vote v = e.getVote();
		final Player p = UniversalVotingMain.getPlugin().getServer().getPlayer(v.getUsername());
		
		if (p == null) {
			return;
		}
		
	
		if ((v.getUsername().contains(".")) || (v.getUsername().length() > 24) || (v.getUsername().contains("porn")) || 
				(v.getUsername().contains("fuck")) || (v.getUsername().contains(" ") || (v.getUsername().contains("http")))) {
			return;
		}
		
		VotingMethod.registerVote(p, Methods.selectEnum()	, v.getServiceName());
	}

}
