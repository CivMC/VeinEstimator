package com.biggestnerd.veinestimator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VeinEstimator {
	private SimplexNoiseGenerator heightGen;
	private SimplexNoiseGenerator densityGen;
	private double density;
	private double maxSpan;
	private double densityBonus;
	private double areaHeight;
	private double areaSpan;
	private double heightLength;
	private double densityLength;
	private int radius;
	
	public VeinEstimator(long heightSeed, long densitySeed, double density, double maxSpan, double densityBonus,
			double areaHeight, double areaSpan, double heightLength, double densityLength, int radius) {
		heightGen = new SimplexNoiseGenerator(heightSeed);
		densityGen = new SimplexNoiseGenerator(densitySeed);
		this.density = density;
		this.maxSpan = maxSpan;
		this.densityBonus = densityBonus;
		this.areaHeight = areaHeight;
		this.areaSpan = areaSpan;
		this.heightLength = heightLength;
		this.densityLength = densityLength;
		this.radius = radius;
	}

	private void calculate(boolean drawImage) {
		long ores = 0;
		long sqr = radius * radius;
		int two = radius * 2;
		BufferedImage image = null;
		Graphics2D graphics = null;
		if(drawImage) {
			image = new BufferedImage(two, two, BufferedImage.TYPE_INT_RGB);
			graphics = image.createGraphics();
		}
		for(int x = 0; x < two; x++) {
			for(int z = 0; z < two; z++) {
				if(distance(x - radius, z - radius) > sqr) continue;
				for(int y = 1; y <= 16; y++) {
					if(Math.random() < Math.max(getOreChance(x - radius, y, z - radius), 0)) {
						ores++;
						if(drawImage) {
							graphics.setPaint(Color.WHITE);
							graphics.drawLine(x, z, x, z);
						}
					}
				}
			}
		}
		if(drawImage) {
			try {
				ImageIO.write(image, "PNG", new File(System.getProperty("user.dir"), "veins.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Total ores: " + ores);
	}
	
	private double getOreChance(int x, int y, int z) {
		double chance = Math.abs(y - getVeinHeight(x, z));
		if(chance > maxSpan) return 0;
		return ((Math.cos(chance * Math.PI / maxSpan) + 1) / 2) * getVeinDensity(x, z);
	}
	
	private double getVeinDensity(int x, int z) {
		return (densityGen.noise(x / densityLength, z / densityLength) + densityBonus) * density;
	}
	
	private double getVeinHeight(int x, int z) {
		return heightGen.noise(x / heightLength, z / heightLength) * areaSpan + areaHeight;
	}
	
	private double distance(int x, int z) {
		return (x * x) + (z * z);
	}
	
	public static void main(String[] args) {
		VeinEstimator estimator;
		boolean drawImage = true;
		if(args.length < 10) {
			estimator = getDefault();
		}
		try {
			long heightSeed = Long.parseLong(args[0]);
			long densitySeed = Long.parseLong(args[1]);
			double density = Double.parseDouble(args[2]);
			double maxSpan = Double.parseDouble(args[3]);
			double densityBonus = Double.parseDouble(args[4]);
			double areaHeight = Double.parseDouble(args[5]);
			double areaSpan = Double.parseDouble(args[6]);
			double heightLength = Double.parseDouble(args[7]);
			double densityLength = Double.parseDouble(args[8]);
			int radius = Integer.parseInt(args[9]);
			if(args.length == 11) {
				drawImage = Boolean.parseBoolean(args[10]);
			}
			estimator = new VeinEstimator(heightSeed, densitySeed, density, maxSpan, densityBonus,
					areaHeight, areaSpan, heightLength, densityLength, radius);
		} catch (Exception e) {
			estimator = getDefault();
		}
		estimator.calculate(drawImage);
	}
	
	public static VeinEstimator getDefault() {
		return new VeinEstimator(1, 1, 0.5, 5, 0, 10, 10, 10, 80, 1000);
	}
}
