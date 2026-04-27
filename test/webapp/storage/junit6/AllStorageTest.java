package webapp.storage.junit6;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapUuidHashStorageTest.class,
        MapResumeStorageTest.class,
        FileStorageTest.class,
        ObjectStreamPathStorageTest.class
})
public class AllStorageTest {
}