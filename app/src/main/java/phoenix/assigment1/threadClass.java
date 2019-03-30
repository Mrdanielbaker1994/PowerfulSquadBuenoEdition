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
        changePicture();

    }

        @Override
        public  void run() {

            boolean onStart = true;
            if(onStart == true)
            {

            }

            for (int i = 0; i <= 10; i++) {
                final int value = i;
                try {
                    Log.d("MainActivity", "Thread babyyyyyy");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


               /* ImageView imageView = findViewById(R.id.image_viewID);
                String url = "https://picsum.photos/200";
                Picasso.with(this).load(url).into(imageView);*/

            }
        }


        public void changePicture()
        {
            Log.d("New", "Called");

            try
            {
                Log.d("Success", "Success");

                ImageView imageView = (ImageView)((MainActivity)context).findViewById(R.id.image_viewID);
                String url = "https://picsum.photos/200";
                Picasso.with(context).load(url).into(imageView);
            }
            catch (Exception e)
            {
                Log.d("New", "For this reason:" + e.toString());
            }
        }




    }

