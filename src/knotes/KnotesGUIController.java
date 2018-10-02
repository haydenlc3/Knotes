/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knotes;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private ChoiceBox fontType;
    

      
    public void fontChosen() {
        textArea.setFont(Font.font(fontType.getValue().toString(), (float) (textArea.getFont().getSize())));
        System.out.println(textArea.getFont().getName()+"\t"+fontType.getValue().toString());
    }
    
    public void increaseFont() {
        textArea.setFont(Font.font(fontType.getValue().toString(), (float) (textArea.getFont().getSize() + 1.0f)));
    }
    
    public void decreaseFont() {
        textArea.setFont(Font.font(fontType.getValue().toString(), (float) (textArea.getFont().getSize() - 1.0f)));
    }
    
    public void newFile() {
        
    }
    
    public void openFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select .txt files");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".txt", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println("Opened");
            File file = new File(selectedFile.getAbsolutePath());
            Scanner sc = new Scanner(file);  
            while (sc.hasNextLine()) {
                textArea.appendText(sc.nextLine()+"\n");
            }
        } else {
            System.out.println("Cancelled");
        }
    }
    
    public void saveFile() {
        
    }
    
    public void saveAsFile() {
        
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