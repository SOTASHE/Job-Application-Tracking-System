package searchEntry;

import application.Main;
import javafx.application.Application;
import javafx.geometry.*; // for Insets, Pos
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*; // for Button, Label, TextField
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.*; // for GridPane, HBox
import javafx.scene.text.*; // for Text
import utilities.*; //for getWindows, searchEntryUtil

public class searchData extends Application 
{
	@Override
	public void start(Stage searchPageStage) 
	{
		//declaring all components
		GridPane grid;
		Text sceneTitle;
		Label searchBy, searchFor;
		final ToggleGroup searchSelection = new ToggleGroup();
		RadioButton dt, cn, pos, city, state, refNo;
		TextField searchTextField;
		final DatePicker chooseDate = new DatePicker();;
		Alert errorAlert;
		Button searchBtn, btn;
		HBox searchHBtn, hBtn;
		Scene searchEntryScene;
		
		try
		{
			//layout container
			grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			ColumnConstraints col1 = new ColumnConstraints();
		    col1.setPercentWidth(30);
		    ColumnConstraints col2 = new ColumnConstraints();
		    col2.setPercentWidth(50);
			//nodes
			sceneTitle = new Text("               Data Search Details");
			sceneTitle.setId("dataSearchSceneTitle");
			searchBy = new Label("SEARCH BY: ");
			dt = new RadioButton("Date");
			dt.setToggleGroup(searchSelection);
			cn = new RadioButton("Company Name");
			cn.setToggleGroup(searchSelection);
			pos = new RadioButton("Position");
			pos.setToggleGroup(searchSelection);
			city = new RadioButton("City");
			city.setToggleGroup(searchSelection);
			state = new RadioButton("State");
			state.setToggleGroup(searchSelection);
			refNo = new RadioButton("Application No.");
			refNo.setToggleGroup(searchSelection);
			searchFor = new Label("SEARCH FOR : ");
			searchTextField = new TextField();
			chooseDate.setShowWeekNumbers(true);
			chooseDate.setPrefWidth(400);
			searchBtn =  new Button("SEARCH");
			searchHBtn = new HBox(10);
			searchHBtn.setId("searchDataHBtn");
			searchHBtn.setAlignment(Pos.BOTTOM_RIGHT);
			searchHBtn.getChildren().add(searchBtn);
			btn =  new Button("CANCEL");
			hBtn = new HBox(10);
			hBtn.setId("searchDataCancelHBtn");
			hBtn.setAlignment(Pos.CENTER);
			hBtn.getChildren().add(btn);
			//add nodes to layout
			grid.getColumnConstraints().addAll(col1,col2);
			grid.add(sceneTitle, 0, 0, 2, 1);
			grid.add(searchBy, 0, 1, 2, 1);
			grid.add(dt, 0, 2); grid.add(cn, 0, 3); grid.add(pos, 0, 4); 
			grid.add(city, 0, 5); grid.add(state, 0, 6); grid.add(refNo, 0, 7);
			grid.add(searchFor, 0, 8);
			grid.add(searchTextField, 1, 8);
			grid.add(searchHBtn, 0, 9);
			grid.add(hBtn, 1, 9);
			grid.setGridLinesVisible(false);
			//dialog boxes for buttons
			errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setTitle("Error");
			errorAlert.setHeaderText(null);
			errorAlert.setContentText("All necessary fields must be filled");
			//radio button events
			dt.setOnAction(event ->
			{
				 searchTextField.setPromptText("YYYY-MM-DD format");
			});
			cn.setOnAction(event ->
			{
				searchTextField.setPromptText("Ex. Google");
			});
			pos.setOnAction(event ->
			{
				searchTextField.setPromptText("Ex. Software Engineer");
			});
			city.setOnAction(event ->
			{
				searchTextField.setPromptText("Ex. New York");
			});
			state.setOnAction(event ->
			{
				searchTextField.setPromptText("Ex. IL for Illinois");
			});
			refNo.setOnAction(event ->
			{
				searchTextField.setPromptText("Ex. #177714B");
			});
			//button events
			searchBtn.setOnAction(event -> 
			{
				if (searchSelection.getSelectedToggle() == null || searchTextField.getText().isEmpty())
				{
					errorAlert.showAndWait();
				}
				else
				{
					//get toggle selection
					String radioSelectionString = "";
					RadioButton selectedRadioButton = (RadioButton) searchSelection.getSelectedToggle();
					if (selectedRadioButton == dt)
					{
						radioSelectionString = "Date";
					}
					else if (selectedRadioButton == cn)
					{
						radioSelectionString = "Company";
					}
					else if (selectedRadioButton == pos)
					{
						radioSelectionString = "Position";
					}
					else if (selectedRadioButton == city)
					{
						radioSelectionString = "City";
					}
					else if (selectedRadioButton == state)
					{
						radioSelectionString = "State";
					}
					else if (selectedRadioButton == refNo)
					{
						radioSelectionString = "Ref No";
					}
					else
					{
						errorAlert.showAndWait();
					}
					//search data in database
					getWindows.getSearchResultsWindow(searchPageStage,radioSelectionString, searchTextField);
					//clear all fields for next data entry
					searchTextField.clear();
					searchSelection.selectToggle(null);
				}
			});
			btn.setOnAction(event ->
			{
				getWindows.getMainWindow(searchPageStage);
			});
			//final adding to layout
			searchEntryScene = new Scene(grid, 800, 600);
			searchEntryScene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			searchPageStage.setTitle("Job Application Tracking System");
			searchPageStage.getIcons().add(new Image("file:hireme.png"));
			searchPageStage.setScene(searchEntryScene);
			searchPageStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
