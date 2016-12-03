package org.androidtown.lbs.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Administrator on 2016-11-26.
 */

public class InsertInfo extends Activity {
    Button buttonGoMain,buttonTakePic;
    String category;
    RadioGroup radioGroupCategory;
    EditText editTextTilte,editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_insert);
        editTextTilte = (EditText)findViewById(R.id.editText_title);
        editTextContent=(EditText)findViewById(R.id.editText_title);

        buttonGoMain = (Button)findViewById(R.id.button_goMain);
        buttonGoMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertInfo.this,MainActivity.class);
                startActivity(i);
            }
        });
        buttonTakePic = (Button)findViewById(R.id.button_takePic);
        buttonTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InsertInfo.this,TakePicAndStore.class);
                i.putExtra("category",category);
                i.putExtra("title",editTextTilte.getText().toString());
                i.putExtra("centent",editTextContent.getText().toString());
                startActivity(i);
           }
        });
        radioGroupCategory = (RadioGroup)findViewById(R.id.radioGroup_cate);
        radioGroupCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton radioButton = (RadioButton) findViewById(radioGroupCategory.getCheckedRadioButtonId());
                category = radioButton.getText().toString();
            }
        });
    }
}