package package1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Document;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.cell.CheckBoxTableCell;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Javafxshow extends Application 
{
	
	//private CuserModel cuser=new CuserModel();
	
	private static  List<User> userlist=null;
	private static  List<Message> messagelist=null;
	FileMain filemain=new FileMain();
	public static void main(String[] args)
	{
		Application.launch(args);
	}
	@Override
	public void start(Stage primaryStage)
	{
		
	
		
MenuBar menuBar = new MenuBar();
		
GridPane   gridpane=new GridPane();
GridPane griduser=getuserpane(primaryStage);
GridPane gridmessage=getmessagepane(primaryStage);
	
		
	    Menu menuFile = new Menu("用户");           
	    Menu add=new Menu("用户管理 ");
	    
	    add.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
			
				
				griduser.setVisible(true);
				 gridmessage.setVisible(false);
				 menuFile.hide();
			}
		});
	  
	    menuFile.getItems().addAll(add);
	    	
	    Menu menuEdit = new Menu("留言");                 
	    Menu add1=new Menu("留言管理");
	  
	   
	   
	    menuEdit.getItems().addAll(add1);
	
	    add1.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e){
				griduser.setVisible(false);
				 gridmessage.setVisible(true);
				 menuEdit.hide();
				 
			}
		});
	   
	  
	 
	   
	   
	   
	   

	 
	    menuBar.getMenus().addAll(menuFile, menuEdit);
	 
	
	    
	    gridpane.add(menuBar,0,2);
	
	    gridpane.add(griduser,0,3);
		
	    gridpane.add(gridmessage,0,3);
	    
	    gridmessage.setVisible(false);
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	    System.out.println("1111111111111111111111111");
		Scene scene =new Scene(gridpane,primaryScreenBounds.getWidth(),primaryScreenBounds.getHeight()-30);
		 
		
		primaryStage.setScene(scene);
		primaryStage.show();
		 System.out.println("1222222222222222222222222222222222");
		   System.out.println("333");
	
	}
	public GridPane getuserpane(Stage primaryStage)
	{
		GridPane grid=new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setHgap(10);
		grid.setVgap(10);
		Label label=new Label("用户管理");
		label.setFont(new Font("Times New Roman",20));
		grid.add(label,0,0);
		grid.add(getVBoxuser(primaryStage), 0, 1);
		
		return grid;
	}
	public GridPane getmessagepane(Stage primaryStage)
	{
		GridPane grid=new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setHgap(10);
		grid.setVgap(10);
		Label label=new Label("留言管理");
		label.setFont(new Font("Times New Roman",20));
		grid.add(label,0,0);
		grid.add(getVBoxmessage(primaryStage), 0, 1);
		
		return grid;
	}
	@SuppressWarnings("unchecked")
	private VBox getVBoxuser(Stage primaryStage)
	{
		   ObservableList<User> data =
		        FXCollections.observableArrayList();
		   userlist= filemain.getuser();
	//	   CuserModel cuser=new CuserModel();
		//userlist=cuser.javafxloaduser();
		data.addAll(userlist);
		VBox vBox=new VBox();
		vBox.setPadding(new Insets(10,10,10,10));
		TableView table=new TableView();
		
		TableColumn tc1=new TableColumn("用户名");
		
		tc1.setMinWidth(400);
		tc1.setCellValueFactory( new PropertyValueFactory<>("Username"));
		
		TableColumn tc2=new TableColumn("用户类型");
		tc2.setMinWidth(400);
		tc2.setCellValueFactory( new PropertyValueFactory<>("Vary"));
		
		TableColumn tc3=new TableColumn("删除");
		tc3.setMinWidth(400);
	
		//tc3.setCellValueFactory( new PropertyValueFactory<>("Option"));
		
		tc3.setCellFactory(new Callback<TableColumn<User, Boolean>, TableCell<User, Boolean>>()
        {
            public TableCell<User, Boolean> call(TableColumn<User, Boolean> p) 
            {
                return new shanchushow(primaryStage,table,data);
            }

        });
	
		
		table.getColumns().addAll(tc1,tc2,tc3);
		vBox.getChildren().addAll(table);
		
		table.setItems(data);
	
		return vBox;
	}
	
	 static class shanchushow extends TableCell<User, Boolean> 
	    {
	
		 static ArrayList<Button> buttonlist=new  ArrayList<Button>();
		 final Button button = new Button("删除");
	          
	            shanchushow(Stage r,TableView table, ObservableList<User> data)
	            {
	            	button.setOnAction(e->shan(r,table,data));
	            }

	            public  void shan(Stage r,TableView table, ObservableList<User>data) 
	            {	
	            //	button.setVisible(false);
	            	
	            
	            	
	            	
	         int size=   userlist.size();
	           
	            	
	           userlist.remove(0); 
	           data.clear();
	           data.addAll(userlist);
	           table.setItems(data);
	           int buttonsize=   buttonlist.size();
	           buttonlist.get(buttonsize-1).setVisible(false);
	      System.out.println(buttonlist.indexOf(button));
	           // 	this.getTableRow().re
	            
	    		//	getTableRow().setVisible(false);
	    		}

	    		protected void updateItem(Boolean t, boolean empty) {
	                super.updateItem(t, empty);
	             	
	                if(!empty){
	                	buttonlist.add(button);
	                    setGraphic(button);
	                }
	            }
	            
	    }
	private VBox getVBoxmessage(Stage primaryStage)
	{
		

		   ObservableList<Message> data =
		        FXCollections.observableArrayList();
		   messagelist=filemain.getmessage();
		//   messagelist=cuser.javafxloadmessage();
		data.addAll(messagelist);
		VBox vBox=new VBox();
		vBox.setPadding(new Insets(10,10,10,10));
		TableView table=new TableView();
		TableColumn tc1=new TableColumn("用户名");
		tc1.setMinWidth(300);
		tc1.setCellValueFactory( new PropertyValueFactory<>("Username"));
		
	
		
		TableColumn tc3=new TableColumn("留言");
		tc3.setMinWidth(300);
		
		tc3.setCellValueFactory( new PropertyValueFactory<>("Message"));
		TableColumn tc4=new TableColumn("操作");
		tc4.setMinWidth(300);
		
	
	
		tc4.setCellFactory(new Callback<TableColumn<Message, Boolean>, TableCell<Message, Boolean>>()
        {
            public TableCell<Message, Boolean> call(TableColumn<Message, Boolean> p) 
            {
                return new shanchushow2(primaryStage,table,data);
            }

        });
		
	
     
		table.getColumns().addAll(tc1,tc3,tc4);
		vBox.getChildren().addAll(table);
		
		table.setItems(data);
		return vBox;
	}
	
	 static class shanchushow2 extends TableCell<Message, Boolean> 
	    {
	
		 static ArrayList<Button> buttonlist=new  ArrayList<Button>();
		 final Button button = new Button("删除");
	          
	            shanchushow2(Stage r,TableView table, ObservableList<Message> data)
	            {
	            	button.setOnAction(e->shan(r,table,data));
	            }

	            public  void shan(Stage r,TableView table, ObservableList<Message>data) 
	            {	
	            //	button.setVisible(false);
	            	
	            
	            	
	            	
	         int size=   messagelist.size();
	           
	            	
	         messagelist.remove(0); 
	           data.clear();
	           data.addAll(messagelist);
	           table.setItems(data);
	           int buttonsize=   buttonlist.size();
	           buttonlist.get(buttonsize-1).setVisible(false);
	      System.out.println(buttonlist.indexOf(button));
	           // 	this.getTableRow().re
	            
	    		//	getTableRow().setVisible(false);
	    		}

	    		protected void updateItem(Boolean t, boolean empty) {
	                super.updateItem(t, empty);
	             	
	                if(!empty){
	                	buttonlist.add(button);
	                    setGraphic(button);
	                }
	            }
	            
	    }
	
	

}  
 