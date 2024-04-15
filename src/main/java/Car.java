import java.util.ArrayList;

public class Car {
    ArrayList<Integer> map;
    ArrayList<Integer> coor;
    String direction;

    public Car(ArrayList<Integer> map, ArrayList<Integer> coor, String direction) {
        this.map = map;
        this.coor = coor;
        this.direction = direction;
    }

    public void input(String instruction) {
        switch (instruction) {
            case "F" -> this.moveForward();
            case "L" -> this.turn(Turndir.LEFT);
            case "R" -> this.turn(Turndir.RIGHT);
        }
    }

    // Moves one space in current direction
    public void moveForward() {
        Integer x = this.coor.get(0);
        Integer y = this.coor.get(1);
        ArrayList<Integer> newcoor = new ArrayList<>();
        Integer maxX = this.map.get(0);
        Integer maxY = this.map.get(1);

        switch (direction) {
            case "N" -> y += 1;
            case "S" -> y -= 1;
            case "E" -> x += 1;
            case "W" -> x -= 1;
        }

        // Check for exceeding map boundaries
        if (x < 0) {
            x = 0;
        } else if (x >= maxX) {
            x = maxX - 1;
        }
        if (y < 0) {
            y = 0;
        } else if (y >= maxY) {
            y = maxY - 1;
        }

        newcoor.add(x);
        newcoor.add(y);
        this.coor = newcoor;
    }

    // Changes direction
    public void turn(Turndir turndir) {
        if (turndir == Turndir.LEFT) {
            switch (direction) {
                case "N" -> this.direction = "W";
                case "W" -> this.direction = "S";
                case "S" -> this.direction = "E";
                case "E" -> this.direction = "N";
            }
        } else if (turndir == Turndir.RIGHT) {
            switch (direction) {
                case "N" -> this.direction = "E";
                case "E" -> this.direction = "S";
                case "S" -> this.direction = "W";
                case "W" -> this.direction = "N";
            }
        }
    }

    public String pingstep(int stepNo) {
        String x = this.coor.get(0).toString();
        String y = this.coor.get(1).toString();
        return x + " " + y + " " + stepNo;
    }

    public String pingdir() {
        String x = this.coor.get(0).toString();
        String y = this.coor.get(1).toString();
        return x + " " + y + " " + this.direction;
    }

}
