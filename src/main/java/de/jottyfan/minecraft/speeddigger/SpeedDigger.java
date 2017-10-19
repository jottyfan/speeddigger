package de.jottyfan.minecraft.speeddigger;

import de.jottyfan.minecraft.speeddigger.event.BlockBreakEvent;
import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
	public static final String VERSION = "1.12.2.0";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void load(FMLPostInitializationEvent event) {
		// TODO
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.addShapedRecipe(SpeedDiggerItems.AXE_GUNPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.AXE_GUNPOWDER), "## ", "#| ", " | ", '#', Items.GUNPOWDER, '|',
				Items.STICK);
		MinecraftForge.EVENT_BUS.register(new BlockBreakEvent());
	}
}
