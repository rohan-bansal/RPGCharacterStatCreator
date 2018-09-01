package com.rohan;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.util.List;

class FileUtils {

    private PDDocumentCatalog docCatalog;
    private PDAcroForm acroForm;

    FileUtils(String fileName)  throws java.lang.Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        System.out.println("\nStatus: I/O directory = " + file.getAbsolutePath());
        Main.pdfDoc = PDDocument.load(file);
        docCatalog = Main.pdfDoc.getDocumentCatalog();
        acroForm = docCatalog.getAcroForm();
    }

    void statusCheck() {
        List fields = acroForm.getFields();
        System.out.println("Status: " + Integer.toString(fields.size()) + " top-level fields found");
        System.out.println("Status: " + System.getProperty("user.dir"));
    }

    void setFields(String fieldCallSign, String value) throws java.lang.Exception {
        PDField field = acroForm.getField(fieldCallSign);
        field.setValue(value);

        Main.pdfDoc.save(new File("/home/rbansal/dev/IDEA-JavaProjects/RpgStatGenerator/target/classes/Files/CharacterSheet.pdf"));

    }
}
