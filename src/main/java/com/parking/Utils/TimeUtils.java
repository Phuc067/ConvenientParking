package com.parking.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import com.parking.model.TimeFormat;

public class TimeUtils {
	
	public static TimeFormat convertHourAndMinute(String timeString)
	{
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        LocalTime localTime = LocalTime.parse(timeString, formatter);

	        Long hour = (long) localTime.getHour();
	        Long minute = (long) localTime.getMinute();
	        
	        TimeFormat timeFormat = new TimeFormat(hour, minute);
	        return timeFormat;
	}
	
	public static TimeFormat calculatePeriod(Timestamp time1, Timestamp time2)
	{
		Long miliseconds = time1.getTime() - time2.getTime();
		TimeFormat timeFormat = new TimeFormat(miliseconds/1000);
		return timeFormat;
	}
	
	public static Timestamp convert(String timeString)
	{
//        String pattern = "yyyy-MM-dd HH:mm:ss"; 

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//        ZonedDateTime zonedDateTime = ZonedDateTime.parse(timeString, formatter);

        Instant instant = Instant.parse(timeString);
        Timestamp timestamp = Timestamp.from(instant); 
        timestamp.setTime(timestamp.getTime() - TimeUnit.HOURS.toMillis(7));
        return timestamp;
	}
}
