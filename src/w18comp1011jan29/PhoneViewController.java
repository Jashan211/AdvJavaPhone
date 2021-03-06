package w18comp1011jan29;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author JWright
 */
public class PhoneViewController implements Initializable {

    @FXML    private ComboBox<String> brandCB;    
    @FXML    private TextField modelTextField;
    @FXML    private TextField osTextField;
    @FXML    private Slider resolutionSlider;
    @FXML    private Label resolutionLabel;
    @FXML    private Label errorMsg;

    /**
     * This method will automatically be called when the scene is launched
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        brandCB.getItems().addAll(Phone.getManufacturers());
        brandCB.getSelectionModel().selectFirst();
        
        resolutionSlider.setMin(4);
        resolutionSlider.setMax(20);
        resolutionSlider.setValue(12); //set the default value
        resolutionLabel.setText(Double.toString(resolutionSlider.getValue()) + "MP");
        
        errorMsg.setText("");
    }  
    
    /**
     * This method will update the resolution label and should be called
     * when the slider is dragged
     */
    public void resolutionSliderMoved()
    {
        String label = String.format("%.1f%n", resolutionSlider.getValue());
        resolutionLabel.setText(label);
    }
    
    public void createPhoneButtonPushed()
    {
        try{
        Phone newPhone = new Phone(this.resolutionSlider.getValue(), 
                                    this.brandCB.getValue(), 
                                    this.modelTextField.getText(), 
                                    this.osTextField.getText());
                                    
        System.out.println(newPhone.toString());
        errorMsg.setText("");
        }
        catch ( IllegalArgumentException e)
        {
            errorMsg.setText(e.getMessage());
        }
    }
    
}
