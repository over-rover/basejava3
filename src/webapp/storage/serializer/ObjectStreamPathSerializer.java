package webapp.storage.serializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import webapp.exception.StorageException;
import webapp.model.Resume;

public class ObjectStreamPathSerializer implements Serializer<Path, Path> {
    @Override
    public void doWrite(Resume r, Path path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume doRead(Path path) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Read error", null, e);
        }
    }
}
