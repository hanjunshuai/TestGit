package hjs.shougang.com.fragment_add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class StartFragment extends Fragment implements View.OnClickListener {
    private View view;
    Button btn1;
    Button btn2;
    Button btn3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.start_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MainActivity ma = (MainActivity) getActivity();
        switch (v.getId()) {
            case R.id.btn1:
                ma.switch21();
                break;
            case R.id.btn2:
                ma.switch22();
                break;
            case R.id.btn3:
                ma.switch23();
                break;
        }
    }
}
