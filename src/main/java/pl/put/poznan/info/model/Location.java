package pl.put.poznan.info.model;

public abstract class Location {
    private int id;
    private String name;

    protected Location(){}

    protected Location(int id){
        this.id = id;
        this.name = "";
    }

    protected Location(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public abstract float getArea();
    public abstract float getCube();
    public abstract float getLightning();
}
