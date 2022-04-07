package br.com.loja.rn;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class convertDateInBank {
	
	
	
	


	public Calendar execute(String date) {
		String[] dateSplit = date.split("-"); //divisor ano-mes-dia
		
		Integer ano = Integer.parseInt(dateSplit[0]);
		Integer mes = Integer.parseInt(dateSplit[1]);
		Integer dia = Integer.parseInt(dateSplit[2]);
		
		Calendar calendar = new GregorianCalendar(ano, (mes - 1), dia);
		return calendar;
	}
}
