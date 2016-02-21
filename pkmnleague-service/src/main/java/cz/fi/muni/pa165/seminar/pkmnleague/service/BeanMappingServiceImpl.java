package cz.fi.muni.pa165.seminar.pkmnleague.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dhanak @domhanak on 11/26/15.
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {

    @Autowired
    private Mapper dozer;

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public <T> T mapTo(Object u, Class<T> mapToClass) {
        if (u == null) {
            return null;
        }
        return dozer.map(u, mapToClass);
    }

    public Mapper getMapper() {
        return dozer;
    }

}
