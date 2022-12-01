package pl.put.poznan.info.model;

public abstract class Location {
    private int id;
    private String name;

    protected Location(int id, String name){
        this.id = id;
        this.name = name;
    }
    protected Location(){}

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public abstract float getArea();
}
