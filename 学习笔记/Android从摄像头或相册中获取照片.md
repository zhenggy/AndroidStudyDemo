##Android从摄像头或相册中获取照片


###关键代码：

		/**
	     * 从相册中获取，返回结果会在onActivityResult()中
	     */
	    private void selectPicFromAlbum() {
	        Intent intent = new Intent();
	        intent.setAction(Intent.ACTION_GET_CONTENT);
	        intent.setType("image/*");
	        startActivityForResult(intent, RESULT_FROM_ALBUM);
	    }
	
	    /**
	     * 从摄像头中获取，返回结果会在onActivityResult()中
	     */
	    private void selectPicFromCamera() {
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        startActivityForResult(intent, RESULT_FROM_CAMERA);
	    }

---

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (null == data) return;
        switch (requestCode) {
            case RESULT_FROM_ALBUM:
                Uri imageUri = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case RESULT_FROM_CAMERA:
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
                break;
        }

    }

---

	

###具体代码：

	package com.ashzheng.studydemo.demo7;
	
	import android.app.Activity;
	import android.content.Intent;
	import android.graphics.Bitmap;
	import android.net.Uri;
	import android.os.Bundle;
	import android.provider.MediaStore;
	import android.view.View;
	import android.widget.ImageView;
	import android.widget.TextView;
	
	import com.ashzheng.studydemo.R;
	
	import java.io.IOException;
	
	public class Demo7Activity extends Activity {
	
	
	    private ImageView imageView;
	    private TextView textView;
	
	    public static final int RESULT_FROM_ALBUM = 1;
	    public static final int RESULT_FROM_CAMERA = 2;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_demo7);
	
	        imageView = (ImageView) findViewById(R.id.demo7_iv);
	        textView = (TextView) findViewById(R.id.demo7_tv);
	
	        findViewById(R.id.demo7_bt1).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                selectPicFromAlbum();
	            }
	        });
	
	        findViewById(R.id.demo7_bt2).setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	                selectPicFromCamera();
	            }
	        });
	    }
	
	    /**
	     * 从相册中获取，返回结果会在onActivityResult()中
	     */
	    private void selectPicFromAlbum() {
	        Intent intent = new Intent();
	        intent.setAction(Intent.ACTION_GET_CONTENT);
	        intent.setType("image/*");
	        startActivityForResult(intent, RESULT_FROM_ALBUM);
	    }
	
	    /**
	     * 从摄像头中获取，返回结果会在onActivityResult()中
	     */
	    private void selectPicFromCamera() {
	        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        startActivityForResult(intent, RESULT_FROM_CAMERA);
	    }
	
	
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	
	        if (null == data) return;
	        switch (requestCode) {
	            case RESULT_FROM_ALBUM:
	                Uri imageUri = data.getData();
	
	                try {
	                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
	                    imageView.setImageBitmap(bitmap);
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	
	                break;
	
	            case RESULT_FROM_CAMERA:
	                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
	                imageView.setImageBitmap(bitmap);
	                break;
	        }
	
	    }
	}
