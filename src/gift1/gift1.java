/*
ID: jerdenn1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
    public static void main(String[] args) throws IOException {
        String[] lines = new String[40];
        int[][] numbers = new int[5][2];
        String[] names = new String[19];
        int i = 0;
        int numbersCounter = 0;
        int nameCounter = 0;

        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        Scanner scanner = new Scanner(new File("gift1.in"));

        while (scanner.hasNextLine()) {
            lines[i] = scanner.nextLine();
            i++;
        }

        for(int j = 0; j < lines.length; j++) {
            String tokenA;
            String tokenB = null;
            if (lines[j] != null) {

                StringTokenizer st = new StringTokenizer(lines[j]);

                tokenA = st.nextToken();
                try {
                    tokenB = st.nextToken();
                } catch (Exception ignore) {}

                try {
                    if (printType(Integer.parseInt(tokenA)).equals("int")) {
                        numbers[numbersCounter][0] = Integer.parseInt(tokenA);
                        numbers[numbersCounter][1] = Integer.parseInt(tokenB);
                        numbersCounter++;
                    }
                } catch(Exception ignore) {
                    try {
                        Integer.parseInt(tokenA);
                    } catch(Exception ignoreB) {
                        names[nameCounter] = tokenA;
                        nameCounter++;
                    }
                }
            }
        }

//        for (int j = 0; j < names.length; j++) {
//            if (names[j] != null) {
//                System.out.println(names[j]);
//            }
//        }
//
//        for (int j = 0; j < numbers.length; j++) {
//            System.out.println(numbers[j][0] + " " + numbers[j][1]);
//        }

        out.close();                                  // close the output file
    }
    static String printType(String x) {
        return("String");
    }
    static String printType(int x) {
        return("int");
    }

}

