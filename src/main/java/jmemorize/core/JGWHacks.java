package jmemorize.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class JGWHacks {

    public static InputStream getLocalizationFile() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(
                new File("J:\\Coding-Projects\\jMemorize\\workspace\\jMemorize\\test\\fixtures\\simple_de.jml"));
        return fis;

    }

    public static InputStream getLangsFile() throws FileNotFoundException {

        FileInputStream fis = new FileInputStream(
                new File("J:\\Coding-Projects\\jMemorize\\workspace\\jMemorize\\resource\\text\\langs.txt"));
        return fis;

    }

}
