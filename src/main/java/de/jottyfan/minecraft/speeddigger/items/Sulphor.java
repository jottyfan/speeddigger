package de.jottyfan.minecraft.speeddigger.items;

import java.util.Set;

import com.google.common.collect.Sets;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

/**
 * 
 * @author jotty
 *
 */
public class Sulphor extends Item {

	public Sulphor(CreativeTabs tabs) {
		super();
		super.setRegistryName(SpeedDigger.MODID, "sulphor");
		super.setUnlocalizedName("sulphor");
		super.setCreativeTab(tabs);
	}
}
