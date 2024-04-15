import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static String singleCarRun(ArrayList<String> data) {
        ArrayList<Integer> map = new ArrayList<>();
        ArrayList<Integer> coor = new ArrayList<>();
        String direction;

        String[] mapSplit = data.get(0).split(" ");
        Integer mapX = Integer.valueOf(mapSplit[0]);
        Integer mapY = Integer.valueOf(mapSplit[1]);
        map.add(mapX);
        map.add(mapY);

        String[] coorSplit = data.get(1).split(" ");
        Integer coorX = Integer.valueOf(coorSplit[0]);
        Integer coorY = Integer.valueOf(coorSplit[1]);
        coor.add(coorX);
        coor.add(coorY);
        direction = coorSplit[2];

        Car car = new Car(map, coor, direction);

        String steps = data.get(2);
        for (int i = 0; i < steps.length(); i++) {
            car.input(String.valueOf(steps.charAt(i)));
        }

        return car.pingdir();
    }

    public static String multiCarRun(ArrayList<String> data) {
        String result = "";
        ArrayList<Integer> map = new ArrayList<>();
        int stepNo = 0; // Number of steps
        int maxSteps = 0;

        String[] mapSplit = data.get(0).split(" ");
        Integer mapX = Integer.valueOf(mapSplit[0]);
        Integer mapY = Integer.valueOf(mapSplit[1]);
        map.add(mapX);
        map.add(mapY);

        HashMap<String, Car> cars = new HashMap<>();
        HashMap<String, String> steps = new HashMap<>();

        for (int i = 1; i < data.size() ; i = i+3) { // Populating cars
            ArrayList<Integer> coor = new ArrayList<>();
            String direction;

            String[] coorSplit = data.get((i + 1)).split(" ");
            Integer coorX = Integer.valueOf(coorSplit[0]);
            Integer coorY = Integer.valueOf(coorSplit[1]);
            coor.add(coorX);
            coor.add(coorY);
            direction = coorSplit[2];

            cars.put(data.get(i), new Car(map, coor, direction));
            String carSteps = data.get((i + 2));
            if (carSteps.length() > maxSteps) {
                maxSteps = carSteps.length();
            }
            steps.put(data.get(i), carSteps);
        }

        HashMap<String, String> locations = new HashMap<>();

        executor:
        while (stepNo < maxSteps) {

            // Go over each car and advance a step
            for (HashMap.Entry<String, String> entry : steps.entrySet()) {
                String key = entry.getKey(); // Car label
                String step = entry.getValue(); // Car steps
                Car car = cars.get(key); // Car object

                if (step.length() > stepNo) { // If car still has steps left to execute
                    car.input(String.valueOf(step.charAt(stepNo)));
                }
                String location = car.pingstep(stepNo);
                if (locations.containsValue(location)) { // Crash check
                    String crashKey = null;
                    for (Map.Entry<String, String> crashEntry : locations.entrySet()) {
                        if (location.equals(crashEntry.getValue())) {
                            crashKey = crashEntry.getKey();
                        }
                    }
                    result += crashKey + " " + key + "\n";
                    result += location.substring(0,3) + "\n";
                    result += (stepNo + 1);
                    break executor;
                } else {
                    locations.put(key, location);
                }
            }

            // All cars have moved without issue, advance step
            stepNo++;
            if (!(stepNo < maxSteps)) {
                result = "no collision";
            }
        }
        return result;
    }

}
