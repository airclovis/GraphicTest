package com.example.graphictest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements View.OnTouchListener, View.OnClickListener{

	private RadioGroup group = null;
	private EditText poids = null;
	private EditText taille = null;
	private TextView result = null;
	private String defaultResult = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        group = (RadioGroup) this.findViewById(R.id.group);
        
        result = (TextView)findViewById(R.id.imcResult);
        
        taille = (EditText)findViewById(R.id.taille);
        
        poids = (EditText)findViewById(R.id.poids);
        
        defaultResult = getResources().getString(R.string.imcResult);
        
        Button b = (Button)this.findViewById(R.id.raz);
        b.setOnTouchListener(this);
        b.setOnClickListener(this);
        
        Button b2 = (Button)this.findViewById(R.id.calculer);
        b2.setOnClickListener(this);
        b2.setOnTouchListener(this);
    }


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onClick(View v) {

		switch(v.getId())
		{
		case R.id.calculer:
			if(this.taille.getText().toString().equals(""))
			{
				Toast.makeText(Main.this, "Veuillez entrer une taille", Toast.LENGTH_SHORT).show();
				break;
			}
			float tailleValue = (group.getCheckedRadioButtonId() == R.id.radio2) ? Float.valueOf(this.taille.getText().toString()) / 100
					: Float.valueOf(this.taille.getText().toString());
			
			if(tailleValue <= 0)
			{
				Toast.makeText(Main.this, "La taille doit être supérieur à 0 !", Toast.LENGTH_SHORT).show();
				break;
			}
			
			
			if(this.poids.getText().toString().equals(""))
			{
				Toast.makeText(Main.this, "Veuillez entrer un poids", Toast.LENGTH_SHORT).show();
				break;
			}
			
			float poidsValue = Float.valueOf(this.poids.getText().toString());
			
			if(poidsValue <= 0)
			{
				Toast.makeText(Main.this, "Le poids doit être supérieur à 0 !", Toast.LENGTH_SHORT).show();
				break;
			}
			
			float imc = poidsValue / (tailleValue * tailleValue);
			
			result.setText("Votre imc : " + imc);
			break;
		case R.id.raz:
			poids.getText().clear();
			taille.getText().clear();
			result.setText(defaultResult);
			break;
		}
	}
	
	public class TextListener implements TextWatcher
	{

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			result.setText(defaultResult);			
		}
	
	}
}
