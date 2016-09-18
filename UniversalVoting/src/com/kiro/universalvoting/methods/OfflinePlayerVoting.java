package com.kiro.universalvoting.methods;


public class OfflinePlayerVoting {

/*	public static void OfflinePlayerVote(OfflinePlayer offlinePlayer) {
		

		OfflinePlayer op = offlinePlayer;
		if (op == null) {
			return;
		}
		else {
			UUID uop = op.getUniqueId();
			String puuid = uop.toString().replace("-", "");
			FileConfiguration data = SettingsManager.getInstance().getData();
			if (data.getInt(puuid) > 0) {
				int nv = data.getInt(puuid) + 1;
				
			}
			else {
				data.createSection(puuid);
				data.set(puuid, 1);	
			}
		}
		return;
	
	}*/
}
