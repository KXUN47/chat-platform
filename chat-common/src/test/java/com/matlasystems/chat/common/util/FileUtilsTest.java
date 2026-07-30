package com.matlasystems.chat.common.util;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileUtilsTest {
    @TempDir Path temp;
    @Test void writesAndReadsFiles() throws Exception {
        Path file=temp.resolve("nested/test.txt");
        FileUtils.writeBytes(file,new byte[]{1,2});
        assertTrue(FileUtils.exists(file));
        assertArrayEquals(new byte[]{1,2},FileUtils.readBytes(file));
        assertEquals("txt",FileUtils.extension(file));
        assertTrue(FileUtils.delete(file));
    }
}
