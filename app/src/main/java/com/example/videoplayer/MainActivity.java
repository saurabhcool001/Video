package com.example.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button LoadVid;
    VideoView videoView;
    TextView videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadVid = findViewById(R.id.LoadVid);
        videoView = findViewById(R.id.videoView);
        videoPath = findViewById(R.id.videoPath);

        LoadVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Uri video = data.getData();
            Log.d("Video File Path: ", String.valueOf(video));

            try {
                videoView.setVideoURI(video);
                videoView.start();
                videoPath.setText(String.valueOf(video));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
