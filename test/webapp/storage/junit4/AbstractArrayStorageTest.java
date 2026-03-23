package webapp.storage.junit4;

import org.junit.Before;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.Storage;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private final Resume r1 = new Resume(UUID_1);
    private final Resume r2 = new Resume(UUID_2);
    private final Resume r3 = new Resume(UUID_3);
    private final Resume r4 = new Resume(UUID_4);

    private final int initialSize = 3;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void saveTest() {
        storage.save(r4);
        assertEquals(r4, storage.get(UUID_4));
        assertEquals(initialSize + 1, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverflowTest() {
        int storageLimit = 10_000;
        try {
            storage.clear();
            for (int i = 0; i < storageLimit; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            fail("Массив переполнен раньше времени " + e.getMessage());
        }

        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExistTest() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getIfNotExistTest() {
        storage.get(UUID_4);
    }

    @Test
    public void getTest() {
        assertEquals(r1, storage.get(UUID_1));
        assertEquals(r2, storage.get(UUID_2));
        assertEquals(r3, storage.get(UUID_3));
    }

    @Test
    public void getAllTest() {
        Resume[] expectedResumes = {r1, r2, r3};
        assertArrayEquals(expectedResumes, storage.getAll());
    }

    @Test
    public void updateTest() {
        Resume resume = new Resume(UUID_1);
        storage.update(resume);
        assertSame(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfNotExistTest() {
        storage.update(r4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteTest() {
        storage.delete(UUID_2);
        assertEquals(initialSize - 1, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteIfNotExistTest() {
        storage.delete(UUID_4);
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
        assertArrayEquals(new Resume[0], storage.getAll());
    }

    @Test
    public void sizeTest() {
        assertEquals(initialSize, storage.size());
    }
}