package DaySeven;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DaySeven {
    public static void main(String[] args)  {
        //File inputFile = new File("src/DaySeven/input.txt");
        HashMap<String,List<String>> bagContainers = new HashMap<>();
        try{
            bagContainers = readBags("src/DaySeven/input.txt");
           System.out.println(countShinyGold(bagContainers));
        }catch (Exception e){

        }

        /*bagContainers.put("light red", Arrays.asList("bright white", "muted yellow"));
        bagContainers.put("dark orange",Arrays.asList("bright white","muted yellow"));
        bagContainers.put("bright white",Arrays.asList("shiny gold"));
        bagContainers.put("muted yellow",Arrays.asList("shiny gold","faded blue"));
        bagContainers.put("shiny gold",Arrays.asList("dark olive","dotted black"));
        //bagContainers.put("dark olive",Arrays.asList("light red","dark orange"));*/

    }


    public static HashMap<String, List<String>> readBags(String path) throws FileNotFoundException {
        HashMap<String, List<String>> bags = new HashMap<>();
        File file = new File(path);
        Scanner sc = new Scanner(file);

        StringBuilder sb = new StringBuilder();
        while (sc.hasNext())
        {
            String[] data = sc.nextLine().split("contain");
            data[0] = data[0].replace(" bags ", "");
            String[] subBags = data[1].split(",");
            ArrayList<String> sB = new ArrayList<>();
            for(int i = 0; i < subBags.length; i++){
                //subBags[i] = subBags[i].substring(3);
                subBags[i] = subBags[i].replace(".","");
                subBags[i] = subBags[i].replace(" bags", "");
                subBags[i] = subBags[i].replace(" bag", "");
                sB.add(subBags[i]);
            }
            bags.put(data[0],sB);
        }
        return bags;
    }

    public static int countShinyGold(HashMap<String, List<String>> bags){
        //we will add to queue and increment counter
        //pop off and iterate while queue is not empty

        int canContainGold = 0;
        Queue<String> searchQ = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        //populate queue
        for(String s : bags.keySet()){
            List<String> childBags = bags.get(s);
            if(childBags.contains("shiny gold")){
                canContainGold++;
                searchQ.add(s);
                visited.add(s);
            }
        }

        //now do a search through all bags until queue is empty
        while(!searchQ.isEmpty()){
            String containsThis = searchQ.poll();
            //now go through all of our containers to see if it contains this bag
            //make sure that we don't add to queue if its already in there
            for(String s : bags.keySet()){
                List<String> childBags = bags.get(s);
                if(childBags.contains(containsThis)){
                    if(!visited.contains(s)){
                        canContainGold++;
                        searchQ.add(s);
                        visited.add(s);
                    }
                }
            }
        }
        return canContainGold;
    }
}
