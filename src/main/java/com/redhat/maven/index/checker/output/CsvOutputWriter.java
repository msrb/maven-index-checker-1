package com.redhat.maven.index.checker.output;

import java.io.IOException;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;

public class CsvOutputWriter extends AbstractOutputWriter implements OutputWriter {

    @Override
    public void writeOne(String groupId, String artifactId, String version) throws IOException {
        this.writer.write(groupId + ',' + artifactId + ',' + version + '\n');
        this.writer.flush();
    }

    @Override
    public void writeAll(ISqlJetCursor cursor) throws SqlJetException, IOException {

        while (!cursor.eof()) {
            writeOne(cursor.getString("groupId"), cursor.getString("artifactId"), cursor.getString("version"));
            cursor.next();
        }
        this.writer.flush();
    }
}
