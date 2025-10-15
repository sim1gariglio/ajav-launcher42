import utils.ConsoleColors;

public class JetPlane extends Aircraft {
    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
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
                lat += 10;
                hgt += 2;
                System.out.println(ConsoleColors.YELLOW + "JetPlane#" + name + "(" + id + "): Here comes the sun, going 2 units up! and 10 units west." + ConsoleColors.RESET);
                break;
            case "RAIN":
                lat += 5;
                System.out.println(ConsoleColors.BLUE + "JetPlane#" + name + "(" + id + "): It's raining, going 5 units west." + ConsoleColors.RESET);
                break;
            case "FOG":
                lat += 1;
                System.out.println(ConsoleColors.CYAN + "JetPlane#" + name + "(" + id + "):  Fog ahead, going 1 unit west." + ConsoleColors.RESET);
                break;
            case "SNOW":
                hgt -= 7;
                System.out.println(ConsoleColors.WHITE + "JetPlane#" + name + "(" + id + "): Snow is dangerous, going down 7 units." + ConsoleColors.RESET);
                break;
        }
        if (hgt > 100) hgt = 100;
        if (hgt <= 0) {
            hgt = 0;
            if (!landed) {
                System.out.println(ConsoleColors.RED + "JetPlane#" + name + "(" + id + ") landing." + ConsoleColors.RESET);
                landed = true;
            }
        }
        System.out.println(ConsoleColors.MAGENTA +
            "JetPlane#" + name + "(" + id + ") Weather: " + weather +
            " | From [" + prevLon + "," + prevLat + "," + prevHgt + "]" +
            " -> [" + lon + "," + lat + "," + hgt + "]" +
            ConsoleColors.RESET);

        coordinates = new Coordinates(lon, lat, hgt);
    }
}