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
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import example.android.com.utils.SharedPreferenceHandler;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;
    private EditText mobileNumber;
    private EditText password;
    private ProgressBar progressBar;
    String response;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();
    }

    private void initializeView()
    {
        progressBar=(ProgressBar)findViewById(R.id.progress);
        login=(Button)findViewById(R.id.btnLoginAsCustomer);
        register=(Button)findViewById(R.id.btnRegisterLink);
        mobileNumber=(EditText)findViewById(R.id.etPhone);
        password=(EditText)findViewById(R.id.etPassword);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        OkHttpHandler handler = new OkHttpHandler(this,progressBar,"http://android-rahi.herokuapp.com/index.php");
        Intent intent;
        LoginUser loginUser =new LoginUser();
        Validator validator =new Validator();
        if (v==login) try {
            validator.validateLoginDetails(mobileNumber.getText().toString(), password.getText().toString());
            response = handler.execute(mobileNumber.getText().toString(), password.getText().toString(), "login").get();
            if (response != null && response != "") {
                Map<String, Object> mapObject = new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {
                }.getType());
                if (mapObject.get("error").toString().equals("true")) {
                    throw new Exception(mapObject.get("error_msg") + "");
                } else {
                    loginUser = new Gson().fromJson(response, LoginUser.class);
                    Log.i("ResponseType", loginUser.toString());
                }
                SharedPreferenceHandler.writeValue(this, "LoginObject", loginUser.toString());
                Log.i("asdfghjkl", SharedPreferenceHandler.readValue(this, "LoginObject"));

                intent = new Intent(this, UserHomeActivity.class);
                startActivity(intent);
            } else if (response == null) {
                throw new Exception("Technical error");
            }
        } catch (Exception e) {
            e.printStackTrace();
           ShowError.displayError(this,e.getMessage());

        }
        else if (v==register){
            intent=new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }
}