package de.zeroco.dates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public class Dates {

	public static final String YEARS = "years";
	public static final String MONTHS = "months";
	public static final String DAYS = "days";

	public static void main(String[] args) throws ParseException{
//		System.out.println(findEarlierDateByTime("2021-04-21T10:30", "2021-04-23T10:30"));
//		System.out.println(findEarlierDate("2022-05-21", "2023-05-23"));
//		System.out.println(getIncrementInDate("2021-03-11", "days", 21));
//		System.out.println(getFormatDate("2021-03-18", "dd/yyyy/MM"));
//		System.out.println(getIntervelTime("2021-03-13 10:30", 20));
//		System.out.println(getDate("2021-04-16"));
//		System.out.println(calculateDateDifference("2012-02-17", "2015-06-20"));
//		System.out.println(measureDateTimeGap("2021-04-21T10:30", "2021-04-23T10:30"));
//		System.out.println(getDecrementInDate("2023-04-16", "days", 2));
	}

	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	public static String getDayOfWeekAndYearDay(int years, int months, int days) {
		LocalDate date = LocalDate.of(years, months, days);
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int dayOfYear = date.getDayOfYear();
		return dayOfWeek + " " + dayOfYear;
	}

	public static String formatingLocalDate(String input) {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(input);
		String formattedDate = date.format(formatter);
		return formattedDate;
	}

	public static boolean getLocalTime() {
		LocalTime localTime = LocalTime.now();
		LocalTime time = LocalTime.of(10, 30);
		return (time.isBefore(localTime)) ? true : false;
	}

	public static String getFormattedLocalTime() {
		LocalTime localTime = LocalTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("ss:mm:HH");
		String fes = localTime.format(format);
		return fes;
	}

	public static boolean isValidDate(String date) {
		return Pattern.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$", date);
	}

	@SuppressWarnings("deprecation")
	public static int calculateAge(String date) throws ParseException {
		return new Date().getYear() - getDate(date).getYear();
	}

	public static String calculateDateDifference(String startDateTime, String endDateTime) {
		Period duration = Period.between(LocalDate.parse(startDateTime), LocalDate.parse(endDateTime));
		return duration.getYears() + " years " + duration.getMonths() + " months " + duration.getDays() + " days";
	}

	public static String measureDateTimeGap(String StartTime, String endTime) {
		Duration duration = Duration.between(LocalDateTime.parse(StartTime), LocalDateTime.parse(endTime));
		return duration.toDays() / 365 + " years " + duration.toDays() % 365 / 30 + " months " + " "
				+ duration.toDays() % 365 % 30 + " days " + duration.toHours() % 24 + "Hours"
				+ duration.toMinutes() % 60 + " Minutes";
	}

	public static String findEarlierDateByTime(String firstDate, String secondDate) {
		if ((LocalDateTime.parse(firstDate).isBefore(LocalDateTime.parse(secondDate)))) {
			return firstDate + " is smaller then  " + secondDate;
		} else {
			return secondDate + " is smaller then " + firstDate;
		}
	}

	public static String findEarlierDate(String firstDate, String secondDate) {
		if ((LocalDate.parse(firstDate)).isBefore(LocalDate.parse(secondDate))) {
			return firstDate + " is smaller then last date " + secondDate;
		} else if ((LocalDate.parse(firstDate)).isAfter(LocalDate.parse(secondDate))) {
			return secondDate + " is smaller then last date " + firstDate;
		} else {
			return "Both are equal dates";
		}
	}

	public static LocalDate getIncrementInDate(String date, String component, int values) {
		LocalDate localDate = LocalDate.parse(date);
		if (component.equalsIgnoreCase(YEARS)) {
			localDate = localDate.plusYears(values);
		} else if (component.equalsIgnoreCase(MONTHS)) {
			localDate = localDate.plusMonths(values);
		} else if (component.equalsIgnoreCase(DAYS)) {
			localDate = localDate.plusDays(values);
		}
		return localDate;
	}

	public static LocalDate getDecrementInDate(String date, String component, int values) {
		LocalDate localDate = LocalDate.parse(date);
		if (component.equalsIgnoreCase(YEARS)) {
			localDate = localDate.minusYears(values);
		} else if (component.equalsIgnoreCase(MONTHS)) {
			localDate = localDate.minusMonths(values);
		} else if (component.equalsIgnoreCase(DAYS)) {
			localDate = localDate.minusDays(values);
		}
		return localDate;
	}

	public static String getIntervelTime(String inputTime, int count) {
		String formattedTime = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime startTime = LocalDateTime.parse(inputTime, formatter);
		LocalTime endTime = LocalTime.of(23, 59);
		LocalDateTime endTimeOfDay = startTime.toLocalDate().atTime(endTime);
		LocalDateTime intervalTime = startTime;
		while (!intervalTime.isAfter(endTimeOfDay)) {
			formattedTime = formattedTime + intervalTime.format(formatter) + ",";
			intervalTime = intervalTime.plusMinutes(count);
		}
		return formattedTime;
	}

	public static String getFormatDate(String date, String format) {
		return LocalDate.parse(date).format(DateTimeFormatter.ofPattern(format));
	}

	public static Date getDate(String input) throws ParseException {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simple.parse(input);
		return date;
	}
	
}
