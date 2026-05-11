package webapp.util;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class MainFile {
    private static final Comparator<File> FILE_COMPARATOR = MainFile::compareFiles;

    public static void main(String[] args) {
        File dir = new File("src");
        System.out.println(dir.getName());
        recurseWalk(dir, "");
    }

    private static void recurseWalk(File dir, String offset) {
        Objects.requireNonNull(dir, "directory must not be null");
        File[] entries = dir.listFiles();
        Objects.requireNonNull(entries, "directory is not found");
        Arrays.sort(entries, FILE_COMPARATOR);
        for (File entry : entries) {
            if (entry.isFile()) {
                String fileOffset = offset.substring(0, offset.length() - 2) + "|   ";
                System.out.println(fileOffset + entry.getName());
            } else {
                System.out.println(offset + "|_" + entry.getName());
                recurseWalk(entry, offset + "  ");
            }
        }
    }

    private static int compareFiles(File o1, File o2) {
        if (o1.isFile() && o2.isDirectory()) return -1;
        if (o1.isDirectory() && o2.isFile()) return 1;
        return o1.compareTo(o2);
    }
}