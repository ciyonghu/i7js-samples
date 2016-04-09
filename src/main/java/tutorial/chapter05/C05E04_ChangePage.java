/*
 * This example is part of the iText 7 tutorial.
 */
package tutorial.chapter05;

import com.itextpdf.kernel.color.*;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.File;
import java.io.IOException;

/**
 * Simple changing page properties example.
 */
public class C05E04_ChangePage {

    public static final String SRC = "src/main/resources/pdf/ufo.pdf";
    public static final String DEST = "results/chapter05/change_page.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C05E04_ChangePage().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws IOException {

        //Initialize PDF reader
        PdfReader reader = new PdfReader(SRC);

        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdfDoc = new PdfDocument(reader, writer);

        float margin = 72;
        for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
            PdfPage page = pdfDoc.getPage(i);
            Rectangle mediaBox = page.getMediaBox();
            Rectangle newMediaBox = new Rectangle(mediaBox.getLeft() - margin, mediaBox.getBottom() - margin,
                    mediaBox.getWidth() + margin * 2, mediaBox.getHeight() + margin * 2);
            page.setMediaBox(newMediaBox);

            PdfCanvas over = new PdfCanvas(pdfDoc.getPage(i));
            over.setStrokeColor(Color.GRAY);
            over.rectangle(mediaBox.getLeft(), mediaBox.getBottom(), mediaBox.getWidth(), mediaBox.getHeight());
            over.stroke();

            if (i % 2 == 0) {
                page.setRotation(180);
            }
        }

        pdfDoc.close();

    }
}