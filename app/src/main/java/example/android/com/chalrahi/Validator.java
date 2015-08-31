package example.android.com.chalrahi;

public class Validator
{
    public void validateLoginDetails(String mobileNumber,String password) throws Exception
    {
        if ((mobileNumber == null) || (mobileNumber.isEmpty()))
        {
            throw new Exception("Please Enter a Moblie Number");
        } else if (mobileNumber.length() != 10)
        {
            throw new Exception("Mobile Number should contain 10 digits");
        } else if ((password == null)||(password.isEmpty()))
        {
            throw new Exception("Password cannot be empty");
        } else if (password.length() <= 5)
        {
            throw new Exception("Password should greater than 5 character");
        }
    }

    public void validateRegistrationDetails(String name, String email, String mobileNumber, String dateOfBirth, String password, String gender) throws Exception {
        if ((name == null) || (name.isEmpty()))
        {
            throw new Exception("Please Enter Name");
        } else if ((email == null) || (email.isEmpty()))
        {
            throw new Exception("Please enter Email");
        } else if ((mobileNumber == null) || (mobileNumber.isEmpty()))
        {
            throw new Exception("Please enter Mobile Number");
        } else if ((dateOfBirth == null) || (dateOfBirth.isEmpty()))
        {
            throw new Exception("Please enter Date Of Birth");
        }else if ((password == null) || (password.isEmpty()))
        {
            throw new Exception("Please enter Password");
        }else if ((gender == null) || (gender.isEmpty()))
        {
           // throw new Exception("Please enter Gender");
        }
    }
}