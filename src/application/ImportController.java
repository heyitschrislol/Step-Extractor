package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import application.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImportController implements Initializable {

	ObservableList<Step> teststeps = FXCollections.observableArrayList();
	ObservableList<FormattedItem> jsonsteps = FXCollections.observableArrayList();

	@FXML
	private SplitPane mainsplit;
	@FXML
	private Pane exportpane;
	@FXML
	private Button openfilebtn;
	@FXML
	private Button exportbtn;
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
		stepnumcol.setCellValueFactory(new PropertyValueFactory<Step, String>("stepnum"));
		stepcol = new TableColumn("Step");
		stepcol.setCellValueFactory(new PropertyValueFactory<Step, String>("step"));
		datacol = new TableColumn("Data");
		datacol.setCellValueFactory(new PropertyValueFactory<Step, String>("data"));
		resultcol = new TableColumn("Expected Result");
		resultcol.setCellValueFactory(new PropertyValueFactory<Step, String>("result"));

	}

	@FXML
	public void openFileHandler() throws IOException {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
		File file = chooser.showOpenDialog(new Stage()).getAbsoluteFile();
		if (file != null) {
			teststeps.clear();
			String path = "///" + file.getAbsolutePath();
			ArrayList<String> steplines = testReadCSV(new File(path));
			teststeps = sortSteps(steplines);
			stepstable.setItems(teststeps);
			stepstable.getColumns().addAll(stepnumcol, stepcol, datacol, resultcol);
		}
	}

	@FXML
	public void exportJSONHandler() {
		int index = 0;
		int size;
		FormattedItem item;
		// populate the ObservableList<FormattedItem> with the sorted steps from the loaded csv
		for (Step s : teststeps) {
			jsonsteps.add(new FormattedItem(s));
		}
		size = jsonsteps.size();

		FileChooser chooser = new FileChooser();
		chooser.setInitialFileName("jsonsteps.json");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
		File file = chooser.showSaveDialog(new Stage());
		if (file != null) {
			File dir = file.getParentFile();// gets the selected directory
			chooser.setInitialDirectory(dir);
			try {
				// Create file
				FileWriter stream = new FileWriter(file);
				BufferedWriter out = new BufferedWriter(stream);
				out.write("[");
				out.newLine();
				for (int i = 0; i < size; i++) {
					item = jsonsteps.get(i);
					if ((size - 1) != i) {
						out.write("{");
						out.newLine();
						out.write(item.getStepnumber().getKey() + " : " + item.getStepnumber().getValue() + ",");
						out.newLine();
						out.write(item.getStep().getKey() + " : " + item.getStep().getValue() + ",");
						out.newLine();
						out.write(item.getData().getKey() + " : " + item.getData().getValue() + ",");
						out.newLine();
						out.write(item.getResult().getKey() + " : " + item.getResult().getValue());
						out.newLine();
						out.write("},");
					} else {
						out.write("{");
						out.newLine();
						out.write(item.getStepnumber().getKey() + " : " + item.getStepnumber().getValue() + ",");
						out.newLine();
						out.write(item.getStep().getKey() + " : " + item.getStep().getValue() + ",");
						out.newLine();
						out.write(item.getData().getKey() + " : " + item.getData().getValue() + ",");
						out.newLine();
						out.write(item.getResult().getKey() + " : " + item.getResult().getValue());
						out.newLine();
						out.write("}");
						out.newLine();
						out.write("]");
					}
				}
				// Close the output stream
				out.close();
			} catch (Exception e) {// Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	public ArrayList<String> testReadCSV(File path) throws IOException {
		String line = "";
		StringBuilder sb = new StringBuilder();
		ArrayList<String> rawlines = new ArrayList<String>();
		String[] lines;
		BufferedReader br = new BufferedReader(new FileReader(path));
		int i = 0;
		while ((line = br.readLine()) != null) {
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
		lines = sb.toString().split("@");
		for (String s : lines) {
			if (s.isBlank()) {
				s = "--";
			}
			rawlines.add(s);
			i++;
		}
		br.close();
		return removeQuotes(rawlines);
	}
	public ArrayList<String> removeQuotes(ArrayList<String> rawlines) {
		String formattedtext = "";
		ArrayList<String> formattedlines = new ArrayList<>();
		for (String s : rawlines) {
			formattedtext = s;
			if (s.contains("\"")) {
				formattedtext = s.replace('\"', ' ');
				System.out.println(formattedtext);
			}
			
			formattedlines.add(formattedtext);
			
//			s = formattedtext;
//			s.replace('\"', '\'');
			

		}
		return formattedlines;
	}

	public ObservableList<Step> sortSteps(ArrayList<String> rawlines) {
		int index = 0;
		int count = 0;
		String[] pholder = new String[3];
		for (String s : rawlines) {
			if (index > 2) {
				index = 0;
				count++;
				teststeps.add(new Step(String.valueOf(count), pholder[0], pholder[1], pholder[2]));
			}
			pholder[index] = s;
			index++;
		}

		return teststeps;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
