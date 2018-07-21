package gaffer;

import uk.gov.gchq.gaffer.store.StoreProperties;

import java.io.File;
import java.util.List;

/**
 * Implements the core Gaffer ingest logic
 */
public class GafferIngestServiceImpl implements IngestService {

    // Todo Inject this???
    IngestService ingestService;


    public GafferIngestServiceImpl(BasicGafferIngestService gafferIngestService) {
        this.ingestService = gafferIngestService;
    }

    @Override
    public void ingest(String id, String domain, List<File> ingestFiles, IngestContext ingestContext) {

    }

    @Override
    public Boolean cancel(String id, IngestContext ingestContext) {
        return null;
    }


}
