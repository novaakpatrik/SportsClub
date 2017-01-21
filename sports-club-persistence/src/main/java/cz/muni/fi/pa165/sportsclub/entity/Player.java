package cz.muni.fi.pa165.sportsclub.entity;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

/**
 * Entity class which represents a player in SportsClub. Player can be a member
 * of multiple {@link Team}s, which is represented via {@link Membership}
 *
 * @author Patrik Novak
 */
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Min(0)
    @Max(230)
    private int height;

    @Min(0)
    @Max(130)
    private int weight;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Past
    private Date dateOfBirth;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "player")
    private Collection<Membership> memberships;

    public long getId() {
        return id;
    }

    public Player setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Player setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Player setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Player setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public Player setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime());
    }

    public Player setDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            this.dateOfBirth = null;
            return this;
        }
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        return this;
    }

    public Collection<Membership> getMemberships() {
        return Collections.unmodifiableCollection(memberships);
    }

    public Player setMemberships(Set<Membership> memberships) {
        this.memberships = memberships;
        return this;
    }

    public void addMembership(Membership membership) {
        memberships.add(membership);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(firstName);
        hash = 89 * hash + Objects.hashCode(lastName);
        hash = 89 * hash + Objects.hashCode(dateOfBirth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || !(obj instanceof Player)) {
            return false;
        }
        final Player other = (Player) obj;

        if (!getFirstName().equals(other.getFirstName())) {
            return false;
        }
        if (!getLastName().equals(other.getLastName())) {
            return false;
        }
        return getDateOfBirth().equals(other.getDateOfBirth());
    }

}
