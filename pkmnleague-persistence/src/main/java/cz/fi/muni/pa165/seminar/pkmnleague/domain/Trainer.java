package cz.fi.muni.pa165.seminar.pkmnleague.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for Trainer object.
 *
 * @author dhanak @domhanak on 10/28/15.
 */
@Entity
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false)
    private String fullName;

    @NotNull
    @Column(nullable = false)
    private Date dateOfBirth;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Badge> badges = new HashSet<>();

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Pokemon> pokemon = new HashSet<>();

    @OneToOne
    private Gym gym;

    public Trainer(String fullName, String email, String password, Date dateOfBirth) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    protected Trainer() {
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.util.Date date) {
        this.dateOfBirth = date;
    }

    void addBadge(Badge badge) {
        badges.add(badge);
    }

    public Set<Badge> getBadges() {
        return Collections.unmodifiableSet(badges);
    }

    public Set<Pokemon> getPokemon() {
        return Collections.unmodifiableSet(pokemon);
    }

    public void addPokemon(Pokemon pokemon) {
        if (pokemon != null) {
            this.pokemon.add(pokemon);
        }
    }

    public void addPokemon(Collection<Pokemon> pokemon) {
        if (pokemon != null) {
            this.pokemon.addAll(pokemon);
        }
    }

    public boolean isGymLeaderAtGym(Gym gym) {
        return gym.getLeader().equals(this);
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public boolean isGymLeader() {
        return gym != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainer)) return false;

        Trainer trainer = (Trainer) o;

        if (!getFullName().equals(trainer.getFullName())) return false;
        if (!getEmail().equals(trainer.getEmail())) return false;
        return getDateOfBirth().equals(trainer.getDateOfBirth());

    }

    @Override
    public int hashCode() {
        int result = getFullName().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getDateOfBirth().hashCode();
        return result;
    }

}
