package webapp.storage.junit6;

import webapp.storage.ObjectStreamStorage;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}