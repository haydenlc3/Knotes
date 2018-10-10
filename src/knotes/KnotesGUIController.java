/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knotes;

import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author HLC
 */
public class KnotesGUIController implements Initializable {
    
    public enum state {
        YES,
        NO,
        CANCELLED;
    }
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private ChoiceBox fontType;
    
    @FXML
    private Tab tab;
    
    File selectedFile;
      
    public void fontChosen() {
        textArea.setFont(Font.font(fontType.getValue().toString(), (float) (textArea.getFont().getSize())));
    }
    
    public void increaseFont() {
        textArea.setFont(Font.font(fontType.getValue().toString(), (float) (textArea.getFont().getSize() + 1.0f)));
    }
    
    public void decreaseFont() {
        textArea.setFont(Font.font(fontType.getValue().toString(), (float) (textArea.getFont().getSize() - 1.0f)));
    }
    
    public void newFile() throws IOException {
        textArea.clear();
        selectedFile = null;
    }
    
    public void openFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select .txt files");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".txt", "*.txt", ".file", "*.file"));
        selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            System.out.println("Opened");
            File file = new File(selectedFile.getAbsolutePath());
            Scanner sc = new Scanner(file);  
            tab.setText(selectedFile.getName());
            textArea.clear();
            
            while (sc.hasNextLine()) {
                textArea.appendText(sc.nextLine()+"\n");
            }
        } else {
            System.out.println("Cancelled");
        }
    }
    
    public void saveAsFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".txt", "*.txt", ".file", "*.file"));
        selectedFile = fileChooser.showSaveDialog(null);
        
        if (selectedFile != null) {
            System.out.println("Saved");
            File file = new File (selectedFile.getAbsolutePath());
            BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
            out.write(textArea.getText());
            out.close();
        } else {
            System.out.println("Cancelled");
        }
    }
    
    public void saveFile() throws IOException {
        if (selectedFile != null) {
            File file = new File (selectedFile.getAbsolutePath());
            BufferedWriter out = new BufferedWriter(new FileWriter(file)); 
            out.write(textArea.getText());
            out.close();
        } else {
            saveAsFile();
        }
    }
    
    public void clearText() {
        textArea.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailab‌​leFontFamilyNames();
        
        for(String font: fonts) {
            fontType.getItems().addAll(font);
        }
        
        fontType.setValue("Times New Roman");
    }    
}