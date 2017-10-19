package de.jottyfan.minecraft.speeddigger.util;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import de.jottyfan.minecraft.speeddigger.items.GunpowderAxe;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
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

	@Mod.EventBusSubscriber(modid = SpeedDigger.MODID)
	public static class RegistrationHandler {
		public static final HashSet<Item> ITEMS = new HashSet<>();

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final Item[] items = { AXE_GUNPOWDER };

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final Item item : items) {
				registry.register(item);
				ITEMS.add(item);
			}
		}
	}

}
