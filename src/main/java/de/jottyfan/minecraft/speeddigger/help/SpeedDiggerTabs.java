package de.jottyfan.minecraft.speeddigger.help;

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
	
	public SpeedDiggerTabs() {
		super(SpeedDigger.MODID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack getTabIconItem() {
		StringBuilder buf = new StringBuilder(SpeedDigger.MODID);
		buf.append(":").append("gunpowderaxe");
		Item item = Item.getByNameOrId(buf.toString());
		return new ItemStack(item);
	}
}
