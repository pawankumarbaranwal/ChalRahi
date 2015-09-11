package example.android.com.chalrahi;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import example.android.com.utils.OnTaskCompleted;
import example.android.com.utils.SharedPreferenceHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private Button login;
    private Button register;
    private EditText mobileNumber;
    private EditText password;
    String response;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();
    }

    private void initializeView() {
        login = (Button) findViewById(R.id.btnLoginAsCustomer);
        register = (Button) findViewById(R.id.btnRegisterLink);
        mobileNumber = (EditText) findViewById(R.id.etPhone);
        password = (EditText) findViewById(R.id.etPassword);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        OkHttpHandler handler = new OkHttpHandler(this, this,"http://android-rahi.herokuapp.com/index.php");

        Validator validator = new Validator();
        if (v == login) try {
            validator.validateLoginDetails(mobileNumber.getText().toString(), password.getText().toString());
            handler.execute(mobileNumber.getText().toString(), password.getText().toString(), "login");
            //response =handler;
            /**/
        } catch (Exception e) {
            e.printStackTrace();
            ShowError.displayError(this, e.getMessage());

        }
        else if (v==register){
            intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onTaskCompleted(String response) {
        Log.d("Response", response);
        LoginUser loginUser = new LoginUser();
        Intent intent;
        try {
            if (response != null && response != "") {

                Map<String, Object> mapObject = new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (mapObject.get("error").toString().equals("true")) {
                    throw new Exception(mapObject.get("error_msg") + "");
                } else {
                    loginUser = new Gson().fromJson(response, LoginUser.class);
                    Log.i("ResponseType", loginUser.toString());
                }
                SharedPreferenceHandler.writeValue(this, "LoginObject", response);
                Log.i("asdfghjkl", SharedPreferenceHandler.readValue(this, "LoginObject"));

                intent = new Intent(this, UserHomeActivity.class);
                startActivity(intent);
            } else if (response == null) {
                throw new Exception("Technical error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ShowError.displayError(this, e.getMessage());
        }
    }
}