package com.ddt.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtils {
	
	private static Logger logger = Logger.getLogger(FreeMarkerUtils.class);
	
	public static String parse(String fileName, Map<String, Object> map) {
		
		try {
			File dir = new File(FreeMarkerUtils.class.getClassLoader().getResource("").toURI());
			
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setDirectoryForTemplateLoading(dir);
			Template template = configuration.getTemplate(fileName);
			
			StringWriter writer = new StringWriter();
			
			template.process(map, writer);
			
			return writer.toString();
			
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (TemplateException e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
}
