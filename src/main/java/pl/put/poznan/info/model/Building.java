package pl.put.poznan.info.model;

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
        return buildingLight;
    }
}
