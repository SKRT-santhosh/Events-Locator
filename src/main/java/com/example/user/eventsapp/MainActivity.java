package com.example.user.eventsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yupptv.mobile.widget.HListView;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.TRANSPARENT;
import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String itemcity;
    ImageButton plays,movies,music,magic;
    String cityname;
    LinearLayout ll;
    int a;
    int images=categoryImages[0];
    public static int[] categoryImages={R.drawable.eventplays,R.drawable.eventmovies,R.drawable.eventevents,R.drawable.eventmusic};
    String category="plays";
    double lat=17.64358;
    double lon=78.974885;
    Event[] sampleEvents;
    Object[] eventarray;
    String[] dates = {"Today","Tomorrow","26-02-2016","27-02-2016","28-02-2016","29-02-2016","01-03-2016"};  // these can be later retrieved for an database
    CustomAdapter[] adapter ;
    Spinner spinner;
    int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.top_icon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
            if(savedInstanceState!=null){
                selected = savedInstanceState.getInt("oldversion");
                System.out.println("Selected: "+ selected);
                inflation();
                ll = (LinearLayout) findViewById(R.id.containerLayout);
                setSpiner();
                buttonList();
                listViewCreation(cityname, category);
                if(selected==1){
                    movies.setImageResource(R.drawable.movies_pressed);
                    category="movies";
                }
                else if(selected==2){
                    music.setImageResource(R.drawable.music_pressed);
                    category="music";
                }
                else if(selected==3){
                    plays.setImageResource(R.drawable.plays_pressed);
                    category="plays";
                }
                else if(selected==4){
                    magic.setImageResource(R.drawable.events_pressed);
                    category="events";
                }
                else{
                    //do nothing
                }
                System.out.println("category: "+ category);
            }
            else {
              inflation();
                ll = (LinearLayout) findViewById(R.id.containerLayout);
                setSpiner();
                spinner.setOnItemSelectedListener(this);
                buttonList();
                listViewCreation(cityname, category);
            }
        }
    public void inflation(){
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("oldversion",selected);

    }
    public void buttonList(){

        movies = (ImageButton) findViewById(R.id.movies);
        plays = (ImageButton) findViewById(R.id.plays);
        music = (ImageButton) findViewById(R.id.music);
        magic = (ImageButton) findViewById(R.id.events);
        movies.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        category = "movies";
                        movies.setImageResource(R.drawable.movies_pressed);
                        movies.setBackgroundColor(TRANSPARENT);
                        music.setImageResource(R.drawable.music);
                        plays.setImageResource(R.drawable.plays);
                        magic.setImageResource(R.drawable.events);
                        images = categoryImages[1];
                        selected=1;
                        notifyEventAdapters(category, images);
                        }
                    });

            music.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category = "music";
                    music.setBackgroundColor(TRANSPARENT);
                    music.setImageResource(R.drawable.music_pressed);
                    movies.setImageResource(R.drawable.movies);
                    plays.setImageResource(R.drawable.plays);
                    magic.setImageResource(R.drawable.events);
                    images = categoryImages[3];
                    selected=2;
                    notifyEventAdapters(category, images);
                }
            });
            plays.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category = "plays";
                    images = categoryImages[0];
                    plays.setImageResource(R.drawable.plays_pressed);
                    plays.setBackgroundColor(TRANSPARENT);
                    movies.setImageResource(R.drawable.movies);
                    music.setImageResource(R.drawable.music);
                    magic.setImageResource(R.drawable.events);
                    selected=3;
                    notifyEventAdapters(category, images);

                }
            });

            magic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    category = "events";
                    magic.setBackgroundColor(TRANSPARENT);
                    magic.setImageResource(R.drawable.events_pressed);
                    movies.setImageResource(R.drawable.movies);
                    plays.setImageResource(R.drawable.plays);
                    music.setImageResource(R.drawable.music);
                    images = categoryImages[2];
                    selected=4;
                    notifyEventAdapters(category, images);
                }
            });
        }


    // to create the list view
    public void listViewCreation(String sample, String cate){
        String[] values = new String[dates.length];
        eventarray = new Object[dates.length];
        adapter = new CustomAdapter[7];
        for(int i=0;i<dates.length;i++){
            HListView lv = new HListView(this);
            lv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            TextView tv = new TextView(this);
            tv.setText(dates[i]);
            tv.setHintTextColor(rgb(237, 237, 237));

            createEventDataForACityForADayAndCity(dates[i], sample, cate);
            ll.addView(tv);
            values[i] = ""+i;
            adapter[i] = new CustomAdapter(this,itemcity,sampleEvents);
            eventarray[i]=sampleEvents;
            lv.setAdapter(adapter[i]);

            ll.addView(lv);// reload the items from database
            lv.setOnItemClickListener(new com.yupptv.mobile.widget.AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(com.yupptv.mobile.widget.AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "i think u have selected something" + sampleEvents, Toast.LENGTH_LONG).show();
                    Intent c=new Intent(MainActivity.this,LocationActivity.class);
                    c.putExtra("event", sampleEvents[position]);
                    startActivity(c);
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    // to se the spinner class
    private void setSpiner(){
        spinner = (Spinner) findViewById(R.id.spinner);
        //adding the event when clicked

        //adding the elements in to the spinner
        List<String> categories = new ArrayList<String>();
        categories.add("Bombay");
        categories.add("Delhi");
        categories.add("Chennai");
        categories.add("Hyderabad");
        categories.add("Banglore");
        categories.add("Kochi");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        System.out.println(item);


        if(item=="Hyderabad"){
            cityname="Hyderabad";
        }
        else if(item=="Bombay"){
            cityname="Bombay";
        }
        else if(item=="Delhi"){
            cityname="Delhi";
        }
        else if(item == "Chennai") {
            cityname="Chennai";
        }
        else if(item=="Banglore"){
            cityname="Banglore";
        }
        else {
            cityname="Kochi";
        }
//        ll.removeAllViewsInLayout();
//        listViewCreation(cityname,category);

        notifyEventAdapters(category, images);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
    // this menthod creates the dummydata to pass to event and then collected by the adapater
    private void createEventDataForACityForADayAndCity(String date,String cityname, String category){
        sampleEvents = new Event[10];
        for(int i=0;i<10;i++){
            double lats=lat+i;
            double lons=lon+i;
            Event event = new Event();
            event.setEventName("event" + "@" + date + "@" + cityname);
            event.setImage(images);
            event.setCategory(category);
            event.setLongtitudes(lons);
            event.setLatitudes(lats);
            sampleEvents[i] = event;

        }


    }

    private void notifyEventAdapters(String category,int finalImage){
    changeCategory(category,finalImage);
    for(int i =0;i<dates.length;i++){
        adapter[i].notifyDataSetChanged();
        }
    }

    private  void changeCategory(String cateory,int image){
    for(int i=0;i<dates.length;i++){
        Event [] ev = (Event[])eventarray[i];
        for(int j=0;j<10;j++){
            ev[j].setCategory(cateory);
            ev[j].setImage(image);
            ev[j].setEventName("event" + "@" + dates[i] + "@" + cityname);
        }

    }

    }

}
