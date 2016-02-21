package cz.fi.muni.pa165.seminar.pkmnleague.rest.mixins;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@JsonIgnoreProperties({"password"})
public class TrainerDTOMixin {
}

