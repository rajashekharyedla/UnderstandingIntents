package com.example.understandingintents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by sashank on 10/09/17.
 */

public class ImagePickAcitvity extends Activity {
    private static final int REQUEST_CODE = 1;
    private Bitmap bitmap;
    private ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pick);
        imageView = (ImageView) findViewById(R.id.result);
    }

    public void pickImage(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        InputStream stream = null;
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            try{
                //recycle used bitmaps
                if(bitmap != null){
                    bitmap.recycle();
                }
                Uri imageUri = data.getData();
                System.out.println(imageUri.getPath());
                stream = getContentResolver().openInputStream(imageUri);
                bitmap = BitmapFactory.decodeStream(stream);
                System.out.println(bitmap.getByteCount());
                imageView.setImageBitmap(bitmap);

            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(stream != null){
                    try{
                        stream.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
