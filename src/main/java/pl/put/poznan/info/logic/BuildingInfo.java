package pl.put.poznan.info.logic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.info.exceptions.NoIdException;
import pl.put.poznan.info.model.Building;
import pl.put.poznan.info.model.Level;
import pl.put.poznan.info.model.Room;
import pl.put.poznan.info.rest.BuildingInfoController;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class BuildingInfo {
    private static final Logger logger = LoggerFactory.getLogger(BuildingInfo.class);

    public static Building createBuilding(JSONObject jsonObject) throws NoIdException {

        if(!jsonObject.has("id")) throw new NoIdException();

        Building building;

        //check if request body has name field
        if (jsonObject.has("name")){
            building = new Building(jsonObject.getInt("id"), jsonObject.getString("name"));
        } else {
            building = new Building(jsonObject.getInt("id"));
        }

        logger.debug(building.getId() + " " + building.getName());

        //if no level return empty building
        if (!jsonObject.has("levels")) return building;

        JSONArray levels = jsonObject.getJSONArray("levels");

        for (Object lvl : levels){

            JSONObject jsonLevel = new JSONObject(lvl.toString());

            if (!jsonLevel.has("id")) throw new NoIdException();

            Level level;
            if (jsonLevel.has("name")) {
                level = new Level( jsonLevel.getInt("id"), jsonLevel.getString("name"));
            } else {
                level = new Level( jsonLevel.getInt("id"));
            }

            //if level has no rooms continue
            if (!jsonLevel.has("rooms")) continue;

            JSONArray rooms = jsonLevel.getJSONArray("rooms");
            for (Object r : rooms){

                JSONObject jsonRoom = new JSONObject(r.toString());

                //check if room has id
                if(!jsonRoom.has("id")) throw new NoIdException();

                //TODO add new exception for room fields
                Room room = new Room(jsonRoom);
                level.addRoom(room);
            }

            building.addLevel(level);

        }

        logger.debug(building.getLevels().toString());

        return building;
    }


}
