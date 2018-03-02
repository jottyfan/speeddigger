package de.jottyfan.minecraft.speeddigger.blocks;

import java.util.Random;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerItems;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * 
 * @author jotty
 *
 */
public class SandSalpeter extends BlockGravel {

	public SandSalpeter(CreativeTabs tab) {
		super();
		super.setRegistryName(SpeedDigger.MODID, "sandsalpeter");
		super.setUnlocalizedName("sandsalpeter");
		super.setHardness(3.1f);
		super.setCreativeTab(tab);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return SpeedDiggerItems.ITEM_SALPETER;
	}
}
