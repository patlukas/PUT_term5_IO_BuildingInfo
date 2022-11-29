package pl.put.poznan.info.logic;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class BuildingInfo {

    private final String[] infos;

    public BuildingInfo(String[] infos){
        this.infos = infos;
    }

    public String info(String text){
        return text.toUpperCase();
    }
}
