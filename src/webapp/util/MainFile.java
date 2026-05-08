package webapp.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MainFile {
    public static final Comparator<File> FILE_COMPARATOR = MainFile::doCompare;

    public static void main(String[] args) {
        File dir = new File("src");
        System.out.println(dir.getName());
        recurseWalk(dir, "");
    }

    private static void recurseWalk(File obj, String offset) {
        Objects.requireNonNull(obj);
        File[] objects = obj.listFiles();
        Arrays.sort(objects, FILE_COMPARATOR);
        for (File element : objects) {
            if (element.isFile()) {
                String fileOffset = offset.substring(0, offset.length() - 2) + "|   ";
                System.out.println(fileOffset + element.getName());
            } else {
                System.out.println(offset + "|_" + element.getName());
                recurseWalk(element, offset + "  ");
            }
        }
    }

    private static int doCompare(File o1, File o2) {
        if (o1.isDirectory() && o2.isFile()) {
            return 1;
        } else if (o1.isFile() && o2.isDirectory()) {
            return -1;
        }
        return o1.compareTo(o2);
    }
}