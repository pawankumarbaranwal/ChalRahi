package example.android.com.chalrahi;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class UserHomeActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private DrawerLayout drawerlayout;
    private ListView listview;
    private List<String> drawerItemList;
    private ActionBarDrawerToggle drawerListener;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private ImageView profilePic;
    private Context context = this;
    private static final int LOAD_IMAGE_FROM_GALLERY = 1;
    private Intent builderIntent;
    LinearLayout pawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerItemList = Arrays.asList(getResources().getStringArray(R.array.fragment_drawer_items));
        listview = (ListView) findViewById(R.id.drawerList);
        profilePic = (ImageView) findViewById(R.id.ivProfilePic);
        pawan = (LinearLayout) findViewById(R.id.pawan);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        AdapterClass adapter = new AdapterClass();
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);
        profilePic.setOnClickListener(this);


        drawerListener = new ActionBarDrawerToggle(this, drawerlayout, R.string.drawer_open, R.string.drawer_close) {

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

        loadSelection(1);
    }

    private void loadSelection(int i) {
        switch (i) {
            case 0:
                ProfileFragment profileFragment= new ProfileFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, profileFragment);
                fragmentTransaction.commit();
                break;

            case 1:
                AutoBookActivity autoBookActivity = new AutoBookActivity();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, autoBookActivity);
                fragmentTransaction.commit();
                break;

            case 2:
                InviteAndEarnFragment inviteAndEarnFragment = new InviteAndEarnFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, inviteAndEarnFragment);
                fragmentTransaction.commit();
                break;

            case 3:
                FairDetailsFragment fairDetailsFragment = new FairDetailsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, fairDetailsFragment);
                fragmentTransaction.commit();
                break;

            case 4:
                SupportFragment supportFragment = new SupportFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, supportFragment);
                fragmentTransaction.commit();
                break;


            case 5:
                AboutUsFragment aboutUsFragment = new AboutUsFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, aboutUsFragment);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerListener.onOptionsItemSelected(item)) {
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
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,ListItem[position],Toast.LENGTH_SHORT).show();

        drawerlayout.closeDrawers();
        loadSelection(position);

        selectItem(position);
    }


    public void selectItem(int position) {
        listview.setItemChecked(position, true);
        setTitle(drawerItemList.get(position));
    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onClick(View v) {
        if (v == profilePic) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserHomeActivity.this);
            builderIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(builderIntent, LOAD_IMAGE_FROM_GALLERY);
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
            //drawerListener.syncState();
            //drawerlayout.setScrimColor(Color.WHITE);
        }
    }

    private class AdapterClass extends BaseAdapter {
        @Override
        public int getCount() {
            return drawerItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        class ViewHolder {
            TextView drawerItem;

            ViewHolder(View view) {
                drawerItem = (TextView) view.findViewById(R.id.tvDrawerItem);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.row_for_fragment_item, parent, false);
                viewHolder = new ViewHolder(row);
                row.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) row.getTag();
            }
            //Customer c=customerList.get(position);
            String itemName = drawerItemList.get(position);
            viewHolder.drawerItem.setText(itemName);
            //viewHolder.nameTextView.setTag(c);
            return row;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap;
        if (data != null) {
            Uri selectedImageFromUri = data.getData();
            String pathOFImage = getRealPathFromURI(selectedImageFromUri);
            File imgFile = new File(pathOFImage);
            bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            bitmap = getCroppedBitmap(bitmap);
            profilePic.setImageBitmap(bitmap);
            //drawerlayout.setDrawerListener(drawerListener);

        }
    }

    private String getRealPathFromURI(Uri selectedImageFromUri) {
        Cursor cursor = getContentResolver().query(selectedImageFromUri, null, null, null, null);
        if (cursor == null) {
            return selectedImageFromUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    public Bitmap getCroppedBitmap(Bitmap source) {

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;

    }
}
