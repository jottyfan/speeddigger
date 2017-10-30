package de.jottyfan.minecraft.speeddigger.blocks;

import java.util.Random;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerItems;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * 
 * @author jotty
 *
 */
public class OreNetherSulphor extends BlockOre {

	public OreNetherSulphor(CreativeTabs tab) {
		super();
		super.setRegistryName(SpeedDigger.MODID, "orenethersulphor");
		super.setUnlocalizedName("orenethersulphor");
		super.setHardness(2.1f);
		super.setCreativeTab(tab);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return SpeedDiggerItems.ITEM_SULPHOR;
	}
}
