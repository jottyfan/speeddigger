package de.jottyfan.minecraft.speeddigger.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;

/**
 * 
 * @author jotty
 *
 */
public interface RangeableTool {

	public static final Set<String> BIOMESOPLENTY_SHOVEL = Sets
			.newHashSet(new String[] { "tile.flesh", "tile.loamy_dirt", "tile.loamy_grass_block", "tile.mud",
					"tile.origin_grass_block", "tile.sandy_dirt", "tile.silty_dirt", "tile.white_sand" });

	public static final Set<String> BIOMESOPLENTY_PICKAXE = Sets.newHashSet(new String[] { "tile.dried_sand" });

	public static final Set<String> BIOMESOPLENTY_AXE = Sets
			.newHashSet(new String[] { "tile.log_0", "tile.log_1", "tile.log_2", "tile.log_3", "tile.log_4" });

	/**
	 * @return range of blocks to be harvested
	 */
	public Integer getRange();

	/**
	 * check if this block state is one that affects the neighbor blocks to break
	 * also if they are from the same type
	 * 
	 * @param blockState
	 *            the block state of the current block
	 * @return true or false
	 */
	public boolean canBreakNeigbbors(IBlockState blockState);
}
