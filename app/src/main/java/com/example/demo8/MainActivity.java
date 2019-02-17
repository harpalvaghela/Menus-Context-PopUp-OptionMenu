package com.example.demo8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    Button btn;
    PopupMenu popupMenu;
    String name[]={"Gmail","Facebook","Twitter","WhatsApp","Instagram"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.lv);
        btn=(Button)findViewById(R.id.btn);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,name);
        lv.setAdapter(adapter);

        registerForContextMenu(lv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu   = new PopupMenu(MainActivity.this,btn);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());

                //click events on popmenu items ->
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(MainActivity.this, ""+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

    }//end of oncreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
               if(item.getTitle().equals("Logout")){
            Toast.makeText(this, "Logout...", Toast.LENGTH_SHORT).show();
        }

        if(item.getTitle().equals("Settings")){
            Toast.makeText(this, "Settings...", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Select any one");
        menu.addSubMenu("Login");
        menu.addSubMenu("Logout");
        menu.setHeaderIcon(R.drawable.select);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle().equals("Login")){
            Toast.makeText(this, "Login clicked", Toast.LENGTH_SHORT).show();
        }
        if(item.getTitle().equals("Logout")){
            Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
