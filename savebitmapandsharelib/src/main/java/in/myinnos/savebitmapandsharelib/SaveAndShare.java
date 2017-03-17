package in.myinnos.savebitmapandsharelib;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import in.myinnos.savebitmapandsharelib.helper.BottomDialog;

/**
 * Created by 10 on 03-03-2017.
 */

public class SaveAndShare {

    public static void save(Activity activity, Bitmap bmImg, String imageName,
                            String shareTitle, String shareMessage) {

        if (checkPermissionForExternalStorage(activity)) {
            File filename;
            try {
                String path1 = android.os.Environment.getExternalStorageDirectory()
                        .toString();
                //Log.i("in save()", "after mkdir");
                File file = new File(path1 + "/" + activity.getString(R.string.app_name));
                if (!file.exists())
                    file.mkdirs();

                String DEFAULT_IMAGE_NAME = imageName;
                if (imageName == null) {
                    DEFAULT_IMAGE_NAME = String.valueOf(System.currentTimeMillis()) + "_" + "fansfolio";
                }

                filename = new File(file.getAbsolutePath() + "/" + DEFAULT_IMAGE_NAME
                        + ".jpg");
                //Log.i("in save()", "after file");
                FileOutputStream out = new FileOutputStream(filename);
                //Log.i("in save()", "after outputstream");
                bmImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
                //Log.i("in save()", "after outputstream closed");
                //File parent = filename.getParentFile();
                ContentValues image = getImageContent(filename, DEFAULT_IMAGE_NAME, activity);
                Uri result = activity.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, image);

                String SHARE_TITLE = shareTitle;
                String SHARE_MESSAGE = shareMessage;
                if (shareMessage == null) {
                    SHARE_MESSAGE = "Image is saved in " + filename;
                }
                if (shareTitle == null) {
                    SHARE_TITLE = "Awesome!";
                }
                // open share options
                openShareOptions(SHARE_TITLE, SHARE_MESSAGE, result, activity);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            requestPermissionForExternalStorage(activity);
        }
    }

    public static ContentValues getImageContent(File parent, String imageName, Activity activity) {
        ContentValues image = new ContentValues();
        image.put(MediaStore.Images.Media.TITLE, activity.getString(R.string.app_name));
        image.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
        image.put(MediaStore.Images.Media.DESCRIPTION, "Fb Page Galley Image - MyInnos.in | by fansfolio");
        image.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        image.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
        image.put(MediaStore.Images.Media.ORIENTATION, 0);
        image.put(MediaStore.Images.ImageColumns.BUCKET_ID, parent.toString()
                .toLowerCase().hashCode());
        image.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, parent.getName()
                .toLowerCase());
        image.put(MediaStore.Images.Media.SIZE, parent.length());
        image.put(MediaStore.Images.Media.DATA, parent.getAbsolutePath());
        return image;
    }

    public static void openShareOptions(String title, String description,
                                        final Uri imageUrl, final Activity activity) {
        new BottomDialog.Builder(activity)
                .setTitle(title)
                .setContent(description)
                .setPositiveText("SHARE")
                .setPositiveBackgroundColorResource(R.color.colorPrimary)
                .setPositiveTextColorResource(android.R.color.white)
                .setNegativeText("OPEN")
                .setNegativeTextColorResource(R.color.colorPrimaryDark)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        // share image globally
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("image/jpeg");
                        share.putExtra(Intent.EXTRA_STREAM, imageUrl);
                        activity.startActivity(Intent.createChooser(share, "Choose app to share"));
                        dialog.dismiss();
                    }
                }).onNegative(new BottomDialog.ButtonCallback() {
            @Override
            public void onClick(BottomDialog dialog) {
                // opining default gallery app
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(imageUrl, "image/*");
                activity.startActivity(intent);
                dialog.dismiss();
            }
        }).show();
    }

    public static void requestPermissionForExternalStorage(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(activity,
                    "External Storage permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    2000);
        }
    }

    public static boolean checkPermissionForExternalStorage(Activity activity){
        if(ActivityCompat.checkSelfPermission((Activity) activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        }else {
            return true;
        }
    }
}