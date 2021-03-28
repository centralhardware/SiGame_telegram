package ru.centralhardware.telegram.sigame;

import com.vladimirkhil.ygpackage3_0.Pack;
import jakarta.xml.bind.JAXBException;

import java.io.File;
import java.io.IOException;

public class SiqArchive {

    private static final String CONTENT_XML_FILE = "content.xml";

    private final Pack pack;
    private final Zip zip;

    public SiqArchive(File zipFile){
        zip = new Zip(zipFile);
        try {
            zip.unzip();
            ParsePackXml parsePackXml = new ParsePackXml(new File(zip.getUnzipFolder(), CONTENT_XML_FILE));
            pack = parsePackXml.unmarshal();
        } catch (IOException | JAXBException e) {
            throw new IllegalStateException();
        }
    }

    public Pack getPack() {
        return pack;
    }
}
