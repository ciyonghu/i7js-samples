package com.itextpdf.samples.sandbox.objects;

import com.itextpdf.basics.PdfException;
import com.itextpdf.basics.image.Image;
import com.itextpdf.basics.image.ImageFactory;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.model.Document;
import com.itextpdf.model.element.List;
import com.itextpdf.samples.GenericTest;
import org.junit.Ignore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Ignore("Unignore when images are supported in layout")
public class ListWithImageAsBullet extends GenericTest {

    public static final String IMG = "src/test/resources/img/bulb.gif";
    public static final String DEST = "./target/test/resources/sandbox/objects/list_with_image_bullet.pdf";

    public static void main(String[] args) throws IOException, PdfException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListWithImageAsBullet().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws IOException, PdfException {
        FileOutputStream fos = new FileOutputStream(dest);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);

        Image image = ImageFactory.getImage(IMG);
//        image.scaleAbsolute(12, 12);
//        image.setScaleToFitHeight(false);
        List list = new List().
                setListSymbol(new com.itextpdf.model.element.Image(image)).
                add("Hello World").
                add("This is a list item with a lot of text. It will certainly take more than one line. This shows that the list item is indented and that the image is used as bullet.").
                add("This is a test");
        doc.add(list);

        doc.close();
    }

}
