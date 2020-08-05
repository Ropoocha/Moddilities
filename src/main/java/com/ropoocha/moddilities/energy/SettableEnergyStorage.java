package com.ropoocha.moddilities.energy;

import net.minecraftforge.energy.EnergyStorage;

public class SettableEnergyStorage extends EnergyStorage {

  public SettableEnergyStorage(int capacity, int maxTransfer) {
    super(capacity, maxTransfer);
  }

  public void setEnergy(int energy) {
    this.energy = energy;
  }

  public void addEnergy(int energy) {
    this.energy += energy;
    if (this.energy > getMaxEnergyStored()) {
      this.energy = getMaxEnergyStored();
    }
  }

  public void consumeEnergy(int energy) {
    this.energy -= energy;
    if (this.energy < 0) {
      this.energy = 0;
    }
  }
}
