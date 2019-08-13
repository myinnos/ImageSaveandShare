package in.myinnos.savebitmapandshare;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import in.myinnos.savebitmapandsharelib.SaveAndShare;

public class MainActivity extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Picasso.get()
                .load("https://www.tollywood.net/wp-content/uploads/2018/08/Surprise-Trisha-wish-come-true.jpg")
                .into(imageView);
    }

    public void saveBitmap(View view) {

        // getting bitmap from
        imageView.buildDrawingCache();
        Bitmap image = imageView.getDrawingCache();

        // implementing save and share function
        SaveAndShare.save(this,
                image, // bitmap image
                System.currentTimeMillis() + "_" + "fansfolio", // name for image
                "Awesome!" // title for bottom popup
                , null // message for bottom popup
        );
    }
}
