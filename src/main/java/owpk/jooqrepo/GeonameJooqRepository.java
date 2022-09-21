package owpk.jooqrepo;

import test.tables.records.GeonameRecord;

import java.util.List;

/**
 * @author Vorobyev Vyacheslav
 */
public interface GeonameJooqRepository {
    List<GeonameRecord> findAll();
}
