/*
 * @(#)UploadController.java
 *
 */
package com.ddt.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.constants.StatusCode;
import com.ddt.core.meta.User;
import com.ddt.core.service.RollBookService;

/**
 * UploadController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @since      1.0
 */
@Controller
@RequestMapping("/rollbook")
public class UploadController extends BaseController {
	
	@Autowired
	private RollBookService rollBookService;
	
	@RequestMapping("upload")
	public ModelAndView token(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("upload.token");
		User user = getSession();
		if (user == null) {
			view.addObject("status", StatusCode.USER_NAME_NOT_EXISTS);
			return view;
		}
		
		if (file == null) {
			return view;
		}
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			SXSSFWorkbook swk = new SXSSFWorkbook(workbook);
			int sheetNum = swk.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				Sheet sheet = swk.getSheetAt(i);
				int rowNum = sheet.getLastRowNum();
				for (int j = 0; j < rowNum; j++) {
					Row row = sheet.getRow(j);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return view;
	}
}
