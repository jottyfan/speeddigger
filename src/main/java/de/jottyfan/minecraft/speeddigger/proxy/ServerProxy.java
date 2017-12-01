package de.jottyfan.minecraft.speeddigger.proxy;

import javax.xml.ws.WebServiceClient;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author jotty
 *
 */
@SideOnly(Side.SERVER)
public class ServerProxy implements IProxy {
	@Override
	public void registerCustomModel(Item item, int metadata, ResourceLocation registryName) {
		// nothing to do on server side
	}
}
