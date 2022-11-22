package n1exercici5;

import java.io.Serializable;

public class Demo implements Serializable {
    private int number;
    private String name;

    public Demo(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
