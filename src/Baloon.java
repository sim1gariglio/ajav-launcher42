import utils.ConsoleColors;

public class Baloon extends Aircraft {
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
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
                lon += 2;
                hgt += 4;
                System.out.println(ConsoleColors.YELLOW + "Baloon#" + name + "(" + id + "): Here comes the sun, going 4 units up! and 2 units east." + ConsoleColors.RESET);
                break;
            case "RAIN":
                hgt -= 5;
                System.out.println(ConsoleColors.BLUE + "Baloon#" + name + "(" + id + "): It's raining, descending 5 units." + ConsoleColors.RESET);
                break;
            case "FOG":
                hgt -= 3;
                System.out.println(ConsoleColors.CYAN + "Baloon#" + name + "(" + id + "): Fog ahead, losing 3 units of altitude." + ConsoleColors.RESET);
                break;
            case "SNOW":
                hgt -= 15;
                System.out.println(ConsoleColors.WHITE + "Baloon#" + name + "(" + id + "): Snow is dangerous, going down 15 units." + ConsoleColors.RESET);
                break;
        }
        if (hgt > 100) hgt = 100;
        if (hgt <= 0) {
            hgt = 0;
            if (!landed) {
                System.out.println(ConsoleColors.RED + "Baloon#" + name + "(" + id + ") landing." + ConsoleColors.RESET);
                landed = true;
            }
        }
        System.out.println(ConsoleColors.MAGENTA +
            "Baloon#" + name + "(" + id + ") Weather: " + weather +
            " | From [" + prevLon + "," + prevLat + "," + prevHgt + "]" +
            " -> [" + lon + "," + lat + "," + hgt + "]" +
            ConsoleColors.RESET);

        coordinates = new Coordinates(lon, lat, hgt);
    }
}