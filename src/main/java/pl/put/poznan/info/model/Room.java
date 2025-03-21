package pl.put.poznan.info.model;

public class Room extends Location{
    private float area;
    private float cube;
    private float heating;
    private float light;
    private float rent;

    public Room(int id, String name, float area, float cube, float heating, float light, float rent){
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
        this.rent = rent;
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

    @Override
    public float getHeating() { return this.heating; }

    @Override
    public float getRent() { return this.rent; }
}
