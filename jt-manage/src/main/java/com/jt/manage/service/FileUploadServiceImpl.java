package com.jt.manage.service;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	/**
	 * 1、 判断是否为图片 2、判断是否为恶意木马 3、开始实现文件的本地磁盘入库操作 4、为了检索方便，采用分文件存储 yyyy/MM/dd
	 * 5、为了防止图片重名，a)通过名字+随机参数+_hash.jpg b)UUID()的方式 c)随机数+毫秒数+公司名称+哈希
	 * 6、准备图片虚拟路径，为了让用户访问图片
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public PicUploadResult uploadFile(MultipartFile uploadFile) {

		PicUploadResult picUploadResult = new PicUploadResult();
		// 1 获取图片名
		String fileName = uploadFile.getOriginalFilename();
		// 2 判断是否是图片
		if (!fileName.matches("^.*\\.(jpg|png|gif)$")) {
			picUploadResult.setError(1);
			return picUploadResult;
		}
		// 3 判断是否为恶意木马
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();
			if (height == 0 || width == 0) {
				picUploadResult.setError(1);
				return picUploadResult;
			}
			//4 保存文件   
			//a) 定义本地磁盘路径
			String localDir="F:/jt/jt-upload/";
			//b) 准备日期格式的目录
			String dataDir=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			//c) 穿件文件夹
			File picFile=new File(localDir+dataDir);
			if(!picFile.exists())
				picFile.mkdirs();
			//d) 准备随机数，拼接成图片名称
			String uuid=UUID.randomUUID().toString().replace("-","");
			String fileType=fileName.substring(fileName.lastIndexOf("."));
			String realPath=localDir+dataDir+"/"+uuid+fileType;
			System.out.println(realPath);
			//e)将文件写入磁盘
			uploadFile.transferTo(new File(realPath));
			//5 准备虚拟路径
			String urlPre="http://image.jt.com/";
			String url=urlPre+dataDir+"/"+uuid+fileType;
			//6 准备返回值
			picUploadResult.setUrl(url);
			picUploadResult.setHeight(String.valueOf(height));
			picUploadResult.setWidth(String.valueOf(width));
			return picUploadResult;

		} catch (Exception e) {
			e.printStackTrace();
			picUploadResult.setError(1);
			return picUploadResult;
		}

	}

}
