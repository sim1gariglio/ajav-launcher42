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

     public String getCurrentWeather(Coordinates p_coordinates) {
          // Simple random weather for now
          int idx = rand.nextInt(weather.length);
          return weather[idx];
     }
}