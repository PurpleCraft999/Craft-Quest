package purple.mod.items.swords;

import java.util.List;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
// import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.nbt.NbtCompound;

// import net.minecraft.scoreboard.ScoreboardCriterion;
// import net.minecraft.scoreboard.ScoreboardObjective;
// import net.minecraft.scoreboard.Team;
// import net.minecraft.scoreboard.ScoreboardCriterion.RenderType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import purple.mod.CraftQuest;
import purple.mod.ModItems;
// import purple.mod.management.CraftQuestAttributeHandler;
import purple.mod.management.TeamManager;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
// import net.minecraft.entity.EntityType;
public class UndeadSword extends SwordItem {
    final int MAX_SUMMONS = 25;
    // public TeamManager teamManager;
    public static final String TeamName = "summonedMobTeam";

    static{
        CraftQuest.TEAMS.put(TeamName, null);
    }
    final String summonCountKey = "summon count";
    TeamManager teamManager;
    // Team summonedTeam;
    //TODO make summons damage other things
    public UndeadSword(){
        super(ToolMaterials.IRON, 3, ModItems.SWORD_SPEED, new FabricItemSettings());
        // this.teamManager = CraftQuest.TEAMS.get(TeamName);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world instanceof ServerWorld server) {
            // MattMod.LOGGER.info("server");
            int summon_count = getNbtSummonCount(stack);
            if (user.getAbilities().creativeMode){
                summon_count = MAX_SUMMONS;
            }
            if (summon_count >0){
                for (int i = 0;i<summon_count;i++){
                    // MattMod.LOGGER.info("Spawn");
                    MobEntity summonedMob = getSummon(world);
                    summonedMob.initialize(server, world.getLocalDifficulty(user.getBlockPos()), SpawnReason.MOB_SUMMONED, null, null);
                    summonedMob.setPos(user.getPos().x, user.getPos().y, user.getPos().z);
                    // summonedMob.setCustomName(Text.literal(user.getEntityName()+"_summoned_minion"));
                    teamManager.addMember(summonedMob);
                
                    summonedMob.setTarget(null);
                    summonedMob.setAttacking(false);
                    
                    
                    server.spawnEntity(summonedMob);
                    // addMobToSummons(stack, summonedMob);
                }


                setNbtSummonCount(stack,0);
                return TypedActionResult.success(stack);
            } else{
                return TypedActionResult.fail(stack);
            }
        }


        return TypedActionResult.pass(stack);
    }
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //         if (this.teamManager ==null && entity instanceof PlayerEntity livingEntity && entity.getWorld() instanceof ServerWorld serverWorld){
        //     teamManager = TeamManager.makeFriendlyTeam(livingEntity, serverWorld, TeamName);
        //     CraftQuest.LOGGER.info("added to team");
        // }
        if (teamManager ==null){
        teamManager = CraftQuest.TEAMS.get(TeamName);
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
    
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target.isDead()){
            if (getNbtSummonCount(stack)<this.MAX_SUMMONS){
                setNbtSummonCount(stack, getNbtSummonCount(stack)+1);
            }
            
        } else{
            teamManager.setTeamTarget(target);

        }
        return super.postHit(stack, target, attacker);
    }
    
    void setNbtSummonCount(ItemStack stack,int value){
        if (stack != null){
        NbtCompound nbt = stack.getOrCreateNbt();
        
        nbt.putInt(summonCountKey, value);
        }
    }
    int getNbtSummonCount(ItemStack stack){
        if (stack != null){
        NbtCompound nbt = stack.getNbt();
        if (nbt != null) {
            return nbt.getInt(summonCountKey);
        };
        }
        return -1;
    }
    // void addMobToSummons(ItemStack stack,MobEntity mob){
    //     NbtCompound nbt = stack.getOrCreateNbt();
    //     NbtList list = nbt.getList("saved summons", NbtElement.INT_ARRAY_TYPE);

    //     list.add(NbtHelper.fromUuid(mob.getUuid()));
    //     nbt.put("saved summons", list);
        

    // }
    
    // ArrayList<UUID> getSummonsList(ItemStack stack){
    //     NbtList list = stack.getOrCreateNbt().getList("saved summons", NbtElement.INT_ARRAY_TYPE);
    //     ArrayList<UUID> summonList = new ArrayList<>();
    //     for (int i =0;i<list.size();i++){
            
    //     summonList.add(  NbtHelper.toUuid(list.get(0)));
    //     list.remove(0);
    //     }
    //     return summonList;
    // }



  @Override
  public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
      
    tooltip.add(Text.translatable(("count: "+(Integer)getNbtSummonCount(stack)).toString()));
    // tooltip.add(Text.translatable(getSummonsList(stack).toString()));
    // if (teamManager !=null){
    // tooltip.add(Text.translatable(teamManager.toString()));}

      super.appendTooltip(stack, world, tooltip, context);
  }
   MobEntity getSummon(World world){
         return new ZombieEntity(world);
   }



    
}
