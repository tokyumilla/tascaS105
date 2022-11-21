package n1exercici3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.Collator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args)  {

        File file = new File ("D:/cursoespecializacionjava/documentos");
        System.out.println("La carpeta " + file.getName() + " tiene la siguiente estructura (de abajo a arriba):" );
        try {
            printTreeOfFiles(file);
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
                    listOfFiles.add(files[i].getName()+"(D)"+ Files.getLastModifiedTime(files[i].toPath()).toString());
                    printTreeOfFiles(files[i]);
                } else {
                    listOfFiles.add(files[i].getName()+"(F)" + Files.getLastModifiedTime(files[i].toPath()).toString());
                }
            }
            System.out.println("La carpeta " + file.getName() + " contiene:");
            System.out.println(listOfFiles);

        }

    }

}