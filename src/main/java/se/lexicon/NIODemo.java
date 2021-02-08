package se.lexicon;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIODemo {

    public static void main(String[] args) {
        try {
            ex7();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Path
    // absolute path always contains the root element
    // relative path must e combined with another path
    public static void ex1(){
        FileSystem fileSystem= FileSystems.getDefault();
        Path path1= fileSystem.getPath("destination/strings.txt");
        Path path2= Paths.get("destination","strings.txt");

        System.out.println("FileName: "+path1.getFileName());
        System.out.println("getParent:"+path1.getParent());
        System.out.println(path1.toAbsolutePath());
        System.out.println(Files.exists(path1));
    }


    public static void ex2(){
        try {
            /*Path dir = Files.createTempDirectory("my-dir");
            Path fileToCreatePath = dir.resolve("test-file.txt");
            System.out.println(fileToCreatePath);
             */
            Path testPath = Paths.get("dir");
            if (!Files.exists(testPath)){
                Files.createDirectory(testPath);
                Files.createFile(testPath.resolve("test.txt"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ex3(){
        try {
            Stream<Path> files = Files.list(Paths.get("."));
            files.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ex4(){
        try {
            Stream<Path> files = Files.walk(Paths.get("E:\\mproject\\g34_io_lecture"));
            List<Path> result = files.filter(Files::isRegularFile) // is a file
            .filter(path -> path.getFileName().toString().endsWith(".java"))
                    .collect(Collectors.toList());
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ex5(){
        FileSystem fileSystem= FileSystems.getDefault();
        Path path= fileSystem.getPath("dir/test.txt");
        System.out.println("-------------------------------");
        try {
            Stream<String> result = Files.lines(path);
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ex6(){
        FileSystem fileSystem= FileSystems.getDefault();
        Path path= fileSystem.getPath("dir/test.txt");
        String content = "Hey, there!\n Whats up?";
        try {
            Files.write(path,content.getBytes(StandardCharsets.UTF_8),StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ex7() throws IOException {
       Path f = Paths.get("source/java.png");
        Files.copy(Paths.get("source/java.png"),Paths.get("destination/java_copy_nio.png"),StandardCopyOption.REPLACE_EXISTING);
        Files.move(f,f,StandardCopyOption.REPLACE_EXISTING);
    }
}
