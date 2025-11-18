package purple.mod.management;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import purple.mod.CraftQuest;

public class TeamManager {
     Team team;
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
        
        summonedMobTeam.getScoreboard().addPlayerToTeam(userName, summonedMobTeam);
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
           
        } else {
            userName = livingEntity.getUuidAsString();

        }
        return userName;
    }
   
}
