package gaffer;

import spi.QueryService;

import java.util.Map;

public interface Sink {
    void initialize(final Map<String, String> configuration);

    void onStart();

    void onShutdown();

    String getName();

    IngestService getIngestService();

    QueryService getQueryService();
}
