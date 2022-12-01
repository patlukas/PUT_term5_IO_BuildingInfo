package pl.put.poznan.info.rest;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;


import pl.put.poznan.info.logic.BuildingInfo;
import pl.put.poznan.info.model.Building;
import pl.put.poznan.info.model.Level;
import pl.put.poznan.info.model.Location;
import pl.put.poznan.info.model.Room;

import java.sql.Array;
import java.util.Arrays;


@RestController
@RequestMapping("/api")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);

    //example request body
//    {
//        "id": 1,
//            "name": "BudynekTestowy",
//            "levels": [
//        {
//            "id": 2,
//                "name": "pietro1",
//                "rooms": [
//            {
//                "id": 3,
//                    "name": "pokoj11",
//                    "area": 10,
//                    "cube": 20,
//                    "heating": 10.5,
//                    "light": 5.5
//            },
//            {
//                "id": 4,
//                    "name": "pokoj12",
//                    "area": 12,
//                    "cube": 24,
//                    "heating": 10.5,
//                    "light": 5.5
//            }
//            ]
//        },
//        {
//            "id": 5,
//                "name": "pietro1",
//                "rooms": [
//            {
//                "id": 6,
//                    "name": "pokoj21",
//                    "area": 5.0,
//                    "cube": 10.0,
//                    "heating": 10.5,
//                    "light": 5.5
//            }
//            ]
//        }
//    ]
//
//
//    }


    @RequestMapping(value = "/area/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBuildingArea(@RequestBody String reqBody) {

        Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));


        float areaAll = building.getArea();

        JSONObject res = new JSONObject();
        res.put("area", areaAll);
        return res.toString();
    }

    @RequestMapping(value = "/cube/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBuildingCube(@RequestBody String reqBody) {

        Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));


        float cubeAll = building.getCube();

        JSONObject res = new JSONObject();
        res.put("cube", cubeAll);
        return res.toString();
    }

    @RequestMapping(value = "/light/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBuildingLight(@RequestBody String reqBody) {

        Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));


        float lightAll = building.getLightning();

        JSONObject res = new JSONObject();
        res.put("light", lightAll);
        return res.toString();
    }

    @RequestMapping(value = "/area/id/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAreaById(@PathVariable int id, @RequestBody String reqBody) {

        Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));


        JSONObject res = new JSONObject();

        if (building.getId() == id) {
            res.put("area", building.getArea());
            res.put("id", id);
        } else {
            for (Level lvl : building.getLevels()){
                if (lvl.getId() == id) {
                    res.put("area", lvl.getArea());
                    res.put("id", id);
                    break;
                }
                for (Room room : lvl.getRooms()) {
                    if (room.getId() == id) {
                        res.put("area", room.getArea());
                        res.put("id", id);
                        break;
                    }
                }
            }
        }
        //if ()
        return res.toString();
    }

    @RequestMapping(value = "/cube/id/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCubeById(@PathVariable int id, @RequestBody String reqBody) {

        Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));


        JSONObject res = new JSONObject();

        if (building.getId() == id) {
            res.put("cube", building.getCube());
            res.put("id", id);
        } else {
            for (Level lvl : building.getLevels()){
                if (lvl.getId() == id) {
                    res.put("cube", lvl.getCube());
                    res.put("id", id);
                    break;
                }
                for (Room room : lvl.getRooms()) {
                    if (room.getId() == id) {
                        res.put("cube", room.getCube());
                        res.put("id", id);
                        break;
                    }
                }
            }
        }
        //if ()
        return res.toString();
    }

    @RequestMapping(value = "/light/id/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLightById(@PathVariable int id, @RequestBody String reqBody) {

        Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));


        JSONObject res = new JSONObject();

        if (building.getId() == id) {
            res.put("light", building.getLightning());
            res.put("id", id);
        } else {
            for (Level lvl : building.getLevels()){
                if (lvl.getId() == id) {
                    res.put("light", lvl.getLightning());
                    res.put("id", id);
                    break;
                }
                for (Room room : lvl.getRooms()) {
                    if (room.getId() == id) {
                        res.put("light", room.getLightning());
                        res.put("id", id);
                        break;
                    }
                }
            }
        }
        //if ()
        return res.toString();
    }

}


