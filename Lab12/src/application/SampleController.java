package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SampleController {
	
	@FXML
	private Label sensorTestLabel, testClassLabel, averageLabel;
	
	@FXML
	public void close(ActionEvent event) { //exits the app
		Platform.exit();
		System.exit(0);
	}
	
	@FXML
	public void about(ActionEvent event) {
		sensorTestLabel.setText("This application was created by Ivan Williams, a Farmingdale State College student.");
		testClassLabel.setText("");
		averageLabel.setText("");
	}
	
	@FXML
	public void openstdData(ActionEvent event) {
		try {
			File x = new File("stdData.csv"); //file object retrieves the file
			Scanner sc = new Scanner(x); //for reading the file
			sc.useDelimiter(","); //sets the delimiter to a comma
			int lines = 0, count = 0, sensorTests = 0, testClass = 0;
			double sum = 0, average = 0;
			String str = "", tests = "";
			while(sc.hasNextLine()) { //loops once per line
				String line = sc.nextLine(); //retrieves a line from the file
				String[] test = line.split(","); //splits the string based on the comma
				if(lines > 1) { //runs if lines is greater than 0
					for(int i = 0; i < test.length; i++) { //runs at the same amount as the length of the string array
						if(i == test.length - 1) { //runs if the value of i equals the string's length minus 1
							if(!str.contains(test[i])) { //checks if the value of the string is in the string object
								str += test[i] + "\t"; //adds the string value into the string object following a tab
								testClass++; //increments testClass
							}
						}
						else {
							if(!tests.contains(test[i])) { //checks if the value of the element is in the string object
								tests += test[i] + "\t"; //adds the element into the string object following a tab
								sum += Double.parseDouble(test[i]); //converts the string element into a double value then adds it into the sum variable
								sensorTests++; //increments sensorTests
							}
							count++; //increments count
						}
					}
				}
				lines++; //increments lines
			}
			sensorTestLabel.setText("" + sensorTests);
			testClassLabel.setText("" + testClass);
			average = sum/count; //calculates the average
			averageLabel.setText("" + average);
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error");
		}
	}
}
