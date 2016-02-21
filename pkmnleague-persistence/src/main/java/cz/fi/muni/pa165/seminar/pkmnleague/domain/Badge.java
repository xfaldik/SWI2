package cz.fi.muni.pa165.seminar.pkmnleague.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Zuzana Goldmannova
 */
@Entity
public class Badge {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="TRAINER_ID")
    private Trainer trainer;

    @NotNull
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="GYM_ID")
    private Gym gym;

    public Badge(Trainer trainer, Gym gym) {
        this.trainer = trainer;
        this.gym = gym;
        trainer.addBadge(this);
    }

    protected Badge() {
    }
    
    public int getId() {
        return id;
    }
    
    public Trainer getTrainer() {
        return trainer;
    }
    
    public Gym getGym() {
        return gym;
    }
    
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    
    public void setGym(Gym gym) {
        this.gym = gym;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Badge)) return false;

        Badge badge = (Badge) o;

        if (!getTrainer().equals(badge.getTrainer())) return false;
        return getGym().equals(badge.getGym());

    }

    @Override
    public int hashCode() {
        int result = getTrainer().hashCode();
        result = 31 * result + getGym().hashCode();
        return result;
    }

}
