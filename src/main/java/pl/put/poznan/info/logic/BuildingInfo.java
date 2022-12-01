package pl.put.poznan.info.logic;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.info.model.Building;
import pl.put.poznan.info.model.Level;
import pl.put.poznan.info.model.Room;
import pl.put.poznan.info.rest.BuildingInfoController;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class BuildingInfo {
    private static final Logger logger = LoggerFactory.getLogger(BuildingInfo.class);

    public static Building createBuilding(JSONObject jsonObject) {


        Building building = new Building((int) jsonObject.get("id"), (String) jsonObject.get("name"));
        logger.debug(building.getId() + " " + building.getName());
        JSONArray levels = jsonObject.getJSONArray("levels");

        for (Object lvl : levels){

            JSONObject jsonLevel = new JSONObject(lvl.toString());
            Level level = new Level( (int) jsonLevel.get("id"), (String) jsonLevel.get("name"));

            JSONArray rooms = jsonLevel.getJSONArray("rooms");
            for (Object r : rooms){

                Room room = new Room(new JSONObject(r.toString()));
                level.addRoom(room);
            }

            building.addLevel(level);

        }

        logger.debug(building.getLevels().toString());

        return building;
    }


}
