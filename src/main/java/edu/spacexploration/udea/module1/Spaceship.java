package edu.spacexploration.udea.module1;

import edu.spacexploration.udea.entities.CrewMember;
import edu.spacexploration.udea.utils.ChainedHashTable;
import java.util.ArrayList;
import java.util.List;

public class Spaceship {

  public static final int MIN_AGE = 18;
  private final Integer spaceshipMaxCabins;
  private final Integer cabinSize;
  private ChainedHashTable cabins;

  public Spaceship(Integer spaceshipMaxCabins, Integer cabinSize) {
    this.spaceshipMaxCabins = spaceshipMaxCabins;
    this.cabinSize = cabinSize;
  }

  public void saveMembers(List<CrewMember> crewMembers) {
    cabins = new ChainedHashTable(spaceshipMaxCabins);
    List<CrewMember> underageCrewMembers = new ArrayList<>();
    // 1. Iterate all the crew members and assign them to the spaceship
    for (CrewMember crewMember : crewMembers) {
      Integer familyId = crewMember.getFamilyId();
      List<Integer> hashValues;
      hashValues = cabins.getHashValues(familyId);
      int counter = 0;
      for (Integer cabinId : hashValues) {
        if (crewMember.getAge() < MIN_AGE) {
          underageCrewMembers.add(crewMember);
          break;
          // crewMember = handleUnderageCrewMember(crewMember, cabinId);
        }
        if (insertCrewMember(crewMember, cabinId)) {
          break;
        }
        counter++;
      }
      if (counter == hashValues.size()) {
        insertCrewMemberInAvailableCabin(crewMember);
      }
    }
    handleUnderageCrewMembers(underageCrewMembers);
  }

  private void handleUnderageCrewMembers(List<CrewMember> underageCrewMembers) {
    for (CrewMember underageCrewMember : underageCrewMembers) {
      CrewMember adultCrewMember;
      Integer familyId = underageCrewMember.getFamilyId();
      for (int cabinId = 0; cabinId < spaceshipMaxCabins; cabinId++) {
        //Tiene q haber un adulto de la misma familia, este es el unico requisito. Acá habria ese condicional
        //Hay que intercambiar a un familiar de distinta familia que no tenga menores a cargo.

      }
    }
  }

  private void insertCrewMemberInAvailableCabin(CrewMember crewMember) {
    Integer cabinId = cabins.findAvailableCabin(cabinSize);
    insertCrewMember(crewMember, cabinId);
  }

  //  private CrewMember insertUnderageCrewMember(CrewMember underageCrewMember, Integer cabinId) {
  ////    if(cabins.findFirstUnderageCrewMember(cabinId)!=null){
  ////      return underageCrewMember;
  ////    }
  //    // 1. Find one adult crew member in the cabin
  //    CrewMember adultCrewMember = cabins.findFirstAdultCrewMember(cabinId);
  //    cabins.getCrewMembers(cabinId).remove(adultCrewMember);
  //    insertCrewMember(underageCrewMember, cabinId);
  //    //2. If not adult is found, continue with next cabin with same underage crew member
  //    return adultCrewMember;
  //  }

  private boolean insertCrewMember(CrewMember crewMember, Integer cabinId) {
    Integer sizeCrewMembersInCabin = cabins.getCrewMembers(cabinId).size();
    if (sizeCrewMembersInCabin >= cabinSize) {
      return false;
    }
    if (cabins.crewMemberExists(crewMember)) {
      return true;
    }
    cabins.add(crewMember, cabinId);
    return true;
  }

  public ChainedHashTable getCabins() {
    return cabins;
  }
}
