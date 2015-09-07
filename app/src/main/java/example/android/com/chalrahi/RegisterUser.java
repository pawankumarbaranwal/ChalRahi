package example.android.com.chalrahi;

public class RegisterUser
{
    private String uid;
    private String error;
    private UserRegisterDetails user;

    public String getUid ()
    {
        return uid;
    }

    public void setUid (String uid)
    {
        this.uid = uid;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    public UserRegisterDetails getUser ()
    {
        return user;
    }

    public void setUser (UserRegisterDetails user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "RegisterUser [uid = "+uid+", error = "+error+", user = "+user+"]";
    }
}

