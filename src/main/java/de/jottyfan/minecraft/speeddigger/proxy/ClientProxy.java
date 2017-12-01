package de.jottyfan.minecraft.speeddigger.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author jotty
 *
 */
@SideOnly(Side.CLIENT)
public class ClientProxy implements IProxy {
	@Override
	public void registerCustomModel(Item item, int metadata, ResourceLocation registryName) {
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(registryName, "inventory"));
	}
}
