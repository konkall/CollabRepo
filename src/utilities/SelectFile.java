package utilities;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.lang.Object;

public class SelectFile {
    public static String OpenWindow(){

        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);

        if(dialog.getDirectory() != null){
            return dialog.getDirectory() + dialog.getFile();
        }
        else{
            return null;
        }
    }
}
