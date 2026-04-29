package webapp.storage.junit6;

import webapp.storage.PathStorage;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName()));
    }
}