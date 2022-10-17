import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ListVIewFormController {
    public TextField txtLanguages;
    public ListView<String> listView;
    public TextField selectedLanguages;

    ObservableList<String> obList = FXCollections.observableArrayList();

    public void initialize() {
        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldVal, newValue) -> {
                    selectedLanguages.setText(newValue);
                });
    }

    public void saveLanguagesOnAction(ActionEvent actionEvent) {
        if(!isExist(txtLanguages.getText())){
            obList.add(txtLanguages.getText());
            listView.setItems(obList);
        }else{
            new Alert(Alert.AlertType.CONFIRMATION,"already exists").show();
        }
    }

    private boolean isExist(String value){
        for(String temp:obList){
            if(temp.equals(value)){
                return true;
            }
        }
        return false;
    }

}
