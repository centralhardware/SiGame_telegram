package ru.centralhardware.telegram.sigame;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ZipTest {

    private Zip zip;

    @After
    public void tearDown() {
        File unzipFolder = zip.getUnzipFolder();
        Assert.assertTrue(zip.deleteArchive());
        Assert.assertFalse(unzipFolder.exists());
    }

    @Test
    public void unzip() throws IOException {
        zip = new Zip(new File(getClass().getClassLoader().getResource("Voprosy_dlya_vsekh.siq").getFile()));
        File unzipFolder = zip.unzip();
        Assert.assertTrue(unzipFolder.exists());
        Assert.assertEquals(5, unzipFolder.list().length);
    }
}