package display.graphics;

import javafx.scene.paint.Color;
import simulation.world.World;

import java.util.HashMap;

public class Pallet {
	private HashMap<String, Color[]> colorMap = new HashMap<>();

	public void generateBackgroundColors(String key, Color basicColor, int numberOfSteps){
		Color[] colors = new Color[numberOfSteps];
		colors[0] = basicColor;
		double maxBrightness = basicColor.getBrightness();
		double brightnessStep = maxBrightness/numberOfSteps;

		double h = basicColor.getHue();
		double s = basicColor.getSaturation();
		double b = basicColor.getBrightness();

		for(int i=1; i < numberOfSteps; i++){
			colors[i] = Color.hsb(h,s, b - i*brightnessStep);
		}
		if(colorMap.containsKey(key)){
			colorMap.replace(key, colors);
		}else {
			colorMap.put(key, colors);
		}
	}

	public void generateCellColorsOpacity(String key, Color basicColor, int numberOfSteps){
		Color[] colors = new Color[numberOfSteps];
		colors[0] = basicColor;
		double opacityStep = 1d/numberOfSteps;
		for(int i = 1; i < numberOfSteps; i++){
			colors[i] = basicColor.deriveColor(0d, 1d, 1d, 1-i*opacityStep);
		}
		if(colorMap.containsKey(key)){
			colorMap.replace(key, colors);
		}else {
			colorMap.put(key, colors);
		}
	}

	public Color overlayColors(Color overlay, Color underlay){
		if(overlay.getOpacity() == 1) return overlay;
		if(overlay.getOpacity() == 0) return underlay;

		double overlayAlpha = overlay.getOpacity() * 255d;
		double overlayRed = overlay.getRed();
		double overlayGreen = overlay.getGreen();
		double overlayBlue = overlay.getBlue();

		double underlayAlpha = underlay.getOpacity() * 255d;
		double underlayRed = underlay.getRed();
		double underlayGreen = underlay.getGreen();
		double underlayBlue = underlay.getBlue();

		double q = (1 - overlayAlpha / 255d) * underlayAlpha;
		double resultAlpha = q + overlayAlpha;
		double resultRed = (q * underlayRed + overlayAlpha * overlayRed) / resultAlpha;
		double resultGreen = (q * underlayGreen + overlayAlpha * overlayGreen) / resultAlpha;
		double resultBlue = (q * underlayBlue + overlayAlpha * overlayBlue) / resultAlpha;

		return Color.color(resultRed, resultGreen, resultBlue, resultAlpha / 255d);
	}

	public Color getColor(String key, int value, int max){
		Color[] cellColors = colorMap.get(key);
		double step = (double) max/(cellColors.length-1);
		int delta = max - value;
		int index = (int)Math.floor(delta/step);
		return cellColors[index];
	}
}
