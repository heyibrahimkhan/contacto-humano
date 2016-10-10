package contacto.humano.com.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by Ibrahim Ali Khan on 10/10/2016.
 */

public class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    private int data = 0;
    String imageUrl;
    private downloadCompleteCallBack dc;

    public BitmapWorkerTask(ImageView imageView, String Url, downloadCompleteCallBack Dc) {
        // Use a WeakReference to ensure the ImageView can be garbage collected
        imageViewReference = new WeakReference<ImageView>(imageView);
        imageUrl = Url;
        dc = Dc;
    }

    // Decode image in background.
    @Override
    protected Bitmap doInBackground(Integer... params) {
//        data = params[0];
//        return decodeSampledBitmapFromResource(getResources(), data, 100, 100));
        return getBitmap(imageUrl);
    }

    // Once complete, see if ImageView is still around and set bitmap.
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
                dc.onDownComp(true);
            }
        }
    }

    public static Bitmap getBitmap(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Bitmap d = BitmapFactory.decodeStream(is);
            is.close();
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public interface downloadCompleteCallBack{
        public void onDownComp(boolean done);
    }

}
