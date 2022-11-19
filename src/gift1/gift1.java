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
        int nameIndex = 0;
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

        String[] people = new String[6];
        int[] peopleMoney = new int[6];
        for (int k = 1; k < 6; k++) {
            people[k] = lines[k];
            lines[k] = "0";
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

        String[] gifters = new String[5];
        for (int j = 0; j < 5; j++) {
            if (numbers[j][1] != 0) {
                int amount = ((numbers[j][0] - (numbers[j][0] % numbers[j][1])) / numbers[j][1]);
                peopleMoney[(peopleNumber(names[nameIndex]))] += numbers[j][0] % numbers[j][1];
                for (int k = j + 1; k < numbers[j][1]; k++) {
                    peopleMoney[peopleNumber(names[k])] += amount;
                }
                nameIndex += numbers[j][1] + 1;

                System.out.println(names[nameIndex] + " gave " + numbers[j][0] + " to " + numbers[j][1] + " people");

            }

        }

        out.close();                                  // close the output file
    }
    static String printType(String x) {
        return("String");
    }
    static String printType(int x) {
        return("int");
    }

    static int peopleNumber(String person) {
        switch (person){
            case "dave": return 1;
            case "laura": return 2;
            case "owen": return 3;
            case "vick": return 4;
            case "amr": return 5;
        }
        return 0;
    }

}

