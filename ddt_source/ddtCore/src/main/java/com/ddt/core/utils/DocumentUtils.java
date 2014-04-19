/*
 * @(#)DocumentUtils.java 2013-10-18
 *
 * Copyright 2013 mircobuy, Inc. All rights reserved.
 */
package com.ddt.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * DocumentUtils.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2013-10-18
 * @since      1.0
 */
public class DocumentUtils {
	
	public static Document buildDocument(InputStream is) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		return document;
	}
	
	public static Document buildDocument(File file) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		return document;
	}
	
	public static XPath buildXpath() {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xpath = xPathFactory.newXPath();
		return xpath;
	}
	
	public static String selectNode(Document document, XPath xPath, String expression) throws XPathExpressionException {
		return xPath.evaluate(expression, document);
	}
}
