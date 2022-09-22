package owpk.services;

import owpk.domain.CityLocation;

import java.util.List;

/**
 * @author Vorobyev Vyacheslav
 */
public interface CitySearchingService {
    List<CityLocation> search(String searchName, Float lat, Float lon, Integer totalShow);
}
