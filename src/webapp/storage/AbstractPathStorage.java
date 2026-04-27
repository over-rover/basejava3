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

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        Objects.requireNonNull(dir, "directory must not be null");
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not directory");
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath() + " is not readable/writable");
        }
    }

    @Override
    public void clear() {
        /* try {
            Files.list(directory).forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    doDelete(path);
                }
            });
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }*/

        /* try {
            Files.list(directory).forEach(path -> doDelete(path));
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }*/

        try (Stream<Path> stream = Files.list(directory)) {
            stream.forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public int size() {
        try (Stream<Path> stream = Files.list(directory)) {
            return (int) stream.count();
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
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
            throw new StorageException("Couldn't create file ",
                    path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return doRead(path);
        } catch (IOException e) {
            throw new StorageException("File read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getListStorage() {
        /* List<Path> paths;
        try {
            paths = Files.list(directory).toList();
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }

        List<Resume> resumes = new ArrayList<>(paths.size());
        for (Path path : paths) {
            resumes.add(doGet(path));
        }
        return resumes;*/

        /* try {
            return Files.list(directory).map(path -> doGet(path)).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        try (Stream<Path> stream = Files.list(directory)) {
            return stream.map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            doWrite(r, path);
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    protected abstract void doWrite(Resume r, Path path) throws IOException;

    protected abstract Resume doRead(Path path) throws IOException;
}