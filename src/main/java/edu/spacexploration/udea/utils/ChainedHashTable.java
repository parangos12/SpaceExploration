package edu.spacexploration.udea.utils;

import edu.spacexploration.udea.entities.CrewMember;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ChainedHashTable {

  private int size;
  private List<Function<Integer, Integer>> hashFunctions;
  private List<List<CrewMember>> table;

  public ChainedHashTable(int size) {
    this.size = size;
    this.hashFunctions = getHashFunctions();
    this.table = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      table.add(new ArrayList<>());
    }
  }

  public List<CrewMember> getCrewMembers(Integer cabinId) {
    return table.get(cabinId);
  }

  public List<Integer> getHashValues(Integer key) {
    // Apply each hash function to the key to get a list of hash values
    List<Integer> hashValues = new ArrayList<>();
    for (Function<Integer, Integer> hashFunction : hashFunctions) {
      hashValues.add(hashFunction.apply(key) % size);
    }
    return hashValues;
  }

  public Integer findAvailableCabin(Integer cabinSize) {
    for (int i = 0; i < table.size(); i++) {
      if (table.get(i).size() < cabinSize) {
        return i;
      }
    }
    return -1;
  }

  // TODO: Find a cabin where there is an adult from same family, adult with different family without a minor of the same different family.
  public Integer findCabinWithAdultSameFamilyAndAdultWithoutMinorDifferentFamily(
      Integer familyId) {
    for (int i = 0; i < table.size(); i++) {
        if (areThereAdultSameAndDifferentFamilyInCabin(i, familyId)) {
          //TODO:Is there any minor from the different family?
          for(CrewMember crewMember: table.get(i)){
            if(!crewMember.getFamilyId().equals(familyId) && crewMember.getAge()<18){
              break;
            }
          }
          return i;
        }
    } return -1;
  }

  public void add(CrewMember crewMember, Integer cabinId) {
    table.get(cabinId).add(crewMember);
  }

  public boolean crewMemberExists(CrewMember crewMember) {
    for (List<CrewMember> cabin : table) {
      if (cabin.contains(crewMember)) {
        return true;
      }
    }
    return false;
  }

  public CrewMember findFirstAdultCrewMember(Integer cabinId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    for (CrewMember crewMember : crewMembersInCabin) {
      if (crewMember.getAge() >= 18) {
        return crewMember;
      }
    }
    return null;
  }

  public CrewMember findFirstUnderageCrewMember(Integer cabinId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    for (CrewMember crewMember : crewMembersInCabin) {
      if (crewMember.getAge() < 18) {
        return crewMember;
      }
    }
    return null;
  }

  public boolean areThereAdultSameAndDifferentFamilyInCabin(Integer cabinId, Integer familyId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    boolean sameFamily = false;
    boolean differentFamily = false;
    for (CrewMember crewMember : crewMembersInCabin) {
      if (crewMember.getFamilyId().equals(familyId) && crewMember.getAge() >= 18) {
        sameFamily = true;
      }
      if (!crewMember.getFamilyId().equals(familyId) && crewMember.getAge() >= 18) {
        differentFamily = true;
      }
    }
    return sameFamily && differentFamily;
  }

  //  public boolean isThereAdultSameFamilyInCabin(Integer cabinId,Integer familyId) {
  //    List<CrewMember> crewMembersInCabin = table.get(cabinId);
  //    for (CrewMember crewMember : crewMembersInCabin) {
  //      if (crewMember.getFamilyId().equals(familyId) && crewMember.getAge() >= 18) {
  //        return true;
  //      }
  //    }
  //    return false;
  //  }

  public void display() {
    for (int i = 0; i < table.size(); i++) {
      List<String> crewMembers =
          table.get(i).stream()
              .map(
                  x -> {
                    if (x.getAge() < 18) {
                      return "*" + x.getFamilyId().toString();
                    } else {
                      return x.getFamilyId().toString();
                    }
                  })
              .toList();

      System.out.println("Cabin " + i + ": " + crewMembers);
    }
  }
  public Integer findCabinWithAdultFromSameFamily(Integer familyId) {
    for (int cabinId = 0; cabinId < table.size(); cabinId++) {
      List<CrewMember> crewMembersInCabin = table.get(cabinId);
      for (CrewMember crewMember : crewMembersInCabin) {
        if (Objects.equals(crewMember.getFamilyId(), familyId) && crewMember.getAge() >= 18) {
          return cabinId;
        }
      }
    }
    return -1; // No se encontró ninguna cabina adecuada
  }

  public void countCrewMembers() {
    int count = 0;
    for (List<CrewMember> cabin : table) {
      count += cabin.size();
    }
    System.out.println("There are " + count + " crew members in the spaceship.");
  }

  private List<Function<Integer, Integer>> getHashFunctions() {
    List<Function<Integer, Integer>> hashFunctions = new ArrayList<>();
    // Modulo based hash function
    hashFunctions.add(
        key -> {
          return key % size;
        });
    // Mid-square hash function
    hashFunctions.add(
        key -> {
          int squared = key * key;
          String squaredStr = String.valueOf(squared);
          int mid = squaredStr.length() / 2;
          int extractLength = Math.min(2, squaredStr.length());
          String middleDigitsStr =
              squaredStr.substring(mid - extractLength / 2, mid + extractLength / 2);
          int middleDigits = Integer.parseInt(middleDigitsStr.equals("") ? "0" : middleDigitsStr);
          return middleDigits % size;
        });
    // Bit manipulation hash function
    hashFunctions.add(
        key -> {
          int hash = key ^ (key >>> 16);
          return hash % size;
        });
    return hashFunctions;
  }

  public CrewMember findAdultDifferentFamily(Integer cabinId, Integer familyId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    for (CrewMember crewMember : crewMembersInCabin) {
      if (!Objects.equals(crewMember.getFamilyId(), familyId) && crewMember.getAge() >= 18) {
        return crewMember;
      }
    }
    return null;
  }

  public boolean areAllAdultsMembersSameFamily(Integer cabinId, Integer familyId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    for (CrewMember crewMember : crewMembersInCabin) {
      if (!crewMember.getFamilyId().equals(familyId) && crewMember.getAge() >= 18) {
        return false;
      }
    }
    return true;

  }

  public boolean isThereAdultFromSameFamily(int cabinId, Integer familyId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    for (CrewMember crewMember : crewMembersInCabin) {
      if (Objects.equals(crewMember.getFamilyId(), familyId) && crewMember.getAge() >= 18) {
        return true;
      }
    }
    return false;
  }

  public CrewMember findAdultDifferentFamilyWithoutMinor(int cabinId, Integer familyId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    for (CrewMember crewMember : crewMembersInCabin) {
      if (!Objects.equals(crewMember.getFamilyId(), familyId) && crewMember.getAge() >= 18) {
        if(!findMinorWithFamily(cabinId, crewMember.getFamilyId())){
          return crewMember;
        }
        return crewMember;
      }
    }
    return null;
  }

  public boolean findMinorWithFamily(int cabinId, Integer familyId) {
    List<CrewMember> crewMembersInCabin = table.get(cabinId);
    for (CrewMember crewMember : crewMembersInCabin) {
      if (Objects.equals(crewMember.getFamilyId(), familyId) && crewMember.getAge() < 18) {
        return true;
      }
    }
    return false;
  }
}
