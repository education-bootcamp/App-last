package Controller;

import DB.Database;
import Entity.Customer;
import View.TM.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainFormController {
    public TableView<CustomerTM> tblCustomers;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;

    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllCustomers();
    }

    public void loadAllCustomers() {
        ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
        for(Customer c : Database.customerTable){
            Button btn = new Button("delete");
            CustomerTM tm = new CustomerTM( c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn);
            tmList.add(tm);
        }
        tblCustomers.setItems(tmList);
    }
}
