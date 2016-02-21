package cz.fi.muni.pa165.seminar.pkmnleague.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Pavel Kouřil
 */
@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false)
    private int speciesId;

    @NotNull
    @Column(nullable = false)
    private String speciesName;

    @Column()
    private String nickname;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PokemonType primaryType;

    @Column()
    @Enumerated(EnumType.STRING)
    private PokemonType secondaryType;

    @NotNull
    @Column(nullable = false)
    private int level;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRAINER_ID")
    private Trainer trainer;

    public Pokemon(Trainer trainer, int speciesId, String speciesName, PokemonType primaryType, int level) {
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.primaryType = primaryType;
        this.level = level;
        this.trainer = trainer;
        trainer.addPokemon(this);
    }

    public Pokemon(Trainer trainer, int speciesId, String speciesName, PokemonType primaryType, PokemonType secondaryType, int level) {
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.primaryType = primaryType;
        this.secondaryType = secondaryType;
        this.level = level;
        this.trainer = trainer;
        trainer.addPokemon(this);
    }

    protected Pokemon() {
    }

    public int getId() {
        return id;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PokemonType getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(PokemonType primaryType) {
        this.primaryType = primaryType;
    }

    public PokemonType getSecondaryType() {
        return secondaryType;
    }

    public void setSecondaryType(PokemonType secondaryType) {
        this.secondaryType = secondaryType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon)) return false;

        Pokemon pokemon = (Pokemon) o;

        if (getSpeciesId() != pokemon.getSpeciesId()) return false;
        if (getLevel() != pokemon.getLevel()) return false;
        if (getNickname() != null ? !getNickname().equals(pokemon.getNickname()) : pokemon.getNickname() != null)
            return false;
        return getTrainer().equals(pokemon.getTrainer());

    }

    @Override
    public int hashCode() {
        int result = getSpeciesId();
        result = 31 * result + (getNickname() != null ? getNickname().hashCode() : 0);
        result = 31 * result + getLevel();
        result = 31 * result + getTrainer().hashCode();
        return result;
    }

}
