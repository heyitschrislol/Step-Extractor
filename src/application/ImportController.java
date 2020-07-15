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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
//	    Parent root = (Parent)loader.load();
	    FileChooser chooser = new FileChooser();
	    chooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
		File file = chooser.showOpenDialog(new Stage()).getAbsoluteFile();
        if (file != null) {
        	String path = "///" + file.getAbsolutePath();
        	
    		ObservableList<Step> teststeps = testReadCSV(new File(path));
    		stepstable.setItems(teststeps);
    		int index = 0;
    		stepstable.getColumns().addAll(stepnumcol, stepcol, datacol, resultcol);
    	} 
//        else {
//        	String path = "///Users/christopher.hendlibm.com/Documents/IBM/projects/@SCMMRP/CGISS-35253.csv";
//    		ObservableList<Step> teststeps = readCSV(new File(path));
//    		stepstable.setItems(teststeps);
//    		int index = 0;
//    		stepstable.getColumns().addAll(stepnumcol, stepcol, datacol, resultcol);
//        }

	}
	
	public ObservableList<Step> testReadCSV(File path) throws FileNotFoundException {
		ObservableList<Step> steps = FXCollections.observableArrayList();
		String regexA = "[\\w*[\\w*,]|[\\w\\d,]|[\\w*\\s,]]*";
		String line = "";
		String[] holder;
		ArrayList<String> steplines = new ArrayList<String>();
		int index = 0;
		int count = 1;
		Scanner sc = new Scanner(path);
		while (sc.hasNextLine()) {
			line = sc.next();
			if (line.matches(regexA)) {
				
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("bob");
		sb.append("job");
		sb.append("mob");
		
		System.out.println(sb);
		
		steps.add(new Step("1", "boot", "shoot", "POOT"));
		return steps;
	}
//	public ObservableList<Step> readCSV(File path) throws FileNotFoundException {
//		ObservableList<Step> steps = FXCollections.observableArrayList();
//		String pline = "";
//		String line = "";
//		String[] holder;
//		ArrayList<String> stepline = new ArrayList<String>();
//		int index = 0;
//		int count = 1;
//		Scanner sc = new Scanner(path);
////		sc.useDelimiter(",|\\r\\n");
//		sc.useDelimiter(",");
//		while (sc.hasNext()) {
//			line = sc.next();
////			if (!line.matches("Step,Data,Expected Result")) {
//			if (!line.equals("Step") && !line.equals("Data") && !line.contains("Expected Result")) {
//				if (line.contains("\\r\\n")) {
//					pline = line;
//				}
//				
//				index++;
//			}
//		}
////		while (sc.hasNextLine()) {
////			line = sc.nextLine();
////			if (!line.isBlank()){
////				if (!line.matches("Step,Data,Expected Result")) {
////					holder = line.split(",");
////					for (int i = 0; i < holder.length; i++) {
////						if (holder[i].isEmpty()) {
////							stepline.add(" ");
////						} else {
////							stepline.add(holder[i]);
////						}
////					}
////					steps.add(new Step(String.valueOf(count), stepline.get(0), stepline.get(1), stepline.get(2)));
////					count++;
////					stepline.clear();
////				}
////			}
////			
////		}  
//		sc.close();
//		
//		return steps;
//	}
	
	public void csvReader(File path) throws FileNotFoundException {
		String part = "";
		String line = "";
		String[] holder;
		ArrayList<String> stepline = new ArrayList<String>();
		int index = 0;
		int count = 1;
		Scanner sc = new Scanner(path);
		
		sc.useDelimiter(",");
		while (sc.hasNextLine()) {
			part = sc.next();
//			if (!line.matches("Step,Data,Expected Result")) {
			if (!part.matches("Step") && !part.matches("Data") && !part.matches("Expected Result")) {
				if (line.contains("\\r\\n")) {
					
				}
				
				index++;
			}
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
