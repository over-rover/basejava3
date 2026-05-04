package webapp.storage.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import webapp.model.Resume;

public interface Serializer {
    void doWrite(Resume r, OutputStream os) throws IOException;

    Resume doRead(InputStream is) throws IOException;
}