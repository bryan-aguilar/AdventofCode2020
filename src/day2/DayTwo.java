package day2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwo {

    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList();
        File inputFile = new File("src/day2/input.txt");
        try{
            Scanner fileScanner = new Scanner(inputFile);
            while(fileScanner.hasNext()){
                input.add(fileScanner.nextLine());
            }
        }catch(Exception e){
        }


        System.out.println(countPass(input));
        System.out.println(countPassPartTwo(input));
    }

    public static int countPass(ArrayList<String> input){
        String[] values;
        String[] bounds;
        char countThis;
        int counter;
        int totalCounter = 0;
        for(String s: input){
            values = s.split(" ");
            bounds = values[0].split("-");
            countThis = values[1].charAt(0);
            counter = 0;

            for(char a: values[2].toCharArray()){
                if(a == countThis){
                    counter++;
                }
            }
            if(counter >= Integer.parseInt(bounds[0]) && counter <= Integer.parseInt(bounds[1])){
                totalCounter++;
            }
        }
        return totalCounter;
    }
    public static int countPassPartTwo(ArrayList<String> input){
        String[] values;
        String[] bounds;
        char desiredChar;
        int counter;
        int totalCounter = 0;
        for(String s: input){
            values = s.split(" ");
            bounds = values[0].split("-");
            desiredChar = values[1].charAt(0);
            if(values[2].charAt(Integer.parseInt(bounds[0])-1) == desiredChar && values[2].charAt(Integer.parseInt(bounds[1])-1)!=desiredChar){
                totalCounter++;
            }
            else if(values[2].charAt(Integer.parseInt(bounds[0])-1) != desiredChar && values[2].charAt(Integer.parseInt(bounds[1])-1)==desiredChar){
                totalCounter++;
            }
        }
        return totalCounter;
    }


}
