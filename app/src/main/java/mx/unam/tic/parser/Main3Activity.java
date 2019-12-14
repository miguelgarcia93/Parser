package mx.unam.tic.parser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main3Activity extends AppCompatActivity {

    private TextView tvREsultado3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvREsultado3 = findViewById(R.id.tvResultado3);

        try{
            InputStream is = getAssets().open("archivo.txt");
            BufferedReader br = new BufferedReader( new InputStreamReader(is));
            StringBuilder out = new StringBuilder();
            String line;
             while((line=br.readLine())!=null){
                 out.append(line);
             }
            br.close();

            JSONObject reader = new JSONObject(out.toString());

            JSONObject sys = reader.getJSONObject("sys");
            String pais = sys.getString("country");

            JSONObject main = reader.getJSONObject("main");
            String temperatura = main.getString("temp");

            tvREsultado3.setText("Pais: "+pais+"\nTemperatura: " +temperatura);

        }catch(Exception e){}
    }
}
