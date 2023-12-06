package com.example.globegatherer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.common.util.concurrent.ListenableFuture;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.widget.TextView;

public class camera extends AppCompatActivity {

    private static final String TAG = camera.class.getSimpleName();
    private static final String URL = "http://coms-309-013.class.las.iastate.edu:8080/GoogleImages/{SignUpName}";


    private ProgressDialog pDialog;

    private TextView responses;


    private static final int REQUEST_CODE_PERMISSIONS = 10;
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.CAMERA};

    private ImageCapture imageCapture;
    private Executor executor = Executors.newSingleThreadExecutor();
    private ImageView capturedImageView; // New ImageView variable
    private String lastCapturedImagePath; // Store the path of the last captured image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Initialize TextureView
        PreviewView previewView = findViewById(R.id.previewTextureView);
        responses = findViewById(R.id.responsesTextView);


        capturedImageView = findViewById(R.id.capturedImageView); // Initialize ImageView

        if (allPermissionsGranted()) {
            startCamera(previewView);
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

        Button captureButton = findViewById(R.id.captureButton);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        Button uploadButton = findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the captured image as base64
                Bitmap capturedBitmap = BitmapFactory.decodeFile(lastCapturedImagePath);
                String base64Image = bitmapToBase64(capturedBitmap);

                // Upload the base64 image
                uploadImage(base64Image);
            }
        });
    }

    private void startCamera(PreviewView previewView) {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraUseCases(cameraProvider, previewView);
            } catch (ExecutionException | InterruptedException e) {
                // Handle any errors
                Log.e("CameraX", "Error starting camera: " + e.getMessage());
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private void bindCameraUseCases(@NonNull ProcessCameraProvider cameraProvider, PreviewView previewView) {
        Preview preview = new Preview.Builder().build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetResolution(new Size(1280, 720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(executor, new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(@NonNull ImageProxy image) {
                // You can perform image analysis here if needed
                image.close();
            }
        });

        imageCapture = new ImageCapture.Builder().build();

        Camera camera = cameraProvider.bindToLifecycle(
                this, cameraSelector, preview, imageAnalysis, imageCapture);

        // Set the surface provider for the preview
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private File getOutputDirectory() {
        File mediaDir = new File(getExternalMediaDirs()[0], "GlobeGatherer");
        if (!mediaDir.exists() && !mediaDir.mkdirs()) {
            Log.e("CameraX", "Failed to create directory");
        }
        return mediaDir;
    }

    private void takePhoto() {
        File photoFile = createFile(getOutputDirectory(), "GlobeGatherer", ".jpg");

        ImageCapture.OutputFileOptions outputOptions =
                new ImageCapture.OutputFileOptions.Builder(photoFile).build();

        imageCapture.takePicture(outputOptions, executor, new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                // Image saved successfully
                Uri savedUri = Uri.fromFile(photoFile);
                displayCapturedImage(savedUri); // Display the captured image
                lastCapturedImagePath = photoFile.getAbsolutePath(); // Store the path
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                // Image capture failed, log the error
                Log.e("CameraX", "Error capturing image: " + exception.getMessage());
            }
        });
    }

    private File createFile(File baseFolder, String format, String extension) {
        if (!baseFolder.exists() && !baseFolder.mkdirs()) {
            Log.e("CameraX", "Failed to create directory");
            return null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String fileName = format + "_" + timeStamp + extension;
        return new File(baseFolder, fileName);
    }

    private void displayCapturedImage(Uri imageUri) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Load the captured image into ImageView
                Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath());
                capturedImageView.setImageBitmap(bitmap);
                capturedImageView.setVisibility(View.VISIBLE);
            }
        });
    }

    private String bitmapToBase64(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } else {
            Log.e(TAG, "Bitmap is null");
            return "";
        }
    }



    private void uploadImage(String base64Image) {
        try {
            // Create JSON object with the base64-encoded image
            JSONObject params = new JSONObject();
            params.put("params", base64Image); // Use "params" instead of "image"

            // Call the post request method with the JSON object
            postRequest(params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // Your post request method
    // Your post request method
    private void postRequest(JSONObject params) {
        if (!isFinishing() && !isDestroyed()) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();
        }

        String Iusername = SharedPrefsUtil.getUsername(this);
        String url = URL.replace("{SignUpName}", Iusername);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                responses.setText(response.toString());
                dismissProgressDialog(); // Dismiss the dialog when the response is received
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                responses.setText("Error: " + error.getMessage());
                dismissProgressDialog(); // Dismiss the dialog in case of an error
            }
        }) {
            {
                setShouldCache(false); // Disable caching for debugging
                VolleyLog.DEBUG = true;
            }
        };

        // Adding the request to the request queue
        Volley.newRequestQueue(this).add(jsonObjReq);
    }

    // Method to dismiss the ProgressDialog safely
    private void dismissProgressDialog() {
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

}
