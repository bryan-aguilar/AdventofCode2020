package DayEight;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DayEight {
    public static void main(String[] args) {

        File inputFile = new File("src/DayEight/input.txt");
        ArrayList<String> instructions = new ArrayList<>();
        try{
            Scanner fileScanner = new Scanner(inputFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                instructions.add(nextLine);


            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(processPartOne(instructions));
            System.out.println(processPartTwo(instructions));
        }


    }

    public static int processPartOne(ArrayList<String> instructions){
        int index = 0;
        Set<Integer> visited = new HashSet<>();
        int acc = 0;
        while(!visited.contains(index)){
            visited.add(index);
            String[] ins = instructions.get(index).split(" ");
            switch(ins[0]){
                case "nop":
                    index++;
                    break;
                case "acc":
                    index++;
                    acc += Integer.parseInt(ins[1]);
                    break;
                case "jmp":
                    index += Integer.parseInt(ins[1]);
                    break;
            }

        }
        return acc;
    }

    public static int processPartTwo(ArrayList<String> instructions){
       for(int i = 0; i<instructions.size();i++){
           String[] ins = instructions.get(i).split(" ");
           if(ins[0].equals("jmp") || (ins[0].equals("nop") && Integer.parseInt(ins[1]) != 0)){
               ArrayList<String> modified = new ArrayList(instructions);
               switch(ins[0]){
                   case "nop" -> modified.set(i , "jmp " + ins[1]);
                   case "jmp" -> modified.set(i , "nop " + ins[1]);
               }
               Integer tryExecuteAnswer = tryExecute(modified);
                if(tryExecuteAnswer != null){
                    return tryExecuteAnswer;
                }
           }
       }
       return -1;
    }

    public static Integer tryExecute(ArrayList<String> instructions){
        //returns null if bad. returns acc if good
        int index = 0;
        Set<Integer> visited = new HashSet<>();
        int acc = 0;

        while(index < instructions.size()){
            if(visited.contains(index)){
                return null;
            }
            visited.add(index);
            String[] ins = instructions.get(index).split(" ");
            switch(ins[0]){
                case "nop":
                    index++;
                    break;
                case "acc":
                    index++;
                    acc += Integer.parseInt(ins[1]);
                    break;
                case "jmp":
                    index += Integer.parseInt(ins[1]);
                    break;
            }
        }
        return acc;
    }
}
