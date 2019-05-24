package com.levio.cil.accuchek.dtos;

public class GlucoseMeasurementContextDto {
 
  private FlagsContextDto flags;
  private int sequenceNumber;
  private String carbohydrateId;
  private String carbohydrateUnits;
  private String meal;
  private String tester;
  private String health;
  
  public GlucoseMeasurementContextDto() {
    
  }

  public FlagsContextDto getFlags() {
    return flags;
  }

  public void setFlags(FlagsContextDto flags) {
    this.flags = flags;
  }

  public int getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(int sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public String getCarbohydrateId() {
    return carbohydrateId;
  }

  public void setCarbohydrateId(String carbohydrateId) {
    this.carbohydrateId = carbohydrateId;
  }

  public String getCarbohydrateUnits() {
    return carbohydrateUnits;
  }

  public void setCarbohydrateUnits(String carbohydrateUnits) {
    this.carbohydrateUnits = carbohydrateUnits;
  }

  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }

  public String getTester() {
    return tester;
  }

  public void setTester(String tester) {
    this.tester = tester;
  }

  public String getHealth() {
    return health;
  }

  public void setHealth(String health) {
    this.health = health;
  }

}
