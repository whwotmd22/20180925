package com.javalec.parser.xmlClass;

public class PlaceInfo {
	
	String PLACE_CODE;
	String PLACE_NAME;
	String USE_YN;
	
	public PlaceInfo(String pLACE_CODE, String pLACE_NAME, String uSE_YN) {
		super();
		PLACE_CODE = pLACE_CODE;
		PLACE_NAME = pLACE_NAME;
		USE_YN = uSE_YN;
	}

	public String getPLACE_CODE() {
		return PLACE_CODE;
	}
	public void setPLACE_CODE(String pLACE_CODE) {
		PLACE_CODE = pLACE_CODE;
	}
	public String getPLACE_NAME() {
		return PLACE_NAME;
	}
	public void setPLACE_NAME(String pLACE_NAME) {
		PLACE_NAME = pLACE_NAME;
	}
	public String getUSE_YN() {
		return USE_YN;
	}
	public void setUSE_YN(String uSE_YN) {
		USE_YN = uSE_YN;
	}
	
}
