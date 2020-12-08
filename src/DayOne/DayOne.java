package DayOne;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) throws FileNotFoundException {
        int[] test = {1721,
                979,
                366,
                299,
                675,
                1456};
        //System.out.println(findSum(test));
        File inputFile = new File("C:\\Users\\Bryan\\AdventOfCode2020\\src\\dayoneinput.txt");
        Scanner fileScanner = new Scanner(inputFile);
        ArrayList<Integer> input = new ArrayList();
        while(fileScanner.hasNext()){
            input.add(Integer.parseInt(fileScanner.nextLine()));
        }
        System.out.println(findSum(input));
        System.out.println(findSumTwo(input));
    }

    public static int findSum(ArrayList<Integer> input){
        for(int i = 0;i<input.size()-1;i++){
            for(int j = i+1;j<input.size();j++){
                if(input.get(i)+input.get(j) == 2020){
                    return input.get(i) * input.get(j);
                }
            }
        }
        return 0;
    }

    public static int findSumTwo(ArrayList<Integer> input){
        for(int i = 0; i < input.size()-2;i++){
            for(int j = i+1;j<input.size() -1;j++){
                for(int k = j+1;k<input.size();k++){
                    if(input.get(i)+input.get(j)+input.get(k)==2020){
                        return input.get(i)*input.get(j)*input.get(k);
                    }
                }
            }
        }
        return 0;
    }
}
