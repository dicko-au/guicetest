package spi;

import java.util.Iterator;

/**
 * Responsible for service loading sink implementations
 */
public interface SinkLoader {
    void loadSinks();
    Iterator<SinkManager> getLoadedSinks();
}
