/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;
import Classes.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Jahmil
 */

public class ScreenController implements Initializable 
{
    static ArrayList<Integer> index = new ArrayList<Integer>();
    static double totalPrice = 0.0;
    static ObservableList<Book> Library = FXCollections.observableArrayList();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private DecimalFormat df = new DecimalFormat("0.00");
    private static String name;
    private static int userPoints;
    private static int deltaPoints;
    
    //Scene Variables:
        //Login Screen:
        
            //Password
            @FXML private PasswordField tfPassword;

            //Username
            @FXML private TextField tfUsername;

            //Showing Password
            @FXML private TextField ViewPassword;
            
            //Show-Password CheckBox
            @FXML private CheckBox pass_toggle;

            //Incorrect Password Text
            @FXML private Text IncorPass;
        
        //Owner Book Screen

            //List of Books
            @FXML private TableView<Book> bkCatalogue;
            
                //Book name Column
                @FXML private TableColumn<Book, String> bkname;

                //Book price Column
                @FXML private TableColumn<Book, Double> bkprice;

            //New Book name 
            @FXML private TextField newbkname;
            
            //New Book price
            @FXML private TextField newbkprice;

            //Invalid Price
            @FXML private Text InvalPrice;

            //Invalid Name
            @FXML private Text Invalname;

        //Owner Customer Screen
        
            //List of Customers
            @FXML private TableView<User> UsersList;  
        
            //Customer Username
            @FXML private TableColumn<User, String> Username;  
            
            //Customer Password
            @FXML private TableColumn<User, String> UserPassword;

            //Customer Points
            @FXML private TableColumn<User, Integer> UserPoints;
            
            //New Customer Password
            @FXML private TextField newUserPassword;

            //New Customer Username
            @FXML private TextField newUsername;

            //Invalid Username
            @FXML private Text InvalUsername;

        //Customer Start Screen

            //Customer View of Book Catalogue
            @FXML private TableView<Book> CustomerbkView;
                
                @FXML private TableColumn<Book, String> CSbkname;

                @FXML private TableColumn<Book, Double> CSbkprice;

                //If it is to be added to the cart
                @FXML private TableColumn<Book, CheckBox> Select;

            //Customer points
            @FXML private Label customerpoints;

            //Customer points Rank: Silver
            @FXML private Label customerRankS;

            //Customer points Rank: Gold
            @FXML private Label customerRankG;

            //Welcome Statement (Welcome + username.)
            @FXML private Label welcomeName;
            
            //Distance to next rank
            @FXML private ProgressBar fromNextRank;

        //Customer Cart Screen
        

            //Total Cost of books in cart
            @FXML private Label TotalCost;

            //Points Being Redeemed
            @FXML
            private Label PointsRedeemed;

            //View books in Cart
            @FXML private TableView<Book> bkInCart;

                //Cart Book Name
                @FXML private TableColumn<Book, String> Cartbkname;

                //Cart Book Price
                @FXML private TableColumn<Book, Double> Cartbkprice;

    //Scene Openers:

        //Login Screen
        public void OpenLS(ActionEvent event)throws Exception
        {
            Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene= new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        //Owner Start Screen 
            public void OpenOSS(ActionEvent event)throws Exception
            {
                Parent root = FXMLLoader.load(getClass().getResource("OwnerSS.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();          
            }

        //Owner Book Screen
            public void OpenOBS (ActionEvent event)throws Exception
            {
                Parent root = FXMLLoader.load(getClass().getResource("OwnerBS.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        //Owner Customer Screen
            public void OpenOCS (ActionEvent event)throws Exception
            {
                Parent root = FXMLLoader.load(getClass().getResource("OwnerCS.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        
        //Customer Start Screen
            public void OpenCSS (ActionEvent event)throws Exception
            {
                Parent root = FXMLLoader.load(getClass().getResource("CustomerSS.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        
        //Customer Cart Screen
            public void OpenCCS (ActionEvent event)throws Exception
            {
                Parent root = FXMLLoader.load(getClass().getResource("CustomerCS.fxml"));
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene= new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

    //Scene Methods:
        //Login Screen:

            //Show-Password Method
            @FXML void ShowPassword(ActionEvent event) 
            {
                if (pass_toggle.isSelected()) 
                {
                    ViewPassword.setText(tfPassword.getText());
                    ViewPassword.setVisible(true);
                    tfPassword.setVisible(false);

                    return;
                }
                tfPassword.setText(ViewPassword.getText());
                tfPassword.setVisible(true);
                ViewPassword.setVisible(false);

            }
            
            //Login Method
            @FXML public void VerifyUser(ActionEvent event) throws Exception
            {
                //Admin Check
                if (tfUsername.getText().equals("admin"))
                {
                    if((tfPassword.getText().equals("admin")||ViewPassword.getText().equals("admin")))
                    {
                        OpenOSS(event);
                        return;
                    } else 
                    {
                        IncorPass.setText("Invalid Credentials");
                    }

                } else if((!tfUsername.getText().isEmpty()))
                {
                    for(User user: BookStore.UsersList){
                        if (tfUsername.getText().equals(user.getUsername()))
                        {
                            if(tfPassword.getText().equals(user.getPassword()) ||ViewPassword.getText().equals(user.getPassword()))
                            {
                                name=String.valueOf(tfUsername.getText());
                                OpenCSS(event);
                                return;
                            } else if(tfUsername.getText().equals(user.getUsername()) && (tfPassword.getText().equals(user.getPassword()) ||ViewPassword.getText().equals(user.getPassword())))
                            {
                                
                                IncorPass.setText("Invalid Credentials");
                                continue;
                            }
                        } else if(!tfUsername.getText().equals(user.getUsername()))
                        {
                            IncorPass.setText("Invalid Credentials");
                        }
                        
                    }
                    
                    
                    
                }
                else 
                {
                   
                    
                }
                
            }
        //Owner Start Screen:
            
            //Logout Method (returns to login screen)
            @FXML public void Logout(ActionEvent event) throws Exception
            {
                Library.clear(); //clears visual cart
                index.clear();
                OpenLS(event);
            }

            //View Owner-Books Screen Method
            @FXML public void ViewOBS(ActionEvent event) throws Exception
            {
                OpenOBS(event);
            }

            //View Owner Customers Screen
            @FXML void ViewOCS(ActionEvent event) throws Exception
            {
                OpenOCS(event);
            }

        //Owner Book Screen

            //Get Books into Observable List
            public ObservableList<Book> getBooks()
            {
                ObservableList<Book> Library = FXCollections.observableArrayList();
                for(Book book: BookStore.BooksList)
                {

                    Library.add(book);
                }
                
                return Library;
            }
        
            //Method to add new book to list
            @FXML void addnewbook(ActionEvent event) throws Exception
            {
                try
                {
                    if (newbkname.getText().isEmpty()) 
                    {
                        throw new NullPointerException("Book name cannot be null");
                    }
                    Book book = new Book(newbkname.getText(),Double.parseDouble(newbkprice.getText()));
                    BookStore.BooksList.add(book);
                    OpenOBS(event);    
                } catch (NumberFormatException e) 
                {
                    InvalPrice.setText("Error: Invalid price format");
                    // Handle the exception or show an error message to the user
                } catch (NullPointerException e) 
                {
                    Invalname.setText("Error: Book name cannot be null");
                    // Handle the exception or show an error message to the user
                }
  
            }
        
            //Method to remove book from list
            @FXML void removebook(ActionEvent event) throws Exception
            {
                for (Book book:BookStore.BooksList)
                {
                    if(book.equals(bkCatalogue.getSelectionModel().getSelectedItem()))
                    {
                        BookStore.BooksList.remove(book);
                        bkCatalogue.getItems().removeAll(bkCatalogue.getSelectionModel().getSelectedItem());       
                    }
                }
                OpenOBS(event);
            }

            //Back Button (returns to Owner Start Screen)
            @FXML void returnToOSS(ActionEvent event) throws Exception
            {
                OpenOSS(event);
            }
        //Owner Customer Screen
        
            //Get Users into Observable List
            public ObservableList<User> getUsers()
            {
                ObservableList<User> Users = FXCollections.observableArrayList();
                for(User user: BookStore.UsersList)
                {
                    Users.add(user);
                }
                
                return Users;
            }

            public double getProgress(int points)
            {
                double Gold=1000.00;
                double prg= (double)points/Gold;
                if(points > 1000)
                {
                    fromNextRank.setStyle("-fx-accent: gold;");
                    return 1;
                }else
                {
                    fromNextRank.setStyle("-fx-accent: silver;");
                    return(prg);
                }
            }




            //Method to Create a new Customer User
            @FXML void newUser(ActionEvent event) throws Exception
            {
                try
                {
                    if (newUsername.getText().isEmpty() || newUserPassword.getText().isEmpty()) 
                    {
                        throw new NullPointerException("Fields cannot be Empty");
                    }
                    for (User user: BookStore.UsersList)
                    {
                        if (user.getUsername().equals(newUsername.getText()))
                        {
                            InvalUsername.setText("Error: Username Taken");
                            return;
                        }
                    }
                   
                    User newUser = new User(newUsername.getText(),newUserPassword.getText());
                    BookStore.UsersList.add(newUser);
                    OpenOCS(event);    
                    
                } catch (NullPointerException e) 
                {
                    InvalUsername.setText("Error: Fields cannot be Empty");
                    // Handle the exception or show an error message to the user
                }
  
            }

            //Method to remove a Customer User
            @FXML void removeUser(ActionEvent event) throws Exception
            {
                for (User user:BookStore.UsersList)
                {
                    if(user.equals(UsersList.getSelectionModel().getSelectedItem()))
                    {
                        BookStore.UsersList.remove(user);
                        UsersList.getItems().removeAll(UsersList.getSelectionModel().getSelectedItem()); 
                    }
                }
                OpenOCS(event);
            }

        //Customer Start Screen
            static int choice = 0;

            //Get Customer View of Books into Observable List
            public ObservableList<Book> getCustomerBooks()
            {
                ObservableList<Book> Library = FXCollections.observableArrayList();
                for(Book book: BookStore.BooksList)
                {

                    Library.add(book);
                }
                
                return Library;
            }
        

            //Buy books
            @FXML void Buy(ActionEvent event) throws Exception
            {
                Library.clear(); //clears visual cart
                index.clear();
                choice = 1;
                getCartIndexes();
                getCartBooks();
                OpenCCS(event);
            }
            
            //Redeem Points then Buy Books
            @FXML void RedeemBuy(ActionEvent event) throws Exception
            {
                Library.clear(); //clears visual cart
                index.clear();
                User user = BookStore.UsersList.get(getCurrentUserIndex());
                ArrayList<Book> currentCart = new ArrayList<Book>();
                for(int a: index){
                    currentCart.add(BookStore.BooksList.get(a));
                }
                
                choice = 2;
                getCartIndexes();
                getCartBooks();
                deltaPoints -= Math.min(user.getPoints(), totalPrice * 100);
                totalPrice = Math.max(totalPrice - (user.getPoints() / 100.0), 0);
                
                
                
                
                OpenCCS(event);
            }

        //Customer Cart Screen
            
            
            private void getCartIndexes(){
                for(Book book:CustomerbkView.getItems())
                {
                    if (book.isSelected().isSelected())
                    {
                        
                        System.out.println(BookStore.BooksList.indexOf(book) + book.getTitle());
                        index.add(BookStore.BooksList.indexOf(book));
                    }
                }
            }
            
            
            //Get Books into Observable List
            public void getCartBooks()
            {
                
                
                for(int a : index)
                {
                    if(Library.isEmpty()){
                        System.out.println("Get Cart Function is empty in getCartBooks during loop");
                    }
                    totalPrice += BookStore.BooksList.get(a).getPrice();
                    Book book = BookStore.BooksList.get(a);
                    Library.add(book);
                    
                    
                    
                    System.out.println(totalPrice + " " + BookStore.BooksList.get(a).getPrice() + BookStore.BooksList.get(a).getTitle());
                }
                
                if(Library.isEmpty()){
                System.out.println("Get Cart Function is empty in getCartBooks after loop");
                }
                //return Library;
            }

            //Checkout cart
            
            private int getCurrentUserIndex(){
                User currentUser;
                
                
                for(User user: BookStore.UsersList) 
                    if(name.equals(user.getUsername()))
                        return BookStore.UsersList.indexOf(user);
                
                return -1;
            }
            
            
            
            @FXML
            void Checkout(ActionEvent event) throws Exception
            {
                ArrayList<Book> currentCart = new ArrayList<Book>();
                User user = BookStore.UsersList.get(getCurrentUserIndex());
                
                for(int a: index){
                    currentCart.add(BookStore.BooksList.get(a));
                }
                
                if(choice == 1){
                    user.buy(currentCart, false);
                } else if (choice == 2){
                    user.buy(currentCart, true);
                }
                for(int a: index){
                    BookStore.BooksList.remove(BookStore.BooksList.get(a));
                }
                currentCart.clear();
                OpenCSS(event);
            }
                
            

            //Back Button (returns to Customer Start Screen)
            @FXML
            void returnToCSS(ActionEvent event) throws Exception
            {
                Library.clear();    
                index.clear();
                bkInCart.getItems().clear();
                totalPrice = 0.0;
                OpenCSS(event);
            }

            public void initialize(URL url, ResourceBundle rb) 
    {  
        try
        { //Owner-Books Catalogue
            
            bkname.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
            bkprice.setCellValueFactory((new PropertyValueFactory<Book,Double>("price")));
            bkCatalogue.setItems(getBooks());

        }catch(NullPointerException e)
        {
            System.out.println("Some Screen Elements are Hidden: 1");
        }
        try 
        { //Owner-Users List
            
            Username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
            UserPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
            UserPoints.setCellValueFactory(new PropertyValueFactory<User, Integer>("points"));
            UsersList.setItems(getUsers());

        }catch(NullPointerException e)
        {
            System.out.println("Some Screen Elements are Hidden: 2");
        } 
        
        try
        { //Customer-Start Screen

            Select.setCellValueFactory(new PropertyValueFactory<Book, CheckBox>("selected"));
            Select.setEditable(true);
            CSbkname.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
            CSbkprice.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
            welcomeName.setText(name);
            if(CustomerbkView.getItems().isEmpty())
            {
                CustomerbkView.setItems(getCustomerBooks());
            }else 
            {
                CustomerbkView.getItems().clear();
                CustomerbkView.setItems(getCustomerBooks());
            }
            

        }catch(NullPointerException e)
        {
            System.out.println("Some Screen Elements are Hidden: 3");
        } 
        try            
        { //Display Customer Rank and Points
            for (User user: BookStore.UsersList)
            {
                if(user.getUsername().equals(name))
                {
                    userPoints=user.getPoints();
                    customerpoints.setText("" + user.getPoints());
                    fromNextRank.setProgress(getProgress(user.getPoints()));
                
                    if (user.getPoints()>=1000)
                    {
                        customerRankS.setStyle("-fx-accent: gold;");
                        customerRankG.setText("Gold");
                    }else
                    {
                        customerRankS.setStyle("-fx-accent: silver;");
                        customerRankS.setText("Silver");
                    }

                }
            }
        }catch(NullPointerException e)
        {
            System.out.println("Some Screen Elements are Hidden: 4");
        } 
        try            
        { //Customer-Cart Screen
            
            Cartbkname.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
            Cartbkprice.setCellValueFactory(new PropertyValueFactory<Book,Double>("price"));
            TotalCost.setText("Total Cost: $" + Double.toString(totalPrice));
            bkInCart.setItems(Library);
            if((userPoints+deltaPoints)>=0)
            {
                PointsRedeemed.setText("New Point Balance: "+ (userPoints+deltaPoints));
            }else
            {
                PointsRedeemed.setText("New Point Balance: 0");
            }
            
        
        }catch(NullPointerException e)
        {
            System.out.println("Some Screen Elements are Hidden: 5");
        } 
    }    
}
