/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/28163159/about-visible-digital-signatures
 */
package com.itextpdf.samples.sandbox.acroforms;

import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfReader;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.samples.GenericTest;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Category(SampleTest.class)
public class FlattenSignature extends GenericTest {
    public static final String SRC = "./src/test/resources/sandbox/acroforms/input_signed.pdf";
    public static final String DEST = "./target/test/resources/sandbox/acroforms/flatten_signature.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FlattenSignature().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(
                new FileInputStream(SRC)), new PdfWriter(new FileOutputStream(DEST)));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);
        form.flatFields();
        pdfDoc.close();
    }
}