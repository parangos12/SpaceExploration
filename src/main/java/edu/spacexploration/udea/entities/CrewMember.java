package edu.spacexploration.udea.entities;

import com.google.gson.annotations.SerializedName;

public class CrewMember {

  @SerializedName("_id")
  private String id;

  @SerializedName("Name")
  private String name;

  @SerializedName("Surname")
  private String surname;

  @SerializedName("Age")
  private Integer age;

  @SerializedName("Gender")
  private String gender;

  @SerializedName("FamilyID")
  private Integer familyId;

  @SerializedName("CivilStatus")
  private String civilStatus;

  @SerializedName("Children")
  private Integer children;

  @SerializedName("TripsAchieved")
  private Integer tripsAchieved;

  @SerializedName("Salary")
  private Float salary;

  public CrewMember(String id, String name, String surname, Integer age, String gender,
                    Integer familyId, String civilStatus, Integer children, Integer tripsAchieved,
                    Float salary) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.age = age;
    this.gender = gender;
    this.familyId = familyId;
    this.civilStatus = civilStatus;
    this.children = children;
    this.tripsAchieved = tripsAchieved;
    this.salary = salary;
  }


  @Override
  public String toString() {
    return "CrewMember{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        ", familyId=" + familyId +
        ", civilStatus='" + civilStatus + '\'' +
        ", children=" + children +
        ", tripsAchieved=" + tripsAchieved +
        ", salary=" + salary +
        '}';
  }

  public Integer getFamilyId() {
    return familyId;
  }

  public Integer getAge() {
    return age;
  }
}
