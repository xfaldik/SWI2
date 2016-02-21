package cz.fi.muni.pa165.seminar.pkmnleague.dto;

import java.util.Objects;

/**
 * @author Oldrich Faldik
 */
public class PokemonEditDTO {

    private int id;

    private String nickname;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 37 * hash + Objects.hashCode(this.nickname);

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
        final PokemonEditDTO other = (PokemonEditDTO) obj;


        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }

        return true;
    }

}
