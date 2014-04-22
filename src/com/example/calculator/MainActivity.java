package com.example.calculator;
 
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;


 
public class MainActivity extends Activity {
	
  SpinnerActivity spinnerActivity = new SpinnerActivity();
  String state_chosen = spinnerActivity.getStateSelected();
  
  
  Float stateTax;   
  public String str ="";
  Character op = 'q';
  int i,num,numtemp;  
  EditText showResult;
   
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
      
    showResult = (EditText)findViewById(R.id.result_id);     
    
    Spinner spinner = (Spinner) findViewById(R.id.spinner1);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.states_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    
  }
  
  public void chooseState(String state_chosen){
	  System.out.println("State chosen is: " + state_chosen);
	  if (state_chosen == "IL"){
		  stateTax = Float.parseFloat("1.0925");		  
	  } else if (state_chosen == "IN"){
		  stateTax = Float.parseFloat("1.0705");
	  }
  }
    
  public void btn0Clicked(View v){
	insert("0");  
  }
  
  public void btn1Clicked(View v){
    insert("1");        
  }
       
  public void btn2Clicked(View v){
    insert("2");        
  }
  
  public void btn3Clicked(View v){
    insert("3");        
  }
  
  public void btn4Clicked(View v){
    insert("4");       
  }
  
  public void btn5Clicked(View v){
    insert("5");       
  }
  
  public void btn6Clicked(View v){
    insert("6");
  }
  
  public void btn7Clicked(View v){
    insert("7");        
  }
  
  public void btn8Clicked(View v){
    insert("8");        
  }
  
  public void btn9Clicked(View v){
    insert("9");        
  }
  
  public void btnPClicked(View v){
	if (checkValid()){
	  insert(".");	
	}	  
  }
  
  public void btnplusClicked(View v){
	if (checkValid() && str.length() != 0 ){
	  insert("+");	
	}    
  }
       
  public void btnminusClicked(View v){
	if (checkValid() && str.length() != 0 ){
      insert("-");	
	}  
  }
  
  public void btndivideClicked(View v){
	if (checkValid() && str.length() != 0 ){
	  insert("/");	
	}     
  }
  
  public void btnmultiClicked(View v){
	if (checkValid() && str.length() != 0 ){
	  insert("*");	
	}    
  }

  public void btnequalClicked(View v){  	
	
	if (checkValid()){
	  calculate();		  
	  str = "";
	  str = showResult.getText().toString();	  
	  //showResult.setText(str);
	}
	       		
  }
       
  public void btnclearClicked(View v){
    reset();
  }
  
  public void btnTaxClicked(View v){
	  if (checkValid()){
		calculateWithTax();
		str = "";
		str = showResult.getText().toString();
	  }
  }
  
  private void reset() {
    // TODO Auto-generated method stub
    str ="";
    op ='q';
    num = 0;
    numtemp = 0;
    showResult.setText("");    
  }
  
  private void insert(String j) {
    // TODO Auto-generated method stub
    str = str+j;       
    showResult.setText(str);             
  }
  
  private boolean checkValid(){
	boolean canInsert;  
	  
	if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/") || str.endsWith(".") || str.equals("")){
      canInsert = false;
	} else {
	  canInsert = true; 	
	}
	
	return canInsert; 
  }
  
  
  
  
      
  private void calculate() {
    // TODO Auto-generated method stub
	ArrayList<String> arrayList=new ArrayList<String>();        
	Float final_result;
	
	int first_counter = 0;
    int last_counter = 0;

    for (int i = 0; i < str.length(); i++){
      if ( str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/' ){
        arrayList.add(str.substring(first_counter, last_counter));
        arrayList.add(str.substring(last_counter, last_counter+1));
        first_counter = last_counter + 1;        
      } else if (i == str.length() - 1){
        arrayList.add(str.substring(first_counter));
      }
      last_counter++;
    }
    
    ArrayList<Integer> indexArrayList=new ArrayList<Integer>();
    getIndexArraylist(arrayList, indexArrayList);
    
    
    while (arrayList.contains("*") || arrayList.contains("/")){                   
      partialOperation(arrayList, indexArrayList);                     
      getIndexArraylist(arrayList, indexArrayList);            
    }    
    
    final_result = partialOperation2(arrayList);     
    
    showResult.setText(final_result.toString());    

  }

  
  
  
  private void calculateWithTax() {
	    // TODO Auto-generated method stub
		ArrayList<String> arrayList=new ArrayList<String>();        
		Float final_result;
		
		int first_counter = 0;
	    int last_counter = 0;

	    for (int i = 0; i < str.length(); i++){
	      if ( str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/' ){
	        arrayList.add(str.substring(first_counter, last_counter));
	        arrayList.add(str.substring(last_counter, last_counter+1));
	        first_counter = last_counter + 1;        
	      } else if (i == str.length() - 1){
	        arrayList.add(str.substring(first_counter));
	      }
	      last_counter++;
	    }
	    
	    ArrayList<Integer> indexArrayList=new ArrayList<Integer>();
	    getIndexArraylist(arrayList, indexArrayList);
	    
	    
	    while (arrayList.contains("*") || arrayList.contains("/")){                   
	      partialOperation(arrayList, indexArrayList);                     
	      getIndexArraylist(arrayList, indexArrayList);            
	    }    
	    
	    final_result = partialOperation2(arrayList);     
	    final_result = final_result * stateTax;
	    
	    showResult.setText(final_result.toString());    

	  }

  
  
  
  
  private void getIndexArraylist(ArrayList<String> arrayList, ArrayList<Integer> indexArrayList){
	    
    // initialize indexArrayList
	int size = indexArrayList.size();
	for (int i = 0; i < size; i++){
	  indexArrayList.remove(0);
	}
	    
	for (int i = 0; i < arrayList.size(); i++){                  
	  if ( (arrayList.get(i).contains("*")) || (arrayList.get(i).contains("/")) ){        
	    indexArrayList.add(i);                
	  }
	}
	    
  }
	  

  private void partialOperation(ArrayList<String> arrayList, ArrayList<Integer> indexArrayList){    
	int first_index = indexArrayList.get(0);
	int last_index = 0, first_number, last_number = 0;
	int counter = 1;
	int index_counter = 0;    

	while( (counter < indexArrayList.size()) && (indexArrayList.get(counter) == indexArrayList.get(counter-1) + 2) ){
	  last_index = indexArrayList.get(counter);
	  counter++;      
	} 
	    
	// start doing partial calculation   
	first_number = first_index - 1;
	if (indexArrayList.size() == 1){
	  last_number = first_index + 1;
	}else{
	  last_number = last_index + 1;  
	}

	if (last_number == 1){
	  last_number = first_index + 1;
	}        

	Float temp_result = Float.parseFloat(arrayList.get(first_number));
	for (int i = first_number; i < last_number; i++){
	  if (arrayList.get(i).contains("/")){
	    temp_result = temp_result / Float.parseFloat(arrayList.get(i+1));       
	    index_counter++;         
	  }
	  else if (arrayList.get(i).contains("*")){
	    temp_result = temp_result * Float.parseFloat(arrayList.get(i+1));           
	    index_counter++;         
	  }
	}
	        
	for (int i = first_number; i < last_number; i++){
	  arrayList.set(i, "");        
	}
	arrayList.set(last_number, temp_result.toString());        
	while(arrayList.remove("")) { }             
	        
  }  


  private float partialOperation2(ArrayList<String> arrayList){    
	int first_number = 0;        

	Float temp_result;    
	      
	if (arrayList.get(0).contains("-")){
	  temp_result = 0 - Float.parseFloat(arrayList.get(first_number+1));
	  first_number++;
	} else{
	  temp_result = Float.parseFloat(arrayList.get(first_number));  
	}
	    
	for (int i = first_number; i < arrayList.size(); i++){
	  if (arrayList.get(i).contains("+")){        
	    temp_result = temp_result + Float.parseFloat(arrayList.get(i+1));               
	  }
	  else if (arrayList.get(i).contains("-")){        
	    temp_result = temp_result - Float.parseFloat(arrayList.get(i+1));                   
	  }
	      
	}
	        
	return temp_result;
	    
  }  
  
     
}