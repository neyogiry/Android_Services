package neyogiry.app.android_services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyService extends Service {

    private MyTask task;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Servicio creado!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        task = new MyTask();
        task.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio destruído!", Toast.LENGTH_SHORT).show();
        task.cancel(true);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class MyTask extends AsyncTask<String, String, String>{

        private Calendar calendar;
        private SimpleDateFormat simpleDateFormat;
        private String time;
        private boolean state;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            state = true;
        }

        @Override
        protected String doInBackground(String... params) {
            while(state){
                calendar = Calendar.getInstance();
                time = simpleDateFormat.format(calendar.getTime());
                try{
                    //Stop 5 seconds
                    publishProgress(time);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(), "Hora actual -> " + values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            state = false;
        }
    }

}
