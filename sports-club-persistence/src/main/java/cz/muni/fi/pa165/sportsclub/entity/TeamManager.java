package cz.muni.fi.pa165.sportsclub.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Entity class which represents a team manager in SportsClub. Team manager 
 * can manage multiple teams.
 * 
 * @author Marian Sulgan
 */

@Entity
public class TeamManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String contact;

    @OneToMany(mappedBy = "teamManager")
    private Collection<Team> teams;

    public long getId() {
        return id;
    }

    public TeamManager setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeamManager setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public TeamManager setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public TeamManager setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public Collection<Team> getTeams() {
        return Collections.unmodifiableCollection(teams);
    }

    public TeamManager setTeams(Collection<Team> teams) {
        this.teams = teams;
        return this;
    }

    public TeamManager addTeam(Team team) {
        this.teams.add(team);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(getName());
        hash = 67 * hash + Objects.hashCode(getAddress());
        hash = 67 * hash + Objects.hashCode(getContact());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || !(obj instanceof TeamManager)) {
            return false;
        }

        final TeamManager other = (TeamManager) obj;

        if (!getName().equals(other.getName()))
            return false;

        if (!getAddress().equals(other.getAddress()))
            return false;

        return (getContact().equals(other.getContact()));
    }

}