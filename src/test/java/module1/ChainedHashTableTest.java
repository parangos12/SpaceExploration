package module1;

import edu.spacexploration.udea.entities.CrewMember;
import edu.spacexploration.udea.module1.ChainedHashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChainedHashTableTest {

  private ChainedHashTable table;

  @BeforeEach
  void setUp() {
    table = new ChainedHashTable(10);
  }

  @Test
  void testGetCrewMembers_EmptyTable() {
    assertEquals(0, table.getCrewMembers(0).size());
  }

  @Test
  void testGetCrewMembers_NonEmptyTable() {
    CrewMember member1 = new CrewMember();
    member1.setFamilyId(1);
    table.add(member1, 0);

    CrewMember member2 = new CrewMember();
    member2.setFamilyId(2);
    table.add(member2, 1);

    List<CrewMember> expected = new ArrayList<>();
    expected.add(member1);
    assertEquals(expected, table.getCrewMembers(0));

    expected = new ArrayList<>();
    expected.add(member2);
    assertEquals(expected, table.getCrewMembers(1));
  }

  @Test
  void testGetHashValues() {
    assertEquals(List.of(0, 1, 2), table.getHashValues(123));
  }

  @Test
  void testFindAvailableCabin_EmptyTable() {
    assertEquals(-1, table.findAvailableCabin(1));
  }

  @Test
  void testFindAvailableCabin_NonEmptyTable() {
    table.add(new CrewMember(), 0);
    table.add(new CrewMember(), 0);
    table.add(new CrewMember(), 1);

    assertEquals(-1, table.findAvailableCabin(1));
    assertEquals(1, table.findAvailableCabin(2));
  }

  @Test
  void testFindCabinWithAdultSameFamilyAndAdultWithoutMinorDifferentFamily() {
    // Cabin 0: Family 1 (adult), Family 2 (adult, minor)
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 2), 0);
    table.add(new CrewMember(3, 10, 2), 0);

    // Cabin 1: Family 3 (adult), Family 4 (adult, minor)
    table.add(new CrewMember(4, 18, 3), 1);
    table.add(new CrewMember(5, 18, 4), 1);
    table.add(new CrewMember(6, 10, 4), 1);

    // Cabin 2: Family 5 (adult)
    table.add(new CrewMember(7, 18, 5), 2);

    // Cabin 3: Family 6 (adult, minor)
    table.add(new CrewMember(8, 18, 6), 3);
    table.add(new CrewMember(9, 10, 6), 3);

    assertEquals(0, table.findCabinWithAdultSameFamilyAndAdultWithoutMinorDifferentFamily(1));
    assertEquals(1, table.findCabinWithAdultSameFamilyAndAdultWithoutMinorDifferentFamily(2));
    assertEquals(2, table.findCabinWithAdultSameFamilyAndAdultWithoutMinorDifferentFamily(3));
    assertEquals(-1, table.findCabinWithAdultSameFamilyAndAdultWithoutMinorDifferentFamily(4));
  }

  @Test
  void testAdd() {
    CrewMember member = new CrewMember();
    member.setFamilyId(1);
    table.add(member, 0);

    assertEquals(1, table.getCrewMembers(0).size());
    assertTrue(table.crewMemberExists(member));
  }

  @Test
  void testCrewMemberExists_True() {
    CrewMember member = new CrewMember();
    member.setFamilyId(1);
    table.add(member, 0);

    assertTrue(table.crewMemberExists(member));
  }

  @Test
  void testCrewMemberExists_False() {
    CrewMember member = new CrewMember();
    member.setFamilyId(1);

    assertFalse(table.crewMemberExists(member));
  }

  @Test
  void testFindFirstAdultCrewMember() {
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 10, 1), 0);

    assertEquals(1, table.findFirstAdultCrewMember(0).getFamilyId());
  }

  @Test
  void testFindFirstAdultCrewMember_NoAdult() {
    table.add(new CrewMember(1, 10, 1), 0);
    table.add(new CrewMember(2, 10, 1), 0);

    assertNull(table.findFirstAdultCrewMember(0));
  }

  @Test
  void testFindFirstUnderageCrewMember() {
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 10, 1), 0);

    assertEquals(2, table.findFirstUnderageCrewMember(0).getFamilyId());
  }

  @Test
  void testFindFirstUnderageCrewMember_NoUnderage() {
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 1), 0);

    assertNull(table.findFirstUnderageCrewMember(0));
  }

  @Test
  void testAreThereAdultSameAndDifferentFamilyInCabin() {
    // Cabin 0: Family 1 (adult), Family 2 (adult, minor)
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 2), 0);
    table.add(new CrewMember(3, 10, 2), 0);

    assertTrue(table.areThereAdultSameAndDifferentFamilyInCabin(0, 1));
    assertFalse(table.areThereAdultSameAndDifferentFamilyInCabin(0, 2));
    assertFalse(table.areThereAdultSameAndDifferentFamilyInCabin(0, 3));
  }

  @Test
  void testDisplay() {
    // TODO: Implement test for display method
  }

  @Test
  void testCountCrewMembers() {
    // TODO: Implement test for countCrewMembers method
  }

  @Test
  void testGetHashFunctions() {
    // TODO: Implement test for getHashFunctions method
  }

  @Test
  void testFindAdultDifferentFamily() {
    // Cabin 0: Family 1 (adult), Family 2 (adult, minor)
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 2), 0);
    table.add(new CrewMember(3, 10, 2), 0);

    assertEquals(2, table.findAdultDifferentFamily(0, 1).getFamilyId());
    assertNull(table.findAdultDifferentFamily(0, 2));
  }

  @Test
  void testAreAllAdultsMembersSameFamily() {
    // Cabin 0: Family 1 (adult), Family 2 (adult, minor)
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 2), 0);
    table.add(new CrewMember(3, 10, 2), 0);

    assertTrue(table.areAllAdultsMembersSameFamily(0, 1));
    assertFalse(table.areAllAdultsMembersSameFamily(0, 2));
  }

  @Test
  void testIsThereAdultFromSameFamily() {
    // Cabin 0: Family 1 (adult), Family 2 (adult, minor)
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 2), 0);
    table.add(new CrewMember(3, 10, 2), 0);

    assertTrue(table.isThereAdultFromSameFamily(0, 1));
    assertFalse(table.isThereAdultFromSameFamily(0, 2));
  }

  @Test
  void testFindAdultDifferentFamilyWithoutMinor() {
    // Cabin 0: Family 1 (adult), Family 2 (adult, minor)
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 2), 0);
    table.add(new CrewMember(3, 10, 2), 0);

    assertEquals(2, table.findAdultDifferentFamilyWithoutMinor(0, 1).getFamilyId());
    assertNull(table.findAdultDifferentFamilyWithoutMinor(0, 2));
  }

  @Test
  void testFindMinorWithFamily() {
    // Cabin 0: Family 1 (adult), Family 2 (adult, minor)
    table.add(new CrewMember(1, 18, 1), 0);
    table.add(new CrewMember(2, 18, 2), 0);
    table.add(new CrewMember(3, 10, 2), 0);

    assertTrue(table.findMinorWithFamily(0, 2));
    assertFalse(table.findMinorWithFamily(0, 1));
  }
}