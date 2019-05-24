package com.levio.cil.accuchek.dtos;

public class SystemIdDto {

  private long manufacturerIdentifier;
  private int organizationallyUniqueIdentifier;

  public SystemIdDto() {}

  public long getManufacturerIdentifier() {
    return manufacturerIdentifier;
  }

  public void setManufacturerIdentifier(long manufacturerIdentifier) {
    this.manufacturerIdentifier = manufacturerIdentifier;
  }

  public int getOrganizationallyUniqueIdentifier() {
    return organizationallyUniqueIdentifier;
  }

  public void setOrganizationallyUniqueIdentifier(int organizationallyUniqueIdentifier) {
    this.organizationallyUniqueIdentifier = organizationallyUniqueIdentifier;
  }
}
