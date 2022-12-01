package pl.put.poznan.info.model;

import java.util.LinkedList;
import java.util.List;

public class Building extends Location{

    private List<Location> levels;

    public Building(int id, String name){
        super(id, name);
        this.levels = new LinkedList<>();
    }

    public void addLevel(Level level){
        this.levels.add(level);
    }

    public List<Location> getLevels(){
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
}
