/*
ID: jerdenn1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
    public static void main (String [] args) throws IOException {
        String[] lines = new String[2];
        int i = 0;

        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

        Scanner scanner = new Scanner(new File("ride.in"));

        while(scanner.hasNextLine()) {

            lines[i] = scanner.nextLine();

            i++;
        }

        if (unicode(lines[0].toCharArray()) % 47 ==  unicode(lines[1].toCharArray()) % 47) {
            out.println("GO");
        } else {
            out.println("STAY");
        }

        out.close();                                  // close the output file
    }

    static int unicode(char[] chars) {
        int[] array = new int[6];
        int returnNumber = 1;
        for (int i = 0; i < chars.length; i++) {
            array[i] = chars[i];
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0)
            {
                returnNumber = returnNumber * (array[i] - 64);
            }
        }
        return returnNumber;
    }
}

