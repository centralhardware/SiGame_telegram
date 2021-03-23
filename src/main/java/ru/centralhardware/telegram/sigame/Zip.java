package ru.centralhardware.telegram.sigame;

import org.apache.commons.codec.binary.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Zip {

    private static final String OUTPUT_DIR = "./packs/";
    private final File file;

    public Zip(File file){
        this.file = file;
    }

    public void unzip() throws IOException {
        File destDir = new File(OUTPUT_DIR + file.getName());
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file.getPath()));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        System.out.println(toUTF_8(zipEntry.getName()));
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }

    private String toUTF_8(String rawString) throws UnsupportedEncodingException {
        byte ptext[] = rawString.getBytes("ISO-8859-1");
        return new String(ptext, "UTF-8");

    }

    public boolean deleteArchive(){
        File toDelete = new File(OUTPUT_DIR + file.getName());
        return toDelete.delete();
    }

}
