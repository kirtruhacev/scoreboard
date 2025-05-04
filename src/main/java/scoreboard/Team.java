package scoreboard;

public class Team {

    private final String name;

    public Team(String name) {
        this.name = name;
    }

    public static Team fromValue(String name) {
        return new Team(name);
    }

    public String getName() {
        return name;
    }
}
