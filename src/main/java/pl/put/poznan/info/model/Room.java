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
    public Room(JSONObject jsonRoom){
        super( (int) jsonRoom.get("id"), (String) jsonRoom.get("name"));
        this.area =  jsonRoom.getFloat("area");
        this.cube =  jsonRoom.getFloat("cube");
        this.heating =  jsonRoom.getFloat("heating");
        this.light = jsonRoom.getFloat("light");
    }

    @Override
    public float getArea() {
        return this.area;
    }
}
