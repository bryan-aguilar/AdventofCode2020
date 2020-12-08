package DayFour;

import java.io.File;
import java.util.*;
import java.util.regex.*;

public class DayFour {
    static int totalCounter = 0;


    public static void main(String[] args) {
        File inputFile = new File("src/DayFour/input.txt");
        ArrayList<StringBuilder> passports = new ArrayList<>();
        StringBuilder curPass = new StringBuilder();
        try{
            Scanner fileScanner = new Scanner(inputFile);
            while(fileScanner.hasNext()){
                String nextLine = fileScanner.nextLine();
                if(!nextLine.isEmpty()){
                    if (curPass.length() != 0) {
                        curPass.append(" ");
                    }
                    curPass.append(nextLine);
                }
                else{
                    passports.add(curPass);
                    curPass = new StringBuilder();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            passports.add(curPass);
        }

        System.out.println(processPassports(passports));


    }

    public static int processPassports(ArrayList<StringBuilder> passports){
        int totalValid = 0;
        Map<String,String> passValues;
        for(StringBuilder s : passports){
            String[] pairs = s.toString().split(" ");
            passValues = new HashMap<>();
            for(String p : pairs){
                String[] kv = p.split(":");
                passValues.put(kv[0],kv[1]);
            }
            if((passValues.size() >= 7 && !passValues.containsKey("cid")) || passValues.size() == 8) {
                int byr = Integer.parseInt(passValues.get("byr"));
                if(byr < 1920 || byr > 2002)
                    continue;

                int iyr = Integer.parseInt(passValues.get("iyr"));
                if(iyr < 2010 || iyr > 2020)
                    continue;

                int eyr = Integer.parseInt(passValues.get("eyr"));
                if(eyr < 2020 || eyr > 2030)
                    continue;


                String hgt = passValues.get("hgt");
                if (!evaluateHgt(hgt)) continue;


                Pattern hcl = Pattern.compile("#([a-f|\\d]{6})");
                //Pattern hcl = Pattern.compile("#\\w{6}");
                Matcher hclMatcher = hcl.matcher(passValues.get("hcl"));
                if(!hclMatcher.matches())
                    continue;

               List<String> validEcl = Arrays.asList("amb","blu", "brn","gry", "grn","hzl","oth");
               if(!validEcl.contains(passValues.get("ecl")))
                   continue;

               Pattern pidPatt = Pattern.compile("\\d{9}");
               Matcher pidMatch = pidPatt.matcher(passValues.get("pid"));
               if(!pidMatch.matches())
                   continue;

                totalValid++;
            }
        }
        return totalValid;
    }

    private static boolean evaluateHgt(String hgt) {
        Pattern hgtPattern = Pattern.compile("\\d{1,3}(in|cm)");
        Matcher hgtMatch = hgtPattern.matcher(hgt);
        if(hgtMatch.matches()){
            int hgtInt = Integer.parseInt(hgt.substring(0, hgt.length()-2));
            String hgtStr = hgt.substring(hgt.length()-2);
            switch(hgtStr){
                case "cm":
                    if(hgtInt < 150 || hgtInt >193)
                        return false;
                    break;
                case "in":
                    if(hgtInt < 59 || hgtInt > 76)
                        return false;
                    break;
            }
            return true;
        }
        return false;
    }
}
