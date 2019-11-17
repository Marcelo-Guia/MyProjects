package Template;

import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class Test {
	
/*	This isn't a necessary part to the rest of the code, but it's a quick and easy demonstration
 * 	of Joda time with everything set up in an easy copy-paste way. This can both be used for 
 * 	dates and periods. If you need syntax, well what's the Internet for huh? 
 * 
 * 	Also remember that this has it's own main, so it won't run with the other code.
 */
	
	static Scanner scan = new Scanner(System.in);
	static PeriodFormatter periodTimeFormat = new PeriodFormatterBuilder().printZeroAlways().appendYears().appendPrefix(" year, ", " years, ").appendMonths().appendPrefix(" month, ", " months, ").appendWeeks().appendPrefix(" week, ", " weeks, ").appendDays().appendPrefix(" day, ", " days, ").appendHours().appendSeparator(":").appendMinutes().appendSeparator(":").appendSeconds().appendSeparator(":").appendMillis3Digit().toFormatter();
	static DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss:SSS");

	public static void main(String[] args) 
	{
		System.out.println("Press something.");
		scan.nextLine();
		Instant instant1 = Instant.now();
		System.out.println("Press something.");
		scan.nextLine();
		Instant instant2 = Instant.now();
		Period timeBetweenInstants = new Period(instant1, instant2);
		String periodString = periodTimeFormat.print(timeBetweenInstants);
		DateTime instant = Instant.now().toDateTime();
		String instantString = dateTimeFormat.print(instant);
		System.out.println(periodString);
		System.out.println(instantString);
	}

}
