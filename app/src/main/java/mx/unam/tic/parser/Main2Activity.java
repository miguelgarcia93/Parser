package mx.unam.tic.parser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main2Activity extends AppCompatActivity {


    private Button btnActivity3;
    private TextView tvResultado2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnActivity3 = findViewById(R.id.btnActivity3);
        tvResultado2 = findViewById(R.id.tvResultado2);

        btnActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        try{
            InputStream is = getAssets().open("archivo2.xml");
            DocumentBuilderFactory dbFabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFabrica.newDocumentBuilder();

            Document doc = dBuilder.parse(is);
            NodeList nList = doc.getElementsByTagName("employee");
            for (int i = 0; i<nList.getLength();i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;
                    tvResultado2.setText(tvResultado2.getText() +
                            "Nombre" + (i+1) + ": " + obtenValor("name", elemento) + "\n"
                            + "Apellido" + (i+1) + ": " + obtenValor("surname", elemento) + "\n"
                            + "Salario" + (i+1) + ": " + obtenValor("salary", elemento) + "\n\n");
                }
            }
        }
        catch(Exception e){

        }
    }


    private static String obtenValor(String tag, Element elemento) {
        NodeList listaNodos = elemento.getElementsByTagName(tag).item(0).getChildNodes();
        Node nodo = listaNodos.item(0);
        return nodo.getNodeValue();
    }

}
