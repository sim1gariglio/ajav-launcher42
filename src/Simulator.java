import java.io.*;
import java.util.*;
import exceptions.*;
import utils.ConsoleColors;

public class Simulator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Simulator scenario.txt");
            return;
        }

        WeatherTower tower = new WeatherTower();
        AircraftFactory factory = AircraftFactory.getFactory();
        int numCycles = 0;
        List<Flyable> aircrafts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line = br.readLine();
            if (line == null) throw new ScenarioFormatException("Empty scenario file.");
            try {
                numCycles = Integer.parseInt(line.trim());
                if (numCycles <= 0)
                    throw new SimulationCycleException("Simulation cycles must be positive.");
            } catch (NumberFormatException e) {
                throw new SimulationCycleException("First line must be a number (simulation cycles).");
            }

            int lineNumber = 2;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 5) {
                    throw new ScenarioFormatException("Invalid format at line " + lineNumber + ": " + line);
                }
                String type = parts[0];
                String name = parts[1];
                int lon, lat, hgt;
                try {
                    lon = Integer.parseInt(parts[2]);
                    lat = Integer.parseInt(parts[3]);
                    hgt = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    throw new CoordinateException("Invalid number at line " + lineNumber + ": " + line);
                }
                if (lon < 0 || lat < 0 || hgt < 0) {
                    throw new CoordinateException("Negative coordinate at line " + lineNumber + ": " + line);
                }
                if (!type.equals("Baloon") && !type.equals("Helicopter") && !type.equals("JetPlane")) {
                    throw new AircraftTypeException("Unknown aircraft type at line " + lineNumber + ": " + type);
                }
                if (name.charAt(0) != type.charAt(0)) {
                    throw new ScenarioFormatException(
                        "Aircraft name must start with the same letter as its type at line " + lineNumber +
                        ": type=" + type + ", name=" + name
                    );
                }
                Coordinates coord = new Coordinates(lon, lat, hgt);
                Flyable f = factory.newAircraft(type, name, coord);
                if (f != null) {
                    f.registerTower(tower);
                    tower.registerFlyable(f);
                    aircrafts.add(f);
                }
                lineNumber++;
            }
        } catch (ScenarioFormatException e) {
            System.err.println("Scenario error: " + e.getMessage());
            return;
        } catch (SimulationCycleException e) {
            System.err.println("Simulation cycle error: " + e.getMessage());
            return;
        } catch (CoordinateException e) {
            System.err.println("Coordinate error: " + e.getMessage());
            return;
        } catch (AircraftTypeException e) {
            System.err.println("Aircraft type error: " + e.getMessage());
            return;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
            return;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return;
        }

        System.out.println("Simulation started for " + numCycles + " cycles.");
        for (int i = 0; i < numCycles; i++) {
            System.out.println("\n--- Simulation Cycle " + (i + 1) + " ---");
            tower.changeWeather();
        }
        System.out.println("\nSimulation ended.");
    }
}