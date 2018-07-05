package Helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class DragAndDropJSHelper {

    private String dragndrop_js = null;
    //private String path="/Users/himrekha/IdeaProjects/TheInternet/src/main/resources/DragAndDrop.js";
    File file4=new File("src/main/resources/DragAndDrop.js");
    private String path=file4.getAbsolutePath();

    public DragAndDropJSHelper()  {
        // the javascript file must be read into a single string no line breaks
        try (BufferedReader br = Files.newBufferedReader(Paths.get(getURIFromURL()))) {
            dragndrop_js = br
                    .lines()
                    .collect(Collectors.joining(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("finally")
    private URI getURIFromURL() {
        URI uri = null;
        try {
            File file=new File(path);
            URL url = file.toURI().toURL();
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            return uri;
        }
    }

    public void dragAndDrop(WebDriver driver,String src,String dst){
        String js = String.format("$('%s').simulateDragDrop({ dropTarget: '%s'});",src,dst);
        JavascriptExecutor j=(JavascriptExecutor)driver;
        String script=dragndrop_js+js;
        j.executeScript(script);
    }
}
