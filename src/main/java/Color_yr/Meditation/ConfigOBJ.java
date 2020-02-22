package Color_yr.Meditation;

import java.util.HashMap;
import java.util.Map;

public class ConfigOBJ {
    private Map<Integer, SetOBJ> Set;

    public ConfigOBJ() {
        Set = new HashMap<>();
    }

    public SetOBJ check(int time) {
        if (Set.containsKey(time))
            return Set.get(time);
        return null;
    }
}
