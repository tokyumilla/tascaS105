package n1exercici5;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Demo object = new Demo (5,"cuchara");
        String filename = "file.ser";

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(object);

            out.close();
            file.close();

            System.out.println("El objeto ha sido serializado");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Demo object1 = null;
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            object1 = (Demo)in.readObject();

            in.close();
            file.close();
            System.out.println("El objeto ha sido deserializado");
            System.out.println("El número del objeto es " + object1.getNumber());
            System.out.println("El nombre del objeto es " + object1.getName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }



    }
}
