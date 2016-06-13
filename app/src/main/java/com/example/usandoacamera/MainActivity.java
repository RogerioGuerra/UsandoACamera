package com.example.usandoacamera;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
/*
 * Testei diversas possibilidades para setar a altura dos componentes button
 * obtendo a altura do dispositivo em pixels e deu certo, porém a
 * largura não consegui setar via codigo.
 * Só foi possivel via xml.
 
 * */



public class MainActivity extends Activity {
	
	
	int scrWidth;
	int scrHeight;
	Button btGravarSom;
	Button btVideo;
	Button btFoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	// objeto que com seus métodos retorna a largura e altura da tela do aparelho em pixels
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		scrWidth = metrics.widthPixels;
		scrHeight = metrics.heightPixels;
	
		
		
	btGravarSom	 = (Button)findViewById(R.id.btGravarSom);
	
	btGravarSom.setHeight(scrHeight/6);
	btGravarSom.setText(String.valueOf(scrWidth+"/"+scrHeight));
	
	
		
		btGravarSom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentSom = new Intent();
				intentSom.setAction(MediaStore.Audio.Media.RECORD_SOUND_ACTION);	
				startActivity(intentSom);
				
			}
		});
		
		
         btVideo = (Button) findViewById(R.id.btVideo);
		btVideo.setHeight(scrHeight/6);
	
		
	    btFoto = (Button) findViewById(R.id.btFoto);
		btFoto.setHeight(scrHeight/6);
		btFoto.setWidth(scrWidth/2);
		
		btFoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivity(intent);
			

				String arquivo = Environment.getExternalStorageDirectory()
						+ "/" + System.currentTimeMillis() + ".jpg";
				File file = new File(arquivo);
				Uri outputFileUri = Uri.fromFile(file);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

			}
		});

		btVideo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				startActivity(intent);

				String arquivo = Environment.getExternalStorageDirectory()
						+ "/" + System.currentTimeMillis() + ".jpg";
				File file = new File(arquivo);
				Uri outputFileUri = Uri.fromFile(file);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

			}
		});

	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
