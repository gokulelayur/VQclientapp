package com.example.vqclientapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.jetbrains.annotations.NotNull;

public class QRFragment extends Fragment {

    ImageView imageQR;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_qr,container,false);

        loadingScreen.startloading(getActivity(),"QR Generating");   //LOADING SREEN STARTED

        imageQR=view.findViewById(R.id.viewQR);
        String deptCompanyID=SaveId.getDepID(getContext())+SaveId.getId(getContext());
        MultiFormatWriter writer=new MultiFormatWriter();
        try {
            BitMatrix matrix= writer.encode(deptCompanyID, BarcodeFormat.QR_CODE,350,350);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(matrix);
            imageQR.setImageBitmap(bitmap);

            loadingScreen.stoploading();        //LOADING SCREEN STOPPED

        } catch (WriterException e) {
            e.printStackTrace();

        }
        return view;
    }
}
