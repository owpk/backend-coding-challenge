package owpk.services;

/**
 * @author Vorobyev Vyacheslav
 */
public interface CitySearchingService {
    void search(String searchName, Float lat, Float lon, Integer coordsSpread);
}
