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
public class SpeedpowderShovel extends ItemTool implements RangeableTool {

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
			new Block[] { Blocks.GRAVEL, Blocks.SAND, Blocks.GRASS, Blocks.DIRT, Blocks.CLAY, Blocks.FARMLAND });

	public Integer range;

	public SpeedpowderShovel(CreativeTabs tabs) {
		super(ToolMaterial.DIAMOND, EFFECTIVE_ON);
		this.range = 7;
		this.attackDamage = 4;
		this.attackSpeed = 2.0f;
		super.setRegistryName(SpeedDigger.MODID, "speedpowdershovel");
		super.setUnlocalizedName("speedpowdershovel");
		super.setCreativeTab(tabs);
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return BIOMESOPLENTY_SHOVEL.contains(state.getBlock().getUnlocalizedName()) ? this.efficiency
				: super.getDestroySpeed(stack, state);
	}

	@Override
	public Integer getRange() {
		return range;
	}

	@Override
	public boolean canBreakNeigbbors(IBlockState blockIn) {
		return EFFECTIVE_ON.contains(blockIn.getBlock())
				|| BIOMESOPLENTY_SHOVEL.contains(blockIn.getBlock().getUnlocalizedName());
	}
}
