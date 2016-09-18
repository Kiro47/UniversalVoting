package com.kiro.universalvoting.methods;

import java.lang.reflect.Constructor;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import com.kiro.universalvoting.UniversalVotingMain;
import com.kiro.universalvoting.methods.Interface.Server;

public class Methods {

	
	
	// TITLES  *****************************************************************************************************************************

    public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String Line1, String Line2) {
        sendTitle(player, fadeIn, stay, fadeOut, Line1, Line2);
    }

    private static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", NMSUtils.getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    private static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String Line1, String Line2) {
        try {
        	
        	String subtitle = Line2;
        	String title = Line1;
        	
            if (subtitle != null) {
                Object enumSubtitle = NMSUtils.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
                Object chatSubtitle = NMSUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
                Constructor<?> subtitleConstructor = NMSUtils.getNMSClass("PacketPlayOutTitle").getConstructor(NMSUtils.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], NMSUtils.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
                Object subtitlePacket = subtitleConstructor.newInstance(enumSubtitle, chatSubtitle, fadeIn, stay, fadeOut);
                sendPacket(player, subtitlePacket);
            }
            
            if (title != null) {
                Object enumTitle = NMSUtils.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
                Object chatTitle = NMSUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
                Constructor<?> titleConstructor = NMSUtils.getNMSClass("PacketPlayOutTitle").getConstructor(NMSUtils.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], NMSUtils.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
                Object titlePacket = titleConstructor.newInstance(enumTitle, chatTitle, fadeIn, stay, fadeOut);
                sendPacket(player, titlePacket);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    
    // TITLES ********************************************************************************************************************************
    
    // RNG ************************************************************************************************\
    
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	// RNG ****************************************************************************************************
	// FW Selectors **********************************************************************************************
	public static void fwRandom(final Player p) {
		// R W & B
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(UniversalVotingMain.getPlugin(), new Runnable() {
			
		 Random randomF = new Random();
		 boolean flicker = randomF.nextBoolean();
		 Random randomT = new Random();
		 boolean trail = randomT.nextBoolean();
		 final Color fade = fade();
		 final Color withColor = withColor();
		 final Type with = with();
		 
			public void run() {
				
				Firework fw = (Firework) p.getWorld().spawn(p.getLocation(), Firework.class);
				FireworkMeta fm = fw.getFireworkMeta();
						fm.addEffect(FireworkEffect.builder()
								.flicker(flicker)
								.trail(trail)
								.with(with)
								.withColor(withColor)
								.withFade(fade)
								.build());
								fm.setPower(3);
						fw.setFireworkMeta(fm);
			}
		},	20L);
	}
	// FW Selectors **********************************************************************************************
	
	// RDM FW HANDLER *****************************************************************************************
	private static Color fade(){
		Integer rint = randInt(0, 16);

		if (rint.equals(1)) return Color.AQUA;
		if (rint.equals(2)) return Color.BLACK;
		if (rint.equals(3)) return Color.BLUE;
		if (rint.equals(4)) return Color.FUCHSIA;
		if (rint.equals(5)) return Color.GRAY;
		if (rint.equals(6)) return Color.GREEN;
		if (rint.equals(7)) return Color.LIME;
		if (rint.equals(8)) return Color.MAROON;
		if (rint.equals(9)) return Color.NAVY;
		if (rint.equals(10)) return Color.OLIVE;
		if (rint.equals(11)) return Color.ORANGE;
		if (rint.equals(12)) return Color.PURPLE;
		if (rint.equals(13)) return Color.RED;
		if (rint.equals(14)) return Color.SILVER;
		if (rint.equals(15)) return Color.TEAL;
		if (rint.equals(0)) return Color.WHITE;
		if (rint.equals(16)) return Color.YELLOW;
		else return Color.YELLOW;
	}

private static Color withColor(){
	Integer rint = randInt(0, 10);

	if (rint.equals(1)) return Color.AQUA;
	if (rint.equals(2)) return Color.BLACK;
	if (rint.equals(3)) return Color.BLUE;
	if (rint.equals(4)) return Color.FUCHSIA;
	if (rint.equals(5)) return Color.GRAY;
	if (rint.equals(6)) return Color.GREEN;
	if (rint.equals(7)) return Color.LIME;
	if (rint.equals(8)) return Color.MAROON;
	if (rint.equals(9)) return Color.NAVY;
	if (rint.equals(10)) return Color.OLIVE;
	if (rint.equals(11)) return Color.ORANGE;
	if (rint.equals(12)) return Color.PURPLE;
	if (rint.equals(13)) return Color.RED;
	if (rint.equals(14)) return Color.SILVER;
	if (rint.equals(15)) return Color.TEAL;
	if (rint.equals(0)) return Color.WHITE;
	if (rint.equals(16)) return Color.YELLOW;
	else return Color.YELLOW;
}

private static Type with() {
	Integer rint = randInt(0,4);
	
	if (rint.equals(1)) return Type.BALL;
	if (rint.equals(2)) return Type.BALL_LARGE;
	if (rint.equals(3)) return Type.BURST;
	if (rint.equals(4)) return Type.CREEPER;
	if (rint.equals(5)) return Type.STAR;
	else return Type.BALL;
}
// RDM FW HANDLER *****************************************************************************************

	public static Server selectEnum() {
		
		String sn = UniversalVotingMain.getPlugin().getServer().getServerName();
		
		if (sn.equalsIgnoreCase("hub")) return Server.HUB;
		if (sn.equalsIgnoreCase("chosencraft")) return Server.SMP;
		if (sn.equalsIgnoreCase("kit pvp")) return Server.KITPVP;
		if (sn.equalsIgnoreCase("chosen build team")) return Server.CBT;
		if (sn.equalsIgnoreCase("arcade")) return Server.ARCADE;
		if (sn.equalsIgnoreCase("survival games")) return Server.SG;
		if (sn.equalsIgnoreCase("skyblock")) return Server.SKYBLOCK;
		else {
			return null;
		}

	}

///////////////////////////////////////////////////////////////////////////////
	private static boolean invIsNotFull(Player p) {
		if (p.getInventory().firstEmpty() == -1) {
			return false;
		}
		else {
			return true;
		}
	}
//////////////////////////////////////////////////////
	private static void spawnItemOnGround(Player p, ItemStack itemstack) {
		World w = p.getLocation().getWorld();
		w.dropItemNaturally(p.getLocation().add(0, 3.0, 0), itemstack);
		
	}
	/////////////////////////////////////////////////////////////
	public static void handout(Player p, ItemStack itemstack) {
		if (invIsNotFull(p)) {
			p.getInventory().addItem(itemstack);
			return;
		}
		
		else {
			spawnItemOnGround(p, itemstack);
		}
	}
}