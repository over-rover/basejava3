package webapp.storage.junit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import webapp.exception.ExistStorageException;
import webapp.exception.NotExistStorageException;
import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.Storage;


public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final int STORAGE_LIMIT = 10_000;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String DUMMY = "dummy";

    private Resume r1 = new Resume(UUID_1);
    private Resume r2 = new Resume(UUID_2);
    private Resume r3 = new Resume(UUID_3);
    private Resume[] initialResumes = new Resume[]{r1, r2, r3};

    private Resume r4 = new Resume(UUID_4);
    private Resume dummy = new Resume(DUMMY);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void save() {
        storage.save(r4);
        Assert.assertEquals(r4, storage.get(UUID_4));
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        try {
            for (int i = initialResumes.length; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail("Массив переполнен раньше времени " + e.getMessage());
        } finally {
            storage.save(new Resume());
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void get() {
        Assert.assertEquals(r1, storage.get(UUID_1));
        Assert.assertEquals(r2, storage.get(UUID_2));
        Assert.assertEquals(r3, storage.get(UUID_3));
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(initialResumes, storage.getAll());
    }

    @Test
    public void update() {
        ;
        storage.update(new Resume(UUID_1));
        Assert.assertEquals(r1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfNotExist() {
        storage.update(dummy);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        storage.get(UUID_2);
    }

    @Test
    public void clear() {
        storage.clear();
        for (Resume r : storage.getAll()) {
            if (r != null) Assert.fail();
        }
    }

    @Test
    public void size() {
        int expectedSize = initialResumes.length;

        Assert.assertEquals(expectedSize, storage.size());

        storage.save(r4);
        expectedSize++;
        Assert.assertEquals(expectedSize, storage.size());

        storage.delete(UUID_1);
        expectedSize--;
        Assert.assertEquals(expectedSize, storage.size());
    }
}