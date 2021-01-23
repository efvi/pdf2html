package com.mycompany.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.tools.PDFText2HTML;
 
public class App {
    public String pdfToHtml(InputStream content) throws IOException {
        PDDocument pddDocument = PDDocument.load(content);
        
        PDFText2HTML stripper = new PDFText2HTML();
        
        return stripper.getText(pddDocument);
    }
    
    public static void main(String[] args) {
    	App app = new App();
    	
		try {
			
			try(FileInputStream in = new FileInputStream("./src/main/resources/input.pdf")) {
				
		        String html = app.pdfToHtml(in);
			
				System.out.println(html);
				
				File output = new File("./src/main/resources/output.html");
				output.delete();
				
				try (PrintWriter out = new PrintWriter(output)) {
					out.println(html);
		        }
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
