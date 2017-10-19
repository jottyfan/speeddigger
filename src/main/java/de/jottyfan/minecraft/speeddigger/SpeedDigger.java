package de.jottyfan.minecraft.speeddigger;

import de.jottyfan.minecraft.speeddigger.help.SpeedDiggerTabs;
import de.jottyfan.minecraft.speeddigger.items.GunpowderAxe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * 
 * @author jotty
 *
 */
@Mod(modid = SpeedDigger.MODID, version = SpeedDigger.VERSION)
public class SpeedDigger {
	public static final String MODID = "speeddigger";
	public static final String VERSION = "1.12.2.0";

	private ItemTool gunpowderaxe;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SpeedDiggerTabs tabs = new SpeedDiggerTabs();
		gunpowderaxe = new GunpowderAxe(tabs);
	}

	@EventHandler
	public void load(FMLPostInitializationEvent event) {
		// TODO
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (event.getSide().equals(Side.CLIENT)) {
			RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
			renderItem.getItemModelMesher().register(gunpowderaxe, 0,
					new ModelResourceLocation(gunpowderaxe.getRegistryName().toString()));
		}
		GameRegistry.addShapedRecipe(gunpowderaxe.getRegistryName(), null, new ItemStack(gunpowderaxe), "## ", "#| ",
				" | ", '#', Items.GUNPOWDER, '|', Items.STICK);
	}
}
