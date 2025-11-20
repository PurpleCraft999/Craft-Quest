package purple.mod.management;

import java.util.Collection;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import purple.mod.CraftQuest;

public class TeamManager {
    public Team team;
    private TeamManager(Team team){
        this.team = team;
    }





    public static TeamManager makeFriendlyTeam(LivingEntity user,ServerWorld server,String name){
        String userName = getName(user);

        



       String teamName =    CraftQuest.MOD_ID+"_"+ userName+ "_"+name;
    // String teamName = "TEAM";
       Scoreboard scoreboard = server.getScoreboard();

       if (scoreboard.getTeam(teamName) == null) {
           scoreboard.addTeam(teamName);
       }
       Team summonedMobTeam = scoreboard.getTeam(teamName);
       summonedMobTeam.setFriendlyFireAllowed(false);
       
            if (user.addCommandTag(teamName)){
                CraftQuest.LOGGER.info("Succesfully added "+userName+"to team"+teamName);
            }
        // summonedMobTeam.getScoreboard().addPlayerToTeam(userName, summonedMobTeam);
        CraftQuest.LOGGER.debug("added "+userName);
       return new TeamManager(summonedMobTeam);
   }

   public void addMember(LivingEntity livingEntity){
       String userName=getName(livingEntity);


       
    this.team.getScoreboard().addPlayerToTeam(userName, team);
   }

    static String getName(LivingEntity livingEntity){
        String userName;
        if (livingEntity instanceof PlayerEntity playerEntity) {
            userName = playerEntity.getEntityName();
           
        } else if (livingEntity instanceof ServerPlayerEntity serverPlayerEntity){

            userName = serverPlayerEntity.getEntityName();
        }
        
        
        else {
            userName = livingEntity.getUuidAsString();

        }
        return userName;
    }
    @Override
    public String toString() {
        
        return this.team.getName();
    }

    public Collection<String> getTeamMembers(){
        return this.team.getPlayerList();
    }

    public void setTeamTarget(LivingEntity target){
        if (target.getWorld() instanceof ServerWorld serverWorld){

        for (String teamMateUUID :getTeamMembers()) {
            UUID uuid = UUID.fromString(teamMateUUID);
           if  (serverWorld.getEntity(uuid) instanceof MobEntity mobEntity){
            mobEntity.setTarget(target);
           }
        }   
        }
    }
   
}
