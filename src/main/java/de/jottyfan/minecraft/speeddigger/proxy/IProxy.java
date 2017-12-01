package de.jottyfan.minecraft.speeddigger.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * 
 * @author jotty
 *
 */
public interface IProxy {
	
	/**
	 * register custom model of item with special metadata
	 * 
	 * @param item to be registered
	 * @param metadata to be used for registration
	 * @param registryName to be used for registration
	 */
	public void registerCustomModel(Item item, int metadata, ResourceLocation registryName);

}
