/*

    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV

*/

/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/34117589
 */
package com.itextpdf.samples.sandbox.stamper;

import com.itextpdf.io.image.Image;
import com.itextpdf.io.image.ImageFactory;
import com.itextpdf.kernel.geom.AffineTransform;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import java.io.File;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class AddImageAffineTransform extends GenericTest {
    public static final String SRC = "./src/test/resources/pdfs/hello.pdf";
    public static final String DEST = "./target/test/resources/sandbox/stamper/add_image_affine_transform.pdf";
    public static final String IMG = "./src/test/resources/img/bruno.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddImageAffineTransform().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(dest));
        Image image = ImageFactory.getImage(IMG);
        com.itextpdf.layout.element.Image imageModel = new com.itextpdf.layout.element.Image(image);
        AffineTransform at = AffineTransform.getTranslateInstance(36, 300);
        at.concatenate(AffineTransform.getScaleInstance(imageModel.getImageScaledWidth(),
                imageModel.getImageScaledHeight()));
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getFirstPage());
        float[] matrix = new float[6];
        at.getMatrix(matrix);
        canvas.addImage(image, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5]);
        pdfDoc.close();
    }
}
