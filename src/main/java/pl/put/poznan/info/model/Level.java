package pl.put.poznan.info.model;

import java.util.LinkedList;
import java.util.List;

public class Level extends Location{

    private List<Room> rooms;

    /**
     *
     * @param id level ID
     */
    public Level(int id){
        super(id);
        this.rooms = new LinkedList<>();
    }

    /**
     *
     * @param id level ID
     * @param name level name
     */
    public Level(int id, String name){
        super(id, name);
        this.rooms = new LinkedList<>();
    }


    /**
     * method adds the room to the list of rooms
     * @param room room to add
     */
    public void addRoom(Room room){
        this.rooms.add(room);
    }

    /**
     * the method returns a list of rooms
     * @return list of rooms
     */
    public List<Room> getRooms(){
        return this.rooms;
    }

    /**
     * the method sums up the area of all rooms on a level and returns the total area
     * @return level Area
     */
    @Override
    public float getArea() {
        float levelArea = 0;
        for (Location room : rooms){
            levelArea += room.getArea();
        }
        return levelArea;
    }

    /**
     * the method sums up the capacity of all rooms on a level and returns the total capacity
     * @return capacity of the level
     */
    @Override
    public float getCube() {
        float levelCube = 0;
        for (Location room : rooms){
            levelCube += room.getCube();
        }
        return levelCube;
    }

    /**
     * the method sums the lighting power of each room on a level and divides it by the total area of the level
     * @return average light intensity in lux
     */
    @Override
    public float getLightning() {
        float levelLight = 0;
        for (Location room : rooms){
            levelLight += room.getLightning();
        }
        return levelLight / getArea();
    }

    /**
     * the method sums up the heat consumption of each room on a level and divides it by the total capacity of the level
     * @return average heating energy consumption per m^3
     */
    @Override
    public float getHeating() {
        float levelHeating = 0;
        for (Location room : rooms){
            levelHeating += room.getHeating();
        }
        return levelHeating / getCube();
    }
}
