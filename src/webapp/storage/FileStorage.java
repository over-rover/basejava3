package webapp.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.serializer.Serializer;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private Serializer serializer;

    public FileStorage(File directory, Serializer serializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;

        this.serializer = serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Clear directory error");
        }
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        String[] list = directory.list();
        if (list == null) {
            throw new StorageException("Directory is not found");
        }
        return list.length;
    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Create file error " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Read file error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> getListStorage() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory is not found");
        }
        List<Resume> resumes = new ArrayList<>(files.length);
        for (File file : files) {
            resumes.add(doGet(file));
        }
        return resumes;
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            serializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Write file error", r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Delete file error", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }
}