package scoreboard.model;

import static java.util.Objects.isNull;
import java.util.Objects;
import scoreboard.exceptions.InvalidTeamException;

public class Team {

    protected static final String TEAM_NAME_MISSING_MESSAGE = "Team name can not be missing!";
    private final String name;

    private Team(String name) {
        if (isNull(name)) {
            throw new InvalidTeamException(TEAM_NAME_MISSING_MESSAGE);
        }
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
