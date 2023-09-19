package com.example.calculatorproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        // Initialize result variable
        var result=0.00;
        // Find views by their IDs
        var listOperation = findViewById<Spinner>(R.id.ListSelection);

        var operationOptions = arrayOf("Addition","Subtraction","Multiplication","Division","Modulo");

        var adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,operationOptions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listOperation.adapter=adapter;

        var operand1= findViewById<EditText>(R.id.operand1);

        var operand2= findViewById<EditText>(R.id.operand2);

        var resultShow = findViewById<TextView>(R.id.result);

        var submitButton = findViewById<Button>(R.id.submit);

        var clearButton = findViewById<Button>(R.id.clear);

        var rotateButton = findViewById<Button>(R.id.rotateButton);

        var sentFirstButton = findViewById<Button>(R.id.resultSentFirst);

        var sentSecondButton = findViewById<Button>(R.id.resultSentSecond);


        // Rotate operands when the Rotate button is clicked
        rotateButton.setOnClickListener {
            var temp = operand1.text;
            operand1.setText(operand2.text);
            operand2.setText(temp);
        }
        // Handle the calculation when the Submit button is clicked
        submitButton.setOnClickListener {
            // Get the selected operation from the spinner
            val selectedOperation = listOperation.selectedItem.toString();
            // Check if operand1 or operand2 is empty
            if (operand1.text.isEmpty() || operand2.text.isEmpty()) {
                Toast.makeText(this, "This is wrong! Please add some value to the operands! ", Toast.LENGTH_LONG).show();
            }
            // Check for invalid expressions with multiple '+' or '-' signs
            else if( ((operand1.text.toString() == "+" ) &&(operand2.text.toString() == "+" )  ) || ((operand1.text.toString() == "+" ) || (operand2.text.toString() == "+" )  )){
                Toast.makeText(this, "Invalid Expression! Please enter a valid operand!", Toast.LENGTH_LONG).show();
            }
            // Check for invalid expressions with multiple '-' signs
            else if( ((operand1.text.toString() == "-" ) &&(operand2.text.toString() == "-" )  ) || ((operand1.text.toString() == "-" ) || (operand2.text.toString() == "-" )  )){
                Toast.makeText(this, "Invalid Expression! Please enter a valid operand!", Toast.LENGTH_LONG).show();
            }
            else {
                // Convert operand1 and operand2 to Double
                var number1 = operand1.text.toString().toDouble();
                var number2 = operand2.text.toString().toDouble();

                // Handle error conditions

                //Error Condition 1 : Division By Zero
                if(selectedOperation == "Division" && number2 == 0.0){
                    Toast.makeText(this,"Answer : Undefined. Division by zero isn't allowed, please use another number.",Toast.LENGTH_LONG).show();

                }
                // Error Condition 2 : Modulo By Zero
                else if(selectedOperation == "Modulo" && number2 == 0.0){
                    Toast.makeText(this,"Answer: Undefined. Operation Not Allowed",Toast.LENGTH_LONG).show();

                }
                // Error Condition 3 : Max Overflow Limit Exceeded
                else if(number1 > Int.MAX_VALUE || number2 > Int.MAX_VALUE){
                    Toast.makeText(this,"Max Integer Limit Exceeded. Going beyond can cause overflow! ",Toast.LENGTH_LONG).show();

                }
                // Error Condition 4 : Min Overflow Limit Detection
                else if(number1 < Int.MIN_VALUE || number2 < Int.MIN_VALUE){
                    Toast.makeText(this,"Min Integer Limit Exceeded. Going below can cause overflow! ",Toast.LENGTH_LONG).show();

                }
//                else if(number1 < Double.MIN_VALUE || number2 < Double.MIN_VALUE){
//                    Toast.makeText(this,"Min Double Limit Exceeded. Going below can cause overflow! ",Toast.LENGTH_LONG).show();
//
//                }
                // Error Condition 5 : Max Overflow Double Value exceeded
                else if(number1 > Double.MAX_VALUE || number2 > Double.MAX_VALUE){
                    Toast.makeText(this,"Max Double Limit Exceeded. Going beyond can cause overflow! ",Toast.LENGTH_LONG).show();

                }
//                else if(number1 < -Double.MAX_VALUE || number2 < Double.MAX_VALUE){
//                    Toast.makeText(this,"Min Double Limit Exceeded. Going below can cause overflow! ",Toast.LENGTH_LONG).show();
//
//                }

                else{
                result = when (selectedOperation) {
                    "Addition" -> number1 + number2
                    "Subtraction" -> number1 - number2
                    "Multiplication" -> number1 * number2
                    "Division" -> number1 / number2
                    "Modulo" -> number1 % number2
                    else -> 0.0
                }

                // Display the result
                resultShow.text = "Result : $result";
                Toast.makeText(this, "$result", Toast.LENGTH_LONG).show()
            }}
            // Clear operand fields when the Clear button is clicked
            clearButton.setOnClickListener {
                operand1.setText("");
                operand2.setText("");
                Toast.makeText(this, "Cleared all Operands", Toast.LENGTH_LONG).show();
            }

            rotateButton.setOnClickListener {
                var temp = operand1.text;
                operand1.setText(operand2.text);
                operand2.setText(temp);
            }
            // Swap operand values with result when the Sent First and Sent Second buttons are clicked
            sentFirstButton.setOnClickListener {
                operand1.setText("$result");
            }

            sentSecondButton.setOnClickListener {
                operand2.setText("$result");
            }
        }
        }
    }
