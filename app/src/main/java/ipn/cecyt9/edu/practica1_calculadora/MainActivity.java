package ipn.cecyt9.edu.practica1_calculadora;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_1 = (Button) findViewById(R.id.button_1);
        Button button_2 = (Button) findViewById(R.id.button_2);
        Button button_3 = (Button) findViewById(R.id.button_3);
        Button button_4 = (Button) findViewById(R.id.button_4);
        Button button_5 = (Button) findViewById(R.id.button_5);
        Button button_6 = (Button) findViewById(R.id.button_6);
        Button button_7 = (Button) findViewById(R.id.button_7);
        Button button_8 = (Button) findViewById(R.id.button_8);
        Button button_9 = (Button) findViewById(R.id.button_9);
        Button button_0 = (Button) findViewById(R.id.button_0);
        Button button_plus = (Button) findViewById(R.id.button_plus);
        Button button_minus = (Button) findViewById(R.id.button_minus);
        Button button_times = (Button) findViewById(R.id.button_times);
        Button button_divide = (Button) findViewById(R.id.button_divide);
        Button button_ac = (Button) findViewById(R.id.button_ac);
        Button button_equals = (Button) findViewById(R.id.button_equals);


        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_ac.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_times.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_equals.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int idClicked_Button = v.getId();
        String Result = "";
        TextView my_text_view = (TextView) findViewById(R.id.result_text);

        switch (idClicked_Button) {
            case R.id.button_1:
                my_text_view.append("1");
                break;
            case R.id.button_2:
                my_text_view.append("2");
                break;
            case R.id.button_3:
                my_text_view.append("3");
                break;
            case R.id.button_4:
                my_text_view.append("4");
                break;
            case R.id.button_5:
                my_text_view.append("5");
                break;
            case R.id.button_6:
                my_text_view.append("6");
                break;
            case R.id.button_7:
                my_text_view.append("7");
                break;
            case R.id.button_8:
                my_text_view.append("8");
                break;
            case R.id.button_9:
                my_text_view.append("9");
                break;
            case R.id.button_0:
                my_text_view.append("0");
                break;
            case R.id.button_plus:
                my_text_view.append("+");
                break;
            case R.id.button_minus:
                my_text_view.append("-");
                break;
            case R.id.button_times:
                my_text_view.append("*");
                break;
            case R.id.button_divide:
                my_text_view.append("/");
                break;
            case R.id.button_ac:
                my_text_view.setText("");
                break;
            case R.id.button_equals:
                Result = make_operations(my_text_view.getText().toString());
                my_text_view.setText(Result);
                break;
        }
    }

    public String make_operations(String operation){
        String current_number = "";
        ArrayList<Float> numbers = new ArrayList<Float>();
        ArrayList<int[]> operators = new ArrayList<int[]>();
        char operators_keys[][] = new char[2][2];

        operators_keys[0][0] = '+';
        operators_keys[0][1] = '-';
        operators_keys[1][0] = '*';
        operators_keys[1][1] = '/';

        for (int x = 0; x < operation.length(); x += 1) {
            char current_char = operation.charAt(x);
            if (current_char == '+' || current_char == '-' || current_char == '*' || current_char == '/') {
                int current_operator[] = new int[2];

                if (!(current_number.equals(""))) {
                    numbers.add(Float.parseFloat(current_number));
                    current_number = "";

                    if (current_char == '+' || current_char == '-') {
                        current_operator[0] = 0;
                        if (current_char == '+') {
                            current_operator[1] = 0;
                        } else {
                            current_operator[1] = 1;
                        }

                    } else if (current_char == '*' || current_char == '/') {
                        current_operator[0] = 1;
                        if (current_char == '*') {
                            current_operator[1] = 0;
                        } else {
                            current_operator[1] = 1;
                        }
                    }

                    operators.add(current_operator);
                }
            }
            if ((current_char == '-' && x == 0) || (current_char != '+' && current_char != '-' && current_char != '*' && current_char != '/')) {
                current_number = current_number + Character.toString(current_char);
            }
        }
        if (current_number.equals("")) {
            operators.remove(operators.size()-1);
        } else {
            numbers.add(Float.parseFloat(current_number));
        }

        int current_first_operator_key = 0;
        int current_second_operator_key = 0;
        char current_operator_key = ' ';
        float current_result = 0;
        int operators_size = operators.size();
        int current_operator_index = 0;
        float number_1;
        float number_2;
        for (int i = 1; i >= 0; i -= 1) {
            current_operator_index = 0;

            while (current_operator_index < operators_size) {
                current_first_operator_key = operators.get(current_operator_index)[0];

                if (current_first_operator_key == i) {
                    number_1 = numbers.get(current_operator_index);
                    number_2 = numbers.get(current_operator_index + 1);
                    current_second_operator_key = operators.get(current_operator_index)[1];
                    current_operator_key = operators_keys[current_first_operator_key][current_second_operator_key];
                    switch (current_operator_key) {
                        case '+':
                            current_result = number_1 + number_2;
                            break;
                        case '-':
                            current_result = number_1 - number_2;
                            break;
                        case '*':
                            current_result = number_1 * number_2;
                            break;
                        case '/':
                            current_result = number_1 / number_2;
                    }
                    numbers.set(current_operator_index, current_result);
                    numbers.remove(current_operator_index + 1);
                    operators.remove(current_operator_index);
                    operators_size -= 1;
                    current_operator_index -= 1;
                    if (operators_size == 0) {
                        break;
                    }
                }
                current_operator_index += 1;
            }
        }

        return Float.toString(current_result);
    }
}
