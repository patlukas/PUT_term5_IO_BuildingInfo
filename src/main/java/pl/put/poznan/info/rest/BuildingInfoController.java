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
import pl.put.poznan.info.model.Room;

import java.sql.Array;
import java.util.Arrays;


@RestController
@RequestMapping("/api")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);

//    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
//    public String get(@PathVariable String text,
//                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] infos) {
//
//        // log the parameters
//        logger.debug(text);
//        logger.debug("JD");
//        logger.debug(Arrays.toString(infos));
//
//        // perform the transformation, you should run your logic here, below is just a silly example
//        BuildingInfo informer = new BuildingInfo(infos);
//        return informer.info(text);
//    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody String reqBody) {


        JSONObject obj = new JSONObject(reqBody);
        Building building = new Building((int) obj.get("id"), (String) obj.get("name"));
        logger.debug(building.getId() + " " + building.getName());
        JSONArray arr = obj.getJSONArray("levels");
        //logger.debug(arr.toString());
        for (Object pietro : arr){
            JSONObject jsonLevel = new JSONObject(pietro.toString());
            Level level = new Level( (int) jsonLevel.get("id"), (String) jsonLevel.get("name"));

            JSONArray rooms = jsonLevel.getJSONArray("rooms");
            for (Object r : rooms){
                //JSONObject jsonRoom = new JSONObject(r.toString());
                Room room = new Room(new JSONObject(r.toString()));
                level.addRoom(room);
            }


            building.addLevel(level);
            logger.debug(pietro.toString());
            //logger.debug(pietro.get("id").toString());
        }
//        JSONObject pietro = (JSONObject)arr.get(0);
        logger.debug(building.getLevels().toString());
//        logger.debug(pietro.get("id").toString());
//        logger.debug(pietro.get("rooms").toString());

//


//        if (obj.has("siema")) {
//            logger.debug("ma");
//        } else {
//            logger.debug("nie ma");
//        }


        //logger.debug(obj.toString());



        // perform the transformation, you should run your logic here, below is just a silly example
        //BuildingInfo informer = new BuildingInfo(infos);
        //return informer.info(text);
        //return Arrays.toString(infos);
        return obj.toString();
    }

}


