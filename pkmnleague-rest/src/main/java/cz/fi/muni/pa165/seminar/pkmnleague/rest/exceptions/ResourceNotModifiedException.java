package cz.fi.muni.pa165.seminar.pkmnleague.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Pavel Kouřil <pk@pavelkouril.cz>
 */
@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "The requested resource was not modified")
public class ResourceNotModifiedException extends RuntimeException {
}