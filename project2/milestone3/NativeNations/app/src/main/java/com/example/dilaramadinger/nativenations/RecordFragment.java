package com.example.dilaramadinger.nativenations;

// Source: https://www.tutorialspoint.com/android/android_audio_capture.htm

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio,
            buttonStopPlayingRecording ;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    Random random;
    Date myDate = new Date();
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;

    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if (view != null){
            getActivity().setTitle("Record and Preserve");

            buttonStart = (Button) view.findViewById(R.id.recordButton);
            buttonStop = (Button) view.findViewById(R.id.stopRecordButton);
            buttonPlayLastRecordAudio = (Button) view.findViewById(R.id.playButton);
            buttonStopPlayingRecording = (Button) view.findViewById(R.id.stopPlayButton);

            buttonStop.setEnabled(false);
            buttonPlayLastRecordAudio.setEnabled(false);
            buttonStopPlayingRecording.setEnabled(false);

            random = new Random();

            buttonStart.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {

                    if(checkPermission()) {
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "NativeNations_";
                        AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "NativeNations_" + getDate() + ".3gp";
                        Log.d("storage", AudioSavePathInDevice);

                        MediaRecorderReady();

                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IllegalStateException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch(Exception e) {
                            Log.e("record", "general exception in record");
                        }

                        buttonStart.setEnabled(false);
                        buttonStop.setEnabled(true);

                        Toast.makeText(getActivity(), "Recording started",
                                Toast.LENGTH_LONG).show();
                    } else {
                        requestPermission();
                    }

                }
            });

            buttonStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mediaRecorder.stop();
                    buttonStop.setEnabled(false);
                    buttonPlayLastRecordAudio.setEnabled(true);
                    buttonStart.setEnabled(true);
                    buttonStopPlayingRecording.setEnabled(false);

                    Toast.makeText(getActivity(), "Recording Completed",
                            Toast.LENGTH_LONG).show();
                }
            });

            buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException {

                    buttonStop.setEnabled(false);
                    buttonStart.setEnabled(false);
                    buttonStopPlayingRecording.setEnabled(true);

                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(AudioSavePathInDevice);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(getActivity(), "Recording Playing",
                            Toast.LENGTH_LONG).show();
                }
            });

            buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonStop.setEnabled(false);
                    buttonStart.setEnabled(true);
                    buttonStopPlayingRecording.setEnabled(false);
                    buttonPlayLastRecordAudio.setEnabled(true);

                    if(mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        MediaRecorderReady();
                    }
                }
            });
        }
    }

    public void MediaRecorderReady(){
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    public String getDate(){
//        StringBuilder stringBuilder = new StringBuilder( string );
//        int i = 0 ;
//        while(i < string ) {
//            stringBuilder.append(RandomAudioFileName.
//                    charAt(random.nextInt(RandomAudioFileName.length())));
//            i++ ;
//        }
        CharSequence charDate = DateFormat.format("MM-dd-yy_hh-mm-ss", myDate.getTime());
        return charDate.toString();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }
}
