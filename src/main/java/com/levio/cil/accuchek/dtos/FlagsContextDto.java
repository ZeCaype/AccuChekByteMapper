package com.levio.cil.accuchek.dtos;

public class FlagsContextDto {

  private boolean carbohydrateIdAndCarbohydratePresent;
  private boolean mealPresent;
  private boolean testerHealthPresent;
  private boolean exerciseDurationAndExerciseIntensityPresent;
  private boolean medicationIdAndMedicationPresent;
  private boolean medicationValueUnits;
  private boolean hba1CPresent;
  private boolean extendedFlagsPresent;

  public FlagsContextDto() {

  }

  public boolean isCarbohydrateIdAndCarbohydratePresent() {
    return carbohydrateIdAndCarbohydratePresent;
  }

  public void setCarbohydrateIdAndCarbohydratePresent(
      boolean carbohydrateIdAndCarbohydratePresent) {
    this.carbohydrateIdAndCarbohydratePresent = carbohydrateIdAndCarbohydratePresent;
  }

  public boolean isMealPresent() {
    return mealPresent;
  }

  public void setMealPresent(boolean mealPresent) {
    this.mealPresent = mealPresent;
  }

  public boolean isTesterHealthPresent() {
    return testerHealthPresent;
  }

  public void setTesterHealthPresent(boolean testerHealthPresent) {
    this.testerHealthPresent = testerHealthPresent;
  }

  public boolean isExerciseDurationAndExerciseIntensityPresent() {
    return exerciseDurationAndExerciseIntensityPresent;
  }

  public void setExerciseDurationAndExerciseIntensityPresent(
      boolean exerciseDurationAndExerciseIntensityPresent) {
    this.exerciseDurationAndExerciseIntensityPresent = exerciseDurationAndExerciseIntensityPresent;
  }

  public boolean isMedicationIdAndMedicationPresent() {
    return medicationIdAndMedicationPresent;
  }

  public void setMedicationIdAndMedicationPresent(boolean medicationIdAndMedicationPresent) {
    this.medicationIdAndMedicationPresent = medicationIdAndMedicationPresent;
  }

  public boolean isMedicationValueUnits() {
    return medicationValueUnits;
  }

  public void setMedicationValueUnits(boolean medicationValueUnits) {
    this.medicationValueUnits = medicationValueUnits;
  }

  public boolean isHba1CPresent() {
    return hba1CPresent;
  }

  public void setHba1CPresent(boolean hba1cPresent) {
    hba1CPresent = hba1cPresent;
  }

  public boolean isExtendedFlagsPresent() {
    return extendedFlagsPresent;
  }

  public void setExtendedFlagsPresent(boolean extendedFlagsPresent) {
    this.extendedFlagsPresent = extendedFlagsPresent;
  }

}
