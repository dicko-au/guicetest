package gaffer;


import com.google.common.collect.Sets;
import uk.gov.gchq.gaffer.accumulostore.AccumuloProperties;
import uk.gov.gchq.gaffer.accumulostore.SingleUseMockAccumuloStore;
import uk.gov.gchq.gaffer.cache.impl.HashMapCacheService;
import uk.gov.gchq.gaffer.cache.util.CacheProperties;
import uk.gov.gchq.gaffer.commonutil.StreamUtil;
import uk.gov.gchq.gaffer.graph.Graph;
import uk.gov.gchq.gaffer.graph.GraphConfig;
import uk.gov.gchq.gaffer.store.Store;
import uk.gov.gchq.gaffer.store.StoreProperties;
import uk.gov.gchq.gaffer.store.schema.Schema;
import uk.gov.gchq.gaffer.store.schema.SchemaEdgeDefinition;
import uk.gov.gchq.gaffer.store.schema.TypeDefinition;
import uk.gov.gchq.koryphe.impl.binaryoperator.Sum;
import uk.gov.gchq.koryphe.impl.predicate.IsMoreThan;
import uk.gov.gchq.koryphe.impl.predicate.IsTrue;

import java.io.File;
import java.util.List;

public class BasicGafferIngestService implements IngestService {
    protected final String schemaPath = "";

    // TODO try to inject this string
    protected final String storePropertiesPath = "";;
    protected final String graphConfigPath = "";

    // TODO Inject this using guice
    private StoreProperties storeProperties;

    // TODO Add @Inject here
    public BasicGafferIngestService(StoreProperties storeProperties) {
        this.storeProperties = storeProperties;
    }

    @Override
    public void ingest(String id, String domain, List<File> ingestFiles, IngestContext ingestContext) {



        /**
         *
         * TODO - Will the loading classes be custom or do we try to make them generic?
         * Difficult to see how to make specific graph building logic generic
         * TODO - If custom, how will the custom class be loaded?
         **/

//        final RoadUseElementGenerator dataGenerator = new RoadUseElementGenerator();
//        for (final String line : IOUtils.readLines(StreamUtil.openStream(getClass(), dataPath))) {
//            elements.add(dataGenerator._apply(line));
//        }
    }

    @Override
    public Boolean cancel(String id, IngestContext ingestContext) {
        return null;
    }

    private Graph loadSchema (String domain) {
        Graph graph;
        // [graph from files] Create a graph using config, schema and store properties files
        // ---------------------------------------------------------
        graph = new Graph.Builder()
                .config(StreamUtil.openStream(getClass(), graphConfigPath))
                .addSchemas(StreamUtil.openStreams(getClass(), schemaPath))
                .storeProperties(StreamUtil.openStream(getClass(), storePropertiesPath))
                .build();
        // ---------------------------------------------------------

        return null;
    }



    // [graph] Create a graph using config, schema and store properties from java
    // ---------------------------------------------------------
    final GraphConfig config = new GraphConfig.Builder()
            .graphId(getClass().getSimpleName())
            .build();

    final Schema schema = new Schema.Builder()
            .edge("RoadUse", new SchemaEdgeDefinition.Builder()
                    .description("A directed edge representing vehicles moving from junction A to junction B.")
                    .source("junction")
                    .destination("junction")
                    .directed("true")
                    .property("count", "count.long")
                    .build())
            .type("junction", new TypeDefinition.Builder()
                    .description("A road junction represented by a String.")
                    .clazz(String.class)
                    .build())
            .type("count.long", new TypeDefinition.Builder()
                    .description("A long count that must be greater than or equal to 0.")
                    .clazz(Long.class)
                    .validateFunctions(new IsMoreThan(0L, true))
                    .aggregateFunction(new Sum())
                    .build())
            .type("true", new TypeDefinition.Builder()
                    .description("A simple boolean that must always be true.")
                    .clazz(Boolean.class)
                    .validateFunctions(new IsTrue())
                    .build())
            .build();


    protected StoreProperties getDefaultStoreProperties() {
        return getAccumuloStoreProperties();
    }

    protected StoreProperties getAccumuloStoreProperties() {
        final AccumuloProperties properties = new AccumuloProperties();
        properties.setStoreClass(SingleUseMockAccumuloStore.class);
        properties.setInstance("instance1");
        properties.setZookeepers("zookeeper1");
        properties.setUser("user01");
        properties.setPassword("password");
        properties.set(CacheProperties.CACHE_SERVICE_CLASS, HashMapCacheService.class.getName());
        properties.setJobTrackerEnabled(true);
        properties.setOperationDeclarationPaths("sparkAccumuloOperationsDeclarations.json,ResultCacheExportOperations.json,ExportToOtherGraphOperationDeclarations.json,ScoreOperationChainDeclaration.json");
        return properties;
    }
}
