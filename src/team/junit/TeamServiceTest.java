package team.junit;

import org.junit.Test;
import team.domain.Programmer;
import team.service.TeamService;

public class TeamServiceTest {
    @Test
    public void addTest(){
        TeamService team = new TeamService();
        Programmer[] currentTeam = team.getTeam();
        if(currentTeam.length == 0){
            System.out.println("开发团队目前没有成员");
        }else{
            for(int i = 0; i<currentTeam.length;i++){
                //i+1表示tid
                System.out.println((i+1)+"/"+currentTeam[i].getMemberId()+currentTeam[i].toString());
            }
        }
    }
}
