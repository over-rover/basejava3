package webapp.storage.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import webapp.model.Company;
import webapp.model.CompanySection;
import webapp.model.Link;
import webapp.model.ListSection;
import webapp.model.Resume;
import webapp.model.TextSection;
import webapp.util.XmlParser;

public class XmlStreamSerializer implements Serializer {
    private final XmlParser xmlParser;

    public XmlStreamSerializer() {
        xmlParser = new XmlParser(Resume.class, Link.class, Company.class, Company.Position.class,
                CompanySection.class, ListSection.class, TextSection.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, writer);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(reader);
        }
    }
}