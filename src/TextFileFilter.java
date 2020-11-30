/**
 * CSE360
 * Final Project
 * This class is made to filter file options to just text files.
 * Based on: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/ImageFilter.java
 */

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class TextFileFilter extends FileFilter {

    @Override
    /**
     * This determines if the file is of the correct extension.
     * @param f the file to be checked.
     * @return whether or not the file is accepted.
     */
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
    /**
     * This returns the specified file extension.
     * @return string the file extension.
     */
    public String getDescription() {
        return ".txt";
    }
}
