package edu.spacexploration.udea.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import edu.spacexploration.udea.entities.CrewMember;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.lang.reflect.Type;

public class Reader {

  public List<CrewMember> readCrewMembers(String path) {
    try {
      FileInputStream fileInputStream = new FileInputStream(path);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
      Gson gson = new Gson();
      Type personListType = new TypeToken<List<CrewMember>>() {}.getType();
      List<CrewMember> crewMembers = gson.fromJson(inputStreamReader, personListType);
      return crewMembers;
    } catch (Exception e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
