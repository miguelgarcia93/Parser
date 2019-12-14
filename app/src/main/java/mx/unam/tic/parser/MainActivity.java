package mx.unam.tic.parser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button btnActivity2;
    private TextView tvResultado1;
    private XmlPullParserFactory xmlPullParserFactory;
    private XmlPullParser xmlPullParser;
    String temperatura,pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivity2 = findViewById(R.id.btnActivity2);
        tvResultado1 = findViewById(R.id.tvResultado1);
        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        try{
            InputStream is = getAssets().open("archivo1.xml");

            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(is,null);
            boolean encontrado = false;

            int evento = xmlPullParser.getEventType();
            while(evento!=XmlPullParser.END_DOCUMENT){
                String nombre = xmlPullParser.getName();
                switch (evento){
                    case XmlPullParser.START_TAG:
                        if (nombre.equals("country")){
                            encontrado = true;
                        }
                        break;

                    case XmlPullParser.TEXT:
                        if(encontrado){
                            pais=xmlPullParser.getText();
                            encontrado = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (nombre.equals("temperature")){
                            temperatura=xmlPullParser.getAttributeValue(null,"value");
                        }
                        break;
                }
                evento=xmlPullParser.next();
            }

        }catch(Exception e){

        }
        tvResultado1.setText("El valor del tag country es: "+pais+" y el atributo value del tag temperature es: "+temperatura);


    }
}
