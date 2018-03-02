package de.jottyfan.minecraft.speeddigger.world;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * 
 * @author jotty
 *
 */
public class OreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		int posX = chunkX * 16;
		int posZ = chunkZ * 16;

		Integer dimension = world.provider.getDimension();
		if (dimension == 0) { // overworld
			generateOverworldOres(world, random, posX, posZ, chunkGenerator, chunkProvider);
		} else if (dimension == 1) { // the end
			// no ore generation now
		} else if (dimension == -1) { // nether
			generateNetherOres(world, random, posX, posZ, chunkGenerator, chunkProvider);
		} else { // other mods
			generateOverworldOres(world, random, posX, posZ, chunkGenerator, chunkProvider);
		}
	}

	/**
	 * generate nether ores
	 * 
	 * @param world
	 * @param random
	 * @param posX
	 * @param posZ
	 * @param chunkGenerator
	 * @param chunkProvider
	 */
	private void generateNetherOres(World world, Random random, int posX, int posZ, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		addOreSpawn(SpeedDiggerBlocks.ORE_NETHER_SULPHOR, world, random, posX, posZ, 18, 20, 5, 250, Blocks.NETHERRACK);
	}

	/**
	 * generate overworld ores
	 * 
	 * @param world
	 * @param random
	 * @param posX
	 * @param posZ
	 * @param chunkGenerator
	 * @param chunkProvider
	 */
	private void generateOverworldOres(World world, Random random, int posX, int posZ, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		addOreSpawn(SpeedDiggerBlocks.DIRT_SALPETER, world, random, posX, posZ, 3, 2, 48, 128, Blocks.DIRT);
		addOreSpawn(SpeedDiggerBlocks.SAND_SALPETER, world, random, posX, posZ, 8, 4, 48, 128, Blocks.SAND);
		addOreSpawn(SpeedDiggerBlocks.ORE_SULPHOR, world, random, posX, posZ, 5, 5, 5, 250, Blocks.LAVA);
		addOreSpawn(SpeedDiggerBlocks.ORE_SULPHOR, world, random, posX, posZ, 5, 5, 5, 250, Blocks.STONE,
				Blocks.COBBLESTONE, Blocks.CONCRETE, Blocks.GRAVEL);
		addOreSpawn(SpeedDiggerBlocks.ORE_SALPETER, world, random, posX, posZ, 5, 5, 5, 250, Blocks.STONE,
				Blocks.COBBLESTONE, Blocks.CONCRETE, Blocks.GRAVEL);
		addOreSpawn(SpeedDiggerBlocks.ORE_SAND_SALPETER, world, random, posX, posZ, 5, 20, 5, 250, Blocks.SANDSTONE,
				Blocks.SAND);
	}

	/**
	 * spawn ore in world
	 * 
	 * @param ore
	 * @param world
	 * @param random
	 * @param posX
	 * @param posZ
	 * @param maxVeinSize
	 * @param count
	 * @param minY
	 * @param maxY
	 * @param blocksToSpawnIn
	 */
	private void addOreSpawn(Block ore, World world, Random random, int posX, int posZ, int maxVeinSize, int count,
			int minY, int maxY, Block... blocksToSpawnIn) {
		Set<String> blockNames = new HashSet<>();
		for (Block block : blocksToSpawnIn) {
			blockNames.add(block.getUnlocalizedName());
		}
		WorldGenMinable generator = new WorldGenMinable(ore.getBlockState().getBaseState(), maxVeinSize);
		for (int i = 0; i < count; i++) {
			BlockPos pos = new BlockPos(posX + random.nextInt(16), minY + random.nextInt(maxY - minY),
					posZ + random.nextInt(16));
			String oldBlockName = world.getBlockState(pos).getBlock().getUnlocalizedName();
			if (blockNames.contains(oldBlockName)) {
				for (int j = 0; j < maxVeinSize; j++) {
					Integer r = random.nextInt(3);
					switch (r) {
					case 0:
						pos = pos.west();
						break;
					case 1:
						pos = pos.north();
						break;
					case 2:
						pos = pos.east();
						break;
					case 3:
						pos = pos.south();
						break;
					}
					world.setBlockState(pos, ore.getDefaultState(), 2);
				}
			}
		}
	}
}
