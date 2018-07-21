package spi;


import stubs.Domain;
import stubs.NestedDomain;
import stubs.QueryStatus;

/**
 * A mechanism to interact with core packages without knowing the internals.  Similar to MapReduce MapperContext
 * and ReducerContext
 *
 * I think the core will call out to the implementations of this to query sinks.
 */
public interface QueryContext {
    LabeledMetricRegistry getMetricRegistry();
    Domain fetchDomain(final String domainName);
    NestedDomain fetchNestedDomain(final String domainName);
    void event(final String queryId, final String message, final QueryStatus status);
}
