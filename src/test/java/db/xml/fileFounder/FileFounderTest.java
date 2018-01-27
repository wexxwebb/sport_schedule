package db.xml.fileFounder;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FileFounderTest {

    private Founder fileFounder;
    private String stringPath;
    private Path path;

    public FileFounderTest() {
        this.stringPath = "src/test/testDir/";
        this.path = Paths.get(stringPath);
        this.fileFounder = new FileFounder(stringPath, "xml", 1);
    }

    @Test
    public void getResourcesList() throws IOException {

        Files.createDirectory(path);
        Files.createDirectory(Paths.get(stringPath + "/innerDir/"));
        Files.createFile(Paths.get(stringPath + "/innerDir/innerDirFile.xml"));
        List<String> sample = new ArrayList<>();

        Files.createFile(Paths.get(stringPath + "test1.xml"));
        sample.add(Paths.get(stringPath + "test1.xml").toAbsolutePath().toFile().getPath());
        Files.createFile(Paths.get(stringPath + "test2.xml"));
        sample.add(Paths.get(stringPath + "test2.xml").toAbsolutePath().toFile().getPath());
        Files.createFile(Paths.get(stringPath + "test3.xml"));
        sample.add(Paths.get(stringPath + "test3.xml").toAbsolutePath().toFile().getPath());
        Files.createFile(Paths.get(stringPath + "test4.xml"));
        sample.add(Paths.get(stringPath + "test4.xml").toAbsolutePath().toFile().getPath());

        sample = sample.stream().sorted().collect(Collectors.toList());

        List<String> test = fileFounder.getResourcesList().stream().sorted().collect(Collectors.toList());

        for (String string : test) {
            Files.deleteIfExists(Paths.get(string));
        }

        Files.deleteIfExists(Paths.get(stringPath + "/innerDir/innerDirFile.xml"));
        Files.deleteIfExists(Paths.get(stringPath + "/innerDir/"));
        Files.deleteIfExists(path);

        assertEquals(sample, test);

    }
}