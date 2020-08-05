package com.ropoocha.moddilities.setup;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHolder {

  public static final ForgeConfigSpec CLIENT_SPEC;
  public static final ForgeConfigSpec COMMON_SPEC;

  public static final Client CLIENT;
  public static final Common COMMON;

  static {
    final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
    CLIENT_SPEC = specPair.getRight();
    CLIENT = specPair.getLeft();
  }

  public static class Client {
    public Client(ForgeConfigSpec.Builder builder) {

    }
  }

  static {
    final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
    COMMON_SPEC = specPair.getRight();
    COMMON = specPair.getLeft();
  }

  public static class Common {
    public final ForgeConfigSpec.IntValue diamondGeneratorMaxPower;
    public final ForgeConfigSpec.IntValue diamondGeneratorGenerate;
    public final ForgeConfigSpec.IntValue diamondGeneratorSend;
    public final ForgeConfigSpec.IntValue diamondGeneratorTicks;

    public Common(ForgeConfigSpec.Builder builder) {

      diamondGeneratorMaxPower = builder
          .comment("The maximum power the Diamond Generator can store.")
          .defineInRange("power.diamond_generator.max_energy", 2137, 0, Integer.MAX_VALUE);

      diamondGeneratorGenerate = builder
          .comment("The amount of energy produced per 1 diamond.")
          .defineInRange("power.diamond_generator.generate", 20, 0, Integer.MAX_VALUE);

      diamondGeneratorSend = builder
          .comment("The amount of energy the Diamond Generator can transfer to other blocks per tick.")
          .defineInRange("power.diamond_generator.send", 10, 0, Integer.MAX_VALUE);

      diamondGeneratorTicks = builder
          .comment("How many ticks it takes to the Diamond Generator to process 1 diamond.")
          .defineInRange("power.diamond_generator.ticks", 20, 0, Integer.MAX_VALUE);
    }
  }
}
