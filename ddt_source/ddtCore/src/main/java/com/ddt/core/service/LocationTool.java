/*
 * @(#)LocationTool.java 2014-7-4
 *
 */
package com.ddt.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ddt.core.location.Location;
import com.ddt.core.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * LocationTool.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-7-4
 * @since      1.0
 */
@Component
public class LocationTool {
	private static String ak = "VEnUn9IFGmw8Cn1e8bUPQT1n";
	
	private static String url = "http://api.map.baidu.com/location/ip";
	
	private static final double EARTH_RADIUS = 6378.137;
	
	public static Location getLocation(String ip) {
		StringBuffer sb = new StringBuffer();
		sb.append(url).append("?ak=").append(ak).append("&ip=").append(ip).append("&coor=bd09ll");
		String content = HttpUtils.getContent(sb.toString());
		Gson gson = new Gson();
		Location location = gson.fromJson(content, Location.class);
		return location;
	}
	
	private static double rad(double d) {
	    return d * Math.PI / 180.0;
	}
	
	/**
	 * 计算2点经纬度距离 单位为千米，返回结果为米
	 * @param lat1 纬度
	 * @param lng1 经度
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
	    double radLat1 = rad(lat1);
	    double radLat2 = rad(lat2);
	    double a = radLat1 - radLat2;
	    double b = rad(lng1) - rad(lng2);
	    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
	     Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	    s = s * EARTH_RADIUS;
	    s = Math.round(s * 10000) / 10000;
	    return s * 1000;
	}
	
	@Autowired
	public void setAk(@Value("${baidu.ak}") String ak) {
		LocationTool.ak = ak;
	}
	
	public String getAk() {
		return LocationTool.ak;
	}

	public static String getUrl() {
		return url;
	}
	
	@Autowired
	public static void setUrl(@Value("${baidu.url}") String url) {
		LocationTool.url = url;
	}
	
	public static void main(String[] args) {
		System.out.println(getDistance(3515188.13, 13382905.27, 3581369.51, 13443135.59));
	}
}
