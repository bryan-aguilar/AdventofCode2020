package DaySix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DaySixPartTwo {
    public static void main(String[] args) {
        File inputFile = new File("src/DaySix/input.txt");
        ArrayList<char[]> currentGroup = new ArrayList<>();
        int totalCounter = 0;
        try {
            Scanner fileScanner = new Scanner(inputFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                if(!nextLine.isEmpty()){
                    currentGroup.add(nextLine.toCharArray());
                }
                else{
                    totalCounter += parseGroup(currentGroup);
                    currentGroup.clear();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            totalCounter += parseGroup(currentGroup);
            System.out.println(totalCounter);
        }

    }

    public static int parseGroup(ArrayList<char[]> group){
        int total = 0;
        //quick base case for single person
        if(group.size() == 1){
            return group.get(0).length;
        }
        HashMap<Character, Integer> groupMap = new HashMap<>();
        //map each character
        for(char[] cArray : group){
            for(char c : cArray){
                groupMap.put(c,groupMap.getOrDefault(c,0)+1);
            }
        }

        //check to see if each value matches the size of the intitial array list
        for(char c : groupMap.keySet()){
            if(groupMap.get(c) == group.size()){
                total++;
            }
        }
        return total;
    }
}
