package webapp.util;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static final StringBuilder INDENT = new StringBuilder();

    public static void main(String[] args) {
        String filePath = "src";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("src");
        System.out.println(dir.getName());
        recurseWalk(dir);
    }

    public static void recurseWalk(File obj) {
        if (obj.isFile()) return;
        File[] objects = obj.listFiles();
        if (objects != null) {
            INDENT.append(" ");
            for (File element : objects) {
                System.out.println(INDENT + element.getName());
                recurseWalk(element);
            }
            INDENT.deleteCharAt(INDENT.length() - 1);
        }
    }
}
