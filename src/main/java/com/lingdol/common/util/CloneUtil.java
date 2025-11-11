package com.lingdol.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CloneUtil {
    /**
     * 深克隆
     */
    @SuppressWarnings("all")
    public static <T> T deepClone(T source) {
        if (source == null) return null;

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos);
             ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            oos.writeObject(source);
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("clone failed");
        }
    }
}
