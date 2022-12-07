package pl.put.poznan.info.model;

import pl.put.poznan.info.exceptions.JsonInputException;

import java.util.LinkedList;
import java.util.List;

public class Building extends Location{

    private List<Level> levels;

    public Building(){
        this.levels = new LinkedList<>();
    }

    public Building(int id){
        super(id);
        this.levels = new LinkedList<>();
    }

    public Building(int id, String name){
        super(id, name);
        this.levels = new LinkedList<>();
    }

    public void addLevel(Level level){
        this.levels.add(level);
    }

    public List<Level> getLevels(){
        return this.levels;
    }

    @Override
    public float getArea() {
        float buildingArea = 0;
        for (Location level : levels){
            buildingArea += level.getArea();
        }
        return buildingArea;
    }

    @Override
    public float getCube() {
        float buildingCube = 0;
        for (Location level : levels){
            buildingCube += level.getCube();
        }
        return buildingCube;
    }

    @Override
    public float getLightning() {
        float buildingLight = 0;
        for (Location level : levels){
            buildingLight += level.getLightning();
        }
        return buildingLight / levels.size();
    }

    public float getAreaById(int id) throws JsonInputException{
        Location location = getLocationById(id);
        return location.getArea();
    }

    public float getCubeById(int id) throws JsonInputException{
        Location location = getLocationById(id);
        return location.getCube();
    }

    public float getLightById(int id) throws JsonInputException{
        Location location = getLocationById(id);
        return location.getLightning();
    }

    public Location getLocationById(int id) throws JsonInputException{
        if(getId() == id) return this;
        for(Level level : levels) {
            if(level.getId() == id) return level;
            for(Room room : level.getRooms()) {
                if(room.getId() == id) return room;
            }
        }
        throw new JsonInputException("Nie istnieje pomieszczenie o żądanym Id");
    }

}
