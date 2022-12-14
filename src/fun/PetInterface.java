package fun;

import javafx.application.Application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class: PetInterface - Main interface
 * 
 * @author Bouba. C
 * @version 15.0
 * 
 * Written on September 20
 * 
 * This class is the main GUI for fun, it contains ListView and allow the 
 * user to launch many other stages using its nodes.
 *
 *
 */
public class PetInterface extends Application{
	
	//Private data fields
	private PetLibrary myPets = new PetLibrary();
	private ObservableList<Pet> lists;
	
	/**
	 * 
	 * Method:start()
	 * 
	 * This method adds the parameter m to the libraryItems array list
	 * 
	 * @param Stage primaryStage
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//read in the binary file 
		myPets.readBinaryFile();
		
		/**
		 * This block of code will display the list of pets using a pane to
		 * add all nodes created
		 * 
		 */
		
		VBox mainPane = new VBox(); //Display child nodes
		
		//Create a label for the pane
		Label mainLabel = new Label("Available Pet");
		mainPane.getChildren().add(mainLabel);
		mainLabel.setFont(Font.font(14));
		
		//Create a ListView for pets
		ListView<Pet> myList = new ListView<>();
		lists = FXCollections.observableArrayList(myPets.getTheList());
		myList.setItems(lists);
		mainPane.getChildren().add(myList);
		
		
		/**
		 * This will display a pop up info about the pet in a separate 
		 * window or stage when it is clicked
		 */
		myList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pet>() {
			
			//This method will be called if the observable value changes
			public void changed(ObservableValue<? extends Pet> observable, Pet oldValue, Pet newValue) {
				
				//Pop up info of a selected pet in a new stage
				Stage stage = null;
				stage = createPetStage(newValue);
				stage.show(); //Show the stage	
			}	
		});
		
		//Create new buttons and close button, plus setting them on action when clicked
		Button newCat = new Button("New Cat");
		newCat.setPrefSize(100, 50);
		newCat.setOnAction(e -> getNewCat()); //Set an action if button is clicked
		Button newDog = new Button("New Dog");
		newDog.setPrefSize(100, 50);
		newDog.setOnAction(e -> getNewDog());
		Button close = new Button("Close");
		close.setPrefSize(100, 50);
		close.setOnAction(e -> writeAndClose());
		
		//Hbox to display button nodes 
		HBox vButton = new HBox();
		vButton.getChildren().addAll(newCat, newDog, close);
		vButton.setSpacing(10);
		vButton.setPadding(new Insets(10, 10, 10, 10));
		vButton.setAlignment(Pos.CENTER);
	
	
		
		//add all the new nodes to the pane
		mainPane.getChildren().add(vButton);
	
	    //Create a scene 
		Scene scene = new Scene(mainPane, 600, 300);
		primaryStage.setTitle("Pet List"); //Set the title of the stage
		primaryStage.setScene(scene); //place the scene in the stage
		primaryStage.show(); //display the scene
	}
	
	/**
	 * Method to create new Stage and scene for details about a pet object called by
	 * ListView selection handler
	 * 
	 * @param Media
	 * 
	 * @return Stage
	 */
	private Stage createPetStage(Pet pet) {
		
		//Create a stage
		Stage stage = new Stage();
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		if(pet.isDog) {
			
			stage.setTitle("Dog"); //set the title
			
			//Create a Dog instance 
			Dog d = (Dog) pet;
			
			//place nodes in the pane
			pane.add(new Label("Name:"), 0, 0);
			pane.add(new Label(d.getName()), 1, 0);
			pane.add(new Label("Year Born:"), 0, 1);
			pane.add(new Label("" + d.getYearOfBirth()), 1, 1);
			pane.add(new Label("Breed:"), 0, 2);
			pane.add(new Label(d.getBreed()), 1, 2);
			pane.add(new Label("Weight:"), 0, 3);
			pane.add(new Label("" + d.getWeight() ), 1, 3);	
		}
		
		else {
			
			stage.setTitle("Cat"); //set the title
			
			Cat c = (Cat) pet;
			//place nodes in the pane
			pane.add(new Label("Name:"), 0, 0);
			pane.add(new Label(c.getName()), 1, 0);
			pane.add(new Label("Year Born:"), 0, 1);
			pane.add(new Label("" + c.getYearOfBirth()), 1, 1);
			pane.add(new Label("Long Haired?:"), 0, 2);
			pane.add(new Label("" + c.isLongHair), 1, 2);
			pane.add(new Label("Weight:"), 0, 3);
			pane.add(new Label("" + c.getWeight() ), 1, 3);
		}
		
		//Create scene
		Scene scene = new Scene(pane);
		stage.setScene(scene); // set the scene in the stage
		
		return stage;	
	}
	
	/**
	 * Method: getNewCat
	 * 
	 * This method is called by EventHandler and it
	 * launches a new stage which allows the user to 
	 * add a new cat
	 * 
	 */
	public void getNewCat() {
		
		//Code to open cat stage form 
		new NewCatStage(lists);
	}
	
	/**
	 * Method: getNewDog
	 * 
	 * This method is called by EventHandler and it
	 * launches a new stage which allows the user to 
	 * add a new dog
	 * 
	 */
	public void getNewDog() {
		
		//Code to open dog stage form 
		new NewDogStage(lists);
		
	}
	
	/**
	 * 
	 * Called by event handler when close button is clicked.
	 * This will override the binary file and close the stage
	 */
	public void writeAndClose() {
		
		//Code to write the file
		myPets.writeBinaryFile(lists);
		System.exit(0); // close the stage
	}
	
	/**
	 * Main: Starter point
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		launch(args);
    }

}
