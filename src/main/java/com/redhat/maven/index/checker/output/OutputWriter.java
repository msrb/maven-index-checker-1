package com.redhat.maven.index.checker.output;

import java.io.IOException;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;

public interface OutputWriter {

    public void writeOne(String groupId, String artifactId, String version) throws IOException;

    public void writeAll(ISqlJetCursor cursor) throws SqlJetException, IOException;
}
