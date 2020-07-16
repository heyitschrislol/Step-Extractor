package application;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImportController implements Initializable {

	private ObservableList<String> lineholder = FXCollections.observableArrayList();
	
	private FileChooser fc;
	@FXML
	private SplitPane mainsplit;
	@FXML
	private Button openfilebtn;
	@FXML
	private TextField filepathtxtfld;
	@FXML
	private TableView<Step> stepstable = new TableView<Step>();
	@FXML
	private TableColumn<Step, String> stepnumcol;
	@FXML
	private TableColumn<Step, String> stepcol;
	@FXML
	private TableColumn<Step, String> datacol;
	@FXML
	private TableColumn<Step, String> resultcol;
	
	
	
	public ImportController() {
		stepnumcol = new TableColumn("#");
		stepnumcol.setCellValueFactory(new PropertyValueFactory<Step,String>("stepnum"));
		stepcol = new TableColumn("Step");
		stepcol.setCellValueFactory(new PropertyValueFactory<Step,String>("step"));
		datacol = new TableColumn("Data");
		datacol.setCellValueFactory(new PropertyValueFactory<Step,String>("data"));
		resultcol = new TableColumn("Expected Result");
		resultcol.setCellValueFactory(new PropertyValueFactory<Step,String>("result"));
		
	}
   
	@FXML
	public void openFileHandler() throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
//	    Parent root = (Parent)loader.load();
	    FileChooser chooser = new FileChooser();
	    chooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
		File file = chooser.showOpenDialog(new Stage()).getAbsoluteFile();
        if (file != null) {
        	String path = "///" + file.getAbsolutePath();
        	ArrayList<String> steplines = testReadCSV(new File(path));
    		ObservableList<Step> teststeps = sortSteps(steplines);
    		stepstable.setItems(teststeps);
    		stepstable.getColumns().addAll(stepnumcol, stepcol, datacol, resultcol);
    	} 
	}
	public ArrayList<String> testReadCSV(File path) throws IOException {
		String line = "";
		StringBuilder sb = new StringBuilder();
		ArrayList<String> rawlines = new ArrayList<String>();
		String[] lines;
		BufferedReader br = new BufferedReader(new FileReader(path));
		int i = 0;
		while((line = br.readLine())!= null){
			if (line.matches("Step@Data@Expected Result")) {
				continue;
			}
			if (i == 0) {
				sb.append(line);
				i++;
				continue;
			}
			if (line.contains("@")) {
				sb.append("@" + line);
			} else {
				sb.append("%%" + line);
			}
			
		}
//		System.out.println(sb);
		lines = sb.toString().split("@");
		for (String s : lines) {
			if (s.isBlank()) {
				s = "--";
			}
//			System.out.println(i + ". " + s);
			rawlines.add(s);
			i++;
		}
		br.close();
		return rawlines;
	}
	public ObservableList<Step> sortSteps(ArrayList<String> rawlines) {
		ObservableList<Step> steps = FXCollections.observableArrayList();
		int index = 0;
		int count = 0;
		String[] pholder = new String[3];
		for (String s : rawlines) {
			if (index > 2) {
				index = 0;
				count++;
				steps.add(new Step(String.valueOf(count), pholder[0], pholder[1], pholder[2]));
			}
			pholder[index] = s;
			index++;
		}
		
		return steps;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
