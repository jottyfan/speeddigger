package de.jottyfan.minecraft.speeddigger.event;

import java.util.ArrayList;
import java.util.List;

import de.jottyfan.minecraft.speeddigger.items.RangeableTool;
import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author jotty
 *
 */
public class BlockBreakEvent {

	private enum BlockBreakDirection {
		UPWARDS, ALL;
	}

	@SubscribeEvent
	public void doBreakBlock(BlockEvent.BreakEvent event) {
		if (event.getPlayer().getHeldItemMainhand() != null) {
			Item item = event.getPlayer().getHeldItemMainhand().getItem();
			World world = event.getWorld();
			IBlockState blockState = world.getBlockState(event.getPos());
			Block block = blockState.getBlock();
			List<String> visitedBlocks = new ArrayList<>();
			if (SpeedDiggerItems.AXE_GUNPOWDER.equals(item)) {
				RangeableTool tool = (RangeableTool) item;
				breakBlockRecursive(visitedBlocks, world, block, event.getPos(), item, tool.getRange(),
						BlockBreakDirection.UPWARDS);
			} else if (SpeedDiggerItems.PICKAXE_GUNPOWDER.equals(item)) {
				RangeableTool tool = (RangeableTool) item;
				breakBlockRecursive(visitedBlocks, world, block, event.getPos(), item, tool.getRange(),
						BlockBreakDirection.ALL);
			} else if (SpeedDiggerItems.SHOVEL_GUNPOWDER.equals(item)) {
				RangeableTool tool = (RangeableTool) item;
				breakBlockRecursive(visitedBlocks, world, block, event.getPos(), item, tool.getRange(),
						BlockBreakDirection.ALL);
			}
		}
	}

	/**
	 * break block recursively;
	 * 
	 * @param visitedBlocks
	 * @param world
	 * @param block
	 * @param item
	 * @param pos
	 * @param radius
	 *            to drop block; if null, no limit is given but the border of non
	 *            equal blocks
	 * @param blockBreakDirection
	 */
	private void breakBlockRecursive(List<String> visitedBlocks, World world, Block block, BlockPos pos, Item item,
			Integer radius, BlockBreakDirection blockBreakDirection) {
		if (visitedBlocks.contains(pos.toString())) {
			return; // reduce loops
		} else {
			visitedBlocks.add(pos.toString());
		}
		// remove block from world
		IBlockState blockState = world.getBlockState(pos);
		if (blockState != null && item.canHarvestBlock(blockState)) {
			NonNullList<ItemStack> list = NonNullList.create();
			Block currentBlock = blockState.getBlock();
			if (block.equals(currentBlock)) {
				block.getDrops(list, world, pos, blockState, 0); // no enchantmentlevel = 0
				world.setBlockToAir(pos);
				for (ItemStack itemStack : list) {
					EntityItem entity = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
					world.spawnEntity(entity);
				}
				if (radius == null || radius > 1) {
					Integer nextRadius = radius == null ? null : radius - 1;
					breakBlockRecursive(visitedBlocks, world, block, pos.north(), item, nextRadius,
							blockBreakDirection);
					breakBlockRecursive(visitedBlocks, world, block, pos.north().east(), item, nextRadius,
							blockBreakDirection);
					breakBlockRecursive(visitedBlocks, world, block, pos.north().west(), item, nextRadius,
							blockBreakDirection);
					breakBlockRecursive(visitedBlocks, world, block, pos.south(), item, nextRadius,
							blockBreakDirection);
					breakBlockRecursive(visitedBlocks, world, block, pos.south().east(), item, nextRadius,
							blockBreakDirection);
					breakBlockRecursive(visitedBlocks, world, block, pos.south().west(), item, nextRadius,
							blockBreakDirection);
					breakBlockRecursive(visitedBlocks, world, block, pos.east(), item, nextRadius, blockBreakDirection);
					breakBlockRecursive(visitedBlocks, world, block, pos.west(), item, nextRadius, blockBreakDirection);

					breakBlockRecursive(visitedBlocks, world, block, pos.up(), item, nextRadius, blockBreakDirection);

					if (BlockBreakDirection.ALL.equals(blockBreakDirection)) {
						breakBlockRecursive(visitedBlocks, world, block, pos.down(), item, nextRadius,
								blockBreakDirection);
					}
				}
			}
		}
	}
}
