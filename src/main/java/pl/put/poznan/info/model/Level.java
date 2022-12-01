package pl.put.poznan.info.model;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class Level extends Location{

    private List<Location> rooms;

    public Level(int id, String name){
        super(id, name);
        this.rooms = new LinkedList<>();
    }



//    public Level(JSONObject level){
//        super( (int) level.get("id"), (String) level.get("name"));
//
//    }

    public void addRoom(Room room){
        this.rooms.add(room);
    }

    @Override
    public float getArea() {
        float levelArea = 0;
        for (Location room : rooms){
            levelArea += room.getArea();
        }
        return levelArea;
    }
}
