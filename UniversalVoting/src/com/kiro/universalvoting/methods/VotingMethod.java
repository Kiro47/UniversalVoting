package com.kiro.universalvoting.methods;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.kiro.universalvoting.methods.Interface.Server;

public class VotingMethod {
	public static HashMap<Player, Integer> voteMap= new HashMap<Player, Integer> ();
	
	public static void registerVote(Player player, Server servername, String servicename) {
		  
	switch (servername) {
	
	case HUB:
		hubVote(player, servicename);
		break;
		
	case SMP:
		smpVote(player, servicename);
		break;

	case KITPVP:
		kitpvpVote(player, servicename);
		break;
		
	case SG:
		sgVote(player, servicename);
		break;
		
	case SKYBLOCK:
		skyblockVote(player, servicename);
		break;
		
	case CBT:
		cbtVote(player, servicename);
		break;
		
	case ARCADE:
		arcadeVote(player, servicename);
		break;
		
	default:
		Bukkit.getServer().getLogger().severe(ChatColor.RED + "Invalid Servername Found: " + servername.toString() + ". Vote for " + player.getName() + " cancelled.");
		break;
		
	}
	}
	
	  
	////////////////////////////////////////////////////////////////////////
	
	private static void hubVote(Player p, String servicename) {
		FileConfiguration d = SettingsManager.getInstance().getData();
		String puuid = p.getUniqueId().toString().replace("-", "");	
			if (d.getInt(puuid) > 0) {
				int nv = d.getInt(puuid)+ 1;
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + 
						ChatColor.LIGHT_PURPLE + ")";
					
				
				if (d.getInt(puuid) + 1 == 6) {
					
					String FinalMsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
							ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6"
							+ ChatColor.LIGHT_PURPLE + ")" + ChatColor.RED + " Voting Completed!";
					
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp points add " + p.getName() +  " 100");
					Bukkit.getServer().broadcastMessage(FinalMsg);
					Methods.fwRandom(p);	
					
					d.set(puuid, null);
					SettingsManager.getInstance().saveData();
					
					return;
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				d.set(puuid, d.getInt(puuid) + 1);
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
			
			else {
				d.createSection(puuid);
				d.set( puuid, 1);
				SettingsManager.getInstance().saveData();
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + "1" + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + ChatColor.LIGHT_PURPLE + ")";
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Bukkit.getServer().broadcastMessage(Firstmsg);
			}
	
		}
	///////////////////////////////////////////////////////////////////////////////////
	
	private static void smpVote(Player p, String servicename) {
		FileConfiguration d = SettingsManager.getInstance().getData();
		String puuid = p.getUniqueId().toString().replace("-", "");	
			if (d.contains(puuid)) {
				int nv = d.getInt(puuid)+ 1;
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + 
						ChatColor.LIGHT_PURPLE + ")";
					
				
				if (d.getInt(puuid) + 1 == 6) {
					
					String FinalMsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
							ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6"
							+ ChatColor.LIGHT_PURPLE + ")" + ChatColor.RED + " Voting Completed!";
					
					Methods.handout(p, new ItemStack(Material.DIAMOND,1));
					Methods.handout(p, new ItemStack(Material.INK_SACK,3, (byte) 4));
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
					p.setLevel(p.getLevel() + 15);
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 600");
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp points add " + p.getName() +  " 100");
					Bukkit.getServer().broadcastMessage(FinalMsg);
					Methods.fwRandom(p);		
					Integer randomint = Methods.randInt(0, 1000);
					if (randomint.intValue() == 47) {
						p.sendMessage(ChatColor.GOLD +"Lucky Winner! You have recieved an extra $300 and 3 Diamonds!");
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 300");
					Methods.handout(p, new ItemStack(Material.DIAMOND,3));
					}
					d.set(puuid, null);
					SettingsManager.getInstance().saveData();
					return;
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Methods.handout(p, new ItemStack(Material.DIAMOND,1));
				d.set(puuid, d.getInt(puuid) + 1);
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
			
			else {
				d.createSection(puuid);
				d.set( puuid, 1);
				SettingsManager.getInstance().saveData();
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + "1" + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + ChatColor.LIGHT_PURPLE + ")";
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Methods.handout(p, new ItemStack(Material.DIAMOND,1));
				Bukkit.getServer().broadcastMessage(Firstmsg);
			}
	}
	///////////////////////////////////////////////////////////////////////////////////
	
	private static void kitpvpVote(Player p, String servicename) {
		FileConfiguration d = SettingsManager.getInstance().getData();
		String puuid = p.getUniqueId().toString().replace("-", "");	
			if (d.getInt(puuid) > 0) {
				int nv = d.getInt(puuid)+ 1;
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + 
						ChatColor.LIGHT_PURPLE + ")";
					
				
				if (d.getInt(puuid) + 1 == 6) {
					
					String FinalMsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
							ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6"
							+ ChatColor.LIGHT_PURPLE + ")" + ChatColor.RED + " Voting Completed!";
					
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp points add " + p.getName() +  " 100");
					Methods.handout(p, new ItemStack(Material.INK_SACK, 3, (byte) 4));
					Methods.handout(p, new ItemStack(Material.EXP_BOTTLE,2));
					Bukkit.getServer().broadcastMessage(FinalMsg);
					Methods.fwRandom(p);
					Integer randomint = Methods.randInt(0, 1000);
					if (randomint.intValue() == 47) {
						p.sendMessage(ChatColor.GOLD +"Lucky Winner! You have recieved an extra 3 Lapis Lazuli and 3 Experience Bottles!");
						Methods.handout(p, new ItemStack(Material.EXP_BOTTLE,3));
						Methods.handout(p, new ItemStack(Material.INK_SACK, 3, (byte) 4));
					}
					
					d.set(puuid, null);
					SettingsManager.getInstance().saveData();
					
					return;
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				d.set(puuid, d.getInt(puuid) + 1);
				SettingsManager.getInstance().saveData();
				Methods.handout(p, new ItemStack(Material.EXP_BOTTLE,2));
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
			
			else {
				d.createSection(puuid);
				d.set( puuid, 1);
				SettingsManager.getInstance().saveData();
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + "1" + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + ChatColor.LIGHT_PURPLE + ")";
				Methods.handout(p, new ItemStack(Material.EXP_BOTTLE,2));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Bukkit.getServer().broadcastMessage(Firstmsg);
			}
	
		}
	///////////////////////////////////////////////////////////////////////////////////
	private static void skyblockVote(Player p, String servicename) {
		FileConfiguration d = SettingsManager.getInstance().getData();
		String puuid = p.getUniqueId().toString().replace("-", "");	
			if (d.getInt(puuid) > 0) {
				int nv = d.getInt(puuid)+ 1;
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + 
						ChatColor.LIGHT_PURPLE + ")";
					
				
				if (d.getInt(puuid) + 1 == 6) {
					
					String FinalMsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
							ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6"
							+ ChatColor.LIGHT_PURPLE + ")" + ChatColor.RED + " Voting Completed!";
					
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp points add " + p.getName() +  " 100");
					Methods.handout(p, new ItemStack(Material.GOLD_NUGGET, 2));
					Bukkit.getServer().broadcastMessage(FinalMsg);
					Methods.fwRandom(p);
					Integer randomint = Methods.randInt(0, 1000);
					if (randomint.intValue() == 47) {
						p.sendMessage(ChatColor.GOLD +"Lucky Winner! You have recieved an extra 3 gold ingots!!");
						Methods.handout(p, new ItemStack(Material.GOLD_INGOT, 3));
					}
					
					d.set(puuid, null);
					SettingsManager.getInstance().saveData();
					
					return;
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				d.set(puuid, d.getInt(puuid) + 1);
				SettingsManager.getInstance().saveData();
				Methods.handout(p, new ItemStack(Material.GOLD_NUGGET, 3));
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
			
			else {
				d.createSection(puuid);
				d.set( puuid, 1);
				SettingsManager.getInstance().saveData();
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + "1" + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + ChatColor.LIGHT_PURPLE + ")";
				Methods.handout(p, new ItemStack(Material.GOLD_NUGGET, 2));
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
	
		}
	///////////////////////////////////////////////////////////////////////////////////
	private static void cbtVote(Player p, String servicename) {
		FileConfiguration d = SettingsManager.getInstance().getData();
		String puuid = p.getUniqueId().toString().replace("-", "");	
			if (d.getInt(puuid) > 0) {
				int nv = d.getInt(puuid)+ 1;
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + 
						ChatColor.LIGHT_PURPLE + ")";
					
				
				if (d.getInt(puuid) + 1 == 6) {
					
					String FinalMsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
							ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6"
							+ ChatColor.LIGHT_PURPLE + ")" + ChatColor.RED + " Voting Completed!";
					
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp points add " + p.getName() +  " 100");
					Bukkit.getServer().broadcastMessage(FinalMsg);
					Methods.fwRandom(p);	
					
					d.set(puuid, null);
					SettingsManager.getInstance().saveData();
					
					return;
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				d.set(puuid, d.getInt(puuid) + 1);
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
			
			else {
				d.createSection(puuid);
				d.set( puuid, 1);
				SettingsManager.getInstance().saveData();
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + "1" + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + ChatColor.LIGHT_PURPLE + ")";
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Bukkit.getServer().broadcastMessage(Firstmsg);
			}
	
		}
	///////////////////////////////////////////////////////////////////////////////////
	private static void sgVote(Player p, String servicename) {
		FileConfiguration d = SettingsManager.getInstance().getData();
		String puuid = p.getUniqueId().toString().replace("-", "");	
			if (d.getInt(puuid) > 0) {
				int nv = d.getInt(puuid)+ 1;
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + 
						ChatColor.LIGHT_PURPLE + ")";
					
				
				if (d.getInt(puuid) + 1 == 6) {
					
					String FinalMsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
							ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6"
							+ ChatColor.LIGHT_PURPLE + ")" + ChatColor.RED + " Voting Completed!";
					
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp points add " + p.getName() +  " 100");
					Bukkit.getServer().broadcastMessage(FinalMsg);
					Methods.fwRandom(p);	
					
					d.set(puuid, null);
					SettingsManager.getInstance().saveData();
					
					return;
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				d.set(puuid, d.getInt(puuid) + 1);
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
			
			else {
				d.createSection(puuid);
				d.set( puuid, 1);
				SettingsManager.getInstance().saveData();
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + "1" + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + ChatColor.LIGHT_PURPLE + ")";
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Bukkit.getServer().broadcastMessage(Firstmsg);
			}
	
		}
	///////////////////////////////////////////////////////////////////////////////////
	private static void arcadeVote(Player p, String servicename) {
		FileConfiguration d = SettingsManager.getInstance().getData();
		String puuid = p.getUniqueId().toString().replace("-", "");	
			if (d.getInt(puuid) > 0) {
				int nv = d.getInt(puuid)+ 1;
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + 
						ChatColor.LIGHT_PURPLE + ")";
					
				
				if (d.getInt(puuid) + 1 == 6) {
					
					String FinalMsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
							ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + nv + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6"
							+ ChatColor.LIGHT_PURPLE + ")" + ChatColor.RED + " Voting Completed!";
						
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp points add " + p.getName() +  " 100");
					Bukkit.getServer().broadcastMessage(FinalMsg);
					Methods.fwRandom(p);	
					
					d.set(puuid, null);
					SettingsManager.getInstance().saveData();
					
					return;
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				d.set(puuid, d.getInt(puuid) + 1);
				Bukkit.getServer().broadcastMessage(Firstmsg);
				return;
			}
			
			else {
				d.createSection(puuid);
				d.set( puuid, 1);
				SettingsManager.getInstance().saveData();
				
				String Firstmsg = ChatColor.LIGHT_PURPLE + p.getDisplayName() + ChatColor.BLUE + " Has Voted on " + servicename + "! " +
						ChatColor.LIGHT_PURPLE + "( " + ChatColor.GOLD + "1" + ChatColor.LIGHT_PURPLE + " / " + ChatColor.GOLD + "6" + ChatColor.LIGHT_PURPLE + ")";
				Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "gp votes add " + p.getName());
				Bukkit.getServer().broadcastMessage(Firstmsg);
			}
	
		}
	///////////////////////////////////////////////////////////////////////////////////
}
