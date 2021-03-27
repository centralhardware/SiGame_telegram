package ru.centralhardware.telegram.sigame;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Zip {

    private static final String OUTPUT_DIR = "./packs/";
    private final File zip;
    private final File unzipFolder;

    public Zip(File zip){
        this.zip = zip;
        unzipFolder = new File(OUTPUT_DIR + zip.getName());
        deleteDirectory(unzipFolder);
    }

    public File getUnzipFolder() {
        return unzipFolder;
    }

    public File unzip() throws IOException {
        FileOutputStream fos;
        try (
                FileInputStream fis = new FileInputStream(zip);
                ZipArchiveInputStream zis = new ZipArchiveInputStream(fis);
        ){
            byte[] buffer = new byte[8192];
            ArchiveEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(unzipFolder, getInWrightEncoding(entry.getName()));
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    file.getParentFile().mkdirs();
                    fos = new FileOutputStream(file);
                    int read;
                    while ((read = zis.read(buffer,0,buffer.length)) != -1)
                        fos.write(buffer,0,read);
                    fos.close();
                }
            }
        }
        return unzipFolder;
    }

    private String getInWrightEncoding(String str){
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }


    public boolean deleteArchive(){
        return deleteDirectory(unzipFolder);
    }

    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

}
