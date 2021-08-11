package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.DataModel;
import model.IndicatorType;

import static model.IndicatorType.GDP_PER_CAPITA;
import static model.IndicatorType.INVALID;
import static model.IndicatorType.SCHOOL_ENROLLMENT;

import java.io.FileNotFoundException;

import csv.InvalidFileFormatException;

/**
 * Instantiates an JavaFX application which creates a model of the data.
 * Uses the model to instantiate an object of type  javafx.scene.chart.LineChart
 * via the GraphView class. Then sets up the scene with basic modification to
 * the stage.
 *
 * @author Foothill College, Juntao Ren
 */
public class ChartGraph extends Application
{
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.INVALID;
	private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.GDP_PER_CAPITA;
	//private static final IndicatorType DEFAULT_INDICATOR = IndicatorType.SCHOOL_ENROLLMENT;


	/**
	 * Called by launch method of Application
	 * @param stage: Stage
	 */
	@Override
	public void start(Stage stage)
	{
		// NOTE: Make sure to use relative path instead of specifying the entire path
		//       such as (/Users/alicew/myworkspace/so_on_and_so_forth).

		// Example of invalid input file
		final String [] INVALID_INPUT = {"resources/childrenEnrolled_invalid.csv"};

		// Example of input file for GDP per capita:
		final String [] GDP_INPUT = { "resources/gdpPerCapita.csv"};

		// Example of input file for net school enrollment for:
		// [0] primary grade
		// [1] secondary grade
		final String [] ENROLLMENT_INPUT = { "resources/childrenEnrolledInPrimary.csv",
				"resources/childrenEnrolledInSecondary.csv"
		};

		// Displays graph* of data by country.


		GraphView graphView = null;
		IndicatorType selectedIndicator = SCHOOL_ENROLLMENT;

		// Provides access to CSV data.


		try
		{
			switch(selectedIndicator)
			{
				case GDP_PER_CAPITA:
					DataModel gdpModel = new DataModel(GDP_INPUT);
					graphView = new GraphView(gdpModel, GDP_PER_CAPITA);
					break;
				case SCHOOL_ENROLLMENT:
					DataModel enrollmentModel = new DataModel(ENROLLMENT_INPUT);
					graphView = new GraphView(enrollmentModel, SCHOOL_ENROLLMENT);
					break;
				case INVALID:
					DataModel invalidModel = new DataModel(INVALID_INPUT);
					break;
				default:
					System.out.println("WARNING: Invalid indicator selected. Exiting program.");
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.printf("Invalid filename");
			System.exit(0);
		}
		catch (InvalidFileFormatException e)
		{
			System.out.printf("Invalid file format %s\n", e.getMessage());
			System.exit(0);
		}


		//   * Here, displays graph of Indicator data by country.
		//
		//  ** Here, our data is composed of Country objects.
		// 
		// *** Get an instance of javafx.collections.ObservableList via a call 
		//     to getData() method.
		// https://docs.oracle.com/javafx/2/api/javafx/scene/chart/XYChart.html#getData()
		graphView.update();

		// Creates a scene and adds the graph to the scene.
		Scene scene = new Scene(graphView);

		// Places the scene in the stage
		stage.setScene(scene);

		// Set the stage title
		stage.setTitle("Data points for " + selectedIndicator.getLabel());

		// Display the stage
		stage.show();
	}

	/**
	 * Launches a standalone JavaFx App
	 */
	public static void main(String[] args)
	{
		launch();
	}
}