package gaffer;


import spi.QueryService;

import java.util.Map;


/**
 * Contains the core Gaffer management logic
 */
public class GafferSink implements Sink {

    // TODO Could use Spring to allow for alternate Query/Load/Purge Services to be wired up

    @Override
    public void initialize(Map<String, String> configuration) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public IngestService getIngestService() {
        return null;
    }

    @Override
    public QueryService getQueryService() {
        return null;
    }
}
