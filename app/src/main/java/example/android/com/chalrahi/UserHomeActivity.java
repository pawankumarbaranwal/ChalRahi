package example.android.com.chalrahi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class UserHomeActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout drawerlayout;
    private ListView listview;
    private List<String> drawerItemList;
    private ActionBarDrawerToggle drawerListener;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerlayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerItemList = Arrays.asList(getResources().getStringArray(R.array.fragment_drawer_items));
        listview = (ListView)findViewById(R.id.drawerList);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        AdapterClass adapter=new AdapterClass();
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);


        drawerListener = new ActionBarDrawerToggle(this,drawerlayout,R.string.drawer_open,R.string.drawer_close)
        {

            @Override
            public void onDrawerOpened(View drawerView) {


            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }
        };


        drawerlayout.setDrawerListener(drawerListener);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentManager = getFragmentManager();

        LoadSelection(0);
    }

    private void LoadSelection(int i)
    {



        switch (i)
        {
            case 0 :
                InviteAndEarnFragment inviteAndEarnFragment = new InviteAndEarnFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,inviteAndEarnFragment);
                fragmentTransaction.commit();
                break;

            case 1 :
                FairDetailsFragment fairDetailsFragment= new FairDetailsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,fairDetailsFragment);
                fragmentTransaction.commit();
                break;

            case 2:
                SupportFragment supportFragment = new SupportFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,supportFragment);
                fragmentTransaction.commit();
                break;


            case 3 :
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, aboutUsFragment);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerListener.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        //Toast.makeText(this,ListItem[position],Toast.LENGTH_SHORT).show();

        drawerlayout.closeDrawers();
        LoadSelection(position);

        selectItem(position);
    }


    public void selectItem(int position)
    {
        listview.setItemChecked(position, true);
        setTitle(drawerItemList.get(position));
    }

    public void setTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }


    private class AdapterClass extends BaseAdapter{


        @Override
        public int getCount() {
            Log.i("error","1");
            return drawerItemList.size();
        }

        @Override
        public Object getItem(int position) {
            Log.i("error","2");
            return null;
        }

        @Override
        public long getItemId(int position) {
            Log.i("error","3");
            return 0;
        }
        class ViewHolder {
            TextView drawerItem;
            ViewHolder(View view) {
                Log.i("error","4");
                drawerItem = (TextView) view.findViewById(R.id.tvDrawerItem);
                Log.i("error","5");
            }
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View row = convertView;
            if (row == null) {
                Log.i("error","6");
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                Log.i("error","7");
                row=inflater.inflate(R.layout.row_for_fragment_item, parent, false);
                Log.i("error","8");
                viewHolder=new ViewHolder(row);
                Log.i("error","9");
                row.setTag(viewHolder);
            }else{
                Log.i("error","10");
                viewHolder=(ViewHolder)row.getTag();
            }
            //Customer c=customerList.get(position);
            String itemName = drawerItemList.get(position);
            viewHolder.drawerItem.setText(itemName);
            //viewHolder.nameTextView.setTag(c);
            return row;
        }
    }

}
