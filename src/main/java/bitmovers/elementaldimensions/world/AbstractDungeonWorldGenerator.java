package bitmovers.elementaldimensions.world;

import bitmovers.elementaldimensions.dimensions.AirDungeonLocator;
import bitmovers.elementaldimensions.dimensions.Dimensions;
import bitmovers.elementaldimensions.dimensions.ores.ElementalDustBlock;
import bitmovers.elementaldimensions.init.BlockRegister;
import bitmovers.elementaldimensions.ncLayer.SchematicLoader;
import bitmovers.elementaldimensions.util.worldgen.WorldGenHelper;
import elec332.core.api.structure.GenerationType;
import elec332.core.world.StructureTemplate;
import elec332.core.world.WorldHelper;
import elec332.core.world.schematic.Schematic;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Elec332 on 6-1-2017.
 */
public abstract class AbstractDungeonWorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (validGenPoint(world, chunkX, chunkZ)){
            Schematic schematic = SchematicLoader.INSTANCE.getSchematic(getDungeonSchematic());
            if (schematic != null) {
                GenerationType type = getGenerationType(world);
                StructureTemplate structure = new StructureTemplate(schematic, type);
                BlockPos pos = randomPos(world, chunkX, chunkZ, random);
                structure.generateStructure(pos, world, chunkProvider);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    protected abstract boolean validGenPoint(World world, int chunkX, int chunkZ);

    protected abstract BlockPos randomPos(World world, int chunkX, int chunkZ, Random random);

    protected abstract ResourceLocation getDungeonSchematic();

    protected abstract GenerationType getGenerationType(World world);


}
