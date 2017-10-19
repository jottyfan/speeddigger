package de.jottyfan.minecraft.speeddigger.items;

import java.util.Set;

import com.google.common.collect.Sets;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;

/**
 * 
 * @author jotty
 *
 */
public class GunpowderAxe extends ItemTool {

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });

	public GunpowderAxe(CreativeTabs tabs) {
		super(ToolMaterial.WOOD, EFFECTIVE_ON);
		this.attackDamage = 2;
		this.attackSpeed = 0.7f;
		super.setRegistryName(SpeedDigger.MODID, "gunpowderaxe");
		super.setUnlocalizedName("gunpowderaxe");
		super.setCreativeTab(tabs);
	}
}
