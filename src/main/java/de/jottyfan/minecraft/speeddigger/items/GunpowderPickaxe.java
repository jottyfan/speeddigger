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
public class GunpowderPickaxe extends ItemPickaxe implements RangeableTool {

	private Integer range;

	public GunpowderPickaxe(CreativeTabs tabs) {
		super(ToolMaterial.WOOD);
		this.range = 3;
		this.attackDamage = 2;
		this.attackSpeed = 0.7f;
		super.setRegistryName(SpeedDigger.MODID, "gunpowderpickaxe");
		super.setUnlocalizedName("gunpowderpickaxe");
		super.setCreativeTab(tabs);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return BIOMESOPLENTY_PICKAXE.contains(state.getBlock().getUnlocalizedName()) ? this.efficiency
				: super.getDestroySpeed(stack, state);
	}

	@Override
	public Integer getRange() {
		return range;
	}

	@Override
	public boolean canBreakNeigbbors(IBlockState blockIn) {
		return super.canHarvestBlock(blockIn)
				|| BIOMESOPLENTY_PICKAXE.contains(blockIn.getBlock().getUnlocalizedName());
	}
}
