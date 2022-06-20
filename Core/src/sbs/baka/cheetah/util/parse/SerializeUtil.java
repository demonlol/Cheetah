package sbs.baka.cheetah.util.parse;

import java.io.*;

public class SerializeUtil {

    public static void serializeObject(Object obj, String fileName) {
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deserializeObject(String fileName) {
        Object obj = null;
        try(ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(fileName))) {
            obj = inStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("No class found for serialization");
            e.printStackTrace();
        }
        return obj;
    }

}
