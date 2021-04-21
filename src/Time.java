/*
 * Author: Bellamy Phan.
 * Date Created: 2020/12/07.
 */

public class Time implements Comparable<Time> {

	// Hold the total seconds from UTC 1970.
	private long time;
	
	
	// Default constructor.
	public Time() {
		time = System.currentTimeMillis() / 1000;
	}
	
	
	// Overloading constructor with timeZone, year, month, day.
	public Time(int timeZone, int year, int month, int day) {
		time = 0;
		setTime(timeZone, year, month, day);
	}
	
	
	// Overloading constructor with TimeZone.
	public Time(int timeZone) {
		time = System.currentTimeMillis() / 1000;
		time = time + timeZone * 3600;
	}
	
	
	// Overloading constructor with long time.
	public Time(long time) {
		this.time = time;
	}
	
	
	// Get the time.
	public long getTime() {
		return time;
	}
	
	
	// Get the year based on UTC 1970.
	public int getYear() {
		// Start from 1970.
		int currentYear = 1970;
		// Copy the totalSeconds.
		long tempTotalSeconds = time;
		// Each year has 365 or 366 days, 365 days = 31,536,000 seconds, 366 days = 31,622,400 seconds.
		int secondsPerYear = 31536000;
		int secondsPerLeapYear = 31622400;
		// Increase the current year until the tempTotalSeconds < secondsPerYear.
		while (tempTotalSeconds >= secondsPerYear) {
			if (isLeapYear(currentYear)) {
				// Check if the currentYear is a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerLeapYear;
			} else {
				// The currentYear is not a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerYear;
			}
		}
		// Return the currentYear.
		return currentYear;
	}
	
	
	// Get the month based on UTC 1970.
	public int getMonth() {
		// Start from 1970.
		int currentYear = 1970;
		// Copy the totalSeconds.
		long tempTotalSeconds = time;
		// Each year has 365 or 366 days, 365 days = 31,536,000 seconds, 366 days = 31,622,400 seconds.
		int secondsPerYear = 31536000;
		int secondsPerLeapYear = 31622400;
		// Increase the current year until the tempTotalSeconds < secondsPerYear.
		while (tempTotalSeconds >= secondsPerYear) {
			if (isLeapYear(currentYear)) {
				// Check if the currentYear is a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerLeapYear;
			} else {
				// The currentYear is not a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerYear;
			}
		}
		// Start from January.
		int currentMonth = 1;
		// Get the minimumSecondPerMonth. 28 days = 2,419,200.
		int minimumSecondPerMonth = 2419200;
		// Get the total seconds for one day.
		int totalSecondOfOneDay = 86400;
		// Increase the current month until the tempTotalSeconds < minimumSecondPerMonth
		while (tempTotalSeconds >= minimumSecondPerMonth) {
			// Get the number of days of the current month.
			int totalDays = getTotalDaysFromMonth(currentMonth);
			// Check for leapYear for February.
			if (isLeapYear(currentYear) && currentMonth == 2) {
				totalDays++;
			}
			// Get the total seconds for the total days of the current month.
			int totalSecondsOfThisMonth = totalSecondOfOneDay * totalDays;
			// Update the currentMonth and tempTotalSeconds.
			currentMonth++;
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfThisMonth;
		}
		// Return the currentMonth.
		return currentMonth;
	}
	
	
	// Get the day based on UTC 1970.
	public int getDay() {
		// Start from 1970.
		int currentYear = 1970;
		// Copy the totalSeconds.
		long tempTotalSeconds = time;
		// Each year has 365 or 366 days, 365 days = 31,536,000 seconds, 366 days = 31,622,400 seconds.
		int secondsPerYear = 31536000;
		int secondsPerLeapYear = 31622400;
		// Increase the current year until the tempTotalSeconds < secondsPerYear.
		while (tempTotalSeconds >= secondsPerYear) {
			if (isLeapYear(currentYear)) {
				// Check if the currentYear is a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerLeapYear;
			} else {
				// The currentYear is not a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerYear;
			}
		}
		// Start from January.
		int currentMonth = 1;
		// Get the minimumSecondPerMonth. 28 days = 2,419,200.
		int minimumSecondPerMonth = 2419200;
		// Get the total seconds for one day.
		int totalSecondOfOneDay = 86400;
		// Increase the current month until the tempTotalSeconds < minimumSecondPerMonth
		while (tempTotalSeconds >= minimumSecondPerMonth) {
			// Get the number of days of the current month.
			int totalDays = getTotalDaysFromMonth(currentMonth);
			// Check for leapYear for February.
			if (isLeapYear(currentYear) && currentMonth == 2) {
				totalDays++;
			}
			// Get the total seconds for the total days of the current month.
			int totalSecondsOfThisMonth = totalSecondOfOneDay * totalDays;
			// Update the currentMonth and tempTotalSeconds.
			currentMonth++;
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfThisMonth;
		}
		// Start from day 1.
		int currentDay = 1;
		// Increase the current day until the tempTotalSeconds < totalSecondOfOneDay.
		while (tempTotalSeconds >= totalSecondOfOneDay) {
			currentDay++;
			tempTotalSeconds = tempTotalSeconds - totalSecondOfOneDay;
		}
		// Return the currentDay.
		return currentDay;
	}
	
	
	// Get the hour based on UTC 1970.
	public int getHour() {
		// Start from 1970.
		int currentYear = 1970;
		// Copy the totalSeconds.
		long tempTotalSeconds = time;
		// Each year has 365 or 366 days, 365 days = 31,536,000 seconds, 366 days = 31,622,400 seconds.
		int secondsPerYear = 31536000;
		int secondsPerLeapYear = 31622400;
		// Increase the current year until the tempTotalSeconds < secondsPerYear.
		while (tempTotalSeconds >= secondsPerYear) {
			if (isLeapYear(currentYear)) {
				// Check if the currentYear is a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerLeapYear;
			} else {
				// The currentYear is not a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerYear;
			}
		}
		// Start from January.
		int currentMonth = 1;
		// Get the minimumSecondPerMonth. 28 days = 2,419,200.
		int minimumSecondPerMonth = 2419200;
		// Get the total seconds for one day.
		int totalSecondOfOneDay = 86400;
		// Increase the current month until the tempTotalSeconds < minimumSecondPerMonth
		while (tempTotalSeconds >= minimumSecondPerMonth) {
			// Get the number of days of the current month.
			int totalDays = getTotalDaysFromMonth(currentMonth);
			// Check for leapYear for February.
			if (isLeapYear(currentYear) && currentMonth == 2) {
				totalDays++;
			}
			// Get the total seconds for the total days of the current month.
			int totalSecondsOfThisMonth = totalSecondOfOneDay * totalDays;
			// Update the currentMonth and tempTotalSeconds.
			currentMonth++;
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfThisMonth;
		}
		// Update the tempTotalSeconds until the currentDay.
		while (tempTotalSeconds >= totalSecondOfOneDay) {
			tempTotalSeconds = tempTotalSeconds - totalSecondOfOneDay;
		}
		// Start from 12AM.
		int currentHour = 0;
		// Get the totalSecondsOfOneDay.
		int totalSecondsOfOneHour = 3600;
		// Increase the current hour until the tempTotalSeconds < totalSecondsOfOneHour.
		while (tempTotalSeconds >= totalSecondsOfOneHour) {
			currentHour++;
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfOneHour;
		}
		// Return the currentHour.
		return currentHour;
	}
	
	
	// Get the minute based on UTC 1970.
	public int getMinute() {
		// Start from 1970.
		int currentYear = 1970;
		// Copy the totalSeconds.
		long tempTotalSeconds = time;
		// Each year has 365 or 366 days, 365 days = 31,536,000 seconds, 366 days = 31,622,400 seconds.
		int secondsPerYear = 31536000;
		int secondsPerLeapYear = 31622400;
		// Increase the current year until the tempTotalSeconds < secondsPerYear.
		while (tempTotalSeconds >= secondsPerYear) {
			if (isLeapYear(currentYear)) {
				// Check if the currentYear is a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerLeapYear;
			} else {
				// The currentYear is not a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerYear;
			}
		}
		// Start from January.
		int currentMonth = 1;
		// Get the minimumSecondPerMonth. 28 days = 2,419,200.
		int minimumSecondPerMonth = 2419200;
		// Get the total seconds for one day.
		int totalSecondOfOneDay = 86400;
		// Increase the current month until the tempTotalSeconds < minimumSecondPerMonth
		while (tempTotalSeconds >= minimumSecondPerMonth) {
			// Get the number of days of the current month.
			int totalDays = getTotalDaysFromMonth(currentMonth);
			// Check for leapYear for February.
			if (isLeapYear(currentYear) && currentMonth == 2) {
				totalDays++;
			}
			// Get the total seconds for the total days of the current month.
			int totalSecondsOfThisMonth = totalSecondOfOneDay * totalDays;
			// Update the currentMonth and tempTotalSeconds.
			currentMonth++;
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfThisMonth;
		}
		// Update the tempTotalSeconds until the currentDay.
		while (tempTotalSeconds >= totalSecondOfOneDay) {
			tempTotalSeconds = tempTotalSeconds - totalSecondOfOneDay;
		}
		// Get the totalSecondsOfOneDay.
		int totalSecondsOfOneHour = 3600;
		// Update the tempTotalSeconds until the currentHour.
		while (tempTotalSeconds >= totalSecondsOfOneHour) {
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfOneHour;
		}
		// Start from 0 minute.
		int currentMinute = 0;
		// Increase the current minute until the tempTotalSeconds < 60.
		while (tempTotalSeconds >= 60) {
			currentMinute++;
			tempTotalSeconds = tempTotalSeconds - 60;
		}
		// Return the currentMinute.
		return currentMinute;
	}
	
	
	// Get the second based on UCT 1970.
	public int getSecond() {
		// Start from 1970.
		int currentYear = 1970;
		// Copy the totalSeconds.
		long tempTotalSeconds = time;
		// Each year has 365 or 366 days, 365 days = 31,536,000 seconds, 366 days = 31,622,400 seconds.
		int secondsPerYear = 31536000;
		int secondsPerLeapYear = 31622400;
		// Increase the current year until the tempTotalSeconds < secondsPerYear.
		while (tempTotalSeconds >= secondsPerYear) {
			if (isLeapYear(currentYear)) {
				// Check if the currentYear is a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerLeapYear;
			} else {
				// The currentYear is not a leapYear.
				currentYear++;
				tempTotalSeconds = tempTotalSeconds - secondsPerYear;
			}
		}
		// Start from January.
		int currentMonth = 1;
		// Get the minimumSecondPerMonth. 28 days = 2,419,200.
		int minimumSecondPerMonth = 2419200;
		// Get the total seconds for one day.
		int totalSecondOfOneDay = 86400;
		// Increase the current month until the tempTotalSeconds < minimumSecondPerMonth
		while (tempTotalSeconds >= minimumSecondPerMonth) {
			// Get the number of days of the current month.
			int totalDays = getTotalDaysFromMonth(currentMonth);
			// Check for leapYear for February.
			if (isLeapYear(currentYear) && currentMonth == 2) {
				totalDays++;
			}
			// Get the total seconds for the total days of the current month.
			int totalSecondsOfThisMonth = totalSecondOfOneDay * totalDays;
			// Update the currentMonth and tempTotalSeconds.
			currentMonth++;
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfThisMonth;
		}
		// Update the tempTotalSeconds until the currentDay.
		while (tempTotalSeconds >= totalSecondOfOneDay) {
			tempTotalSeconds = tempTotalSeconds - totalSecondOfOneDay;
		}
		// Get the totalSecondsOfOneDay.
		int totalSecondsOfOneHour = 3600;
		// Update the tempTotalSeconds until the currentHour.
		while (tempTotalSeconds >= totalSecondsOfOneHour) {
			tempTotalSeconds = tempTotalSeconds - totalSecondsOfOneHour;
		}
		// Update the tempTotalSeconds until the currentMinute.
		while (tempTotalSeconds >= 60) {
			tempTotalSeconds = tempTotalSeconds - 60;
		}
		// Get the currentSecond.
		int currentSecond = (int) tempTotalSeconds;
		// Return the currentSecond.
		return currentSecond;
	}
	
	
	// Get number of days based on the current month.
	private int getTotalDaysFromMonth(int currentMonth) {
		if (currentMonth == 1 || currentMonth == 3 || currentMonth == 5 || currentMonth == 7 ||
				currentMonth == 8 || currentMonth == 10 || currentMonth == 12) {
			// January, March, May, July, August, October, December have 31 days.
			return 31;
		} else if (currentMonth == 2) {
			// February has 28 days.
			return 28;
		} else {
			// April, June, September, November have 30 days.
			return 30;
		}
	}
	
	
	// Set the time based on year, month, day.
	public void setTime(int year, int month, int day) {
		// Count the total years.
		int totalYears = year - 1970;
		// Count the total leap years.
		int totalLeapYears = 0;
		for (int currentYear = 1970; currentYear < year; currentYear++) {
			if (isLeapYear(currentYear)) {
				totalLeapYears++;
			}
		}
		// Count the totalDays based from totalYears.
		int totalDays = totalYears * 365;
		// For each leapYear, there is extra one day.
		totalDays += totalLeapYears;
		// If the current year is a leap year and it passes February, add one day.
		if (isLeapYear(year) && month > 2) {
			totalDays++;
		}
		// Count all days based on the current month.
		for (int currentMonth = 1; currentMonth < month; currentMonth++) {
			totalDays += getTotalDaysFromMonth(currentMonth);
		}
		// Add the current day to the totalDays.
		totalDays += day - 1;
		// Count the totalSeconds of one day.
		int totalSecondsOneDay = 86400;
		// Count the totalSeconds based on totalDays.
		long totalSeconds = totalSecondsOneDay * totalDays;
		// Set the time.
		time = totalSeconds;
		// Check for INVALID INPUT.
		if (year < 1970 || month < 1 || month > 12 || day < 1 || day > 31) {
			time = System.currentTimeMillis();
		}
	}
	
	
	// Set the time based on timeZone, year, month, day.
	public void setTime(int timeZone, int year, int month, int day) {
		setTime(year, month, day);
		this.time = this.time - (timeZone * 3600);
	}
	
	
	// Check leap year.
	public boolean isLeapYear(int currentYear) {
		// Leap year must be divisible by 4.
		if (currentYear % 4 == 0) {
			// If the currentYear is a century, it must be divisible by 400;
			if (currentYear % 100 == 0) {
				if (currentYear % 400 == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			// If the currentYear is not divisible by 4, then it is not the leapYear.
			return false;
		}
	}
	
	
	// Get the date yyyymmdd format.
	public String getyyyymmdd() {
		String string = "";
		string += Integer.toString(this.getYear());
		if (this.getMonth() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getMonth());
		if (this.getDay() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getDay());
		return string;
	}
	
	
	// Get the date and time yyyymmdd_hhmm.
	public String getyyyymmdd_hhmm() {
		String string = "";
		string += Integer.toString(this.getYear());
		if (this.getMonth() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getMonth());
		if (this.getDay() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getDay()) + "_";
		if (this.getHour() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getHour());
		if (this.getMinute() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getMinute());
		return string;
	}
	
	
	// Get the date and time _yyyymmdd_hhmm.
	public String get_yyyymmdd_hhmm() {
		String string = "_";
		string += Integer.toString(this.getYear());
		if (this.getMonth() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getMonth());
		if (this.getDay() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getDay()) + "_";
		if (this.getHour() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getHour());
		if (this.getMinute() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getMinute());
		return string;
	}
	
	
	// compareTo.
	@Override
	public int compareTo(Time anotherTime) {
		if (this.time > anotherTime.getTime()) {
			return 1;
		} else if (this.time == anotherTime.getTime()) {
			return 0;
		} else {
			return -1;
		}
	}
	
	
	// toString.
	@Override
	public String toString() {
		String string = "";
		string += Integer.toString(this.getYear()) + "/";
		if (this.getMonth() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getMonth()) + "/";
		if (this.getDay() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getDay()) + " ";
		if (this.getHour() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getHour()) + ":";
		if (this.getMinute() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getMinute()) + ":";
		if (this.getSecond() < 10) {
			string += "0";
		}
		string += Integer.toString(this.getSecond());
		return string;
	}
}
