package com.example.gettingstartedapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resulttext;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setupbuttonclickListner();
}
    private void findView(){
        resulttext = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);


    }

    private void setupbuttonclickListner() {
        calculateButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
            double bmiResult=calculateBmi();
            String agetext = ageEditText.getText().toString();
            int age= Integer.parseInt(agetext );

            if(age>=18){
                displayResult(bmiResult);

            }else{
                displayGuidence(bmiResult);
            }
    }
    });
    }



    private double calculateBmi(){

        String feettext = feetEditText.getText().toString();
        String inchestext = inchesEditText.getText().toString();
        String weighttext = weightEditText.getText().toString();
        //convert string to integer.
        int feet= Integer.parseInt(feettext );
        int inches= Integer.parseInt(inchestext);
        int weight= Integer.parseInt(weighttext);

        int totelinches=(feet * 12)+inches;

        // height in meters is the totelinches multiply to the o.0254
        double heightinmeters=(totelinches * 0.0254);

        // calculate bmi value.
        return  weight/(heightinmeters * heightinmeters);

    }

    private void   displayResult(double Bmi) {
        DecimalFormat mydecimalformal =new DecimalFormat("0.00");
        String bmitextresult = mydecimalformal.format(Bmi);

        String fullresultstring;
        if(Bmi <18.5){
            //underweight
            fullresultstring =bmitextresult +" --you are underweight";
        }else if (Bmi >25){
            // overweight
            fullresultstring =bmitextresult + "--you are overweight";
        }else{
            //healthy
            fullresultstring =bmitextresult +"--you are healthy";
        }

        resulttext.setText(fullresultstring);
    }
    private void displayGuidence(double Bmi) {
        DecimalFormat mydecimalformal =new DecimalFormat("0.00");
        String bmitextresult = mydecimalformal.format(Bmi);
        String fullresultstring;
        if(maleButton.isChecked()){
            //boys guidence
            fullresultstring= bmitextresult +"--As you are under 18, please contact your docter for the healty range for boys";
        }else if(femaleButton.isChecked()){
            //girls guidence
            fullresultstring= bmitextresult +"--As you are under 18, please contact your docter for the healty range for girls ";
        }else{
            //general guidence
            fullresultstring= bmitextresult +"--As you are under 18, please contact your docter for the healty range for general";
        }
        resulttext.setText(fullresultstring);


    }


}