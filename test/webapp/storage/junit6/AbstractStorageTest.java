package webapp.storage.junit6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.model.Resume;
import webapp.storage.Storage;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume r1;
    private static final Resume r2;
    private static final Resume r3;
    private static final Resume r4;

    protected final Storage storage;
    private final int initialSize = 3;

    static {
        r1 = new Resume(UUID_1);
        r2 = new Resume(UUID_2);
        r3 = new Resume(UUID_3);
        r4 = new Resume(UUID_4);
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
        assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
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
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
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