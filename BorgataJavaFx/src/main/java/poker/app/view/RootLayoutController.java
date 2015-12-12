package poker.app.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import domain.GameRuleDomainModel;
import enums.eGame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import poker.app.MainApp;
import pokerBase.Rule;

import logic.GameRuleBLL;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author Marco Jakob
 */
public class RootLayoutController implements Initializable 
{
	private MainApp mainApp; // Reference to the main application
	@FXML
	private MenuBar mb;
	@FXML
	private Menu mnuGame;
	@FXML
	private ToggleGroup tglGames;
	
	/**
	 * Determines which rule is selected in the Games menu. 
	 * 
	 * Iterates through the menus in the menu bar looking for the Games menu. 
	 * Once found it iterates through the menu items under Games looking for radio buttons. 
	 * For every radio menu item it finds it checks if it's been selected. 
	 * If a selected one is found strRuleName is set and the whole nest is broken out of
	 * and strRuleName is returned. 
	 * @return
	 */
	public String getRuleName()
	{	
		String strRuleName = null;
		for (Menu m: mb.getMenus())
		{
			if (m.getText() == "Games")
			{
				for (MenuItem mi: m.getItems())
				{
					if (mi.getClass().equals(RadioMenuItem.class))
					{
						RadioMenuItem rmi = (RadioMenuItem)mi;
						if (rmi.isSelected() == true)
						{
							strRuleName = rmi.getText();
							break;
						}
					}
				}
			}
		}
		return strRuleName;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// GameChanger changer = new GameChanger(); 

		Menu m = new Menu();
		m.setText("Games");

		tglGames = new ToggleGroup();
		
		for (GameRuleDomainModel gr : GameRuleBLL.getRules()) {
			
			RadioMenuItem mi = new RadioMenuItem();
			String strRuleName = gr.getRULENAME();
			mi.setToggleGroup(tglGames);
			mi.setText(strRuleName);
			// mi.setOnAction(changer); 
			
			if (gr.getDEFAULTGAME() == 1)
			{
				mi.setSelected(true);
			}
			m.getItems().add(mi);
		}

		mb.getMenus().add(0,m);
	}
	
//	class GameChanger implements EventHandler<ActionEvent> {
//		@Override
//		public void handle(ActionEvent e) {
//			RadioMenuItem radio = (RadioMenuItem) e.getSource(); 
//			changeGame(radio.getText());
//		}
//	}
//	
//	public void changeGame(String gameName) { 
//		System.out.println(gameName);
//	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) 
	{	
		this.mainApp = mainApp;
	}


	/**
	 * Opens an about dialog.
	 */
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AddressApp");
		alert.setHeaderText("About");
		alert.setContentText("Author: Bert Gibbons");

		alert.showAndWait();
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	public ToggleGroup getTglGames() {
		return tglGames;
	}

	public void setTglGames(ToggleGroup tglGames) {
		this.tglGames = tglGames;
	}
}