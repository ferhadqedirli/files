/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author User
 */
public class FileUtility {

    private static void writeToFile(String fileName, String text, boolean append) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, append))) {
            bw.write(text);
        }
    }

    public static void appendToFile(String fileName, String text) throws IOException {
        writeToFile(fileName, text, true);
    }

    public static void writeToFile(String fileName, String text) throws IOException {
        writeToFile(fileName, text, false);
    }

    public static String readFile(String fileName) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\User\\Desktop\\" + fileName);
        try (InputStream in = new FileInputStream(file);
                InputStreamReader r = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(r);) {
            String line = "";
            String result = line;
            while ((line = reader.readLine()) != null) {
                result += line + "\n";
            }
            return result;
        }
    }

    public static byte[] readBytes(String fileName) throws FileNotFoundException, IOException {
        File file = new File("C:\\Users\\User\\Desktop\\" + fileName);
        byte[] bytesArray = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file);) {
            fis.read(bytesArray);
            return bytesArray;
        }
    }

    public static void writeBytes(String fileName, byte[] data) throws FileNotFoundException, IOException {

        File file = new File("C:\\Users\\User\\Desktop\\" + fileName);
        try (
                FileOutputStream fop = new FileOutputStream(file);) {
            fop.write(data);
            fop.flush();
        }
    }

    //NIO ile yazilib oxunmasi
    public static void writeBytesNio(byte[] data, String fileName) throws IOException {
        Path filePath = Paths.get("C:\\Users\\User\\Desktop\\" + fileName);
        Files.write(filePath, data);
    }

    public static byte[] readBytesNio(String fileName) throws IOException {
        Path filePath = Paths.get("C:\\Users\\User\\Desktop\\" + fileName);
        byte[] bytesArray = Files.readAllBytes(filePath);
        return bytesArray;
    }

    //Obyektin file-a yazilib oxunmasi
    public static void writeObjectToFile(Serializable object, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(object);
        }
    }

    public static Object readObject(String fileName) throws IOException, ClassNotFoundException {
        Object obj;
        try (FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            obj = ois.readObject();
            return obj;
        }
    }

    public static void writeToFileByUrl(String fromFile, String toFile) throws MalformedURLException, IOException {
        URL website = new URL(fromFile);
        URLConnection con = website.openConnection();
        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);
        try (InputStream is = con.getInputStream();
                ReadableByteChannel rbc = Channels.newChannel(is);
                FileOutputStream fos = new FileOutputStream(toFile);) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

}
