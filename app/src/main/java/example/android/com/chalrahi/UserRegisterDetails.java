package example.android.com.chalrahi;

public class UserRegisterDetails
{
    private String e_contact;
    private String e_contact_name;
    private String sex;
    private String updated_at;
    private String email;
    private String name;
    private String date_of_birth;
    private String created_at;
    private String contact;

    public String getE_contact ()
    {
        return e_contact;
    }

    public void setE_contact (String e_contact)
    {
        this.e_contact = e_contact;
    }

    public String getE_contact_name ()
    {
        return e_contact_name;
    }

    public void setE_contact_name (String e_contact_name)
    {
        this.e_contact_name = e_contact_name;
    }

    public String getSex ()
    {
        return sex;
    }

    public void setSex (String sex)
    {
        this.sex = sex;
    }

    public String getUpdated_at ()
{
    return updated_at;
}

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDate_of_birth ()
    {
        return date_of_birth;
    }

    public void setDate_of_birth (String date_of_birth)
    {
        this.date_of_birth = date_of_birth;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getContact ()
    {
        return contact;
    }

    public void setContact (String contact)
    {
        this.contact = contact;
    }

    @Override
    public String toString()
    {
        return "UserRegisterDetails [e_contact = "+e_contact+", e_contact_name = "+e_contact_name+", sex = "+sex+", updated_at = "+updated_at+", email = "+email+", name = "+name+", date_of_birth = "+date_of_birth+", created_at = "+created_at+", contact = "+contact+"]";
    }
}
