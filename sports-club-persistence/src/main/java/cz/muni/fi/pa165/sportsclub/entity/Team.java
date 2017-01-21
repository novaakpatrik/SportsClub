package cz.muni.fi.pa165.sportsclub.entity;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.sportsclub.enumeration.AgeGroup;

/**
 * Entity class which represents a team in SportsClub. Team can have several
 * {@link Player}s, which is represented via {@link Membership}.
 *
 * @author Fabian Norbert
 */

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "team")
    private Collection<Membership> memberships;

    @ManyToOne
    private TeamManager teamManager;

    @Enumerated
    private AgeGroup ageGroup;

    public long getId() {
        return id;
    }

    public Team setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public Team setTeamManager(TeamManager teamManager) {
        this.teamManager = teamManager;
        return this;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public Team setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
        return this;
    }

    public Team addMembership(Membership membership) {
        memberships.add(membership);
        return this;
    }

    public Team setMemberships(Collection<Membership> memberships){
        this.memberships = memberships;
        return this;
    }
    public Collection<Membership> getMemberships() {
        return Collections.unmodifiableCollection(memberships);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || !(o instanceof Team)) return false;

        Team team = (Team) o;

        return getName().equals(team.getName());

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}