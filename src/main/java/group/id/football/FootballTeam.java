package group.id.football;

import java.util.Objects;

public class FootballTeam {

    private final String name;

    FootballTeam(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballTeam that = (FootballTeam) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
