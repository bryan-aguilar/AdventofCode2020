package DaySix;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DaySix {
    public static void main(String[] args) {
        File inputFile = new File("src/DaySix/input.txt");
        ArrayList<StringBuilder> groups = new ArrayList<>();
        StringBuilder currentGroup = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(inputFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                if(!nextLine.isEmpty()){
                    currentGroup.append(nextLine);
                }
                else{
                    groups.add(currentGroup);
                    currentGroup = new StringBuilder();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            groups.add(currentGroup);
        }

        int totalCount = 0;
        for(StringBuilder s: groups){
            totalCount += parseGroup(s);
        }
        System.out.println(totalCount);
    }

    public static int parseGroup(StringBuilder inputGroup){
        HashSet<Character> uniqueChar = new HashSet<>();

        for(char c : inputGroup.toString().toCharArray()){
            uniqueChar.add(c);
        }

        return uniqueChar.size();
    }
}
