package pl.put.poznan.info.model;

import java.util.LinkedList;
import java.util.List;

public class Level extends Location{

    private List<Room> rooms;

    public Level(int id){
        super(id);
        this.rooms = new LinkedList<>();
    }

    public Level(int id, String name){
        super(id, name);
        this.rooms = new LinkedList<>();
    }

    public void addRoom(Room room){
        this.rooms.add(room);
    }

    public List<Room> getRooms(){
        return this.rooms;
    }

    @Override
    public float getArea() {
        float levelArea = 0;
        for (Location room : rooms){
            levelArea += room.getArea();
        }
        return levelArea;
    }

    @Override
    public float getCube() {
        float levelCube = 0;
        for (Location room : rooms){
            levelCube += room.getCube();
        }
        return levelCube;
    }

    @Override
    public float getLightning() {
        float levelLight = 0;
        for (Location room : rooms){
            levelLight += room.getLightning();
        }
        return levelLight / getArea();
    }

    @Override
    public float getHeating() {
        float levelHeating = 0;
        for (Location room : rooms){
            levelHeating += room.getHeating();
        }
        return levelHeating / getCube();
    }
}
