package com.share.utils;

import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by wahyuoi on 12/18/15.
 */
public class Base64 {
    public static String convert(String base64, String ext, String imagePath) throws IOException {
        if (base64.contains(","))
            base64 = base64.substring(base64.indexOf(",")+1);
        BufferedImage bufferedImage = null;
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(base64);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        bufferedImage = ImageIO.read(bis);
        bis.close();
        String name = System.currentTimeMillis()+"."+ext;
        String path = imagePath +"/"+ name;
        File file = new File(path);
        ImageIO.write(bufferedImage, ext, file);
        return name;
    }
}
