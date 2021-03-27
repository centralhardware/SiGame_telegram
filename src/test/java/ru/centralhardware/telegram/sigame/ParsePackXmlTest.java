package ru.centralhardware.telegram.sigame;

import jakarta.xml.bind.JAXBException;
import org.junit.Test;

import java.io.File;

public class ParsePackXmlTest {

    @Test
    public void unmarshal() throws JAXBException {
        File xml = new File(getClass().getClassLoader().getResource("content.xml").getFile());
        ParsePackXml parsePackXml = new ParsePackXml(xml);
        parsePackXml.unmarshal();
    }
}