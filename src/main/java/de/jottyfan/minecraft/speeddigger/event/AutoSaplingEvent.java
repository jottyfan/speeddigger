package de.jottyfan.minecraft.speeddigger.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author jotty
 *
 */
public class AutoSaplingEvent {

	/**
	 * get item from entityItem if not null
	 * 
	 * @param entityItem
	 *            to be used
	 * @return item if not null
	 */
	private Item getItemFromItemEntity(EntityItem entityItem) {
		return (entityItem == null || entityItem.getItem() == null) ? null : entityItem.getItem().getItem();
	}

	/**
	 * get block from item if item is plantable
	 * 
	 * @param item
	 *            to be used
	 * @return block if found and plantable, null otherwise
	 */
	private Block getBlockIfPlantable(Item item) {
		Block block = Block.getBlockFromItem(item);
		if (block instanceof IPlantable) {
			return block;
		} else {
			return null;
		}
	}

	@SubscribeEvent
	public void itemDecay(ItemExpireEvent event) {
		Item item = getItemFromItemEntity(event.getEntityItem());
		if (item != null) {
			Block block = getBlockIfPlantable(item);
			if (block != null) {
				BlockPos pos = event.getEntity().getPosition();
				if (block.canPlaceBlockAt(event.getEntityItem().world, pos)) {
					int metadata = item.getMetadata(event.getEntityItem().getItem());
					BlockStateContainer bsc = block.getBlockState();
					for (IBlockState ibs : bsc.getValidStates()) {
						if (metadata == block.getMetaFromState(ibs)) {
							event.getEntityItem().world.setBlockState(pos, ibs);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void itemToss(ItemTossEvent event) {
		Item item = getItemFromItemEntity(event.getEntityItem());
		if (item != null && getBlockIfPlantable(item) != null) {
			event.getEntityItem().lifespan = 100; // despawn time
		}
	}
}
