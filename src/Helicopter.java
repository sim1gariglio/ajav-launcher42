import utils.ConsoleColors;

public class Helicopter extends Aircraft {
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    @Override
    public void updateConditions() {
        String weather = WeatherProvider.getProvider().getCurrentWeather(this.coordinates);
        int prevLon = coordinates.getLongitude();
        int prevLat = coordinates.getLatitude();
        int prevHgt = coordinates.getHeight();

        int lon = prevLon;
        int lat = prevLat;
        int hgt = prevHgt;

        switch (weather) {
            case "SUN":
                lon += 10;
                hgt += 2;
                System.out.println(ConsoleColors.YELLOW + "Helicopter#" + name + "(" + id + "): Here comes the sun, going 2 units up! and 10 units east." + ConsoleColors.RESET);
                break;
            case "RAIN":
                lon += 5;
                System.out.println(ConsoleColors.BLUE + "Helicopter#" + name + "(" + id + "): It's raining, going 5 units east." + ConsoleColors.RESET);
                break;
            case "FOG":
                lon += 1;
                System.out.println(ConsoleColors.CYAN + "Helicopter#" + name + "(" + id + "):  Fog ahead, going 1 unit east." + ConsoleColors.RESET);
                break;
            case "SNOW":
                hgt -= 12;
                System.out.println(ConsoleColors.WHITE + "Helicopter#" + name + "(" + id + "): Snow is dangerous, going down 12 units." + ConsoleColors.RESET);
                break;
        }
        if (hgt > 100) hgt = 100;
        if (hgt <= 0) {
            hgt = 0;
            if (!landed) {
                System.out.println(ConsoleColors.RED + "Helicopter#" + name + "(" + id + ") landing." + ConsoleColors.RESET);
                landed = true;
            }
        }
        System.out.println(ConsoleColors.MAGENTA +
            "Helicopter#" + name + "(" + id + ") Weather: " + weather +
            " | From [" + prevLon + "," + prevLat + "," + prevHgt + "]" +
            " -> [" + lon + "," + lat + "," + hgt + "]" +
            ConsoleColors.RESET);

        coordinates = new Coordinates(lon, lat, hgt);
    }
}