package ru.centralhardware.telegram.sigame;

import com.vladimirkhil.ygpackage3_0.Pack;
import jakarta.xml.bind.JAXBException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

@SuppressWarnings("ConstantConditions")
public class ParsePackXmlTest {

    @Test
    public void unmarshal() throws JAXBException {
        File xml = new File(getClass().getClassLoader().getResource("content.xml").getFile());
        ParsePackXml parsePackXml = new ParsePackXml(xml);
        Pack pack = parsePackXml.unmarshal();
        Assert.assertEquals("e1922024-2ad6-48eb-8ae4-4b3ae858d013", pack.getId());
        Assert.assertEquals(4, pack.getRounds().getRound().size());
        Assert.assertEquals("Вопросы для всех", pack.getName());
        Assert.assertEquals("23.02.2021", pack.getDate());
        Assert.assertEquals(5L, (long) pack.getDifficulty());
    }
}