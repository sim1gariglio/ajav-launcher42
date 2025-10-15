import java.util.Random;


public class WeatherProvider {
	  private static WeatherProvider instance = null;
	  private static final String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};
	  private Random rand = new Random();

	  private WeatherProvider() {}

	  public static WeatherProvider getProvider() {
			 if (instance == null) {
					 instance = new WeatherProvider();
			 }
			 return instance;
	  }

	  public String getCurrentWeather(Coordinates coordinates) {
		 int seed = coordinates.getLongitude() * 73856093
					  ^ coordinates.getLatitude() * 19349663
					  ^ coordinates.getHeight() * 83492791;
					// bitwise XOR to combine prime numbers into a single seed
			 Random rand = new Random(seed);
			 return weather[rand.nextInt(weather.length)];
	 }
}