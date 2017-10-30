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
public class OreSalpeter extends BlockOre {

	public OreSalpeter(CreativeTabs tab) {
		super();
		super.setRegistryName(SpeedDigger.MODID, "oresalpeter");
		super.setUnlocalizedName("oresalpeter");
		super.setHardness(3.1f);
		super.setCreativeTab(tab);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return SpeedDiggerItems.ITEM_SALPETER;
	}
}
