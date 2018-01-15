package com.redhat.maven.index.checker.output;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public abstract class AbstractOutputWriter {

    Writer writer;

    public AbstractOutputWriter() {
        this(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
    }

    public AbstractOutputWriter(Writer writer) {
        this.writer = writer;
    }
}
