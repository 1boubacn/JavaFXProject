package fun;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * NewCatStage
 *
 * @author Bouba. C
 * @version 15.0
 * 
 * Written on September 20
 *
 * This class launches a new stage to add a new cat object and it includes a couple
 * of methods to add the pet to the ListView
 */
public class NewCatStage extends Stage {
	
	//Private data fields shared by methods
	private TextField name = new TextField();
	private TextField yearOfBirth = new TextField();
	private ComboBox<Boolean> hairType = new ComboBox<>(FXCollections.observableArrayList(true, false));
	private TextField weight = new TextField();
	private ObservableList<Pet> obserList;
	
	/**
	 * Constructor that creates and makes stage for Cat entry
	 * visible
	 */
	public NewCatStage(ObservableList<Pet> list) {
		
		obserList = list;
		// Create a pane and set its properties
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		//Add the nodes with text fields to the pane 
		pane.add(new Label("Name"), 0, 0);;
		pane.add(name, 1, 0);
		pane.add(new Label("Year of Birth"), 0, 1);
		pane.add(yearOfBirth, 1, 1);
		pane.add(new Label("Is Long Haired"), 0, 2);
		pane.add(hairType, 1, 2);
		pane.add(new Label("Weight"), 0, 3);
		pane.add(weight, 1, 3);
		
		//Add button and set it on action when clicked 
		Button btAdd = new Button("Add");
		btAdd.setOnAction(e -> addCat()); 
		pane.add(btAdd, 1, 4);
		pane.setHalignment(btAdd, HPos.RIGHT);
		
		//Create a scene
		Scene scene = new Scene(pane);
		this.setTitle("Add New Cat"); //stage title
		this.setScene(scene); // place a scene in the stage
		this.show(); // displays the scene		
	}
	
	/**
	 * Method: addCat()
	 * This method reads in the inputs in text fields made by the user
	 * and converts them to a cat object which is then added to the petList 
	 */
	public void addCat() {
		
		//Get inputs from text fields
		String nameField = name.getText();
		String yearField = yearOfBirth.getText();
		boolean hairField = hairType.getSelectionModel().getSelectedItem();
		String weightField = weight.getText();
		
		//Create a cat object and String field is converted to double
		Pet cat = new Cat(nameField, Integer.parseInt(yearField), false, hairField, Double.parseDouble(weightField));
		obserList.add(cat);
		
		//close the stage
		this.close();	
	}	

}
