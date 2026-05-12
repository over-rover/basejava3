package webapp.storage.junit6;

import webapp.storage.PathStorage;
import webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getName(), new XmlStreamSerializer()));
    }
}