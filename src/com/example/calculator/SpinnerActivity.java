package com.example.calculator;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


public class SpinnerActivity extends Activity implements OnItemSelectedListener {
	
	private String state_selected;
        
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    	state_selected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    	state_selected = "IL";
    }
    
    public String getStateSelected(){
    	return this.state_selected;
    }
}