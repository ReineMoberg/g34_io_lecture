package se.lexicon;

import java.io.*;

public class IOBasicDemo {

    public static void main(String[] args) {
        File source = new File("source/java.png");
        File destination = new File("destination/java_copy2.png");
        //copyImage(source,destination);
        //copyImageBuffer(source,destination);

        String content = "\nTest";
        File file = new File("destination/strings.txt");
        //writeText(file, content);
       String data = readText(file);
        System.out.println("data = " + data);
    }

    // FileInputStream && FileOutputStream = read and write image
    public static void copyImage(File source, File destination) {
        try (
                InputStream inputStream = new FileInputStream(source);
                OutputStream outputStream = new FileOutputStream(destination);
        ) {
            int _byte;
            while ((_byte = inputStream.read()) != -1) {
                outputStream.write(_byte);
            }
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
    }

    // BufferedInputStream and BufferedOutputStream = read and write image
    public static void copyImageBuffer(File source, File destination) {
        try (
                FileInputStream inputStream = new FileInputStream(source);
                BufferedInputStream in = new BufferedInputStream(inputStream);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination))
        ) {

            byte[] buffer = new byte[1024]; // 1 MB
            int byteRead;
            while ((byteRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, byteRead);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // FileWriter
    public static void writeText(File destination, String text) {
        try (
                FileWriter writer = new FileWriter(destination, true);
        ) {
            if (!destination.exists()) {
                destination.createNewFile();
            }
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // FileReader
    public static String readText(File source) {
        StringBuilder sb = new StringBuilder();
        try (
                FileReader reader = new FileReader(source)
        ) {
            int i;
            while ((i = reader.read()) != -1) {
                // append to string
                char letter = (char) i;
                sb.append(letter);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
