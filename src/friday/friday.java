/*
ID: jerdenn1
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.Scanner;

public class friday {

    static int[] months = new int[13];
    //Holds how many days are in each month
    static int[] weekdays = new int[7];
    //Holds how many 13ths there are on each day
    static int weekdayIndex = 2;
    //Starts at 2 because 1900 January 1st is a monday
    //Sunday, Saturday, Monday, Tuesday, Wednesday, Thursday, Friday
    static int input;
    static final int STARTING_YEAR = 1900;



    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("friday.in"));
        input = Integer.parseInt(scanner.nextLine());

        months[0] = 31;
        months[1] = 28;
        months[2] = 29;
        //This is febuary in the case of a leap year
        months[3] = 31;
        months[4] = 30;
        months[5] = 31;
        months[6] = 30;
        months[7] = 31;
        months[8] = 31;
        months[9] = 30;
        months[10] = 31;
        months[11] = 30;
        months[12] = 31;

        weekdays[0] = 0;
        weekdays[1] = 0;
        weekdays[2] = 0;
        weekdays[3] = 0;
        weekdays[4] = 0;
        weekdays[5] = 0;
        weekdays[6] = 0;



        for (int i = STARTING_YEAR; i < STARTING_YEAR + input; i++) {
            //Goes through every year
            for (int j = 0; j < 13; j++) {
                //Goes through each month of the year
                for (int k = 0; k < months[j]; k++) {
                    //Goes through each day of the month
                    if (k == 13) {
                        weekdays[weekdayIndex]++;
                        //Adds to count of 13th's when the day is the 13th
                        //The weekday it is will now gain 1 to it's count
                    }

                    if (weekdayIndex == 6) {
                        weekdayIndex = 0;
                        //Resets weekday count if it gets to the 7th day of the week
                    } else {
                        weekdayIndex++;
                        //Goes to the next day of the week
                    }
                }
                if (j == 0 && i % 100 != 0 && i % 4 == 0) {
                    //When the month is january, decides which February it should choose based on if it's a leap year
                    j++ ;
                } else if (j == 0 && i % 400 == 0) {
                    //Checks if a year ending in "00" is a leap year
                    j++;
                } else if (j == 1) {
                    //If it's not a leap year, we use month 1, non-leap year February, and we skip leap year february(Month 2)
                    j++;
                }
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        out.println(weekdays[1] + " " + weekdays[2] + " " + weekdays[3] + " " + weekdays[4] + " " + weekdays[5]
                + " " + weekdays[6] + " " + weekdays[0]);

        out.close();
    }

}
