/**
 * Cristian Mosqueda, Eric Fahy, Albert Schaffer, Will Lord, and Tyler Vaillancourt
 * CSE360
 * Final Project
 * This class is made to get file extensions
 * Based off code from:
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/FileChooserDemo2Project/src/components/Utils.java
 */

import java.io.File;

public class TextFileFilterUtil {
    public final static String txt = "txt";

    /**
     * This gets the file extension for file saving.
     * @param f the file to be checked.
     * @return the file extension.
     */
    public static String getExtension(File f) {
        String extension = null;
        String filename = f.getName();
        int i = filename.lastIndexOf('.');

        if (i > 0 && i < filename.length() - 1) {
            extension = filename.substring(i + 1).toLowerCase();
        }
        return extension;
    }
}