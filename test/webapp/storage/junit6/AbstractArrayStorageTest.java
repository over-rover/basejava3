package webapp.storage.junit6;

import org.junit.jupiter.api.Test;
import webapp.exception.StorageException;
import webapp.model.Resume;
import webapp.storage.Storage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
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

        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

}