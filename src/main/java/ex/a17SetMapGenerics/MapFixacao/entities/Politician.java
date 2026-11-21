package ex.a17SetMapGenerics.MapFixacao.entities;

public class Politician implements Comparable<Politician> {
    private String  name;
    private Integer votes;

    public Politician(String name, Integer votes) {
        this.name = name;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Politician that = (Politician) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public int compareTo(Politician o) {
        return Integer.compare(this.votes, o.votes);
    }

    @Override
    public String toString() {
        return name + ": " +
                votes;
    }
}
