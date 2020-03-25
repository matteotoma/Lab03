package it.polito.tdp.spellchecker;

import java.net.URL;

import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dizionario;
	private ObservableList<String> list = FXCollections.observableArrayList("English", "Italian");

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtErrori;

    @FXML
    private Label lblRisultato;

    @FXML
    private Button btnClearText;

    @FXML
    private Label lblTempo;

    @FXML
    void doClearText(ActionEvent event) {
    	txtTesto.clear();
    	txtErrori.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long start = System.nanoTime();
    	String result = "";
    	List<RichWord> l = new LinkedList<>();
    	String testo = txtTesto.getText();
    	if(choiceBox.getValue().equals("English"))
    		this.dizionario.loadDictionary("src/main/resources/English.txt");
    	else
    		this.dizionario.loadDictionary("src/main/resources/Italian.txt");
    	l.addAll(this.dizionario.spellCheckText(testo));
    	for(RichWord r: l) {
			if(r.isGiusta() == false) {
				if(result.equals(""))
					result += r.getParola();
				else
					result += "\n" + r.getParola();
			}
		}
    	if(l.size() == 0)
    		lblRisultato.setText("Il testo non contiene errori");
    	else {
    		txtErrori.appendText(result);
    		lblRisultato.setText("Il testo contiene " + l.size() + " errori");
    	}
    	long finish = System.nanoTime();
    	lblTempo.setText(""+(finish-start));
    }

    @FXML
    void initialize() {
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblRisultato != null : "fx:id=\"lblRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        
        choiceBox.setItems(list);
        choiceBox.setValue("Italian");

    }

    public void setModel(Dictionary dizionario) {
    	this.dizionario = dizionario;
    }
}
