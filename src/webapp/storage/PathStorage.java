package webapp.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.serializer.Serializer;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private Serializer serializer;

    public PathStorage(String dir, Serializer serializer) {
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not readable/writable");
        }
        this.serializer = serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void clear() {
        try (Stream<Path> stream = Files.list(directory)) {
            stream.forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Clear directory error", e);
        }
    }

    @Override
    public int size() {
        try (Stream<Path> stream = Files.list(directory)) {
            return (int) stream.count();
        } catch (IOException e) {
            throw new StorageException("Directory is not found", e);
        }
    }

    @Override
    protected Path findSearchKey(String uuid) {
        return directory.resolve(Path.of(uuid));
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Create file error",
                    path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializer.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("Read file error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getListStorage() {
        try (Stream<Path> stream = Files.list(directory)) {
            return stream.map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Directory is not found", e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            serializer.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Write file error", r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Delete file error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }
}