package webapp.storage.junit6;

import webapp.storage.PathStorage;
import webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new DataStreamSerializer()));
    }
}