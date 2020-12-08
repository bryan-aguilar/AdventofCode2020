package DayThree;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) {
        //31 columns
        //323 rows
        File inputFile = new File("src/DayThree/input.txt");
        char[][] myMap = new char[323][31];

        try{
            Scanner fileScanner = new Scanner(inputFile);
            int rowCounter = 0;
            while(fileScanner.hasNext()){
                String currentLine = fileScanner.nextLine();
                System.arraycopy(currentLine.toCharArray(),0,myMap[rowCounter],0,myMap[rowCounter].length);
                rowCounter++;
            }
        }catch(Exception e){

        }
        System.out.println(countTrees(myMap));
        int partTwoAnswer = countTreesPartTwo(myMap,1,1) * countTreesPartTwo(myMap,3,1) * countTreesPartTwo(myMap,5,1) * countTreesPartTwo(myMap,7,1) * countTreesPartTwo(myMap,1,2);
        System.out.println(partTwoAnswer);

    }
    public static int countTrees(char[][] input){
        int treeCounter =0;
        int rowPointer =0;
        int colPointer = 0;
        //we want to move right 3 down 1

        while(rowPointer < input.length){
            //check position
            if(input[rowPointer][colPointer] == '#')
                treeCounter++;

            //inc row
            rowPointer+=1;

            //logic to inc column
            colPointer += 3;
            if(colPointer >= input[0].length) {
                colPointer = colPointer - input[0].length;
            }
        }

        return treeCounter;

    }


    public static int countTreesPartTwo(char[][] input, int right, int down){
        int treeCounter =0;
        int rowPointer =0;
        int colPointer = 0;
        //we want to move right 3 down 1

        while(rowPointer < input.length){
            //check position
            if(input[rowPointer][colPointer] == '#')
                treeCounter++;

            //inc row
            rowPointer+=down;

            //logic to inc column
            colPointer += right;
            if(colPointer >= input[0].length) {
                colPointer = colPointer - input[0].length;
            }
        }

        return treeCounter;

    }
}
