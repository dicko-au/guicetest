package gaffer;


import spi.QueryContext;
import spi.QueryService;
import stubs.QueryRequest;
import stubs.ResultBatch;

/**
 * Implements the logic of Gaffer queries
 */
public class GafferQueryServiceImpl implements QueryService {

    @Override
    public String create(QueryRequest queryRequest, QueryContext queryContext) {
        return null;
    }

    @Override
    public ResultBatch next(String queryId, QueryContext queryContext) {
        return null;
    }

    @Override
    public ResultBatch run(QueryRequest queryRequest, QueryContext queryContext) {
        return null;
    }
}
