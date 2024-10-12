package edu.spacexploration.udea.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
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
