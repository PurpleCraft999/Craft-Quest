package purple.mod.items.other;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.world.ServerWorld;
import purple.mod.CraftQuest;

public class TeamManager {
     Team team;
    public TeamManager(){

    }





    public Team makeFriendlyTeam(LivingEntity user,ServerWorld server,String name){
        String userName;

        if (user instanceof PlayerEntity playerEntity){
            userName = playerEntity.getEntityName();

        } else{
            userName = user.getUuidAsString();

        }



       String teamName =    CraftQuest.MOD_ID+"_"+ userName+ "_"+name;
       Scoreboard scoreboard = server.getScoreboard();
       Team summonedMobTeamn = scoreboard.getTeam(teamName);
       if (summonedMobTeamn == null) {
           scoreboard.addTeam(teamName);
       }
       Team summonedMobTeam = scoreboard.getTeam(teamName);
       summonedMobTeam.setFriendlyFireAllowed(false);
        
        scoreboard.addPlayerToTeam(userName, summonedMobTeam);
        this.team = summonedMobTeam;
       return summonedMobTeam;
   }

   public void addMember(LivingEntity livingEntity){
       String userName;

       if (livingEntity instanceof PlayerEntity playerEntity) {
           userName = playerEntity.getEntityName();

       } else {
           userName = livingEntity.getUuidAsString();

       }
    this.team.getScoreboard().addPlayerToTeam(userName, team);
   }
   
}
