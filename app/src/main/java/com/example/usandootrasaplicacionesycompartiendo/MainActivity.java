package com.example.usandootrasaplicacionesycompartiendo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button lanzarWeb, lanzarMapa, compartirWhatsapp, lanzarCorreo;
EditText direccion,coordenadas, textoWhatsapp, direccioncorreo, asunto, mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        direccion = findViewById(R.id.editTextViewURL);
        lanzarWeb = findViewById(R.id.buttonLanzaWeb);
        coordenadas = findViewById(R.id.editTextTextCoordenadas);
        lanzarMapa =findViewById(R.id.buttonMapa);
        compartirWhatsapp = findViewById(R.id.buttonWhatsapp);
        textoWhatsapp = findViewById(R.id.editTextWhatsapp);
        lanzarCorreo = findViewById(R.id.buttonCorreo);
        direccioncorreo = findViewById(R.id.editTextTextEmailAddress);
        asunto = findViewById(R.id.editTextAsunto);
        mensaje = findViewById(R.id.editTextTextPersonName5);

        lanzarWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(Intent.ACTION_VIEW);
                intento.setData(Uri.parse(direccion.getText().toString()));
                //Intent seleccionador = Intent.createChooser(intento, getString(R.string.elige_navegador));
                //startActivity(seleccionador);
                startActivity(intento);
            }
        });

        lanzarMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(Intent.ACTION_VIEW);
                intento.setData(Uri.parse("geo:"+coordenadas.getText().toString()+"?q="+coordenadas.getText().toString()+"(Aquí estoy)"));
                startActivity(intento);
            }
        });

        compartirWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setPackage("com.whatsapp");
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, textoWhatsapp.getText().toString());


                if (getPackageManager().getLaunchIntentForPackage("com.whatsapp")!=null){
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Instala Whatsapp", Toast.LENGTH_LONG).show();
                }


            }
        });
lanzarCorreo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{direccioncorreo.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, mensaje.getText().toString());
        intent.setType("message/rfc822");
        startActivity(intent);
    }
});

    }
}