package de.jottyfan.minecraft.speeddigger.util;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import de.jottyfan.minecraft.speeddigger.blocks.OreNetherSulphor;
import de.jottyfan.minecraft.speeddigger.blocks.OreSalpeter;
import de.jottyfan.minecraft.speeddigger.blocks.OreSandSalpeter;
import de.jottyfan.minecraft.speeddigger.blocks.OreSulphor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
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
public class SpeedDiggerBlocks {
	private static final SpeedDiggerTabs tabs = new SpeedDiggerTabs(SpeedDigger.MODID, "oresulphor");

	public static final OreSulphor ORE_SULPHOR = new OreSulphor(tabs);
	public static final OreNetherSulphor ORE_NETHER_SULPHOR = new OreNetherSulphor(tabs);
	public static final OreSalpeter ORE_SALPETER = new OreSalpeter(tabs);
	public static final OreSandSalpeter ORE_SAND_SALPETER = new OreSandSalpeter(tabs);

	@SideOnly(Side.CLIENT)
	@Mod.EventBusSubscriber(modid = SpeedDigger.MODID)
	public static class RegistrationHandler {
		public static final HashSet<Block> BLOCKS = new HashSet<>();

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			final Block[] blocks = { ORE_SULPHOR, ORE_NETHER_SULPHOR, ORE_SALPETER, ORE_SAND_SALPETER };

			final IForgeRegistry<Block> registry = event.getRegistry();

			for (final Block block : blocks) {
				registry.register(block);

				ItemBlock itemBlock = new ItemBlock(block);
				itemBlock.setRegistryName(block.getRegistryName());
				ForgeRegistries.ITEMS.register(itemBlock);

				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
						new ModelResourceLocation(block.getRegistryName(), "inventory"));

				BLOCKS.add(block);
			}
		}
	}

}
