package cz.fi.muni.pa165.seminar.pkmnleague.dto;

import cz.fi.muni.pa165.seminar.pkmnleague.domain.PokemonType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * @author Oldrich Faldik
 */
public class GymCreateDTO {

    @NotNull
    @Size(min = 4, max = 40)
    private String city;

    @NotNull
    private PokemonType type;

    private TrainerDTO leader;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    public TrainerDTO getLeader() {
        return leader;
    }

    public void setLeader(TrainerDTO leader) {
        this.leader = leader;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.city);
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.leader);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GymCreateDTO other = (GymCreateDTO) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.leader, other.leader)) {
            return false;
        }
        return true;
    }

}
