package de.jottyfan.minecraft.speeddigger.util;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 
 * @author jotty
 *
 */
public class SpeedDiggerTabs extends CreativeTabs {
	
	private final String modId;
	private final String iconname;
	
	public SpeedDiggerTabs(String modId, String iconname) {
		super(SpeedDigger.MODID);
		this.modId = modId;
		this.iconname = iconname;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		StringBuilder buf = new StringBuilder(modId);
		buf.append(":").append(iconname);
		Item item = Item.getByNameOrId(buf.toString());
		return new ItemStack(item);
	}
}
