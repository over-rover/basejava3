package webapp.storage.junit6;

import webapp.storage.FileStorage;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(STORAGE_DIR));
    }
}