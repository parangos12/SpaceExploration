package edu.spacexploration.udea.module1;

import edu.spacexploration.udea.entities.CrewMember;
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
        //Hay que intercambiar a un familiar de distinta familia sin menores.

        if(cabins.isThereAdultFromSameFamily(cabinId, familyId)){
          CrewMember adultDifferentFamilyWithoutMinor = cabins.findAdultDifferentFamilyWithoutMinor(cabinId, familyId);
          if(adultDifferentFamilyWithoutMinor!=null){
            //adultCrewMember = cabins.findAdultDifferentFamily(cabinId, familyId);
            cabins.getCrewMembers(cabinId).remove(adultDifferentFamilyWithoutMinor);
            insertCrewMember(underageCrewMember, cabinId);
            insertCrewMemberInAvailableCabin(adultDifferentFamilyWithoutMinor);
            break;
          }
          if(cabins.areAllAdultsMembersSameFamily(cabinId, familyId)){
            adultCrewMember = cabins.findFirstAdultCrewMember(cabinId);
            cabins.getCrewMembers(cabinId).remove(adultCrewMember);
            insertCrewMember(underageCrewMember, cabinId);
            insertCrewMemberInAvailableCabin(adultCrewMember);
            break;
          }
      }
    }
  }}

  private void insertCrewMemberInAvailableCabin(CrewMember crewMember) {
    Integer cabinId = cabins.findAvailableCabin(cabinSize);
    insertCrewMember(crewMember, cabinId);
  }

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
