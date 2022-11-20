/*
ID: jerdenn1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;
class Person {
    String name;
    int money = 0;
    public Person(String newName) {
        name = newName;
    }
}
/*We specifically have this "Person" class so that we can link a number in an array to a name, it's just a
* bit easier this way. In earlier versions I was having issues with getting the index of an array from
* a person's name, which is critical in this project
 */
class gift1 {
    public static void main(String[] args) throws IOException {
        String[] lines = new String[250];
        //This string holds all the lines in the ".in" file
        int[][] numbers = new int[30][2];
        //After the program parses numbers from Strings in the lines[] array, it stores numbers in this array
        //It is a 2D array to hold the amount being given, and the amount of people it's split between
        String[] names = new String[250];
        //This array holds the list of people's names after the input gets parsed
        int nameIndex = 0;
        /*This variable keeps track of what name we are at in the array, because we don't want to confuse
        * a giver with a recipient. You will notice later on that I store both the givers and the receivers
        * in the same array. We can easily tell which is which by adding the amount of people that money
        * is being given to then adding it to this number + 1. So if the first person is giving away money to
        * 3 people, we can skip from element 0 -> 4, and we know element 4 is the next giver.
         */

        int lineIndex = 0;
        //Variable keeps track of what line we are on in the while loop when adding lines from ".in" to array
        int numbersCounter = 0;
        //Keeps track of what term we are in for the number array when parsing it
        int nameCounter = 0;
        //Same thing as above, just for the names

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        Scanner scanner = new Scanner(new File("gift1.in"));

        while (scanner.hasNextLine()) {
            lines[lineIndex] = scanner.nextLine();
            lineIndex++;
        }

        Person[] people = new Person[Integer.parseInt(lines[0])];
        //"Integer.parseInt(lines[0])" is the first line of text, which contains the amount of people we will have
        for (int k = 1; k < Integer.parseInt(lines[0]) + 1; k++) {
            people[k - 1] = new Person(lines[k]);
            //creates an array of "Person". Each name is gotten by going through the lines that contain the names
            //NOTE: We use the first number in ".in" to know how many names we are going to get
            lines[k] = "0";
            //By setting this to 0 the parser will ignore it and not add it to either array
        }


        for(int j = 0; j < lines.length; j++) {
            String tokenA;
            String tokenB = null;
            //tokenB will only not be null in the case that it is a set of 2 numbers; ex: "300 3"
            if (lines[j] != null) {

                StringTokenizer st = new StringTokenizer(lines[j]);

                tokenA = st.nextToken();
                try {
                    tokenB = st.nextToken();
                } catch (Exception ignore) {}
                //This try catch is to test if there is a second token

                try {
                    if (printType(Integer.parseInt(tokenA)).equals("int")) {
                        //We call our method below that using method overloading to tell type
                        /*If the input is not a string that contains a number, it will throw an error
                        * This is why we are using a try catch, so that if it throws an error we know it is a
                        * string, and we can preform the action we need to take if it is
                         */
                        numbers[numbersCounter][0] = Integer.parseInt(tokenA);
                        //index 0 = money being given
                        numbers[numbersCounter][1] = Integer.parseInt(tokenB);
                        //index 1 = amount of people being given too
                        numbersCounter++;
                    }
                } catch(Exception ignore) {
                    try {
                        Integer.parseInt(tokenA);
                    } catch(Exception ignoreB) {
                        names[nameCounter] = tokenA;
                        nameCounter++;
                        //This is the code we want to run if the string contains a name and not a number
                    }
                }
            }
        }

        for (int j = 0; j < Integer.parseInt(lines[0]); j++) {
            //This for loop is for running all the transactions
            if (numbers[j][1] != 0) {
                //We would get a divide by zero if we didn't have the if statement above
                int amount = (numbers[j][0] - (numbers[j][0] % numbers[j][1])) / numbers[j][1];
                //Subtract the remainder (numbers[j][0] % numbers[j][1]) from the money and divide it by amount of people
                people[peopleNumber(names[nameIndex], people)].money += numbers[j][0] % numbers[j][1];
                //Adding the remainder back to the giver's account
                people[peopleNumber(names[nameIndex], people)].money -= numbers[j][0];
                //Removing money that the giver gave away
                for (int k = nameIndex + 1; k < nameIndex + numbers[j][1] + 1; k++) {
                    people[peopleNumber(names[k], people)].money += amount;
                    /*This loop goes to each person in the list after the giver until the next giver
                    * and gives them the appropriate amount of money, as calculated above.
                     */
                }
            }
            nameIndex += numbers[j][1] + 1;
            //Adds amount of people being given to + 1, so we can get to the next giver

        }


        for (int j = 0; j < people.length; j++) {
            out.println(people[j].name + " " + people[j].money);
            //This just prints each person and their money to the output file
        }

        out.close();                                  // close the output file
    }
    static String printType(String x) {
        return("String");
    }
    static String printType(int x) {
        return("int");
    }
    //Overloading method so we can get the type of a variable

    static int peopleNumber(String personB, Person[] peopleB) {
        //This method loops through the array "people" so we can find what at what index does the object.name equal our input
        //Example; Input: "dave", Output: 0
        //This is because the Person that has the name dave is at index 0 in the array
        for (int j = 0; j < peopleB.length; j++) {
            if (peopleB[j].name.equals(personB)) {
                return j;
            }
        }
        return -1;
        //If -1 is returned, something went wrong
    }

}
