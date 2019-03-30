package phoenix.assigment1;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class threadClass extends MainActivity implements Runnable {

    public Context context;
    public threadClass(Context context)
    {
        this.context = context;

    }

        @Override
        public  void run() {


            for (int i = 0; i <= 1000; i++) {
                final int value = i;
                try {
                    Log.d("ThreadTest", "Thread babyyyyyy");
                    Thread.sleep(1000);
                    changePicture();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d("ThreadTest", "For this reason:" + e.toString());

                }

               /* ImageView imageView = findViewById(R.id.image_viewID);
                String url = "https://picsum.photos/200";
                Picasso.with(this).load(url).into(imageView);*/

            }
        }


        public void changePicture()
        {
            Log.d("ThreadTest", "Called");

            try
            {
                Log.d("ThreadTest", "Success");

                ImageView imageView = (ImageView)((MainActivity)context).findViewById(R.id.image_viewID);
                String url = "https://loremflickr.com/200/200/bikini,girls,brazil/all";
                Picasso.with(context).load(url).into(imageView);
            }
            catch (Exception e)
            {
                Log.d("ThreadTest", "For this reason:" + e.toString());
            }
        }




    }

