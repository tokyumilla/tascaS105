package n1exercici2;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Collator;
import java.util.TreeSet;
import java.util.stream.Collector;

public class FileVisitorImpl extends SimpleFileVisitor<Path> {
    private TreeSet<String> listOfFiles = new TreeSet <> (Collator.getInstance());

//    public TreeSet<String> getListOfFiles() {
//        return listOfFiles;
//    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("La carpeta " + dir.getFileName() + " contiene los siguientes ficheros ordenados " +
                "alfabéticamente:");

        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isDirectory()) {
            listOfFiles.add(file.getFileName()+"(D)");
        } else {
            listOfFiles.add(file.getFileName()+"(F)");
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        System.out.println(listOfFiles);
        listOfFiles.clear();
        return super.postVisitDirectory(dir, exc);
    }
}
