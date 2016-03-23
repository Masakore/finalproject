package com.udacity.gradle.builditbigger;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
  private MainActivity mActivity;
  CountDownLatch signal;
  String mResult;

  public MainActivityTest() {
    super(MainActivity.class);
  }

  @Before
  public void setUp() throws Exception {
    super.setUp();

    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    mActivity = getActivity();

//    setActivityInitialTouchMode(false);
    signal = new CountDownLatch(1);
  }

  @Test
  public void Testdesu() throws InterruptedException{
    MockEndpointAsyncTask task = new MockEndpointAsyncTask();
    task.execute(new Pair<Context, String>(mActivity, "Manfred"));
    signal.await();
    assertEquals(mResult, "Hi, Manfred");
  }

  class MockEndpointAsyncTask extends EndpointsAsyncTask{

    @Override
    protected void onPostExecute(String result) {
      super.onPostExecute(result);
      mResult = result;
      signal.countDown();
    }
  }
}