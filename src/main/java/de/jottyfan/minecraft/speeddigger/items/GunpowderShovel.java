package de.jottyfan.minecraft.speeddigger.items;

import java.util.Set;

import com.google.common.collect.Sets;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemTool;

/**
 * 
 * @author jotty
 *
 */
public class GunpowderShovel extends ItemTool implements RangeableTool {

	private static final Set<Block> EFFECTIVE_ON = Sets
			.newHashSet(new Block[] { Blocks.GRAVEL, Blocks.SAND, Blocks.GRASS, Blocks.DIRT, Blocks.CLAY, Blocks.FARMLAND });

	public Integer range;

	public GunpowderShovel(CreativeTabs tabs) {
		super(ToolMaterial.WOOD, EFFECTIVE_ON);
		this.range = 3;
		this.attackDamage = 2;
		this.attackSpeed = 0.7f;
		super.setRegistryName(SpeedDigger.MODID, "gunpowdershovel");
		super.setUnlocalizedName("gunpowdershovel");
		super.setCreativeTab(tabs);
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return EFFECTIVE_ON.contains(blockIn.getBlock())
				|| BIOMESOPLENTY_SHOVEL.contains(blockIn.getBlock().getUnlocalizedName());
	}

	@Override
	public Integer getRange() {
		return range;
	}
}
