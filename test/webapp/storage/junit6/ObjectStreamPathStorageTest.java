package webapp.storage.junit6;

import webapp.storage.ObjectStreamPathStorage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.getName()));
    }
}