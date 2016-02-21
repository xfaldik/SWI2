package cz.fi.muni.pa165.seminar.pkmnleague.dto;

import java.util.Objects;

/**
 * @author Oldrich Faldik
 */
public class BadgeDTO {

    private int id;

    private TrainerDTO trainer;

    private GymDTO gym;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrainerDTO getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDTO trainer) {
        this.trainer = trainer;
    }

    public GymDTO getGym() {
        return gym;
    }

    public void setGym(GymDTO gym) {
        this.gym = gym;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.trainer);
        hash = 41 * hash + Objects.hashCode(this.gym);
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
        final BadgeDTO other = (BadgeDTO) obj;
        if (!Objects.equals(this.trainer, other.trainer)) {
            return false;
        }
        if (!Objects.equals(this.gym, other.gym)) {
            return false;
        }
        return true;
    }

}

