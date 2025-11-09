package purple.mod.items.pickaxes;



import java.util.HashSet;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterials;

import java.util.ArrayDeque;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import purple.mod.ModItems;
// import purple.mod.CraftQuest;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
public class MinerPickaxe extends PickaxeItem {
    public MinerPickaxe(){
         super(ToolMaterials.DIAMOND, 1, ModItems.PICKAXE_SPEED, new FabricItemSettings());
    }
   
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (state.isIn(ConventionalBlockTags.ORES)){
            
            ArrayDeque<BlockPos> queue = new ArrayDeque<BlockPos>();
            queue.add(pos);
            Block mainBlock = state.getBlock();
            HashSet<BlockPos> broken = new HashSet<>();
            // MattMod.LOGGER.info(queue.toString());
            while(!queue.isEmpty()){
                if (world.isClient) return false;
                
                // MattMod.LOGGER.info(queue.toString());
                BlockPos blockPos = queue.poll();
                if (!broken.contains(blockPos)){
                

                world.breakBlock(blockPos, true, miner);
                
                for (Direction direction : Direction.values()) {
                    BlockPos neighborPos = blockPos.offset(direction);
                    
                    BlockState neighborState = world.getBlockState(neighborPos);
                    
                    Block block = neighborState.getBlock();
                    // MattMod.LOGGER.info("other block pos: "+neighborPos);
                    if (block == mainBlock && !queue.contains(neighborPos)) {
                        queue.add(neighborPos);
                        // MattMod.LOGGER.info("found block");
                        
                    }
                }
                }
                // queue.remove(0);
            }
        }
        
        return super.postMine(stack, world, state, pos, miner);
    }


}
