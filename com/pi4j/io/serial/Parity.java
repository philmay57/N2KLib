package com.pi4j.io.serial;

public enum Parity {
  NONE(0);

	@SuppressWarnings("unused")
  private int val = 0;
  private Parity(int val)
  {
    this.val = val;
  }

}
