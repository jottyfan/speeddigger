package de.jottyfan.minecraft.speeddigger;

import de.jottyfan.minecraft.speeddigger.help.SpeedDiggerTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * 
 * @author jotty
 *
 */
@Mod(modid = SpeedDigger.MODID, version = SpeedDigger.VERSION)
public class SpeedDigger {
	public static final String MODID = "speeddigger";
	public static final String VERSION = "1.12.2.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SpeedDiggerTabs tabs = new SpeedDiggerTabs();
		// TODO: to be set on created items
	}
	
	@EventHandler
	public void load(FMLPostInitializationEvent event) {
		// TODO
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// TODO
	}
}
