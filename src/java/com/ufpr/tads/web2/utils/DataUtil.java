package com.ufpr.tads.web2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataUtil {

//	//converter data do java para string
//	public static String formataDataBeanParaTela(java.util.Date data);
	
    //converter data de string para data java
	public static java.util.Date formataDataTelaParaBean(String data) throws ParseException{
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = formato.parse(data);
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
	
	//converter data e hora de string para data e hora java
	public static java.util.Date formataDataHoraTelaParaBean(String data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        java.util.Date date = formato.parse(data);
        return date;
	}
	
	//converter data e hora java para data e hora string para exibir na tela
	public static String formataDataHoraBeanParaTela(java.util.Date data) throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = formato.format(data);
        return date;
	}
	
	//converter sql date para java date
		public static java.util.Date formataDataHoraSqlParaBean(java.sql.Timestamp dataHora){
			java.sql.Timestamp sqlTimestamp = dataHora;
	        java.util.Date utilDate = new java.util.Date(sqlTimestamp.getTime());
	        return utilDate;
		}
}
