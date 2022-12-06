package pl.put.poznan.info.model;

import org.json.JSONObject;

public class Room extends Location{
    private float area;
    private float cube;
    private float heating;
    private float light;

    public Room(int id, String name, float area, float cube, float heating, float light){
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }
    public Room(int id, float area, float cube, float heating, float light){
        super(id, "name");
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }
    public Room(JSONObject jsonRoom){
        super( jsonRoom.getInt("id"), jsonRoom.getString("name"));
        this.area =  jsonRoom.getFloat("area");
        this.cube =  jsonRoom.getFloat("cube");
        this.heating =  jsonRoom.getFloat("heating");
        this.light = jsonRoom.getFloat("light");
    }

    @Override
    public float getArea() {
        return this.area;
    }

    @Override
    public float getCube() {
        return this.cube;
    }

    @Override
    public float getLightning() {
        return this.light;
    }
}
