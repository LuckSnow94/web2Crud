package com.ufpr.tads.web2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataUtil {

//	//converter data do java para string
//	public static String formataDataBeanParaTela(java.util.Date data);
	
    //converter data de string para data java
	public static java.util.Date formataDataTelaParaBean(String data) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = format.parse(data);
        return date;
	}
	
	//converter java date para sql date
	public static java.sql.Date formataDataBeanParaSql(java.util.Date data){
		java.sql.Date sqlDate = new java.sql.Date(data.getTime());
		return sqlDate;
	}
	
    //converter sql date para java date
	public static java.util.Date formataDataSqlParaBean(java.sql.Date data){
		java.sql.Date sqlDate = data;
        java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
        return utilDate;
	}
}
