package pl.put.poznan.info.rest;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.json.JSONObject;


import pl.put.poznan.info.exceptions.JsonInputException;
import pl.put.poznan.info.logic.BuildingInfo;
import pl.put.poznan.info.model.Building;

@RestController
@RequestMapping("/api")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);

    //example request body
//    {
//        "id": 1,
//            "name": "BudynekTestowy",
//            "levels": [
//              {
//                  "id": 2,
//                  "name": "pietro1",
//                  "rooms": [
//                      {
//                          "id": 3,
//                          "name": "pokoj11",
//                           "area": 10,
//                           "cube": 20,
//                           "heating": 10.5,
//                          "light": 5.5
//                       },
//                       {
//                          "id": 4,
//                          "name": "pokoj12",
//                          "area": 12,
//                          "cube": 24,
//                          "heating": 10.5,
//                          "light": 5.5
//                      }
//                  ]
//              },
//              {
//                  "id": 5,
//                  "name": "pietro1",
//                  "rooms": [
//                      {
//                          "id": 6,
//                          "name": "pokoj21",
//                          "area": 5.0,
//                          "cube": 10.0,
//                          "heating": 10.5,
//                          "light": 5.5
//                      }
//                  ]
//               }
//          ]
//
//
//    }


    @RequestMapping(value = "/area/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBuildingArea(@RequestBody String reqBody) {
        try{
            Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));
            return createSuccessReturnJsonString(building.getArea());
        } catch (JsonInputException e) {
            return createErrorReturnJsonString(e.getMessage());
        }
    }

    @RequestMapping(value = "/cube/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBuildingCube(@RequestBody String reqBody) {
        try {
            Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));
            return createSuccessReturnJsonString(building.getCube());
        } catch (JsonInputException e) {
            return createErrorReturnJsonString(e.getMessage());
        }
    }

    @RequestMapping(value = "/light/all", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getBuildingLight(@RequestBody String reqBody) {
        try {
            Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));
            return createSuccessReturnJsonString(building.getLightning());
        } catch (JsonInputException e) {
            return createErrorReturnJsonString(e.getMessage());
        }
    }

    @RequestMapping(value = "/area/id/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAreaById(@PathVariable int id, @RequestBody String reqBody) {
        try {
            Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));
            return createSuccessReturnJsonString(building.getAreaById(id));
        } catch (JsonInputException e) {
            return createErrorReturnJsonString(e.getMessage());
        }
    }

    @RequestMapping(value = "/cube/id/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCubeById(@PathVariable int id, @RequestBody String reqBody) {
        try {
            Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));
            return createSuccessReturnJsonString(building.getCubeById(id));
        } catch (JsonInputException e) {
            return createErrorReturnJsonString(e.getMessage());
        }
    }

    @RequestMapping(value = "/light/id/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLightById(@PathVariable int id, @RequestBody String reqBody) {
        try {
            Building building = BuildingInfo.createBuilding(new JSONObject(reqBody));
            return createSuccessReturnJsonString(building.getLightById(id));
        } catch (JsonInputException e) {
            return createErrorReturnJsonString(e.getMessage());
        }
    }

    private String createSuccessReturnJsonString(float result) {
        JSONObject res = new JSONObject();
        res.put("status", "Success");
        res.put("result", result);
        return res.toString();
    }

    private String createErrorReturnJsonString(String message) {
        JSONObject res = new JSONObject();
        res.put("status", "Error");
        res.put("message", message);
        return res.toString();
    }

}


