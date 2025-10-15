
public class AircraftFactory {
     private static AircraftFactory instance = null;

     private AircraftFactory() {}

     public static AircraftFactory getFactory() {
          if (instance == null) {
                instance = new AircraftFactory();
          }
          return instance;
     }

     private static long idCounter = 1;

     public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
          long id = idCounter++;
          switch (p_type) {
                case "Helicopter":
                     return new Helicopter(id, p_name, p_coordinates);
                case "JetPlane":
                     return new JetPlane(id, p_name, p_coordinates);
                case "Baloon":
                     return new Baloon(id, p_name, p_coordinates);
                default:
                     return null;
          }
     }
}