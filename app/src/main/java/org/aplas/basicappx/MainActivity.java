package org.aplas.basicappx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Distance dist = new Distance();
    private Weight weight = new Weight();
    private Temperature temp = new Temperature();
    private Button convertBtn;
    private EditText inputTxt,outputTxt;
    private Spinner unitOri,unitConv;
    private RadioGroup unitType;
    private CheckBox roundBox,formBox;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertBtn = (Button) findViewById(R.id.convertButton);
        inputTxt = (EditText) findViewById(R.id.inputText);
        outputTxt = (EditText) findViewById(R.id.outputText);
        unitOri = (Spinner) findViewById(R.id.oriList);
        unitConv = (Spinner) findViewById(R.id.convList);
        unitType = (RadioGroup) findViewById(R.id.radioGroup);
        roundBox = (CheckBox) findViewById(R.id.chkRounded);
        formBox = (CheckBox) findViewById(R.id.chkFormula);
        imgView = (ImageView) findViewById(R.id.img);

        unitType.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                       RadioButton selected = (android.widget.RadioButton) findViewById(checkedId);
                       ArrayAdapter<CharSequence> adapter;
                       inputTxt.setText("0");
                       outputTxt.setText("0");

                       if (selected.getText().equals("Temperature")){
                           adapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                   R.array.tempList, android.R.layout.simple_spinner_item);
                           imgView.setImageResource(R.drawable.temperature);
                           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           unitOri.setAdapter(adapter);
                           unitConv.setAdapter(adapter);
                       }else if (selected.getText().equals("Distance")){
                           adapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                   R.array.distList, android.R.layout.simple_spinner_item);
                           imgView.setImageResource(R.drawable.distance);
                           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           unitOri.setAdapter(adapter);
                           unitConv.setAdapter(adapter);
                       }else{
                           adapter = ArrayAdapter.createFromResource(unitType.getContext(),
                                   R.array.weightList, android.R.layout.simple_spinner_item);
                           imgView.setImageResource(R.drawable.weight);
                           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           unitOri.setAdapter(adapter);
                           unitConv.setAdapter(adapter);
                       }
                    }
                }
        );
    }
    private AlertDialog startDialog;
    protected void onStart(){
        super.onStart();
        startDialog = new AlertDialog.Builder(MainActivity.this).create();
        startDialog.setTitle("Application started");
        startDialog.setMessage("This app can use to convert any units");
        startDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        startDialog.show();
    }

    protected double convertUnit(String type,String oriUnit,String convUnit,double value){
        double result = 0;
        if (type.equals("Temperature")){
            result = temp.convert(oriUnit,convUnit,value);
        }else if (type.equals("Distance")){
            result = dist.convert(oriUnit,convUnit,value);
        }else if (type.equals("Weight")){
            result = weight.convert(oriUnit,convUnit,value);
        }
        return result;
    }

    protected String strResult(double value, boolean cek){
        String kata = "";
        if (cek){
            DecimalFormat f = new DecimalFormat("#.##");
            kata = f.format(value);
        }else{
            DecimalFormat f2 = new DecimalFormat("#.#####");
            kata = f2.format(value);
        }
        return kata;
    }
}