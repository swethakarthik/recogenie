package com.linkedin.hack.model;

public class ConnectionStrengthModel
{

  boolean success = false;
  Double connStrength = 0.0;
  public boolean isSuccess()
  {
    return success;
  }
  public void setSuccess(boolean success)
  {
    this.success = success;
  }
  public Double getConnStrength()
  {
    return connStrength;
  }
  public void setConnStrength(Double connStrength)
  {
    this.connStrength = connStrength;
  }

}
