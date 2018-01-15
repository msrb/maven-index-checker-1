package com.redhat.maven.index.checker.output;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.table.ISqlJetCursor;

public class JsonOutputWriter extends AbstractOutputWriter implements OutputWriter {

    @Override
    public void writeOne(String groupId, String artifactId, String version) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("groupId", groupId);
        obj.put("artifactId", artifactId);
        obj.put("version", version);
        this.writer.write(obj.toJSONString());
        this.writer.flush();
    }

    @Override
    public void writeAll(ISqlJetCursor cursor) throws SqlJetException, IOException {

        // print the resulting JSON manually to avoid having to load all objects in memory
        this.writer.write("[");

        boolean addComma = false;
        while (!cursor.eof()) {

            if (addComma) {
                this.writer.write(",");
            }
            addComma = true;

            writeOne(cursor.getString("groupId"), cursor.getString("artifactId"), cursor.getString("version"));
            cursor.next();
        }
        this.writer.write("]");
        this.writer.flush();
    }
}
