package com.colsubsidio.gestionpsicologicabackend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public Date addMinutesToDate(Date current, long minutes) {
		LocalDateTime currentLocal = current.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusMinutes(minutes);
		return Date.from(currentLocal.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public Date addHoursToDate(Date current, long hours) {
		LocalDateTime currentLocal = current.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().plusHours(hours);
		return Date.from(currentLocal.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public Date minusHoursToDate(Date current, long hours) {
		LocalDateTime currentLocal = current.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().minusHours(hours);
		return Date.from(currentLocal.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public boolean isAuthExpired(Long dateLong, Integer minutes) {
		LocalDateTime oAuthDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateLong), ZoneId.systemDefault());
		return isAuthExpired(oAuthDate, minutes);
	}
	
	public boolean isAuthExpired(LocalDateTime localDateTime, Integer minutes) {
		return localDateTime.until(LocalDateTime.now(), ChronoUnit.MINUTES) >= minutes;
	}
	
	public Long getMillisecondsFromStartDate(Date startdate) {
		LocalDateTime startDateCast = startdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return startDateCast.until(LocalDateTime.now(), ChronoUnit.MILLIS);
	}
	
	public boolean differenceMajorOrEqualsMinutes(Date date1, Date date2, Integer minutes) {
		LocalDateTime dateTime1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime dateTime2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		boolean isMajor = dateTime1.until(dateTime2, ChronoUnit.MINUTES) >= minutes;
		return isMajor;
	}
	
	public Date addDays(Date originalDate, int days){
		Date newDate = DateUtils.addDays(originalDate,days);		
		return newDate; 
	}
	
	public Date stringInstantToDate(String instantString) {
		Instant inst = Instant.parse(instantString);
		 Date dt = Date.from(inst);
		return dt;
	}
        
        public String dateToString(Date fecha,String format){
            SimpleDateFormat frm= new SimpleDateFormat(format);
            return frm.format(fecha);
        }
        
        public Date stringTodate(String fecha,String format) throws ParseException{
            SimpleDateFormat frm= new SimpleDateFormat(format);
            return frm.parse(fecha);
        }
}
