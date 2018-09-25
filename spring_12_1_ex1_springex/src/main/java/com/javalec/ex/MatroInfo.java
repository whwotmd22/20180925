package com.javalec.ex;

public class MatroInfo {
	String line_num		;
	String cyber_st_code;
	String xpoint_wgs   ;
	String ypoint       ;
	String ypoint_wgs   ;
	String xpoint       ;
	String station_cd   ;
	String station_nm   ;
	String fr_code      ;
	
	
	
	public MatroInfo(String line_num, String cyber_st_code, String xpoint_wgs, String ypoint, String ypoint_wgs,
			String xpoint, String station_cd, String station_nm, String fr_code) {
		super();
		this.line_num = line_num;
		this.cyber_st_code = cyber_st_code;
		this.xpoint_wgs = xpoint_wgs;
		this.ypoint = ypoint;
		this.ypoint_wgs = ypoint_wgs;
		this.xpoint = xpoint;
		this.station_cd = station_cd;
		this.station_nm = station_nm;
		this.fr_code = fr_code;
	}
	public String getLine_num() {
		return line_num;
	}
	public void setLine_num(String line_num) {
		this.line_num = line_num;
	}
	public String getCyber_st_code() {
		return cyber_st_code;
	}
	public void setCyber_st_code(String cyber_st_code) {
		this.cyber_st_code = cyber_st_code;
	}
	public String getXpoint_wgs() {
		return xpoint_wgs;
	}
	public void setXpoint_wgs(String xpoint_wgs) {
		this.xpoint_wgs = xpoint_wgs;
	}
	public String getYpoint() {
		return ypoint;
	}
	public void setYpoint(String ypoint) {
		this.ypoint = ypoint;
	}
	public String getYpoint_wgs() {
		return ypoint_wgs;
	}
	public void setYpoint_wgs(String ypoint_wgs) {
		this.ypoint_wgs = ypoint_wgs;
	}
	public String getXpoint() {
		return xpoint;
	}
	public void setXpoint(String xpoint) {
		this.xpoint = xpoint;
	}
	public String getStation_cd() {
		return station_cd;
	}
	public void setStation_cd(String station_cd) {
		this.station_cd = station_cd;
	}
	public String getStation_nm() {
		return station_nm;
	}
	public void setStation_nm(String station_nm) {
		this.station_nm = station_nm;
	}
	public String getFr_code() {
		return fr_code;
	}
	public void setFr_code(String fr_code) {
		this.fr_code = fr_code;
	}
	
	
}
