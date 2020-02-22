package Color_yr.Meditation;

import java.util.HashMap;
import java.util.Map;

public class ConfigOBJ {
    private Map<Integer, SetOBJ> Set;
    private Map<Integer, SetOBJ> Delay;

    public ConfigOBJ() {
        Set = new HashMap<>();
    }

    public SetOBJ check(int time) {
        if (Set.containsKey(time))
            return Set.get(time);
        return null;
    }

    public SetOBJ checkD(int time) {
        for (Integer item : Delay.keySet()) {
            if (time % item == 0)
                return Delay.get(item);
        }
        return null;
    }
}
