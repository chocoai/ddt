/*
 * @(#)UploadController.java
 *
 */
package com.ddt.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.constants.StatusCode;
import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollBookUser;
import com.ddt.core.meta.User;
import com.ddt.core.service.RollBookService;
import com.ddt.core.service.UserService;

/**
 * UploadController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @since      1.0
 */
@Controller
@RequestMapping("/rollbook")
public class UploadController extends BaseController {
	private static Logger logger = Logger.getLogger(UploadController.class);
	
	@Autowired
	private RollBookService rollBookService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="upload", method={RequestMethod.POST})
	public ModelAndView token(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("upload.info");
		User user = getSession();
		if (user == null) {
			view.addObject("status", StatusCode.USER_NAME_NOT_EXISTS);
			view.addObject("result", "上传失败");
			return view;
		}
		
		if (file == null) {
			view.addObject("status", StatusCode.USER_NAME_NOT_EXISTS);
			view.addObject("result", "上传文件不能为空");
			return view;
		}
		
		long rollBookId = ServletRequestUtils.getLongParameter(request, "id", 0);
		if (rollBookId <= 0) {
			view.addObject("status", StatusCode.ROLL_BOOK_NOT_EXISTS);
			view.addObject("result", "点名册不存在");
			return view;
		}
		
		RollBook rollBook = rollBookService.getRollBookById(rollBookId, user.getId());
		if (rollBook == null) {
			view.addObject("status", StatusCode.ROLL_BOOK_NOT_EXISTS);
			view.addObject("result", "点名册不存在");
			return view;
		}
		int userCount = rollBook.getUserCount();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			int sheetNum = workbook.getNumberOfSheets();
			for (int i = 0; i < sheetNum; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				int rowNum = sheet.getLastRowNum();
				for (int j = 1; j <= rowNum; j++) {
					Row row = sheet.getRow(j);
					Cell cell = row.getCell(0);
					String name = cell.getStringCellValue();
					if (StringUtils.isBlank(name)) {
						continue;
					}
					User u = new User();
					u.setUserName(name);
					userService.insertUser(u);
					
					RollBookUser rollBookUser = userService.getRollBookUser(rollBookId, u.getId());
					if (rollBookUser == null) {
						rollBookUser = new RollBookUser();
						rollBookUser.setUserId(u.getId());
						rollBookUser.setBookId(rollBookId);
						userService.insertRollBookUser(rollBookUser);
					}
					userCount++;
				}
			}
			
			rollBook.setUserCount(userCount);
			rollBookService.updateRollBook(rollBook);
			
			view.addObject("status", StatusCode.OK);
			view.addObject("result", "上传成功");
			view.addObject("userCount", userCount);
		} catch (IOException e) {
			logger.error(e);
		}
		
		return view;
	}
}
