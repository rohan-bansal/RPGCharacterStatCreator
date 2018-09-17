package com.rohan;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.util.HashMap;
import java.util.List;

class FileUtils {

    private PDAcroForm acroForm;

    FileUtils(String fileName)  throws java.lang.Exception {
        PDDocumentCatalog docCatalog;
        File file = new File(fileName);
        System.out.println("\nI/O directory = " + file.getAbsolutePath());
        Main.pdfDoc = PDDocument.load(file);
        docCatalog = Main.pdfDoc.getDocumentCatalog();
        acroForm = docCatalog.getAcroForm();
    }

    void statusCheck() {
        List fields = acroForm.getFields();
        System.out.println(Integer.toString(fields.size()) + " top-level fields found");
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }

    void setAllFields(HashMap<String, String> total_fields) throws java.io.IOException {
        for(String key : total_fields.keySet()) {
            setField(key, total_fields.get(key));
        }

        Main.pdfDoc.save(new File(System.getProperty("user.dir") +  "/CharacterSheet.pdf"));
    }

    void setField(String fieldCallSign, String value) throws java.io.IOException {
        PDField field = acroForm.getField(fieldCallSign);
        field.setValue(value);
    }
}
