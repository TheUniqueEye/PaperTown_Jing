/*
 *  Copyright (c) 2017 - present, Xuan Wang
 *  All rights reserved.
 *
 *  This source code is licensed under the BSD-style license found in the
 *  LICENSE file in the root directory of this source tree.
 *
 */

package edu.ucsb.cs.cs190i.papertown.town.townlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import edu.ucsb.cs.cs190i.papertown.R;
import edu.ucsb.cs.cs190i.papertown.application.GeoTownListAdapter;
import edu.ucsb.cs.cs190i.papertown.application.ListTownAdapter;
import edu.ucsb.cs.cs190i.papertown.geo.GeoActivity;
import edu.ucsb.cs.cs190i.papertown.models.Town;
import edu.ucsb.cs.cs190i.papertown.splash.SplashScreenActivity;
import edu.ucsb.cs.cs190i.papertown.town.newtown.NewTownActivity;

import static edu.ucsb.cs.cs190i.papertown.application.AppConstants.CRED;
import static edu.ucsb.cs.cs190i.papertown.application.AppConstants.EMAIL;
import static edu.ucsb.cs.cs190i.papertown.application.AppConstants.PREF_NAME;
import static edu.ucsb.cs.cs190i.papertown.application.AppConstants.TOKEN;
import static edu.ucsb.cs.cs190i.papertown.application.AppConstants.TOKEN_TIME;
import static edu.ucsb.cs.cs190i.papertown.application.AppConstants.USERID;

public class TownListActivity extends AppCompatActivity {
  public List<Town> towns;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_town_list);

    //

    RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list_town);
    //mRecyclerView.setHasFixedSize(true);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
    mRecyclerView.setLayoutManager(linearLayoutManager);

    initData();

    ListTownAdapter mAdapter = new ListTownAdapter(towns);
    mRecyclerView.setAdapter(mAdapter);

    //
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("");
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {

        switch(item.getItemId()){
          case R.id.add_town:
            Intent newTownIntent = new Intent(TownListActivity.this, NewTownActivity.class);
            startActivity(newTownIntent);
            break;
          case R.id.geo_view:
            Intent geoTownIntent = new Intent(TownListActivity.this, GeoActivity.class);
            startActivity(geoTownIntent);
            break;
          case R.id.action_settings:
            SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
            editor.remove(TOKEN);
            editor.remove(TOKEN_TIME);
            editor.remove(USERID);
            editor.remove(EMAIL);
            editor.remove(CRED);
            editor.commit();
            Intent splashIntent = new Intent(TownListActivity.this, SplashScreenActivity.class);
            startActivity(splashIntent);
            finish();
            break;
        }
        return true;
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_list, menu);
    return true;
  }

  private void initData() {
    towns = new ArrayList<>();

    Town.Builder b = new Town.Builder();
    b.setTitle("Town 1");
    b.setCategory("Place");
    b.setDescription("Discription here. ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor ");
    b.setAddress("Address");
    b.setLat(0.0f);
    b.setLng(0.0f);
    b.setUserId("UserId");
    List<String> imgs = new ArrayList<>();
    imgs.add("https://s-media-cache-ak0.pinimg.com/564x/26/fe/dc/26fedc418e4d5f7235e32f59d1bc74a4.jpg");
    b.setImages(imgs);
    b.setSketch("Sketch");

    Town town_1 = b.build();
    towns.add(town_1);
    towns.add(town_1);
    towns.add(town_1);
    towns.add(town_1);
    towns.add(town_1);
  }
}
