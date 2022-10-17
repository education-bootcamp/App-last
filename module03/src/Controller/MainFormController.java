package Controller;

import DB.Database;
import Entity.Customer;
import View.TM.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.html.Option;
import java.util.Optional;


public class MainFormController {
    public TableView<CustomerTM> tblCustomers;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public Button btnSaveCustomer;

    public void initialize(){

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        //=============
        tblCustomers.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setData(newValue);
            }
        });
        //=============

        loadAllCustomers();
    }

    private void setData(CustomerTM value) {
        btnSaveCustomer.setText("Update Customer");

        txtId.setText(value.getId());
        txtName.setText(value.getName());
        txtAddress.setText(value.getAddress());
        txtSalary.setText(String.valueOf(value.getSalary()));
    }

    public void loadAllCustomers() {
        ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
        for(Customer c : Database.customerTable){
            Button btn = new Button("delete");
            CustomerTM tm = new CustomerTM( c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn);

            btn.setOnAction(e->{

                Alert alert= new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure whether do you want delete this customer?",
                        ButtonType.YES, ButtonType.NO);
                //alert.show();
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get()==ButtonType.YES){
                    boolean isDeleted =Database.customerTable.remove(c); // collection ==> array list pre-defined method remove();
                    if(isDeleted){
                        new Alert(Alert.AlertType.CONFIRMATION,"Deleted!").show();
                        loadAllCustomers();
                    }else new Alert(Alert.AlertType.WARNING,"Try Again!").show();
                }


            });

            tmList.add(tm);
        }
        tblCustomers.setItems(tmList);
    }

    public void saveOnAction(ActionEvent actionEvent) {
    }
}
