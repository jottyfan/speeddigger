package de.jottyfan.minecraft.speeddigger.items;

import java.util.ArrayList;
import java.util.List;

import de.jottyfan.minecraft.speeddigger.SpeedDigger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * 
 * @author jotty
 *
 */
public class Dirtfield extends Item {

	private Integer fieldsize;

	public Dirtfield(CreativeTabs tabs) {
		super();
		this.fieldsize = 4;
		super.setRegistryName(SpeedDigger.MODID, "dirtfield");
		super.setUnlocalizedName("dirtfield");
		super.setCreativeTab(tabs);
	}

	public Item setFieldsize(Integer fieldsize) {
		this.fieldsize = fieldsize;
		return this;
	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		BlockPos pos = player.getPosition();

		world.destroyBlock(pos, true);
		world.destroyBlock(pos.up(), true);
		List<BlockPos> positions = new ArrayList<>();
		for (int x = pos.getX() - fieldsize; x <= pos.getX() + fieldsize; x++) {
			for (int z = pos.getZ() - fieldsize; z <= pos.getZ() + fieldsize; z++) {
				positions.add(new BlockPos(x, pos.getY(), z));
			}
		}
		IBlockState plowedField = Blocks.FARMLAND.getDefaultState();
		for (BlockPos p : positions) {
			world.destroyBlock(p, true);
			world.destroyBlock(p.up(), true);
			world.setBlockState(p, plowedField);
		}
		world.destroyBlock(pos, true);
		player.setHeldItem(hand, new ItemStack(Items.AIR));
		return super.onItemRightClick(world, player, hand);
	}
}
