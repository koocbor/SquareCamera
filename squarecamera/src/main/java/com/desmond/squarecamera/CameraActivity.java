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

    public static final String EXTRA_REQ_MIN_LENGTH = "SquareCamera:MinLength";

    /**
     * Starts the CameraActivity.  Observe the response in onActivityResult with a
     * requestCode of CameraActivity.REQEUST_SQUARE_CAMERA.
     *
     * The returned image will have a height and width equal to the size of the screen's
     * smallest dimension in pixels.
     *
     * @param activity
     *
     */
    public static void startSquareCamera(Activity activity) {
        startSquareCamera(activity, null);
    }

    /**
     * Starts the CameraActivity.  Observe the response in onActivityResult with a
     * requestCode of CameraActivity.REQUEST_SQUARE_CAMERA.
     *
     * The returned image will have a height and width equal to the reqMinLength parameter.
     *
     * @param activity
     * @param reqMinLength The requested size (in pixels) of the height and width of the
     *                     returned image.
     *
     */
    public static void startSquareCamera(Activity activity, Integer reqMinLength) {
        Intent intent = new Intent(activity, CameraActivity.class);

        if (reqMinLength != null) {
            intent.putExtra(EXTRA_REQ_MIN_LENGTH, reqMinLength);
        }

        activity.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
    }

    /**
     * Starts the CameraActivity.  Observe the response in onActivityResult with a
     * requestCode of CameraActivity.REQUEST_SQUARE_CAMERA.
     *
     * The returned image will have a height and width equal to the size of the screen's
     * smallest dimension in pixels.
     *
     * @param fragment  The support fragment that is initiating the camera activity.
     *
     */
    public static void startSquareCamera(android.support.v4.app.Fragment fragment) {
        startSquareCamera(fragment, null);
    }

    /**
     * Starts the CameraActivity.  Observe the response in onActivityResult with a
     * requestCode of CameraActivity.REQUEST_SQUARE_CAMERA.  Use this method when
     * the CameraActivity is initiated from a support fragment.
     *
     * The returned image will have a height and width equal to the reqMinLength parameter.
     *
     * @param fragment      The support fragment that is initiating the camera activity.
     * @param reqMinLength  The requested size (in pixels) of the height and width of the
     *                      returned image.
     *
     */
    public static void startSquareCamera(android.support.v4.app.Fragment fragment, Integer reqMinLength) {

        Intent intent = new Intent(fragment.getActivity(), CameraActivity.class);

        if (reqMinLength != null) {
            intent.putExtra(EXTRA_REQ_MIN_LENGTH, reqMinLength);
        }

        fragment.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
    }

    /**
     * Starts the CameraActivity. Observe the response in onActivityResult with a
     * requestCode of CameraActivity.REQUEST_SQUARE_CAMERA.
     *
     * The returned image will have a height and width equal to the size of the screen's
     * smallest dimension in pixels.
     *
     * @param fragment  The fragment that is initiating the camera activity.
     */
    public static void startSquareCamera(android.app.Fragment fragment) {
        startSquareCamera(fragment, null);
    }

    /**
     * Starts the CameraActivity.  Observe the response in onActivityResult with a
     * requestCode of CameraActivity.REQUEST_SQUARE_CAMERA.
     *
     * The returned image will have a height and width equal to the reqMinLength parameter.
     *
     * @param fragment      The fragment that is initiating the camera activity.
     * @param reqMinLength  The requested size (in pixels) of the height and width of the returned image.
     *
     */
    public static void startSquareCamera(android.app.Fragment fragment, Integer reqMinLength) {
        Intent intent = new Intent(fragment.getActivity(), CameraActivity.class);

        if (reqMinLength != null) {
            intent.putExtra(EXTRA_REQ_MIN_LENGTH, reqMinLength);
        }

        fragment.startActivityForResult(intent, REQUEST_SQUARE_CAMERA);
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
