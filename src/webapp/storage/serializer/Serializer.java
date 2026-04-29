package webapp.storage.serializer;

import java.io.IOException;
import webapp.model.Resume;

public interface Serializer<O, I> {
    void doWrite(Resume r, O output) throws IOException;

    Resume doRead(I input) throws IOException;
}