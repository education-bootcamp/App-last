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
import javax.xml.crypto.Data;
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
    public TextField txtSearch;

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

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
            loadAllCustomers(newValue);
        });

        //=============

        loadAllCustomers("");
    }

    private void setData(CustomerTM value) {
        btnSaveCustomer.setText("Update Customer");

        txtId.setText(value.getId());
        txtName.setText(value.getName());
        txtAddress.setText(value.getAddress());
        txtSalary.setText(String.valueOf(value.getSalary()));
    }

    public void loadAllCustomers(String text) {
        ObservableList<CustomerTM> tmList = FXCollections.observableArrayList();
        for(Customer c : Database.customerTable){

            if (c.getName().contains(text) || c.getAddress().contains(text)){
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
                            loadAllCustomers("");
                        }else new Alert(Alert.AlertType.WARNING,"Try Again!").show();
                    }


                });


                tmList.add(tm);
            }


        }
        tblCustomers.setItems(tmList);
    }

    public void saveOnAction(ActionEvent actionEvent) {
        Customer c1= new Customer(
                txtId.getText(),txtName.getText(),txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );
        if (btnSaveCustomer.getText().equalsIgnoreCase("Update Customer")){
            // update
            for (Customer c:Database.customerTable
                 ) {
                if (c.getId().equals(txtId.getText())){
                    c.setName(c1.getName());
                    c.setAddress(c1.getAddress());
                    c.setSalary(c1.getSalary());
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                    loadAllCustomers("");
                }
            }
        }else{
            if(Database.customerTable.add(c1)){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                loadAllCustomers("");
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        }
    }

    public void resetButtonOnAction(ActionEvent actionEvent) {
        btnSaveCustomer.setText("Save Customer");
    }
}
