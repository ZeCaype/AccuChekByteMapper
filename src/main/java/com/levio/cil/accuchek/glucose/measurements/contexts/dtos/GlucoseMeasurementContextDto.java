package com.levio.cil.accuchek.glucose.measurements.contexts.dtos;

public class GlucoseMeasurementContextDto {

  private FlagsContextDto flags;
  private int sequenceNumber;
  private String carbohydrateId;
  private String carbohydrateUnits;
  private float carbohydrateQuantity;
  private String meal;
  private String tester;
  private String health;
  private int exerciceDuration;
  private int exerciceIntensity;
  private String medicationId;
  private String medicationUnits;
  private float medicationQuantity;
  private float HbA1c;

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

  public int getExerciceDuration() {
    return exerciceDuration;
  }

  public void setExerciceDuration(int exerciceDuration) {
    this.exerciceDuration = exerciceDuration;
  }

  public int getExerciceIntensity() {
    return exerciceIntensity;
  }

  public void setExerciceIntensity(int exerciceIntensity) {
    this.exerciceIntensity = exerciceIntensity;
  }

  public String getMedicationId() {
    return medicationId;
  }

  public void setMedicationId(String medicationId) {
    this.medicationId = medicationId;
  }

  public String getMedicationUnits() {
    return medicationUnits;
  }

  public void setMedicationUnits(String medicationUnits) {
    this.medicationUnits = medicationUnits;
  }

  public float getMedicationQuantity() {
    return medicationQuantity;
  }

  public void setMedicationQuantity(float medicationQuantity) {
    this.medicationQuantity = medicationQuantity;
  }

  public float getHbA1c() {
    return HbA1c;
  }

  public void setHbA1c(float hbA1c) {
    HbA1c = hbA1c;
  }

  public float getCarbohydrateQuantity() {
    return carbohydrateQuantity;
  }

  public void setCarbohydrateQuantity(float carbohydrateQuantity) {
    this.carbohydrateQuantity = carbohydrateQuantity;
  }

}
