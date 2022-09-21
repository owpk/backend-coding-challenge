package owpk.jooqrepo;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.tables.Geoname;
import test.tables.records.GeonameRecord;

import java.util.List;

/**
 * @author Vorobyev Vyacheslav
 */
@Service
public class GeonameJooqRepositoryImpl implements GeonameJooqRepository {

    private final DSLContext ctx;
    private final Geoname table;

    @Autowired
    public GeonameJooqRepositoryImpl(DSLContext ctx) {
        this.ctx = ctx;
        table = Geoname.GEONAME;
    }

    @Override
    public List<GeonameRecord> findAll() {
        return ctx.select(table.ID, table.NAME, table.COUNTRY, table.LAT, table.LONG)
                .from(table).fetch()
                .into(GeonameRecord.class);
    }
}
