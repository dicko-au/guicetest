package gaffer;


import stubs.Domain;
import stubs.LablelMetricRegistry;
import stubs.NestedDomain;
import stubs.QueryStatus;

public interface IngestContext {
    LablelMetricRegistry getMetricRegistry();

    Domain fetchDomain(final String domainName);

    NestedDomain fetchNestedDomain(final String domainName);

    void event(final String queryId, final String message, final QueryStatus status);
}
