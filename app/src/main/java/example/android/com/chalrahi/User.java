package example.android.com.chalrahi;

public class User {
    private String updated_at;

    private String email;
    private String name;
    private String created_at;

    public String getUpdated_at()
    {
        return updated_at;
    }

    public void setUpdated_at(String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCreated_at()
    {
        return created_at;
    }

    public void setCreated_at(String created_at)
    {
        this.created_at = created_at;
    }

    @Override
    public String toString()
    {
        return "User [updated_at = " + updated_at + ", email = " + email + ", name = " + name + ", created_at = " + created_at + "]";
    }
}
