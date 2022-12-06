package pl.put.poznan.info.exceptions;

public class NoIdException extends CreateBuildingExceptions{
    @Override
    public String toString(){
        return "No id provided.";
    }
}
