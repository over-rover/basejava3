package webapp.storage.junit6;

import static org.junit.jupiter.api.Assertions.*;
import static webapp.util.ResumeTestData.createResume;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;
import webapp.storage.Storage;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("storage");
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String fullName = "defaultFullName";

    private static final Resume r1;
    private static final Resume r2;
    private static final Resume r3;
    private static final Resume r4;

    private final int initialSize = 3;

    static {
        r1 = createResume(UUID_1, fullName);
        r2 = createResume(UUID_2, fullName);
        r3 = createResume(UUID_3, fullName);
        r4 = createResume(UUID_4, fullName);
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r3);
        storage.save(r2);
    }

    @Test
    public void saveTest() {
        storage.save(r4);
        assertEquals(r4, storage.get(UUID_4));
        assertEquals(initialSize + 1, storage.size());
    }

    @Test
    public void saveIfExistTest() {
        assertThrows(ExistStorageException.class, () -> storage.save(createResume(UUID_1, fullName)));
    }

    @Test
    public void getIFNotExistTest() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    public void getTest() {
        assertEquals(r1, storage.get(UUID_1));
        assertEquals(r2, storage.get(UUID_2));
        assertEquals(r3, storage.get(UUID_3));
    }

    @Test
    public void getAllSortedTest() {
        List<Resume> expectedResumes = List.of(r1, r2, r3);
        List<Resume> actualResumes = storage.getAllSorted();
        assertIterableEquals(expectedResumes, actualResumes);
    }

    @Test
    public void updateTest() {
        Resume resume = createResume(UUID_1,fullName);
        storage.update(resume);
        assertEquals(resume, storage.get(UUID_1));
    }

    @Test
    public void updateIfNotExistTest() {
        assertThrows(NotExistStorageException.class, () -> storage.update(r4));
    }

    @Test
    public void deleteTest() {
        storage.delete(UUID_2);
        assertEquals(initialSize - 1, storage.size());
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    public void deleteIfNotExistTest() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
        assertIterableEquals(List.of(), storage.getAllSorted());
    }

    @Test
    public void sizeTest() {
        assertEquals(initialSize, storage.size());
    }
}