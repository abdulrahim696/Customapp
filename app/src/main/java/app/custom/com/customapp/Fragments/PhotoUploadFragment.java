package app.custom.com.customapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

import app.custom.com.customapp.Activities.MultipleSelectImageActivity;
import app.custom.com.customapp.R;

public class PhotoUploadFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    private int MAGIC_NUMBER_GALLERY = 1001;
    private int MAGIC_NUMBER_CAMERA = 1002;

    private ImageView mImage;

    public PhotoUploadFragment() {
        // Required empty public constructor
    }


    public static PhotoUploadFragment newInstance(String param1, String param2) {
        PhotoUploadFragment fragment = new PhotoUploadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo_upload, container, false);
        showChooserDialog(view);
        return view;
    }

    private void showChooserDialog(View view) {
        mImage = (ImageView) view.findViewById(R.id.image);
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        final CharSequence[] items = {"Choose from Gallery", "Take Picture"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Image")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        if (position == 0) {
                            //launchGallery();
                            launchMultiplePhotos();
                        } else {
                            launchCamera();
                        }
                    }
                });
        builder.show();
    }

    private void launchMultiplePhotos() {

            Intent intent = new Intent(getActivity(), MultipleSelectImageActivity.class);
        getActivity().startActivity(intent);
       /* Intent intent = new Intent(getActivity(),Main2Activity.class);
        intent.putExtra("Hello","Nisar");
        getActivity().startActivity(intent);*/

    }

    /**
     *
     */
    private void launchGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, MAGIC_NUMBER_GALLERY);
        //startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE_MULTIPLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK  ) {
            if (requestCode == MAGIC_NUMBER_GALLERY) {
                    onGalleryImageResult(data);
            } else if (requestCode == MAGIC_NUMBER_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onGalleryImageResult(Intent data) {
        Bitmap photo = null;
        try {
            //http://www.theappguruz.com/blog/android-take-photo-camera-gallery-code-sample
            photo =  MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mImage.setImageBitmap(photo);
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap photo = null;
        try {
            //http://www.theappguruz.com/blog/android-take-photo-camera-gallery-code-sample
            photo =  MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mImage.setImageBitmap(photo);
    }

    /**
     *
     */
    private void launchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, MAGIC_NUMBER_CAMERA);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
