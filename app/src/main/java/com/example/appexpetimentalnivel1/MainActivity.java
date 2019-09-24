package com.example.appexpetimentalnivel1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.objects.Cliente;

public class MainActivity extends AppCompatActivity {

    TextView mTextViewInicial;
    Button mButtonIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciar o recyclerView




        mButtonIniciar = (Button) findViewById(R.id.button);
        mTextViewInicial = (TextView) findViewById(R.id.tv_inicio);

        mTextViewInicial.setText("Aqui pode ficar:\n    Logotipo\n  Ecran de login\n    ...");

        mButtonIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                Context context = MainActivity.this;
                Class activityDestino = ClienteActivity.class;

                intent = new Intent(context, activityDestino);
                startActivity(intent);

               /* Log.d("TAG","Clicado..");
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Log.d("TAG","new FragmentTransaction");
                // Replace the contents of the container with the new fragment

                ft.replace(R.id.your_placeholder, new ClientFragment());
                Log.d("TAG","depois do ft.replace()");
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above

                ft.commit();
                Log.d("TAG","depois do commit()");*/
                /*
               Intent intent;
                Context context = MainActivity.this;
                Class activityDestino = InventarioActivity.class;

                intent = new Intent(context, activityDestino);
                startActivity(intent);*/
            }
        });




    }


}
