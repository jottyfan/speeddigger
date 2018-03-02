package de.jottyfan.minecraft.speeddigger;

import de.jottyfan.minecraft.speeddigger.event.AutoSaplingEvent;
import de.jottyfan.minecraft.speeddigger.event.BlockBreakEvent;
import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerItems;
import de.jottyfan.minecraft.speeddigger.world.OreGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 
 * @author jotty
 *
 */
@Mod(modid = SpeedDigger.MODID, version = SpeedDigger.VERSION)
public class SpeedDigger {
	public static final String MODID = "speeddigger";
	public static final String VERSION = "1.12.2.12";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void load(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new AutoSaplingEvent());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		new Register().registerRecipes();
		MinecraftForge.EVENT_BUS.register(new BlockBreakEvent());
		GameRegistry.registerWorldGenerator(new OreGenerator(), 20);
	}
}
