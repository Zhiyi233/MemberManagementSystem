package team.view;

import team.domain.Employee;
import team.domain.Programmer;
import team.service.NameListService;
import team.service.TeamException;
import team.service.TeamService;

public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();
    public static final String TITTLE = "ID\t 姓名\t工资\t职位\t\t状态\t\t奖金\t\t股票\t\t领用设备";
    private Employee[] employees;

    //主界面显示及控制方法
    public void enterMainMenu() {
        boolean isFlag = true;

        listAllEmployees();

        while(isFlag){
            char option = TSUtility.readMenuSelection();

            switch (option){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    listAllEmployees();
                    break;
                case '3':
                    deleteMember();
                    listAllEmployees();
                    break;
                case '4':
                    System.out.println("是否退出(Y/N)");
                    char exit = TSUtility.readConfirmSelection();

                    if(exit == 'Y'){
                        isFlag = false;
                    }else{
                        System.out.println("----------------------------------------------------------");
                        System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4):_");
                    }
            }
        }


    }

    //以表格形式列出公司所有成员
    public void listAllEmployees(){
        System.out.println("-----------------------开发团队调度软件----------------------");
        System.out.println(TITTLE);
        Employee[] employees = listSvc.getAllEmployees();
        for(int i =0; i<employees.length;i++){
            System.out.println(employees[i]);
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4):_");
    }
    //显示团队成员列表操作
    public void getTeam(){
        System.out.println("-----------------------团队成员列表----------------------");
        Programmer[] currentTeam = teamSvc.getTeam();
        if(currentTeam.length == 0){
            System.out.println("开发团队目前没有成员");
        }else{
            System.out.println("TID/"+TITTLE);
            for(int i = 0; i<currentTeam.length;i++){
                //i+1表示tid
                System.out.println(currentTeam[i].getMemberId()+"/"+currentTeam[i].toString());
            }
        }
        System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4):_");
    }

    //实现添加成员操作
    public void addMember() {
        System.out.println("---------------------添加成员---------------------\n" +
                "请输入要添加的员工ID：");
        int num = TSUtility.readInt(); //获取你要加入的团队成员id
        employees = listSvc.getAllEmployees();
        for(int i=0;i<employees.length;i++){
            if(employees[i].getId() == num){
                try {
                    teamSvc.addMember(employees[i]);
                } catch (TeamException e) {
                    System.out.println("失败原因："+e.getMessage());
                }
            }
        }
        System.out.println("添加成功");
        TSUtility.readReturn();
    }
    //实现删除成员操作
    public void deleteMember()  {
        System.out.print("---------------------删除成员---------------------\n" +
                "请输入要删除员工的TID：");
        int tId = TSUtility.readInt();
        System.out.print("确认是否删除(Y/N)：");
        char delete = TSUtility.readConfirmSelection();
        if(delete == 'Y'){
            try {
                teamSvc.removeMember(tId);
            } catch (TeamException e) {
                System.out.println("失败原因："+e.getMessage());
            }
            System.out.println("删除成功");
        }else if(delete == 'N'){
            System.out.println("取消删除");
        }

        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
