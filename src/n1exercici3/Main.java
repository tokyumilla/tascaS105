package n1exercici3;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.TreeSet;
import static java.nio.file.StandardOpenOption.*;


public class Main {
    public static void main(String[] args)  {
        Path path = Paths.get("D:/cursoespecializacionjava/documentos");
        File file = path.toFile();
        String introduction = "La carpeta " + file.getName() + " tiene la siguiente estructura (de abajo a arriba): \n";

        System.out.println("La carpeta " + file.getName() + " tiene la siguiente estructura (de abajo a arriba):");

        try {
            printTreeOfFiles(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        printInTxt(introduction);
        try {
            printTreeInTxt(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }

    public static void printTreeOfFiles(File file) throws IOException {

        if (file.isDirectory()) {
            File [] files = file.listFiles();

            TreeSet<String> listOfFiles = new TreeSet<>(Collator.getInstance());
            for (int i =0; i< files.length; i++) {
                if(files[i].isDirectory()){
                    listOfFiles.add(files[i].getName()+"(D)"+ Files.getLastModifiedTime(files[i].toPath()));
                    printTreeOfFiles(files[i]);
                } else {
                    listOfFiles.add(files[i].getName()+"(F)" + Files.getLastModifiedTime(files[i].toPath()));
                }
            }
            System.out.println("La carpeta " + file.getName() + " contiene:");
            System.out.println(listOfFiles);

        }

    }

    public static void printTreeInTxt (File file) throws IOException {

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            TreeSet<String> listOfFiles = new TreeSet<>(Collator.getInstance());
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    listOfFiles.add(files[i].getName() + "(D)" + Files.getLastModifiedTime(files[i].toPath()));
                    printTreeInTxt(files[i]);
                } else {
                    listOfFiles.add(files[i].getName() + "(F)" + Files.getLastModifiedTime(files[i].toPath()));
                }
            }
            String string = "La carpeta " + file.getName() + " contiene: \n";
            String string2 = listOfFiles.toString() + "\n";
            printInTxt(string);
            printInTxt(string2);

        }


    }

    public static void printInTxt (String string) {
        byte[] data = string.getBytes();
        Path path = Paths.get("D:/cursoespecializacionjava/exercici3.txt");

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}