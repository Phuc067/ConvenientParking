package com.parking.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.parking.model.TimeFormat;

public class TimeUtils {

	public static TimeFormat convertHourAndMinute(String timeString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime localTime = LocalTime.parse(timeString, formatter);

		Long hour = (long) localTime.getHour();
		Long minute = (long) localTime.getMinute();

		TimeFormat timeFormat = new TimeFormat(hour, minute);
		return timeFormat;
	}

	public static Timestamp getGMT_7(Timestamp timestamp) {
		TimeZone gmt7TimeZone = TimeZone.getTimeZone("GMT+7");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(gmt7TimeZone);

		String gmt7Time = sdf.format(timestamp);
		Timestamp time = Timestamp.valueOf(gmt7Time);
		time.setTime(time.getTime() + TimeUnit.HOURS.toMillis(7));
		return time;
	}

	public static Timestamp convert(String timeString) {
		Timestamp timestamp = Timestamp.valueOf(timeString);
		timestamp.setTime(timestamp.getTime() - TimeUnit.HOURS.toMillis(7));
		return timestamp;
	}

	public static Double intervalToNow(Timestamp time) {
		
		Instant instantA = time.toInstant();
		Instant instantB = TimeUtils.now();
		Duration duration = Duration.between(instantA, instantB);

		Long seconds = duration.getSeconds();
		Double minutes = (double) seconds / 60;
		Double hours = (double) (minutes / 60);
		if(hours<10)
		{
			System.out.println(hours);
		}
		return hours;
	}
	
	public static Instant now()
	{
		Instant currentInstant = Instant.now(); 
        return gmt_7(currentInstant);
	}
	
	public static Instant gmt_7(Instant time)
	{
		Duration sevenHours = Duration.ofHours(7); 
	    Instant newInstant = time.plus(sevenHours);
	    return newInstant;
	}
}
