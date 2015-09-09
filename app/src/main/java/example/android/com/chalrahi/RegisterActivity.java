package example.android.com.chalrahi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

import example.android.com.utils.OnTaskCompleted;
import example.android.com.utils.SharedPreferenceHandler;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, OnTaskCompleted {

    private Button login;
    private ProgressBar progressBar;
    private Button register;
    private EditText name;
    private EditText email;
    private EditText mobileNumber;
    private EditText password;
    private EditText dateOfBirth;
    private RadioGroup radioGroup ;
    private EditText relativePersonName;
    private EditText relativePersonContact;
    private RadioButton radioSexButton;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeView();

    }
    private void initializeView()
    {
        login=(Button)findViewById(R.id.btnLoginLink);
        register=(Button)findViewById(R.id.btnRegister);
        name=(EditText)findViewById(R.id.etName);
        email=(EditText)findViewById(R.id.etEmail);
        mobileNumber=(EditText)findViewById(R.id.etMoblieNumber);
        dateOfBirth=(EditText)findViewById(R.id.etDateOfBirth);
        password=(EditText)findViewById(R.id.etPassword);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        relativePersonName=(EditText)findViewById(R.id.etRelativePersonName);
        relativePersonContact=(EditText)findViewById(R.id.etRelativePersonContact);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        OkHttpHandler handler = new OkHttpHandler(this, this, progressBar,"http://android-rahi.herokuapp.com/index.php");
        Validator validator =new Validator();
        if (v==login){
            intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if (v==register){
            try {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);
                Log.i("asdfghjkl",radioSexButton.getText()+"");


                validator.validateRegistrationDetails(name.getText().toString(),
                        email.getText().toString(),
                        mobileNumber.getText().toString(),
                        dateOfBirth.getText().toString(),
                        password.getText().toString()
                        );

                     handler.execute(
                            name.getText().toString(),
                            email.getText().toString(),
                            mobileNumber.getText().toString(),
                            dateOfBirth.getText().toString(),
                            password.getText().toString(),
                            radioSexButton.getText().toString(),
                            relativePersonName.getText().toString(),
                            relativePersonContact.getText().toString(),
                            "register"
                            );

            } catch (Exception e) {
                ShowError.displayError(this,e.getMessage());
                //Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onTaskCompleted(String response) {
        Log.d("Response Register", response);
        Intent intent;
        RegisterUser registerUser=new RegisterUser();
        try{
            if (response != null && response != "")
            {
                Gson gson = new GsonBuilder().create();
                Map<String, Object> mapObject = new Gson().fromJson(response, new TypeToken<Map<String, Object>>() {}.getType());
                if (mapObject.get("error").toString().equals("true"))
                {
                    throw new Exception(mapObject.get("error_msg")+"");
                }else
                {
                    registerUser = new Gson().fromJson(response, RegisterUser.class);
                    Log.i("ResponseType", registerUser.toString());
                }
                SharedPreferenceHandler.writeValue(this, "RegisterObject", registerUser.toString());
                Log.i("RegisterObjectRead",SharedPreferenceHandler.readValue(this, "RegisterObject"));

                intent=new Intent(this,UserHomeActivity.class);
                startActivity(intent);
            }
            else if (response==null)
            {
                throw new Exception("Technical error");
            }
        } catch (Exception e) {
            ShowError.displayError(this,e.getMessage());
            //Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
