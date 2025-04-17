package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesFromZipParsingTests {

    private ClassLoader cl = FilesFromZipParsingTests.class.getClassLoader();

    @Test
    void zipFilePdfParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("Documents.zip"), Charset.forName("windows-1251")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    assertTrue(pdf.text.contains("Сведения из Единого государственного реестра недвижимости"));

                }
            }

        }
    }

    @Test
    void zipFileXlsxParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("Documents.zip"), Charset.forName("windows-1251")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    String actualValue = xls.excel.getSheetAt(0).getRow(2).getCell(2).getStringCellValue();
                    Assertions.assertTrue(actualValue.contains("Перечень лечебных учреждений"));
                }
            }

        }
    }

    @Test
    void zipFileCsvParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("Documents.zip"), Charset.forName("windows-1251")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(2, data.size());
                    Assertions.assertArrayEquals(
                                new String[] {"Selenide", "https://selenide.org"},
                                data.get(0)
                        );
                    Assertions.assertArrayEquals(
                                new String[] {"JUnit 5","https://junit.org"},
                                data.get(1)
                        );
                }
            }
        }
    }
}
