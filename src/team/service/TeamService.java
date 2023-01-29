package team.service;

import team.domain.Architect;
import team.domain.Designer;
import team.domain.Employee;
import team.domain.Programmer;

public class TeamService {
    private int counter = 1; //unique id
    public static final int MAX_MEMBER = 5; //max member number of the team

    private final Programmer[] team = new Programmer[MAX_MEMBER]; //store the current object of the team

    private int total = 0; //record the actual number of the team


    //注意返回的数组Programmer不能直接return team，因为后面遍历会把没有填满的给遍历从而nullpointerexception
    public Programmer[] getTeam(){
        Programmer[] getTeam  = new Programmer[total];
        for(int i=0; i< getTeam.length;i++){
            getTeam[i] = this.team[i];
        }
        return getTeam;
    }
    /*
    失败信息包含以下几种：
    成员已满，无法添加
    该成员不是开发人员，无法添加
    该员工已在本开发团队中
    该员工已是某团队成员
    该员正在休假，无法添加
    团队中至多只能有一名架构师
    团队中至多只能有两名设计师
    团队中至多只能有三名程序员
     */
    public void addMember(Employee e) throws TeamException{
        int ArchitectCounter = 0;
        int programmerCounter = 0;
        int designerCounter = 0;

        //check if we reach the condition of team exception
        if(total==MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }
        if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }

        Programmer p = (Programmer) e;

        if(p.getStatus().getNAME().equals("VOCATION")){
            throw new TeamException("该员正在休假，无法添加");
        }
        if(p.getStatus().getNAME().equals("BUSY")){
            throw new TeamException("该员工已是某团队成员");
        }

        for(int i=0;i<total;i++){
            if(team[i].getId() == e.getId()){
                throw new TeamException("该员工已在本开发团队中");
            }
            if(team[i] instanceof Architect){
                ArchitectCounter++;

            }else if(team[i] instanceof Designer){
                designerCounter++;
            }else{
                programmerCounter++;
            }
        }

        if(ArchitectCounter >=1 && p instanceof Architect){
            throw new TeamException("团队中至多只能有一名架构师");
        }else if(designerCounter >= 2 && p instanceof Designer){
            throw new TeamException("团队中至多只能有两名设计师");
        }else if(programmerCounter >= 3){
            throw new TeamException("团队中至多只能有三名程序员");
        }

        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
        team[total] = p; //assign the current p into the current index of team
        total++;
    }

    /**
     * 方法：从团队中删除成员
     *  @param：待删除成员的memberId
     * @throws：找不到指定memberId的员工，删除失败
     *
     */
    public void removeMember(int memberId) throws TeamException{
        int i;

        for(i=0;i<total;i++){
            if(team[i].getMemberId() == memberId){
                //找到了指定成员
                //被删除的成员状态重新set为FREE
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        //for loop后i等于total时说明超出了范围
        if(i == total){
            throw new TeamException("找不到指定memeberID的员工，删除失败");
        }

        //此后执行的都是删除team中成员以及对team重新排序的操作
        //j表示从id所在的index开始到倒数第二个index的位置(total-1)
        for(int j=i;j<total-1;j++) {
            //team[j+1]是team最后一个index，不然会超出bound
            team[j] = team[j + 1];
        }

        //成员数减一
        total--;
        //最后一个set为null
        team[total] = null;
    }
}
