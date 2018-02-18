package de.jottyfan.minecraft.speeddigger.items;

import java.util.Set;

import com.google.common.collect.Sets;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

/**
 * 
 * @author jotty
 *
 */
public class SpeedpowderPickaxe extends ItemPickaxe implements RangeableTool {

	private Integer range;

	public SpeedpowderPickaxe(CreativeTabs tabs) {
		super(ToolMaterial.DIAMOND);
		this.range = 7;
		this.attackDamage = 4;
		this.attackSpeed = 2.0f;
		super.setRegistryName(SpeedDigger.MODID, "speedpowderpickaxe");
		super.setUnlocalizedName("speedpowderpickaxe");
		super.setCreativeTab(tabs);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return BIOMESOPLENTY_PICKAXE.contains(state.getBlock().getUnlocalizedName()) ? this.efficiency
				: super.getDestroySpeed(stack, state);
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return super.canHarvestBlock(blockIn)
				|| BIOMESOPLENTY_PICKAXE.contains(blockIn.getBlock().getUnlocalizedName());
	}

	@Override
	public Integer getRange() {
		return range;
	}
}
