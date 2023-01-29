package team.domain;

public class Printer implements Equipment{
    private String name;
    private String type;//表示机器的类型

    public Printer() {
    }

    public Printer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getDescription() {
        return name+'('+type+')';
    }
}
