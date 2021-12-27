package edu.westga.cs.schoolgrades.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import edu.westga.cs.schoolgrades.model.AverageOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.GradeDropperLowestGrade;
import edu.westga.cs.schoolgrades.model.Grade;
import edu.westga.cs.schoolgrades.model.GradeStrategy;
import edu.westga.cs.schoolgrades.model.SimpleGrade;
import edu.westga.cs.schoolgrades.model.SumOfGradesStrategy;
import edu.westga.cs.schoolgrades.model.WeightedGrade;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * This class controls the operations of 
 * SchoolGradeGui.
 * 
 * @author Brandon Drick
 * @version 11/07/2021
 */
public class SchoolGradesController implements Initializable {
	@FXML private MenuBar menuBar;
	@FXML private Menu fileMenu;
	@FXML private MenuItem exit;
	@FXML private MenuItem clearQuizzes;
	@FXML private MenuItem clearHomeworks;
	@FXML private MenuItem clearExams;
	@FXML private MenuItem clearAll;
	@FXML private Menu dataMenu;
	@FXML private MenuItem addQuiz;
	@FXML private MenuItem addHomework;
	@FXML private MenuItem addExam;
	@FXML private ContextMenu quizContextMenu;
	@FXML private ContextMenu homeworkContextMenu;
	@FXML private ContextMenu examContextMenu;
	@FXML private ListView<Grade> quizListView;
	@FXML private ListView<Grade> homeworkListView;
	@FXML private ListView<Grade> examListView;
	@FXML private TextField quizSubtotal;
	@FXML private TextField homeworkSubtotal;
	@FXML private TextField examSubtotal;
	@FXML private TextField finalGradeTotal;
	@FXML private Button recalculateButton;
	
	private ObservableList<Grade> quizList;
	private ObservableList<Grade> homeworkList;
	private ObservableList<Grade> examList;
	private DoubleProperty quizProperty;
	private DoubleProperty homeworkProperty;
	private DoubleProperty examProperty;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.initializeGUI();
	}
	
	/**
	 * Configures the nodes within the GUI
	 */
	private void initializeGUI() {
		this.initializeMenuBar();
		this.initializeGradeListViews();
		this.initializeProperties();
		this.initializeGradeTextFields();
		this.initializeRecalculateButton();
	}
	
	private void initializeProperties() {
		this.quizProperty = new SimpleDoubleProperty(0);
		this.homeworkProperty = new SimpleDoubleProperty(0);
		this.examProperty = new SimpleDoubleProperty(0);
	}
	
	/**
	 * Configures the MenuBar for
	 * the application.
	 */
	private void initializeMenuBar() {
		this.createFileMenu();
		this.createDataMenu();
	}
	
	/**
	 * Creates the File menu.
	 */
	private void createFileMenu() {
		this.fileMenu.setText("_File");
		this.fileMenu.setMnemonicParsing(true);
		this.createFileMenuItems();
	}
	
	/**
	 * Creates the menu items for File menu.
	 */
	private void createFileMenuItems() {
		this.createExitMenuItem();
		this.createClearQuizzesMenuItem();
		this.createClearHomeworksMenuItem();
		this.createClearExamsMenuItem();
		this.createClearAllMenuItem();
	}
	
	/**
	 * Creates the exit menu item for File menu.
	 */
	private void createExitMenuItem() {
		this.exit.setText("E_xit");
		this.exit.setMnemonicParsing(true);
		this.exit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN));
		this.exit.setOnAction(event -> System.exit(0));
	}
	
	/**
	 * Creates the clear quizzes menu item for File menu.
	 */
	private void createClearQuizzesMenuItem() {
		this.clearQuizzes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.quizList.clear();
			}
		});
	}
	
	/**
	 * Creates the clear homeworks menu item for File menu.
	 */
	private void createClearHomeworksMenuItem() {
		this.clearHomeworks.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.homeworkList.clear();
			}
		});
	}
	
	/**
	 * Creates the clear exams menu item for File menu.
	 */
	private void createClearExamsMenuItem() {
		this.clearExams.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.examList.clear();
			}
		});
	}
	
	/**
	 * Creates the clear all menu item for File menu.
	 */
	private void createClearAllMenuItem() {
		this.clearAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.quizList.clear();
				SchoolGradesController.this.homeworkList.clear();
				SchoolGradesController.this.examList.clear();
			}
		});
	}
	
	/**
	 * Configures the MenuItem 
	 * objects for the Data Menu.
	 */
	private void createDataMenu() {
		this.dataMenu.setText("_Data");
		this.dataMenu.setMnemonicParsing(true);
		this.createDataMenuItems();
	}
	
	/**
	 * Creates menu items for Data menu.
	 */
	private void createDataMenuItems() {
		this.createQuizMenuItem();
		this.createHomeworkMenuItem();
		this.createExamMenuItem();
		this.initializeContextMenuItems();
	}
	
	/**
	 * Creates Quiz menu item for Data menu.
	 */
	private void createQuizMenuItem() {
		this.addQuiz.setText("Add _quiz");
		this.addQuiz.setMnemonicParsing(true);
		this.addQuiz.setAccelerator(new KeyCodeCombination(KeyCode.Q,
				KeyCombination.SHORTCUT_DOWN));
		this.addQuiz.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.quizList.add(new SimpleGrade(0));
			}
		});
	}
	
	/**
	 * Creates Homework menu item for Data menu.
	 */
	private void createHomeworkMenuItem() {
		this.addHomework.setText("Add _homework");
		this.addHomework.setMnemonicParsing(true);
		this.addHomework.setAccelerator(new KeyCodeCombination(KeyCode.H,
				KeyCombination.SHORTCUT_DOWN));
		this.addHomework.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.homeworkList.add(new SimpleGrade(0));
			}
		});
	}
	
	/**
	 * Creates Exam menu item for Data menu.
	 */
	private void createExamMenuItem() {
		this.addExam.setText("Add _exam");
		this.addExam.setMnemonicParsing(true);
		this.addExam.setAccelerator(new KeyCodeCombination(KeyCode.E,
				KeyCombination.SHORTCUT_DOWN));
		this.addExam.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.examList.add(new SimpleGrade(0));
			}
		});
	}
	
	/**
	 * Creates Context Menu Items for the ListViews
	 */
	private void initializeContextMenuItems() {
		this.initializeQuizContextMenu();
		this.initializeHomeworkContextMenu();
		this.initializeExamContextMenu();
	}
	
	/**
	 * Creates the addQuiz context menu.
	 */
	private void initializeQuizContextMenu() {
		MenuItem addQuizContext = new MenuItem("Add quiz");
		addQuizContext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.quizList.add(new SimpleGrade(0));
			}
		});
		
		MenuItem removeSelectedQuiz = new MenuItem("Remove quiz");
		removeSelectedQuiz.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int selectedIndex = SchoolGradesController.this.quizListView.getSelectionModel().getSelectedIndex();
				SchoolGradesController.this.quizList.remove(selectedIndex);
			}
		});
		
		this.quizContextMenu.getItems().set(0, addQuizContext);
		this.quizContextMenu.getItems().add(removeSelectedQuiz);
	}
	
	/**
	 * Creates the addHomework context menu.
	 */
	private void initializeHomeworkContextMenu() {
		MenuItem addHomeworkContext = new MenuItem("Add homework");
		addHomeworkContext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.homeworkList.add(new SimpleGrade(0));
			}
		});
		
		MenuItem removeSelectedHomework = new MenuItem("Remove homework");
		removeSelectedHomework.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int selectedIndex = SchoolGradesController.this.homeworkListView.getSelectionModel().getSelectedIndex();
				SchoolGradesController.this.homeworkList.remove(selectedIndex);
			}
		});
		
		this.homeworkContextMenu.getItems().set(0, addHomeworkContext);
		this.homeworkContextMenu.getItems().add(removeSelectedHomework);
	}
	
	/**
	 * Creates the addExam context menu.
	 */
	private void initializeExamContextMenu() {
		MenuItem addExamContext = new MenuItem("Add exam");
		addExamContext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				SchoolGradesController.this.examList.add(new SimpleGrade(0));
			}
		});
		
		MenuItem removeSelectedExam = new MenuItem("Remove exam");
		removeSelectedExam.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int selectedIndex = SchoolGradesController.this.examListView.getSelectionModel().getSelectedIndex();
				SchoolGradesController.this.examList.remove(selectedIndex);
			}
		});
		
		this.examContextMenu.getItems().set(0, addExamContext);
		this.examContextMenu.getItems().add(removeSelectedExam);
	}
	
	/**
	 * Configures the Grade ListViews displaying
	 * each grade group
	 */
	private void initializeGradeListViews() {
		this.initializeQuizListView();
		this.initializeHomeworkListView();
		this.initializeExamListView();
	}
	
	/**
	 * Configures the Quiz Grade ListView
	 */
	private void initializeQuizListView() {
		this.quizList = FXCollections.observableArrayList(new SimpleGrade(0));
		this.quizListView.setItems(this.quizList);
		this.setCellFactoryForListView(this.quizListView);
	}
	
	/**
	 * Configures the Homework Grade ListView
	 */
	private void initializeHomeworkListView() {
		this.homeworkList = FXCollections.observableArrayList(new SimpleGrade(0));
		this.homeworkListView.setItems(this.homeworkList);
		this.setCellFactoryForListView(this.homeworkListView);
	}
	
	/**
	 * Configures the Exam Grade ListView
	 */
	private void initializeExamListView() {
		this.examList = FXCollections.observableArrayList(new SimpleGrade(0));
		this.examListView.setItems(this.examList);
		this.setCellFactoryForListView(this.examListView);
	}
	
	/**
	 * Configures the TextField objects 
	 * displaying each grade group's sub-total.
	 */
	private void initializeGradeTextFields() {
		this.initializeQuizSubtotalField();
		this.initializeHomeworkSubtotalField();
		this.initializeExamSubtotalField();
		this.initializeFinalGradeField();
	}
	
	/*
	 * Configures the Quiz Grade sub-total TextField.
	 * 
	 * Grade calculated by total sum.
	 */
	private void initializeQuizSubtotalField() {
		this.quizSubtotal.setEditable(false);
		this.quizSubtotal.textProperty().bindBidirectional(this.quizProperty, new NumberStringConverter());
	}
	
	/**
	 * Configures the Homework Grade sub-total TextField.
	 * 
	 * Grade calculated by average 
	 * with lowest Grade dropped.
	 */
	private void initializeHomeworkSubtotalField() {
		this.homeworkSubtotal.setEditable(false);
		this.homeworkSubtotal.textProperty().bindBidirectional(this.homeworkProperty, new NumberStringConverter());
	}
	
	/**
	 * Configures the Exam Grade sub-total TextField.
	 * 
	 * Grade calculated by average.
	 */
	private void initializeExamSubtotalField() {
		this.examSubtotal.setEditable(false);
		this.examSubtotal.textProperty().bindBidirectional(this.examProperty, new NumberStringConverter());
	}
	
	/**
	 * Configures the Final Grade TextField.
	 */
	private void initializeFinalGradeField() {
		this.finalGradeTotal.setEditable(false);
	}
	
	/**
	 * Configures the recalculate button
	 * to recalculate final grade on click.
	 */
	private void initializeRecalculateButton() {
		this.recalculateButton.setOnAction(new RecalculateFinalGradeListener());
	}
	
	/**
	 * Updates an IntegerProperty bound to a TextField
	 * @param theProperty to be set
	 * @param theStrategy to be used
	 * @param gradeList to compile
	 */
	private void setTextFieldDoubleProperty(DoubleProperty theProperty, GradeStrategy theStrategy, ObservableList<Grade> gradeList) {
		if (theProperty == null || theStrategy == null || gradeList == null) {
			throw new IllegalArgumentException("Parameters cannot be null");
		}
		
		theProperty.set(theStrategy.calculateGrade(gradeList));
	}
	
	/**
	 * Listener for the Recalculate button.
	 * Recalculates the final grade on click.
	 * 
	 * @author Brandon Drick
	 * @version 11/03/2021
	 */
	private class RecalculateFinalGradeListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			this.updateSubtotals();
			this.updateFinalGrade();
		}
		
		/**
		 * Updates the sub-total TextFields
		 */
		private void updateSubtotals() {
			SchoolGradesController.this.setTextFieldDoubleProperty(SchoolGradesController.this.quizProperty, 
					new SumOfGradesStrategy(), SchoolGradesController.this.quizList);
			SchoolGradesController.this.setTextFieldDoubleProperty(SchoolGradesController.this.homeworkProperty, 
					new GradeDropperLowestGrade(new AverageOfGradesStrategy()), 
					SchoolGradesController.this.homeworkList);
			SchoolGradesController.this.setTextFieldDoubleProperty(SchoolGradesController.this.examProperty, 
					new AverageOfGradesStrategy(), SchoolGradesController.this.examList);
		}
		
		/**
		 * Updates the final grade TextField
		 */
		private void updateFinalGrade() {
			ArrayList<Grade> grades = this.createGradeList();
			
			double finalGrade = 0;
			for (Grade current : grades) {
				finalGrade += current.getValue();
			}
			
			DoubleProperty finalGradeProperty = new SimpleDoubleProperty(finalGrade);
			
			SchoolGradesController.this.finalGradeTotal.textProperty().bindBidirectional(finalGradeProperty, new NumberStringConverter());
		}
		
		/**
		 * Returns a grade list of weighted grades
		 * for calculating the final grade.
		 * @return grades ArrayList
		 */
		private ArrayList<Grade> createGradeList() {
			double quiz = Double.valueOf(SchoolGradesController.this.quizSubtotal.getText());
			double homework = Double.valueOf(SchoolGradesController.this.homeworkSubtotal.getText());
			double exam = Double.valueOf(SchoolGradesController.this.examSubtotal.getText());
			
			Grade quizGrade = new WeightedGrade(new SimpleGrade(quiz), 0.2);
			Grade homeworkGrade = new WeightedGrade(new SimpleGrade(homework), 0.3);
			Grade examGrade = new WeightedGrade(new SimpleGrade(exam), 0.5);
			
			ArrayList<Grade> grades = 
					new ArrayList<Grade>(Arrays.asList(quizGrade, homeworkGrade, examGrade));
			
			return grades;
		}
	}
	
	/**
	 * Configures a ListView to be able to display a Grade object
	 * @param theListView Grade ListView to configure
	 */
	private void setCellFactoryForListView(ListView<Grade> theListView) {
		theListView.setCellFactory(new Callback<ListView<Grade>, ListCell<Grade>>() {
			@Override
			public ListCell<Grade> call(ListView<Grade> grades) {
				return new GradeCell();
			}
		});
	}
	
	/**
	 * Configures a Grade to be displayed
	 * as a String for the ListView.
	 * 
	 * @author Brandon Drick
	 * @version 11/02/2021
	 */
	private static class GradeCell extends TextFieldListCell<Grade> {
		@Override
		public void updateItem(Grade grade, boolean empty) {
			super.updateItem(grade, empty);
		    super.setConverter(new GradeConverter(this));
		    
			if (empty || grade == null) {
				setText(null);
				setGraphic(null);
			} else {
				double cellContents = grade.getValue();
				String value = Double.toString(cellContents);
				super.setText(value);
			}
		}
	}
	
	/**
	 * Configures as ListView cell to allow 
	 * for editing Grades
	 * @author Brandon Drick
	 * @version 11/05/2021
	 */
	private static class GradeConverter extends StringConverter<Grade> {
	    private final ListCell<Grade> cell;
	    
	    GradeConverter(ListCell<Grade> cell) {
	        this.cell = cell;
	    }
	    
	    @Override
	    public String toString(Grade grade) {
	        String gradeValue = String.valueOf(grade.getValue());
	    	return gradeValue;
	    }

	    @Override
	    public Grade fromString(String string) {
	    	SimpleGrade theGrade = (SimpleGrade) this.cell.getItem();
	    	try {
		    	double newGradeValue = Double.valueOf(string);
	    		
		    	if (newGradeValue > 100 || newGradeValue < 0) {
		    		return theGrade;
		    	}
		    	
		    	theGrade.setValue(Double.valueOf(string));
		    	
		    	return theGrade;
	    	} catch (NumberFormatException nfe) {
	    		return theGrade;
	    	}
	    }
	}

}
