package team.service;

import team.domain.*;

/**
 * 负责将Data中的数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法。
 *
 */
public class NameListService {
    private Employee[] employees;

    //根据项目提供的Data类构建相应大小的employees数组
    //再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer和Architect对象，以及相关联的Equipment子类的对象
    //将对象存于数组中
    public NameListService() {
        employees = new Employee[Data.EMPLOYEES.length];

        for(int i = 0;i<Data.EMPLOYEES.length;i++){
            //All the employees in the data arryas
            //convert the String into the primitive type

            int position = Integer.parseInt(Data.EMPLOYEES[i][0]);
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);

            Equipment equipment1;
            double bonus;
            int stock;

            switch (position){

                case Data.EMPLOYEE:
                    employees[i] = new Employee(id,name,age,salary);
                    break;
                case Data.PROGRAMMER:
                    Equipment equipment = createEquipment(i);
                    employees[i] = new Programmer(id,name,age,salary,equipment);
                    break;
                case Data.DESIGNER:
                    equipment1 = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    employees[i] = new Designer(id,name,age,salary,equipment1,bonus);
                    break;
                case Data.ARCHITECT:
                    equipment1 = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
                    employees[i] = new Architect(id,name,age,salary,equipment1,bonus,stock);
                    break;
            }

        }
    }

    private Equipment createEquipment(int i) {
        int equipmentType = Integer.parseInt(Data.EQUIPMENTS[i][0]);
        String model = Data.EQUIPMENTS[i][1];
        String display = Data.EQUIPMENTS[i][2];

        try{
            if(equipmentType == Data.PC){
                return new PC(model,display);
            }

            if(equipmentType == Data.NOTEBOOK){
                double price = Double.parseDouble(Data.EQUIPMENTS[i][2]);
                return new NoteBook(model,price);
            }

            if(equipmentType == Data.PRINTER){
                //there is no difference between model and name, or diplay and type
                return new Printer(model,display);
            }
        }catch (RuntimeException e){
            System.out.println("No such equipment!");
        }
        return null;
    }

    public Employee[] getAllEmployees() {
        return employees;
    }

    //get the specific employee object in the array
    public Employee getEmployees(int id) throws TeamException {
         for(int i=0;i< employees.length;i++){
             if(employees[i].getId() == id){
                 return employees[i];
             }
        }
        throw new TeamException("找不到指定员工");
    }

//    public static void main(String[] args) {
//        NameListService nameListService = new NameListService();
//        Employee[] employees = nameListService.getAllEmployees();
//
//    }
}
