package n1exercici1;

import java.io.IOException;
import java.nio.file.*;
import java.text.Collator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet <String> listOfFiles = new TreeSet<>(Collator.getInstance());
        Path dir = Paths.get("D:/documentos");
        printListOfFiles(listOfFiles,dir);
    }

    public static void printListOfFiles (TreeSet<String> listOfFiles, Path dir) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                listOfFiles.add(file.getFileName().toString());
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }

        System.out.println(listOfFiles.toString());

    }
}