package webapp.storage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import webapp.exception.StorageException;
import webapp.model.Resume;

public class ObjectStreamPathStorage extends AbstractPathStorage {
    public ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void doWrite(Resume r, Path path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(Path path) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Read error", null, e);
        }
    }
}