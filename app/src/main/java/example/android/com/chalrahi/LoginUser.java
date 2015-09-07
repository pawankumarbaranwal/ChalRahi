package example.android.com.chalrahi;

public class LoginUser {
    private String uid;
    private String error;
    private User user;

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

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "LoginUser [uid = "+uid+", error = "+error+", user = "+user+"]";
    }
}