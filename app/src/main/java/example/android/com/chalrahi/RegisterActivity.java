package example.android.com.chalrahi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;
    private Button register;
    private EditText name;
    private EditText email;
    private EditText mobileNumber;
    private EditText password;
    private EditText dateOfBirth;
    String gender;

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
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        Validator validator=new Validator();
        if (v==login){
            intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if (v==register){
            try {
                validator.validateRegistrationDetails(name.getText().toString(), email.getText().toString(),mobileNumber.getText().toString()
                        ,dateOfBirth.getText().toString(),password.getText().toString(),gender);
                Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
