/*
 * @(#)Location.java 2014-7-7
 *
 */
package com.ddt.core.location;

/**
 * Location.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-7-7
 * @since      1.0
 * 
 * {  
    address: "CN|北京|北京|None|CHINANET|1|None",   #地址  
    content:       #详细内容  
    {  
    address: "北京市",   #简要地址  
    address_detail:      #详细地址信息  
    {  
    city: "北京市",        #城市  
    city_code: 131,       #百度城市代码  
    district: "",           #区县  
    province: "北京市",   #省份  
    street: "",            #街道  
    street_number: ""    #门址  
    },  
    point:               #百度经纬度坐标值  
    {  
    x: "116.39564504",  
    y: "39.92998578"  
    }  
    },  
    status: 0     #返回状态码  
} 
 */


public class Location {
	
	private int status;
	
	private String address;
	
	private Content content;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Content getContent() {
		return content;
	}
	
	public void setContent(Content content) {
		this.content = content;
	}
	
	public String getContentX() {
		if (content != null) {
			return content.getPoint().getX();
		}
		return null;
	}
	
	public String getContentY() {
		if (content != null) {
			return content.getPoint().getY();
		}
		return null;
	}
	
	class Content {
		private String address;
		
		private AddressDetail address_detail;
		
		private Point point;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public AddressDetail getAddress_detail() {
			return address_detail;
		}

		public void setAddress_detail(AddressDetail address_detail) {
			this.address_detail = address_detail;
		}

		public Point getPoint() {
			return point;
		}

		public void setPoint(Point point) {
			this.point = point;
		}
	}
	
	class AddressDetail {
		private String city;
		
		private int city_code;
		
		private String district;
		
		private String province;
		
		private String street;
		
		private String street_number;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getCity_code() {
			return city_code;
		}

		public void setCity_code(int city_code) {
			this.city_code = city_code;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getStreet_number() {
			return street_number;
		}

		public void setStreet_number(String street_number) {
			this.street_number = street_number;
		}
	}
	
	class Point {
		private String x;
		
		private String y;

		public String getX() {
			return x;
		}

		public void setX(String x) {
			this.x = x;
		}

		public String getY() {
			return y;
		}

		public void setY(String y) {
			this.y = y;
		}
	}
	
}
