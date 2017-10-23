package de.jottyfan.minecraft.speeddigger.util;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import de.jottyfan.minecraft.speeddigger.items.GunpowderAxe;
import de.jottyfan.minecraft.speeddigger.items.GunpowderPickaxe;
import de.jottyfan.minecraft.speeddigger.items.GunpowderShovel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import scala.collection.mutable.HashSet;

/**
 * 
 * @author jotty
 *
 */
@ObjectHolder(SpeedDigger.MODID)
public class SpeedDiggerItems {
	public static final GunpowderAxe AXE_GUNPOWDER = new GunpowderAxe(new SpeedDiggerTabs());
	public static final GunpowderPickaxe PICKAXE_GUNPOWDER = new GunpowderPickaxe(new SpeedDiggerTabs());
	public static final GunpowderShovel SHOVEL_GUNPOWDER = new GunpowderShovel(new SpeedDiggerTabs());

	@SideOnly(Side.CLIENT)
	@Mod.EventBusSubscriber(modid = SpeedDigger.MODID)
	public static class RegistrationHandler {
		public static final HashSet<Item> ITEMS = new HashSet<>();

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final Item[] items = { AXE_GUNPOWDER, PICKAXE_GUNPOWDER, SHOVEL_GUNPOWDER };

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final Item item : items) {
				registry.register(item);

				ModelLoader.setCustomModelResourceLocation(item, 0,
						new ModelResourceLocation(item.getRegistryName(), "inventory"));

				ITEMS.add(item);
			}
		}
	}

}
