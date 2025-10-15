
public abstract class Aircraft implements Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    protected boolean landed = false;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    @Override
    public void registerTower(WeatherTower p_tower) {
        System.out.println(this.getClass().getSimpleName() + "#" + this.name + "(" + this.id + ") registered to weather tower.");
    }

    public abstract void updateConditions();
}