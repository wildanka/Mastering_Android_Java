package com.wildanka.df_vp_f;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.wildanka.df_vp_f.interactor.IDialogFragmentToVPFragment;

public class DialogExample extends DialogFragment {
    private static final String TAG = "DialogExample";
    public IDialogFragmentToVPFragment iDialogFragmentToVPFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment,container,false);
        Button btnTrigger = rootView.findViewById(R.id.btn_save_change_config_dialog);

        btnTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDialogFragmentToVPFragment.updateSecondaryQty(true);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.e("Width", "" + width);
        Log.e("height", "" + height);

        Window window = getDialog().getWindow();
        window.setLayout(width, (int) (height * 0.65));
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            iDialogFragmentToVPFragment = (IDialogFragmentToVPFragment) getActivity();
        }catch(ClassCastException e){
            Log.e(TAG,"onAttach: ClassCastException: "+e.getMessage());
        }
    }
}
