package webapp.storage.junit6;

import webapp.storage.PathStorage;
import webapp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new JsonStreamSerializer()));
    }
}