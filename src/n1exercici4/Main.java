package n1exercici4;

import java.io.*;
import java.nio.file.*;
import java.text.Collator;
import java.util.TreeSet;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;


public class Main {
    public static void main(String[] args)  {
        Path path = Paths.get("D:/cursoespecializacionjava/documentos");
        File file = path.toFile();
        String introduction = "La carpeta " + file.getName() + " tiene la siguiente estructura (de abajo a arriba): \n";


        printInTxt(introduction);
        try {
            printTreeInTxt(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
                    readingTxt(files[i]);
                }
            }
            String string = "La carpeta " + file.getName() + " contiene: \n";
            String string2 = listOfFiles.toString() + "\n";
            printInTxt(string);
            printInTxt(string2);

        }


    }

    public static void readingTxt (File file) {
        PathMatcher matcher =
                FileSystems.getDefault().getPathMatcher("glob:**.txt");
        Path path = file.toPath();
        if (matcher.matches(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line = null;
                while ((line=reader.readLine())!=null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}