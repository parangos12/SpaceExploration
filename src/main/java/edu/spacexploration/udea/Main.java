package edu.spacexploration.udea;

import edu.spacexploration.udea.entities.CrewMember;
import edu.spacexploration.udea.module1.Spaceship;
import edu.spacexploration.udea.utils.Reader;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Reader reader = new Reader();
    List<CrewMember> crewMembers = reader.readCrewMembers("src/main/resources/generated_10000.json");
    Spaceship spaceship = new Spaceship(1000,10);
    spaceship.saveMembers(crewMembers);
    spaceship.getCabins().display();
    spaceship.getCabins().countCrewMembers();
  }
}