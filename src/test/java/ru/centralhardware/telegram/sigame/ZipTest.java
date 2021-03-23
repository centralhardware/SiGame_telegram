package ru.centralhardware.telegram.sigame;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ZipTest {

    private Zip zip;

    @After
    public void tearDown() throws Exception {
        Assert.assertTrue(zip.deleteArchive());
    }

    @Test
    public void unzip() throws IOException {
        zip = new Zip(new File(getClass().getClassLoader().getResource("Voprosy_dlya_vsekh.siq").getFile()));
        zip.unzip();
        System.out.println("unzip archive");
    }
}