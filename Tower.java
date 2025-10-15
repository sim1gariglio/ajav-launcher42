import java.util.List;
import java.util.ArrayList;

public abstract class Tower {
     private List<Flyable> observers = new ArrayList<>();

     public void registerFlyable(Flyable p_flyable) {
          if (!observers.contains(p_flyable)) {
                observers.add(p_flyable);
          }
     }

     public void unregisterFlyable(Flyable p_flyable) {
          observers.remove(p_flyable);
     }

     protected void conditionChanged() {
          // Notify all observers to update their conditions
          for (Flyable f : new ArrayList<>(observers)) {
                f.updateConditions();
          }
     }
}