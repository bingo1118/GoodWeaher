package com.example.servicebestpractice;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/21.
 */

public class DownloadTast extends AsyncTask<String,Integer,Integer> {
    public final static int TYPE_SUCCESS=0;
    public final static int TYPE_FAILED=1;
    public final static int TYPE_PAUSE=2;
    public final static int TYPE_CANCEL=3;

    private DownloadListener listener;
    private boolean isPause=false;
    private boolean isCanceled=false;
    private int lastPragress;
    public DownloadTast(DownloadListener listener){
        this.listener=listener;
    }


    @Override
    protected Integer doInBackground(String... params) {
        InputStream is=null;
        RandomAccessFile savefile=null;
        File file=null;
        try{
            long downloadlength=0;
            String downloadurl=params[0];
            String filename=downloadurl.substring(downloadurl.lastIndexOf("/"));
            String directory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file=new File(directory+filename);
            if(file.exists()){
                downloadlength=file.length();
            }
            long contentlength=getContentlength(downloadurl);
            if(contentlength==0){
                return TYPE_FAILED;
            }else if(contentlength==downloadlength){
                return TYPE_SUCCESS;
            }
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().addHeader("RANGE","byte="+downloadlength+"-").url(downloadurl).build();
            Response response=client.newCall(request).execute();
            if(response!=null){
                is=response.body().byteStream();
                savefile=new RandomAccessFile(file,"rw");
                savefile.seek(downloadlength);
                byte[] b=new byte[1024];
                int total=0;
                int len;
                while((len=is.read(b))!=-1){
                    if(isPause){
                        return TYPE_PAUSE;
                    }else if(isCanceled){
                        return TYPE_CANCEL;
                    }else{
                        total+=len;
                        savefile.write(b,0,len);
                        int progress=(int)((total+downloadlength)*100/contentlength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(is!=null){
                    is.close();
                }
                if(savefile!=null){
                    savefile.close();
                }
                if(isCanceled&file!=null){
                    file.delete();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress=values[0];
        if(progress>lastPragress){
            listener.onProgress(progress);
            lastPragress=progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSE:
                listener.onPause();
                break;
            case TYPE_CANCEL:
                listener.onCancel();
                break;
            default:
                break;
        }
    }

    public void pauseDownload(){
        isPause=true;
    }
    public void canceldownload(){
        isCanceled=true;
    }

    private long getContentlength(String downloadurl)throws IOException {
        OkHttpClient client=new OkHttpClient();
        Request request = new Request.Builder().url(downloadurl).build();
        Response response=client.newCall(request).execute();
        if(response!=null&&response.isSuccessful()){
            long contentLength=response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
