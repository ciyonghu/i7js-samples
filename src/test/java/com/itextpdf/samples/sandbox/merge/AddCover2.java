/**
 * Example written by Bruno Lowagie.
 * http://stackoverflow.com/questions/29563942/how-to-add-a-cover-pdf-in-a-existing-itext-document
 */
package com.itextpdf.samples.sandbox.merge;

import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfReader;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.test.annotations.type.SampleTest;
import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.samples.GenericTest;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Category(SampleTest.class)
public class AddCover2 extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/merge/add_cover2.pdf";
    public static final String COVER
            = "./src/test/resources/sandbox/merge/hero.pdf";
    public static final String RESOURCE
            = "./src/test/resources/sandbox/merge/pages.pdf";
    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddCover2().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(RESOURCE), new PdfWriter(new FileOutputStream(dest)));
        PdfDocument cover = new PdfDocument(new PdfReader(COVER));
        cover.copyPages(1, 1, pdfDoc, 1, new PdfPageFormCopier());
        cover.close();
        pdfDoc.close();
    }
}