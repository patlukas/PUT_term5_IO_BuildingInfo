package pl.put.poznan.info.exceptions;

abstract class CreateBuildingExceptions extends Exception {
    public CreateBuildingExceptions() {}

    public CreateBuildingExceptions(String message) {
        super(message);
    }
}
