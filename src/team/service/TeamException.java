package team.service;

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

public class TeamException extends Exception{
    public TeamException(){

    }

    public TeamException(String message){
        super(message);
    }
}
