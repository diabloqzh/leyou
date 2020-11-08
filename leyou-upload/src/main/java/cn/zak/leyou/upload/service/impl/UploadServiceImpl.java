package cn.zak.leyou.upload.service.impl;

import cn.zak.leyou.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//@Service
public class UploadServiceImpl implements UploadService {
    private static final List<String>  CONTENT_TYPE= Arrays.asList("image/gif","image/jpeg","image/png");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);
    @Value("${upload.image.path}")
    private String UploadPath;
    @Override
    public String uploadImage(MultipartFile file) {
        //文件大小,由于直接在服务器进行了限制,所以不需要了
        //校验文件类型
        String originalFilename = file.getOriginalFilename();


        String contentType = file.getContentType();
        if(!CONTENT_TYPE.contains(contentType)){
            LOGGER.info("文件类型不合法:{}",originalFilename);
            return null;
        }
        //校验文件数据/(内容)
        BufferedImage read = null;
        try {
            read = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            LOGGER.info("文件内容不合法:{}",originalFilename);
            e.printStackTrace();
            return null;
        }
        if(read==null){
            LOGGER.info("文件内容不合法:{}",originalFilename);
            return null;
        }

        //产生上传文件目录和文件名

        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(new Date());
        File path = new File(this.UploadPath+File.separator+dateStr);
        if(!path.exists()){
            path.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        //用split的方式获取(比较low,但是很实用)
        String[] filenameSplit = originalFilename.split("\\.");

        System.out.println(filenameSplit[filenameSplit.length - 1]);
//        String extName = filenameSplit[filenameSplit.length - 1];
        //用StringUtils获取
        String extName = StringUtils.substringAfterLast(originalFilename, ".");
        String filename=uuid+"."+extName;
        String url="http://img.leyou.com/"+dateStr+"/"+filename;
        String filepath=path+File.separator+filename;
        File file1 = new File(filepath);


        //保存到文件服务器
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回url

        return url;
    }
}
