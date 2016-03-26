package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.masakorelab.jokeapplication.backend.myApi.MyApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.JokeTelling;
import com.masakorelab.jokelibrary.JokeLibActivity;
import com.udacity.gradle.builditbigger.R;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Pair<Context,String>, Void, String> {
  private static MyApi myApiService = null;
  private Context context;

  @Override
  protected String doInBackground(Pair<Context, String>... params) {
    if(myApiService==null) {
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).setRootUrl("https://builditbigger-1244.appspot.com/_ah/api/");
      myApiService = builder.build();
    }

    context = params[0].first;
    String name = params[0].second;

    try {
      return myApiService.sayHi(name).execute().getData();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onPostExecute(String result) {
    Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
  }
}

public class PaidActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_paid);
    new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_paid, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void tellJoke(View view){
    String joke = new JokeTelling().getJoke();
    //Step1
    Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();

    //Step2
    Intent jokeIntent = new Intent(this, JokeLibActivity.class);
    jokeIntent.putExtra(JokeLibActivity.JOKE_KEY, joke);
    startActivity(jokeIntent);
  }
}
