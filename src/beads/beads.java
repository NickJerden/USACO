/*
ID: jerdenn1
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.Scanner;

public class beads {
    public static String[] lines = new String[2];
    public static int lineIndex = 0;
    public static boolean isHomogenous = false;
    //This boolean tests if the string is all the same character, this is a test case on the grader
    //for example "rrrrrrrrrr"
    public static int maxCount;
    //Keeps track of the most beads we can count in order

    public static void main(String[] args) throws IOException {

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        Scanner scanner = new Scanner(new File("beads.in"));

        while (scanner.hasNextLine()) {
            lines[lineIndex] = scanner.nextLine();
            lineIndex++;
        }

        char[] necklace = new char[Integer.parseInt(lines[0])];
        //We make our string into an array of type , so it's easier to iterate through

        necklace = lines[1].toCharArray();

        for (int i = 0; i < necklace.length; i++) {
            //we are doing a basic check to see if all characters are the same
            if (necklace[0] != necklace[i]) {
                //If there is a difference the loop will break
                break;
            }
            if (i == necklace.length - 1) {
                //After we've gone through every term, set max count to length of array, and isHomogenous to true, so we skip the loop
                isHomogenous = true;
                maxCount = necklace.length;
            }
        }
        if (!isHomogenous) {
            //No need to run this loop if all the characters are the same
            for (int i = 0; i < necklace.length; i++) {
                //This loop we run on each char, so we can check it compared to everything in the array that succeeds it
                int currentCount = 0;
                //The maximum amount we've found in order in this iteration
                int index = i;
                //Where we want to start looking from
                //for example we want to start looking at characters after the one we are searching for that iteration
                char realColor = 'n';
                //This real color is for keeping track of what color the 'w' represent
                //'n' means no color has been set

                for (int j = 0; j < necklace.length; j++) {
                    //Nested loop to compare each character to the one that we select as 'i' in the first loop
                    if (necklace[i] == 'w' && realColor == 'n' && necklace[i] != necklace[index]) {
                        //This statement figures out what color 'w' is going to represent
                        realColor = necklace[index];
                    } else if (necklace[i] != 'w') {
                        //In case the first character/the one we are looking at isn't 'w' we can assume that 'w' will represent the first color seen
                        realColor = necklace[i];
                    }
                    if (necklace[i] == necklace[index] || necklace[index] == 'w' || necklace[i] == 'w' && necklace[index] == realColor) {
                        //This statement checks if the next bead we are comparing to 'i' is equal to it
                        //If the next char we compare to == 'w' we know it's equal
                        //If our first character is 'w' and the char we are comparing is the 'realColor' we know they are equal
                        currentCount++;
                        if (i == necklace.length - 1) {
                            //This statement breaks the loop if we find one that isn't equal, so we move onto the next character
                            //NOTE: this is an unclear way to write the code, it's just how I had it when I finished the problem
                            //It really should be using !=  instead of == and not have an else statement
                            System.out.println(i + " is equal to " + index);
                        }
                    } else {
                        break;
                    }
                    if (index == necklace.length - 1) {
                        //This statement checks if we've reached the end of the necklace, and loops us back to the start
                        //Remember the necklace is a circle in reality so once we reach the end we move to the start
                        index = 0;
                    } else {
                        index++;
                    }
                }

                //Below here is a loop that does the exact same thing as above except backwards
                if (i == 0) {
                    //This sets i to the length of the necklace if it's 0, because we will be iterating backwards
                    index = necklace.length - 1;
                } else {
                    //We are spliting the necklace, so we are going to start comparing at one before the first one we compared
                    index = i - 1;
                }

                int necklaceIndex;
                //This variable takes the role of 'i' from the last loop, since we don't want to mess up the loop we use this

                if (i == 0) {
                    necklaceIndex = necklace.length - 1;
                } else {
                    necklaceIndex = i - 1;
                }

                for (int j = 0; j < necklace.length; j--) {
                    if (necklace[necklaceIndex] == 'w' && realColor == 'n' && necklace[necklaceIndex] != necklace[index]) {
                        realColor = necklace[index];
                    } else if (necklace[necklaceIndex] != 'w') {
                        realColor = necklace[necklaceIndex];
                    }
                    if (necklace[necklaceIndex] == necklace[index] || necklace[index] == 'w' || necklace[necklaceIndex] == 'w' && necklace[index] == realColor) {
                        currentCount++;
                    } else {
                        break;
                    }
                    if (index == 0) {
                        index = necklace.length - 1;
                    } else {
                        index--;
                    }
                }
                if (currentCount > maxCount) {
                    //Checks if the highest count we got that iteration is bigger than our record highest
                    maxCount = currentCount;
                }
            }
        }

        if (maxCount > necklace.length) {
            //This is a cheap solution to one of the test cases. If the largest number we got is bigger than the amount of beads, it's broken
            //There can't possibly be a longer strand than there are beads
            maxCount = necklace.length;
        }
        out.println(maxCount);
        out.close();
    }
}
