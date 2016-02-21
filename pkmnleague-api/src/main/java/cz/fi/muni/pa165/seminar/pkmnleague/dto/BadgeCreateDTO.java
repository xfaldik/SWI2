package cz.fi.muni.pa165.seminar.pkmnleague.dto;

import java.util.Objects;

/**
 * @author Oldrich Faldik
 */
public class BadgeCreateDTO {

    private TrainerDTO trainer;

    private GymDTO gym;

    private int trainerId;

    private int gymId;

    public int getTrainerId() {
        return trainerId;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int id) {
        this.gymId = id;
    }

    public void setTrainerId(int id) {
        this.trainerId = id;
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
        hash = 23 * hash + Objects.hashCode(this.trainer);
        hash = 23 * hash + Objects.hashCode(this.gym);
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
        final BadgeCreateDTO other = (BadgeCreateDTO) obj;
        if (!Objects.equals(this.trainer, other.trainer)) {
            return false;
        }
        if (!Objects.equals(this.gym, other.gym)) {
            return false;
        }
        return true;
    }

}
