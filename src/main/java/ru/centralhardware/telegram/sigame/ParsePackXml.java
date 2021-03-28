package ru.centralhardware.telegram.sigame;

import com.vladimirkhil.ygpackage3_0.Pack;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ParsePackXml {

    private final File packXml;

    public ParsePackXml(File contentXml){
        if (!contentXml.exists() || !contentXml.isFile() || !contentXml.getName().equals("contentXml.xml")){
            throw new IllegalArgumentException();
        }
        this.packXml = contentXml;
    }

    public Pack unmarshal() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.vladimirkhil.ygpackage3_0");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try {
            return  (Pack) unmarshaller.unmarshal(new FileInputStream(packXml));
        } catch (FileNotFoundException ignored) { }
        return null;
    }

}
