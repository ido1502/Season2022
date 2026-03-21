// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  // ---------------------------------------------------------------------------
  // AI Image Generator – OpenAI DALL-E configuration
  // ---------------------------------------------------------------------------

  /** OpenAI API key used by {@link frc.robot.AIImageGenerator}.
   *  Replace with your actual key or load from an environment variable. */
  public static final String OPENAI_API_KEY =
      System.getenv("OPENAI_API_KEY") != null ? System.getenv("OPENAI_API_KEY") : "";

  /** DALL-E model to use for image generation (e.g. "dall-e-3" or "dall-e-2"). */
  public static final String OPENAI_IMAGE_MODEL = "dall-e-3";

  /** Image size requested from the API (e.g. "1024x1024", "512x512"). */
  public static final String OPENAI_IMAGE_SIZE = "1024x1024";
}
