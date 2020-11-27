import javax.swing.filechooser.FileFilter;
import java.io.File;

/*
This class is made to filter file options to just text files
Based on: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/ImageFilter.java
 */

public class TextFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = TextFileFilterUtil.getExtension(f);
        if (extension != null) {
            if (extension.equals(TextFileFilterUtil.txt)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return ".txt";
    }
}
