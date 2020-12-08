package DayFive;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DayFive {
    public static void main(String[] args) {
        File inputFile = new File("src/DayFive/input.txt");
        ArrayList<String> boardingPasses = new ArrayList<>();
        try{
            Scanner fileScanner = new Scanner(inputFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                boardingPasses.add(nextLine);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {

        }
        ArrayList<Integer> seatIds = new ArrayList<>();
        for(String s : boardingPasses){
           seatIds.add(getSeatId(s));
        }
        Collections.sort(seatIds);
        System.out.println(seatIds.get(seatIds.size()-1));

        //do some type of middle out search
        int pointerLeft = seatIds.size()/2;
        int pointerRight = seatIds.size()/2;
        boolean found = false;
        while(!found){
            if(seatIds.get(pointerLeft) - seatIds.get(pointerLeft-1)==2){
                System.out.println("answer: " + (seatIds.get(pointerLeft)-1));
                found = true;
            }
            if(seatIds.get(pointerRight) - seatIds.get(pointerRight-1) == 2){
                System.out.println("answer: " + (seatIds.get(pointerRight)-1));

                found = true;
            }
            else{
                pointerLeft--;
                pointerRight++;
            }
        }
    }

    public static int getSeatId(String input){
        int rowTop = 127;
        int rowBot = 0;
        int finalRow = 0;
        for(int i = 0;i<6;i++){
            if(input.charAt(i) == 'F'){
                rowTop = (int)(Math.floor((rowTop+rowBot) /2.0));
            }
            else if(input.charAt(i) == 'B'){
                rowBot = (int)Math.ceil((rowTop+rowBot) /2.0);
            }
        }
        switch(input.charAt(6)){
            case 'F' -> finalRow = rowBot;
            case 'B' -> finalRow = rowTop;
        }



        //now do seat
        int seatTop = 7;
        int seatBot = 0;
        int finalSeat = 0;
        for(int i = 7;i<9;i++){
            switch(input.charAt(i)){
                case 'R' -> seatBot = (int)Math.ceil((seatBot+seatTop)/2.0);
                case 'L' -> seatTop = (int)Math.floor((seatBot+seatTop)/2.0);
            }
        }
        switch(input.charAt(9)){
            case 'R' -> finalSeat = seatTop;
            case 'L' -> finalSeat = seatBot;
        }
        return finalRow * 8 + finalSeat;
    }
}
