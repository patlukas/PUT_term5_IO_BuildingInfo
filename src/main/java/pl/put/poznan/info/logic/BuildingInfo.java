package pl.put.poznan.info.logic;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.put.poznan.info.exceptions.JsonInputException;
import pl.put.poznan.info.model.Building;
import pl.put.poznan.info.model.Level;
import pl.put.poznan.info.model.Room;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class BuildingInfo {

    public static Building createBuilding(JSONObject jsonObject) throws JsonInputException {
        Building building = initBuilding(jsonObject);
        addLevelsInfo(building, jsonObject);

        checkAllIdIsUnique(building);

        return building;
    }

    private static Building initBuilding(JSONObject jsonObject) throws JsonInputException {
        int id = readIntValuesFromJson(jsonObject, "id");
        if(jsonObject.has("name")) return new Building(id, jsonObject.get("name").toString());
        return new Building(id);
    }

    private static void addLevelsInfo(Building building, JSONObject jsonBuild) throws JsonInputException {
        if(!jsonBuild.has("levels")) throw new JsonInputException("Brakuje tablicy 'levels'");
        JSONArray levels = jsonBuild.getJSONArray("levels");
        for (Object levelInfo : levels) {
            JSONObject jsonLevel = new JSONObject(levelInfo.toString());
            int id = readIntValuesFromJson(jsonLevel, "id");

            Level level;
            if(jsonLevel.has("name")) level = new Level(id, jsonLevel.get("name").toString());
            else level = new Level(id);

            addRoomsInfo(level, jsonLevel);
            building.addLevel(level);
        }
    }

    private static void addRoomsInfo(Level level, JSONObject jsonLevel) throws JsonInputException{
        if(!jsonLevel.has("rooms")) throw new JsonInputException("Brakuje tablicy 'rooms'");
        JSONArray rooms = jsonLevel.getJSONArray("rooms");
        for (Object roomInfo : rooms) {
            addRoomInfo(level, new JSONObject(roomInfo.toString()));
        }
    }

    private static void addRoomInfo(Level level, JSONObject jsonRoom) throws JsonInputException {
        int id = readIntValuesFromJson(jsonRoom, "id");
        String name = "";
        if(jsonRoom.has("name")) name = jsonRoom.get("name").toString();
        float area = readFloatValuesFromJson(jsonRoom, "area", false);
        float cube = readFloatValuesFromJson(jsonRoom, "cube", false);
        float heating = readFloatValuesFromJson(jsonRoom, "heating", true);
        float light = readFloatValuesFromJson(jsonRoom, "light", true);

        Room room = new Room(id, name, area, cube, heating, light);
        level.addRoom(room);
    }

    private static void checkAllIdIsUnique(Building building) throws JsonInputException{
        List<Integer> list = new LinkedList<>();
        list.add(building.getId());
        for(Level level : building.getLevels()) {
            list.add(level.getId());
            for(Room room : level.getRooms()) list.add(room.getId());
        }
        Set<Integer> listUnique = new HashSet<>(list);
        if(list.size() != listUnique.size()) throw new JsonInputException("Id musi być unikalne");
    }

    public static int readIntValuesFromJson(JSONObject jsonObject, String key) throws JsonInputException {
        if(!jsonObject.has(key)) throw new JsonInputException(key + " jest obowiązkowe");
        try {
            return jsonObject.getInt(key);
        } catch (Exception e) {
            throw new JsonInputException(key + " musi być całkowitoliczbowe");
        }
    }

    public static float readFloatValuesFromJson(JSONObject jsonObject, String key, Boolean canBeZero) throws JsonInputException {
        if(!jsonObject.has(key)) throw new JsonInputException(key +" jest obowiązkowe");
        float val;
        try {
            val =  jsonObject.getInt(key);
        } catch (Exception e) {
            throw new JsonInputException(key + " musi być liczbą");
        }
        if(canBeZero) {
            if(val < 0) throw new JsonInputException(key + " musi być nieujemne");
        } else {
            if (val <= 0) throw new JsonInputException(key + " musi być dodatnie");
        }
        return val;
    }
}
