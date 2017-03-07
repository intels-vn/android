package com.its.its.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import com.its.its.R;

public class MainFragment extends Fragment {

    VideoView videoView;
    Button btnChannel1, btnChannel2, btnChannel3, btnChannel4, btnChannel5;
    Button btn100, btn200, btn300, btn400, btn500, btn600, btn700, btn800;
    Button btnBetNow;

    Button selectedButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        videoView = (VideoView) view.findViewById(R.id.videoView);

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

    }
}
