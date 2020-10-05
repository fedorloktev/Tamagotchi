package sample.Interfaces;

import java.io.IOException;
        // Interface for create new page
public interface FormViewer{
    void moveOnNewPage( String urlPage, String pageTitle, int width, int height) throws IOException;
}
