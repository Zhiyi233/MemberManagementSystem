package team.domain;

public class NoteBook implements Equipment{
    private String model;//表示机器的型号
    private double price;

    public NoteBook() {
    }

    public NoteBook(String model, double price) {
        this.model = model;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return model+'('+price+')';
    }
}
