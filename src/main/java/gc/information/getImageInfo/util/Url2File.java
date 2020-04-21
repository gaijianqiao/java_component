package gc.information.getImageInfo.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Url2File {
    public static void transUrl2File(String url, String fileDir, String filename) throws IOException {
        URL realURL = new URL(url);
        File realFileDir = new File(fileDir);
        if (!realFileDir.exists()) {
            realFileDir.mkdirs();
        }
        File file = new File(fileDir + File.separator + filename);
        FileUtils.copyURLToFile(realURL, file);
    }
}
