package de.jottyfan.minecraft.speeddigger.event;

import java.util.ArrayList;
import java.util.List;

import de.jottyfan.minecraft.speeddigger.items.RangeableTool;
import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerItems;
import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
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
		EntityPlayer player = event.getPlayer();
		ItemStack mainHand = player.getHeldItemMainhand();
		if (mainHand != null) {
			Item item = mainHand.getItem();
			if (item instanceof RangeableTool) {
				BlockPos pos = event.getPos();
				World world = event.getWorld();
				IBlockState blockState = world.getBlockState(pos);
				Block block = blockState.getBlock();
				handleRangeableTools(item, world, block, pos, player);
			}
		}
	}

	/**
	 * hande the rangeable tools break event
	 * 
	 * @param item
	 *            the item that has been used
	 * @param world
	 *            the world
	 * @param block
	 *            the current block
	 * @param pos
	 *            the position of the current block
	 * @param player
	 *            the current player
	 */
	private void handleRangeableTools(Item item, World world, Block block, BlockPos pos, EntityPlayer player) {
		RangeableTool tool = (RangeableTool) item;
		if (SpeedDiggerItems.AXE_GUNPOWDER.equals(item) || SpeedDiggerItems.AXE_SPEEDPOWDER.equals(item)) {
			breakBlockRecursive(new ArrayList<>(), world, block, pos, tool, tool.getRange(),
					BlockBreakDirection.UPWARDS, player);
		} else if (SpeedDiggerItems.PICKAXE_GUNPOWDER.equals(item)
				|| SpeedDiggerItems.PICKAXE_SPEEDPOWDER.equals(item)) {
			breakBlockRecursive(new ArrayList<>(), world, block, pos, tool, tool.getRange(), BlockBreakDirection.ALL,
					player);
		} else if (SpeedDiggerItems.SHOVEL_GUNPOWDER.equals(item) || SpeedDiggerItems.SHOVEL_SPEEDPOWDER.equals(item)) {
			breakBlockRecursive(new ArrayList<>(), world, block, pos, tool, tool.getRange(), BlockBreakDirection.ALL,
					player);
		}
	}

	/**
	 * break block recursively;
	 * 
	 * @param visitedBlocks
	 * @param world
	 * @param block
	 * @param tool
	 * @param pos
	 * @param radius
	 *            to drop block; if null, no limit is given but the border of non
	 *            equal blocks
	 * @param blockBreakDirection
	 * @param player
	 */
	private void breakBlockRecursive(List<String> visitedBlocks, World world, Block block, BlockPos pos,
			RangeableTool tool, Integer radius, BlockBreakDirection blockBreakDirection, EntityPlayer player) {
		if (visitedBlocks.contains(pos.toString())) {
			return; // reduce loops
		} else {
			visitedBlocks.add(pos.toString());
		}
		// remove block from world
		IBlockState blockState = world.getBlockState(pos);
		if (tool.canBreakNeigbbors(blockState)) {
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
					breakBlockRecursive(visitedBlocks, world, block, pos.north(), tool, nextRadius, blockBreakDirection,
							player);
					breakBlockRecursive(visitedBlocks, world, block, pos.north().east(), tool, nextRadius,
							blockBreakDirection, player);
					breakBlockRecursive(visitedBlocks, world, block, pos.north().west(), tool, nextRadius,
							blockBreakDirection, player);
					breakBlockRecursive(visitedBlocks, world, block, pos.south(), tool, nextRadius, blockBreakDirection,
							player);
					breakBlockRecursive(visitedBlocks, world, block, pos.south().east(), tool, nextRadius,
							blockBreakDirection, player);
					breakBlockRecursive(visitedBlocks, world, block, pos.south().west(), tool, nextRadius,
							blockBreakDirection, player);
					breakBlockRecursive(visitedBlocks, world, block, pos.east(), tool, nextRadius, blockBreakDirection,
							player);
					breakBlockRecursive(visitedBlocks, world, block, pos.west(), tool, nextRadius, blockBreakDirection,
							player);

					breakBlockRecursive(visitedBlocks, world, block, pos.up(), tool, nextRadius, blockBreakDirection,
							player);

					if (BlockBreakDirection.ALL.equals(blockBreakDirection)) {
						breakBlockRecursive(visitedBlocks, world, block, pos.down(), tool, nextRadius,
								blockBreakDirection, player);
					}
				}
			}
		}
	}
}
