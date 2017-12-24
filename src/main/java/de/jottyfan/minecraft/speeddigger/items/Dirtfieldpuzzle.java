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
public class Dirtfieldpuzzle extends Item {

	public Dirtfieldpuzzle(CreativeTabs tabs) {
		super();
		super.setRegistryName(SpeedDigger.MODID, "dirtfieldpuzzle");
		super.setUnlocalizedName("dirtfieldpuzzle");
		super.setCreativeTab(tabs);
	}

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		BlockPos pos = player.getPosition().down();

		world.destroyBlock(pos, true);
		world.destroyBlock(pos.up(), true);
		List<BlockPos> positions = new ArrayList<>();
		for (int x = pos.getX() - 1; x <= pos.getX() + 1; x++) {
			for (int z = pos.getZ() - 1; z <= pos.getZ() + 1; z++) {
				positions.add(new BlockPos(x, pos.getY(), z));
			}
		}
		IBlockState dirt = Blocks.DIRT.getDefaultState();
		for (BlockPos p : positions) {
			world.destroyBlock(p, true);
			world.destroyBlock(p.up(), true);
			world.setBlockState(p, dirt);
		}
		player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
		return super.onItemRightClick(world, player, hand);
	}
}
