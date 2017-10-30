package de.jottyfan.minecraft.speeddigger.world;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import de.jottyfan.minecraft.speeddigger.util.SpeedDiggerBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
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
		Integer dimension = world.provider.getDimension();
		if (dimension == 0) { // overworld
			generateOverworldOres(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
		} else if (dimension == 1) { // the end
			// no ore generation now
		} else if (dimension == -1) { // nether
			generateNetherOres(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
		} else { // other mods
			generateOverworldOres(world, random, chunkX, chunkZ, chunkGenerator, chunkProvider);
		}
	}

	/**
	 * generate nether ores
	 * 
	 * @param world
	 * @param random
	 * @param chunkX
	 * @param chunkZ
	 * @param chunkGenerator
	 * @param chunkProvider
	 */
	private void generateNetherOres(World world, Random random, int chunkX, int chunkZ, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		addOreSpawn(SpeedDiggerBlocks.ORE_NETHER_SULPHOR, world, random, chunkX, chunkZ, 3, 3, 18, 15, 5, 250, null,
				Blocks.NETHERRACK);
	}

	/**
	 * generate overworld ores
	 * 
	 * @param world
	 * @param random
	 * @param chunkX
	 * @param chunkZ
	 * @param chunkGenerator
	 * @param chunkProvider
	 */
	private void generateOverworldOres(World world, Random random, int chunkX, int chunkZ,
			IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		addOreSpawn(SpeedDiggerBlocks.ORE_SULPHOR, world, random, chunkX, chunkZ, 1, 1, 3, 90, 5, 250, Blocks.LAVA,
				Blocks.STONE);
		addOreSpawn(SpeedDiggerBlocks.ORE_SULPHOR, world, random, chunkX, chunkZ, 1, 1, 3, 90, 48, 250, Blocks.STONE,
				Blocks.STONE);
		addOreSpawn(SpeedDiggerBlocks.ORE_SALPETER, world, random, chunkX, chunkZ, 5, 5, 5, 90, 32, 250, null,
				Blocks.STONE);
		addOreSpawn(SpeedDiggerBlocks.ORE_SAND_SALPETER, world, random, chunkX, chunkZ, 5, 5, 5, 90, 32, 250, null,
				Blocks.SANDSTONE);
	}

	/**
	 * spawn ore in world
	 * 
	 * @param ore
	 * @param world
	 * @param random
	 * @param blockXPos
	 * @param blockZPos
	 * @param sizeX
	 * @param siteZ
	 * @param maxVeinSize
	 * @param chance
	 * @param minY
	 * @param maxY
	 * @param blocksToSpawnIn
	 */
	private void addOreSpawn(Block ore, World world, Random random, int blockXPos, int blockZPos, int sizeX, int sizeZ,
			int maxVeinSize, int chance, int minY, int maxY, Block neighbourBlock, Block... blocksToSpawnIn) {
		int diced = random.nextInt(100);
		if (chance > diced) {
			int oreSrcAmount = random.nextInt(64); // for there are 256 blocks, fill at least a quarter of them in a chunk
			for (int j = 0; j < oreSrcAmount; j++) {
				Set<String> blockNames = new HashSet<>();
				for (Block block : blocksToSpawnIn) {
					blockNames.add(block.getUnlocalizedName());
				}
				int diffMinMaxY = maxY - minY;
				int posX = blockXPos + random.nextInt(sizeX);
				int posY = minY + random.nextInt(diffMinMaxY);
				int posZ = blockZPos + random.nextInt(sizeZ);

				BlockPos pos = new BlockPos(posX, posY, posZ);
				for (int i = 0; i < maxVeinSize; i++) {
					BlockPos posNew = pos.add(random.nextInt(3) - 1, random.nextInt(3) - 1, random.nextInt(3) - 1);
					if (!posNew.equals(pos)) {
						pos = posNew;
						String oldBlockName = world.getBlockState(pos).getBlock().getUnlocalizedName();
						if (neighbourBlock == null || checkNeighbour(world, pos, neighbourBlock.getUnlocalizedName())) {
							if (blockNames.contains(oldBlockName)) {
								world.setBlockState(pos, ore.getDefaultState(), 2);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * check if blocks around have one that is called unlocalizedName
	 * 
	 * @param world
	 * @param pos
	 * @param unlocalizedName
	 * @return
	 */
	private boolean checkNeighbour(World world, BlockPos pos, String unlocalizedName) {
		for (int x = pos.getX() - 1; x > pos.getX() + 1; x++) {
			for (int y = pos.getY() - 1; y > pos.getY() + 1; y++) {
				for (int z = pos.getZ() - 1; z < pos.getZ() + 1; z++) {
					if (world.getBlockState(new BlockPos(x, y, z)).getBlock().getUnlocalizedName()
							.equals(unlocalizedName)) {
						return true; // found, abort to reduce calculation time
					}
				}
			}
		}
		return false;
	}
}
