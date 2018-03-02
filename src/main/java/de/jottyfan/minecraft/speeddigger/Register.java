package de.jottyfan.minecraft.speeddigger;

import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 
 * @author jotty
 *
 */
public class Register {
	public void registerRecipes() {
		registerTools();
		registerShapedRecipes();
		registerShapelessRecipes();
	}

	private void registerTools() {
		GameRegistry.addShapedRecipe(SpeedDiggerItems.AXE_GUNPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.AXE_GUNPOWDER), "## ", "#| ", " | ", '#', Items.GUNPOWDER, '|',
				Items.STICK);
		GameRegistry.addShapedRecipe(SpeedDiggerItems.AXE_SPEEDPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.AXE_SPEEDPOWDER), "## ", "#| ", " | ", '#',
				SpeedDiggerItems.ITEM_SPEEDPOWDER, '|', Items.STICK);
		GameRegistry.addShapedRecipe(SpeedDiggerItems.PICKAXE_GUNPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.PICKAXE_GUNPOWDER), "###", " | ", " | ", '#', Items.GUNPOWDER, '|',
				Items.STICK);
		GameRegistry.addShapedRecipe(SpeedDiggerItems.PICKAXE_SPEEDPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.PICKAXE_SPEEDPOWDER), "###", " | ", " | ", '#',
				SpeedDiggerItems.ITEM_SPEEDPOWDER, '|', Items.STICK);
		GameRegistry.addShapedRecipe(SpeedDiggerItems.SHOVEL_GUNPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.SHOVEL_GUNPOWDER), " # ", " | ", " | ", '#', Items.GUNPOWDER, '|',
				Items.STICK);
		GameRegistry.addShapedRecipe(SpeedDiggerItems.SHOVEL_SPEEDPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.SHOVEL_SPEEDPOWDER), " # ", " | ", " | ", '#',
				SpeedDiggerItems.ITEM_SPEEDPOWDER, '|', Items.STICK);
	}

	private void registerShapedRecipes() {
		GameRegistry.addShapedRecipe(new ResourceLocation("speeddigger:dirtfieldhole"), null,
				new ItemStack(SpeedDiggerItems.ITEM_DIRTFIELDHOLE), "###", "# #", "###", '#',
				Item.getItemFromBlock(Blocks.DIRT));
		GameRegistry.addShapedRecipe(new ResourceLocation("speeddigger:dirtfieldpuzzle"), null,
				new ItemStack(SpeedDiggerItems.ITEM_DIRTFIELDPUZZLE), "###", "###", "###", '#',
				Item.getItemFromBlock(Blocks.DIRT));
		GameRegistry.addShapedRecipe(new ResourceLocation("speeddigger:dirtfield"), null,
				new ItemStack(SpeedDiggerItems.ITEM_DIRTFIELD), "###", "#h#", "###", '#',
				SpeedDiggerItems.ITEM_DIRTFIELDPUZZLE, 'h', SpeedDiggerItems.ITEM_DIRTFIELDHOLE);
	}

	private void registerShapelessRecipes() {
		GameRegistry.addShapelessRecipe(new ResourceLocation("speeddigger:gunpowder"), null,
				new ItemStack(Items.GUNPOWDER), Ingredient.fromItem(SpeedDiggerItems.ITEM_SULPHOR),
				Ingredient.fromItem(SpeedDiggerItems.ITEM_SALPETER), Ingredient.fromItem(Items.COAL));
		GameRegistry.addShapelessRecipe(SpeedDiggerItems.ITEM_SPEEDPOWDER.getRegistryName(), null,
				new ItemStack(SpeedDiggerItems.ITEM_SPEEDPOWDER, 1), Ingredient.fromItem(Items.GUNPOWDER),
				Ingredient.fromItem(Items.REDSTONE));
		GameRegistry.addShapelessRecipe(new ResourceLocation("speeddigger:field"), null,
				new ItemStack(SpeedDiggerItems.ITEM_FIELD), Ingredient.fromItem(SpeedDiggerItems.ITEM_DIRTFIELD),
				Ingredient.fromItem(Items.WATER_BUCKET), Ingredient.fromItem(Items.WOODEN_SHOVEL),
				Ingredient.fromItem(Items.REDSTONE), Ingredient.fromItem(Items.WOODEN_HOE));
	}
}
