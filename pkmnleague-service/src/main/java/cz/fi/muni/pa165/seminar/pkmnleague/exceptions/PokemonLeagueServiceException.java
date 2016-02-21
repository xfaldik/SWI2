package cz.fi.muni.pa165.seminar.pkmnleague.exceptions;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
public class PokemonLeagueServiceException extends RuntimeException {

    public PokemonLeagueServiceException() {
        super();
    }

    public PokemonLeagueServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PokemonLeagueServiceException(String message) {
        super(message);
    }

}

