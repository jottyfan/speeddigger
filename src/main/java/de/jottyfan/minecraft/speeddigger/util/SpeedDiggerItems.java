package de.jottyfan.minecraft.speeddigger.util;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import de.jottyfan.minecraft.speeddigger.items.Dirtfield;
import de.jottyfan.minecraft.speeddigger.items.Dirtfieldhole;
import de.jottyfan.minecraft.speeddigger.items.Dirtfieldpuzzle;
import de.jottyfan.minecraft.speeddigger.items.Field;
import de.jottyfan.minecraft.speeddigger.items.GunpowderAxe;
import de.jottyfan.minecraft.speeddigger.items.GunpowderPickaxe;
import de.jottyfan.minecraft.speeddigger.items.GunpowderShovel;
import de.jottyfan.minecraft.speeddigger.items.Salpeter;
import de.jottyfan.minecraft.speeddigger.items.Speedpowder;
import de.jottyfan.minecraft.speeddigger.items.SpeedpowderAxe;
import de.jottyfan.minecraft.speeddigger.items.SpeedpowderPickaxe;
import de.jottyfan.minecraft.speeddigger.items.SpeedpowderShovel;
import de.jottyfan.minecraft.speeddigger.items.Sulphor;
import de.jottyfan.minecraft.speeddigger.proxy.IProxy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
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
	private static final SpeedDiggerTabs tabs = new SpeedDiggerTabs(SpeedDigger.MODID, "speedpowderaxe");

	public static final GunpowderAxe AXE_GUNPOWDER = new GunpowderAxe(tabs);
	public static final GunpowderPickaxe PICKAXE_GUNPOWDER = new GunpowderPickaxe(tabs);
	public static final GunpowderShovel SHOVEL_GUNPOWDER = new GunpowderShovel(tabs);
	public static final SpeedpowderAxe AXE_SPEEDPOWDER = new SpeedpowderAxe(tabs);
	public static final SpeedpowderPickaxe PICKAXE_SPEEDPOWDER = new SpeedpowderPickaxe(tabs);
	public static final SpeedpowderShovel SHOVEL_SPEEDPOWDER = new SpeedpowderShovel(tabs);

	public static final Speedpowder ITEM_SPEEDPOWDER = new Speedpowder(tabs);
	public static final Sulphor ITEM_SULPHOR = new Sulphor(tabs);
	public static final Salpeter ITEM_SALPETER = new Salpeter(tabs);
	public static final Field ITEM_FIELD = new Field(tabs);
	public static final Dirtfield ITEM_DIRTFIELD = new Dirtfield(tabs);
	public static final Dirtfieldhole ITEM_DIRTFIELDHOLE = new Dirtfieldhole(tabs);
	public static final Dirtfieldpuzzle ITEM_DIRTFIELDPUZZLE = new Dirtfieldpuzzle(tabs);

	@SidedProxy(clientSide = "de.jottyfan.minecraft.speeddigger.proxy.ClientProxy", serverSide = "de.jottyfan.minecraft.speeddigger.proxy.ServerProxy")
	public static IProxy proxy;

	@Mod.EventBusSubscriber(modid = SpeedDigger.MODID)
	public static class RegistrationHandler {
		public static final HashSet<Item> ITEMS = new HashSet<>();

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final Item[] items = { AXE_GUNPOWDER, PICKAXE_GUNPOWDER, SHOVEL_GUNPOWDER, AXE_SPEEDPOWDER,
					PICKAXE_SPEEDPOWDER, SHOVEL_SPEEDPOWDER, ITEM_SULPHOR, ITEM_SALPETER, ITEM_SPEEDPOWDER,
					ITEM_FIELD, ITEM_DIRTFIELD, ITEM_DIRTFIELDHOLE, ITEM_DIRTFIELDPUZZLE };

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final Item item : items) {
				registry.register(item);

				proxy.registerCustomModel(item, 0, item.getRegistryName());

				ITEMS.add(item);
			}
		}
	}

}
