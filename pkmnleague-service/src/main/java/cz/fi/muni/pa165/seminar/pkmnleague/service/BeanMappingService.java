package cz.fi.muni.pa165.seminar.pkmnleague.service;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author dhanak @domhanak on 11/26/15.
 */
@Service
public interface BeanMappingService {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();

}
