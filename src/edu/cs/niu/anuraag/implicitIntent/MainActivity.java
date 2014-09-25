package edu.cs.niu.anuraag.implicitIntent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
	private static final int CONTACT_REQUEST = 1;
	private static final int REQUEST_IMAGE_CAPTURE = 2;
	Button btn_browser,btn_contact,btn_dial, btn_cam;
	ImageView thumbView;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_browser = (Button)findViewById(R.id.btn_browser);
		btn_contact = (Button)findViewById(R.id.btn_contact);
		btn_dial = (Button)findViewById(R.id.btn_dial);
		
		btn_cam = (Button)findViewById(R.id.btn_cam);
		thumbView = (ImageView)findViewById(R.id.thumbView);
		
		
		btn_cam.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//error checking 
				if(i.resolveActivity(getPackageManager()) != null)
				{
					startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
				}
				startActivityForResult(i, REQUEST_IMAGE_CAPTURE);
				
				// TODO Auto-generated method stub
			}
			
		});
		
		
		btn_dial.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent i = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel: +8155085621"));
				startActivity(i);
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btn_contact.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				// Different way to pass data to intent
				Intent i = new Intent(android.content.Intent.ACTION_PICK);
				i.setType(ContactsContract.Contacts.CONTENT_TYPE);
				startActivityForResult(i, CONTACT_REQUEST);
	
			}
		});
		
		
		btn_browser.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.cs.niu.edu/index.shtml"));
				startActivity(i);
				
			}
		});
	}

		public void onActivityResult(int requestCode,int resultCode, Intent data)
		{
			
			if(requestCode == CONTACT_REQUEST)
			{
				if(resultCode == RESULT_OK)
				{
					Toast.makeText(this, data.getData().toString(), Toast.LENGTH_LONG).show();
					Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(data.getData().toString()));
					startActivity(i);
				}
				else
				{
					Toast.makeText(this, "No contacts available!", Toast.LENGTH_LONG).show();
				}
			}
			
			if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
			{
				//set the imageview to the data returned fom camera
				Bundle extras = data.getExtras();
				Bitmap imgBitmap = (Bitmap)extras.get("data");
				thumbView.setImageBitmap(imgBitmap);
			}
		}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
