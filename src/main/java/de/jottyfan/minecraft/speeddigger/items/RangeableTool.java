package de.jottyfan.minecraft.speeddigger.items;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 
 * @author jotty
 *
 */
public interface RangeableTool {

	public static final Set<String> BIOMESOPLENTY_SHOVEL = Sets.newHashSet(new String[] { "flesh", "loamy_dirt",
			"loamy_grass_block", "mud", "origin_grass_block", "sandy_dirt", "silty_dirt", "white_sand" });

	public static final Set<String> BIOMESOPLENTY_PICKAXE = Sets.newHashSet(new String[] { "dried_sand" });

	public static final Set<String> BIOMESOPLENTY_AXE = Sets
			.newHashSet(new String[] { "tile.log_0", "tile.log_1", "tile.log_2", "tile.log_3", "tile.log_4" });

	/**
	 * @return range of blocks to be harvested
	 */
	public Integer getRange();
}
