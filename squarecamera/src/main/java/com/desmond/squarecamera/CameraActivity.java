package com.desmond.squarecamera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class CameraActivity extends AppCompatActivity {

    public static final String TAG = CameraActivity.class.getSimpleName();

    public static final int REQUEST_SQUARE_CAMERA = 1932;

    private static final String EXTRA_REQ_MIN_LENGTH = "SquareCamera:MinLength";

    public static void startSquareCamera(Activity activity) {
        startSquareCamera(activity, null);
    }

    public static void startSquareCamera(Activity activity, Integer reqMinLength) {
        Intent intent = new Intent(activity, CameraActivity.class);

        if (reqMinLength != null) {
            intent.putExtra(EXTRA_REQ_MIN_LENGTH, reqMinLength);
        }

        activity.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
    }

    public static void startSquareCamera(Activity activity, android.support.v4.app.Fragment fragment, Integer reqMinLength) {
        Intent intent = new Intent(activity, CameraActivity.class);

        if (reqMinLength != null) {
            intent.putExtra(EXTRA_REQ_MIN_LENGTH, reqMinLength);
        }

        if (fragment != null) {
            fragment.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
        } else {
            activity.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
        }
    }

    public static void startSquareCamera(Activity activity, android.app.Fragment fragment, Integer reqMinLength) {
        Intent intent = new Intent(activity, CameraActivity.class);

        if (reqMinLength != null) {
            intent.putExtra(EXTRA_REQ_MIN_LENGTH, reqMinLength);
        }

        if (fragment != null) {
            fragment.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
        } else {
            activity.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
        }
    }

    Integer reqMinLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.squarecamera__CameraFullScreenTheme);
        super.onCreate(savedInstanceState);

        reqMinLength = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_REQ_MIN_LENGTH)) {
            reqMinLength = extras.getInt(EXTRA_REQ_MIN_LENGTH);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.squarecamera__activity_camera);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, CameraFragment.newInstance(reqMinLength), CameraFragment.TAG)
                    .commit();
        }
    }

    public void returnPhotoUri(Uri uri) {
        Intent data = new Intent();
        data.setData(uri);

        if (getParent() == null) {
            setResult(RESULT_OK, data);
        } else {
            getParent().setResult(RESULT_OK, data);
        }

        finish();
    }

    public void onCancel(View view) {
        getSupportFragmentManager().popBackStack();
    }
}
