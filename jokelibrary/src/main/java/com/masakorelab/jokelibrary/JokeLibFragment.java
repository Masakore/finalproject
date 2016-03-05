package com.masakorelab.jokelibrary;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeLibFragment extends Fragment {

  public JokeLibFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    
    View rootView = inflater.inflate(R.layout.fragment_jokelib, container, false);

    Intent intent = getActivity().getIntent();
    String jokeFromAndroidLibrary = intent.getStringExtra(JokeLibActivity.JOKE_KEY);
    TextView jokeTextView = (TextView) rootView.findViewById(R.id.intent_joke_textview);

    if (jokeFromAndroidLibrary != null && jokeFromAndroidLibrary.length() !=0) {
      jokeTextView.setText(jokeFromAndroidLibrary);
    }

    return rootView;
  }
}
