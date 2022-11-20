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

        String[] people = new String[5];
        int[] peopleMoney = new int[5];
        for (int k = 1; k < 6; k++) {
            people[k - 1] = lines[k];
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

        for (int j = 0; j < 5; j++) {
            if (numbers[j][1] != 0) {
                int amount = (numbers[j][0] - (numbers[j][0] % numbers[j][1])) / numbers[j][1];
                peopleMoney[peopleNumber(names[nameIndex])] += numbers[j][0] % numbers[j][1];
                peopleMoney[peopleNumber(names[nameIndex])] -= numbers[j][0];
                //System.out.println(names[nameIndex] + " has " + peopleMoney[peopleNumber(names[nameIndex])] + " left");
                for (int k = nameIndex + 1; k < nameIndex + numbers[j][1] + 1; k++) {
                    peopleMoney[peopleNumber(names[k])] += amount;
//                    System.out.println(names[k] + " got paid " + amount);
//                    System.out.println(names[k] + " has " + peopleMoney[peopleNumber(names[k])]);
                }

//                System.out.println(numbers[j][0] % numbers[j][1]);
//                System.out.println(names[nameIndex] + " has " + peopleMoney[peopleNumber(names[nameIndex])]);
                nameIndex += numbers[j][1] + 1;
            }


        }

        out.println("dave " + peopleMoney[0]);
        out.println("laura " + peopleMoney[1]);
        out.println("owen " + peopleMoney[2]);
        out.println("vick " + peopleMoney[3]);
        out.println("amr " + peopleMoney[4]);

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
            case "dave": return 0;
            case "laura": return 1;
            case "owen": return 2;
            case "vick": return 3;
            case "amr": return 4;
        }
        return 5;
    }

}

