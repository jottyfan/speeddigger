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
public class Field extends Item {

	private Integer fieldsize;

	public Field(CreativeTabs tabs) {
		super();
		this.fieldsize = 4;
		super.setRegistryName(SpeedDigger.MODID, "field");
		super.setUnlocalizedName("field");
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
				positions.add(new BlockPos(x,pos.getY(),z));
			}
		}
		IBlockState plowedField = Blocks.FARMLAND.getDefaultState();
		for (BlockPos p : positions) {
			world.destroyBlock(p, true);
			world.setBlockState(p, plowedField);
		}
		world.setBlockState(pos, Blocks.WATER.getDefaultState());
		player.setHeldItem(hand, new ItemStack(Items.BUCKET));
		return super.onItemRightClick(world, player, hand);
	}
}
