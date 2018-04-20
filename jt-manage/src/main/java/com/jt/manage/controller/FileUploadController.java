package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileUploadService;

@Controller
@RequestMapping("/pic/")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("upload")
	@ResponseBody
	public PicUploadResult uploadPic(MultipartFile uploadFile){
		System.out.println(uploadFile);
		return fileUploadService.uploadFile(uploadFile);
	}
}
