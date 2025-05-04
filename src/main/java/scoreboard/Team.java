package scoreboard;

import java.util.Objects;

public class Team {

    private final String name;

    private Team(String name) {
        this.name = name;
    }

    public static Team fromValue(String name) {
        return new Team(name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Team team)) {
            return false;
        }
        return Objects.equals(getName(), team.getName());
    }

    public String getName() {
        return name;
    }
}
