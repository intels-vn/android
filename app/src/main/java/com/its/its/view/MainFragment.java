package com.its.its.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.its.its.R;

import io.vov.vitamio.LibsChecker;

public class MainFragment extends Fragment {

    private io.vov.vitamio.widget.VideoView mVideoView;
    Button btnChannel1, btnChannel2, btnChannel3, btnChannel4, btnChannel5;
    Button btn100, btn200, btn300, btn400, btn500, btn600, btn700, btn800;
    Button btnBetNow;
    private ProgressDialog prodlg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!LibsChecker.checkVitamioLibs(getActivity()))
//            return;
//        getActivity().setContentView(R.layout.fragment_main);
//        mVideoView.setVideoPath("rtmp://192.168.100.29:1935/oflaDemo/stream1");
//        mVideoView.setMediaController(new io.vov.vitamio.widget.MediaController(getActivity()));
//        mVideoView.requestFocus();
//        mVideoView.start();
//
//        mVideoView.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(io.vov.vitamio.MediaPlayer mediaPlayer) {
//                mediaPlayer.setPlaybackSpeed(1.0f);
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mVideoView = (io.vov.vitamio.widget.VideoView) view.findViewById(R.id.videoView);

        btnChannel1 = (Button) view.findViewById(R.id.btnChannel1);
        btnChannel2 = (Button) view.findViewById(R.id.btnChannel2);
        btnChannel3 = (Button) view.findViewById(R.id.btnChannel3);
        btnChannel4 = (Button) view.findViewById(R.id.btnChannel4);
        btnChannel5 = (Button) view.findViewById(R.id.btnChannel5);

        btn100 = (Button) view.findViewById(R.id.btn100);
        btn200 = (Button) view.findViewById(R.id.btn200);
        btn300 = (Button) view.findViewById(R.id.btn300);
        btn400 = (Button) view.findViewById(R.id.btn400);
        btn500 = (Button) view.findViewById(R.id.btn500);
        btn600 = (Button) view.findViewById(R.id.btn600);
        btn700 = (Button) view.findViewById(R.id.btn700);
        btn800 = (Button) view.findViewById(R.id.btn800);

        btnBetNow = (Button) view.findViewById(R.id.btnBetNow);

        addEvent();

        return view;
    }

    private void addEvent() {
        btnChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodlg=new ProgressDialog(getActivity());
                prodlg.setMessage("Waiting...");
                prodlg.show();
                runRtmp("rtmp://192.168.100.29:1935/oflaDemo/stream1", getActivity());
            }
        });
        btnChannel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodlg=new ProgressDialog(getActivity());
                prodlg.setMessage("Waiting...");
                prodlg.show();
                runRtmp("rtmp://192.168.100.29:1935/oflaDemo/stream2", getActivity());
            }
        });
        btnChannel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodlg=new ProgressDialog(getActivity());
                prodlg.setMessage("Waiting...");
                prodlg.show();
                runRtmp("rtmp://192.168.100.29:1935/oflaDemo/stream3", getActivity());
            }
        });
        btnChannel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodlg=new ProgressDialog(getActivity());
                prodlg.setMessage("Waiting...");
                prodlg.show();
                runRtmp("rtmp://192.168.100.29:1935/oflaDemo/stream5", getActivity());
            }
        });
    }

    public void runRtmp(String rtmpUrl, Activity activity){
        if (!LibsChecker.checkVitamioLibs(activity))
            return;

        mVideoView.setVideoPath(rtmpUrl);
        mVideoView.setMediaController(new io.vov.vitamio.widget.MediaController(activity));
        mVideoView.setVideoQuality(io.vov.vitamio.MediaPlayer.VIDEOQUALITY_HIGH);
        mVideoView.setBufferSize(2048);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(io.vov.vitamio.MediaPlayer mediaPlayer) {
                prodlg.dismiss();
                mediaPlayer.setPlaybackSpeed(1.0f);
                mediaPlayer.setVolume(0f, 0f);
                mVideoView.start();
            }
        });
    }
}
