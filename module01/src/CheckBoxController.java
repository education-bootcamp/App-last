import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class CheckBoxController {
    public CheckBox cbxjava;
    public CheckBox cbxphp;
    public CheckBox cbxkotlin;
    public CheckBox cbxrooby;
    public TextArea txtSaveLanguage;
    public ToggleGroup gender;
    public TextArea txtGender;
    public RadioButton rdbFemale;

    public void SaveLanguagesOnAction(ActionEvent actionEvent) {
        String languages ="";
        if(cbxjava.isSelected()) languages = languages+", Java ";
        if(cbxphp.isSelected()) languages = languages+", php ";
        if(cbxkotlin.isSelected()) languages = languages+", kotlin ";
        if(cbxrooby.isSelected()) languages = languages+", rooby ";

        txtSaveLanguage.setText(languages);
    }

    public void SaveGenderOnAction(ActionEvent actionEvent) {
        String gender="male";
        if(rdbFemale.isSelected()) gender="Female";
        txtGender.setText(gender);
    }
}
