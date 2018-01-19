package db.xml.fileFounder;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;

public class FileFounder implements Founder {

    private Path path;
    private String extension;
    private List<String> fileList;
    private int maxDepth;

    private class SFV extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (file.getFileName().toString().endsWith(extension)) {
                fileList.add(file.toAbsolutePath().toString());
            }
            return CONTINUE;
        }
    }

    public FileFounder(String pathName, String extension, int maxDepth) {
        this.extension = extension;
        this.path = Paths.get(pathName);
        this.fileList = new ArrayList<>();
        this.maxDepth = maxDepth;
    }

    public List<String> getResourcesList() {
        try {
            EnumSet<FileVisitOption> opts = EnumSet.of(FOLLOW_LINKS);
            Files.walkFileTree(path, opts,  maxDepth, new SFV());
        } catch (IOException e) {
            System.out.println("Can't build path tree!");
        }
        return fileList;
    }
}
