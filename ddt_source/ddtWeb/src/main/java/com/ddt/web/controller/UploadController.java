/*
 * @(#)UploadController.java
 *
 */
package com.ddt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.ddt.core.utils.DateUtils;

/**
 * UploadController.java
 * 
 * @author <A HREF="mailto:ruan635@163.com">Roy</A>
 * @since 1.0
 */
@Controller
@RequestMapping("/rollbook")
public class UploadController extends BaseController {
	private static Logger logger = Logger.getLogger(UploadController.class);

	@Autowired
	private RollBookService rollBookService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "upload", method = { RequestMethod.POST })
	public ModelAndView token(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
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
		
		long rid = ServletRequestUtils.getLongParameter(request, "id", 0);
		String name = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "name", ""));
		String validStartDate = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "validStartDate", ""));
		String validEndDate = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "validEndDate", ""));
		int userCount = ServletRequestUtils.getIntParameter(request, "userCount", 0);
		
		long userId = user.getId();
		
		boolean isAdd = false;
		
		RollBook rollBook = rollBookService.getRollBookById(rid, userId);
		if (rollBook == null) {
			rollBook = new RollBook();
			isAdd = true;
		}
		
		rollBook.setName(name);
		rollBook.setUserCount(userCount);
		rollBook.setUserId(userId);
		rollBook.setValidStartTime(DateUtils.parseStringToDate(DateUtils.DATE_FORMAT, validStartDate));
		rollBook.setValidEndTime(DateUtils.parseStringToDate(DateUtils.DATE_FORMAT, validEndDate));
		
		if (isAdd) {
			rollBookService.addRollBook(rollBook);
		} else {
			rollBookService.updateRollBook(rollBook);
		}
		
		Workbook workbook = initWorkbook(file);
		int sheetNum = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			int rowNum = sheet.getLastRowNum();
			for (int j = 1; j <= rowNum; j++) {
				Row row = sheet.getRow(j);
				Cell cell = row.getCell(0);
				String username = cell.getStringCellValue();
				if (StringUtils.isBlank(username)) {
					continue;
				}
				User u = new User();
				u.setUserName(username);
				userService.insertUser(u);

				RollBookUser rollBookUser = userService.getRollBookUser(
						rollBook.getId(), u.getId());
				if (rollBookUser == null) {
					rollBookUser = new RollBookUser();
					rollBookUser.setUserId(u.getId());
					rollBookUser.setBookId(rollBook.getId());
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

		return view;
	}

	public Workbook initWorkbook(MultipartFile file) {
		Workbook workbook = null;
		try {
			workbook = new HSSFWorkbook(file.getInputStream());
		} catch (Exception e1) {
			try {
				workbook = new XSSFWorkbook(file.getInputStream());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return workbook;
	}

}
