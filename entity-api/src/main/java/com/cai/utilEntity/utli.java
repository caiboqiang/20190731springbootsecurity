package com.cai.utilEntity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class utli {
    /**
     * 生成二维码
     */
    private static String generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        String format = "png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ByteArrayOutputStream os = new ByteArrayOutputStream();//新建流。
        ImageIO.write(image, format, os);//利用ImageIO类提供的write方法，将bi以png图片的数据模式写入流。
        byte b[] = os.toByteArray();//从流中获取数据数组。
        String str = new BASE64Encoder().encode(b).replaceAll("\r\n", "");
        return str;

    }

    /**
     * 提取上传方法为公共方法
     *
     * @param uploadDir
     * @param file
     * @throws Exception
     */
    public static Map<String,String> executeUpload(String uploadDir, MultipartFile file) throws Exception {
        // 文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件名
        String filename = UUID.randomUUID() + suffix;
        // 服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);
        // 将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
        Map<String,String> map = new HashMap<String,String>();
        map.put("fileNam",filename);
        map.put("path",uploadDir);
        return map;
    }
}
