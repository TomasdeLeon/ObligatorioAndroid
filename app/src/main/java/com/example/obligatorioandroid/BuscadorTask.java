package com.example.obligatorioandroid;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscadorTask extends AsyncTask<String,Void,String> {

    private TextView txtLibroTitulo;
    private TextView txtLibroAutor;
    private TextView txtLibroEditorial;
    private Spinner spinnerTipo;

    public BuscadorTask(TextView txtLibroTitulo, TextView txtLibroAutor, TextView txtLibroEditoral) {
        this.txtLibroTitulo = txtLibroTitulo;
        this.txtLibroAutor = txtLibroAutor;
        this.txtLibroEditorial = txtLibroEditoral;
    }

    @Override
    protected String doInBackground(String... strings) {

        if (strings.length == 0) {
            return null;
        }

        try {
            final String FORECAST_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
            final String QUERY_PARAM = "q";
            final String MAX_RESULTS = "maxResults";
            final String PRINT_TYPE = "printType";

            InputStream inputStream;

            Uri builtURI = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, strings[0])
                    .appendQueryParameter(MAX_RESULTS, "1")
                    .appendQueryParameter(PRINT_TYPE, strings[1])
                    .build();

            URL requestURL = new URL(builtURI.toString());
            HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder salida = new StringBuilder();
            String linea = reader.readLine();

            while (linea != null) {
                salida.append(linea+"\n");
                linea = reader.readLine();
            }

            reader.close();
            inputStream.close();

            return salida.toString();

        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String resultado){
        {
            super.onPostExecute(resultado);

            try {
                //Convierto el string de respuesta en un objeto JSON
                JSONObject jsonObject = new JSONObject(resultado);
                //De la respuesta me quedo con el JSONArray items, es es donde esta la info que necesito
                JSONArray itemsArray = jsonObject.getJSONArray("items");

                int i = 0;
                String title = null;
                String authors = null;
                String publisher = null;
                Integer pageCount = null;

                //Itero en el array de libros hasta que encuentro uno con tituolo, autor y editorial
                while (i < itemsArray.length() && (authors == null && title == null && publisher == null)) {
                    //Obtengo la info de cada libro uno a la vez
                    JSONObject book = itemsArray.getJSONObject(i);
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                    //Obtengo la info de autor, titulo y editorial
                    //Utilizo un try por si alguno de los campos es nulo
                    try {
                        title = volumeInfo.getString("title");
                        authors = volumeInfo.getString("authors");
                        publisher = volumeInfo.getString("publisher");
                        pageCount = volumeInfo.getInt("pageCount");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Paso al siguiente libro en el array
                    i++;
                }

                //Si encontre un libro con todos los campos actualizo los textview
                if (title != null && authors != null && publisher != null) {
                    //Como el valor de los autores es un array lo formateo
                    authors = authors.replaceAll("\\[|\\]","");
                    txtLibroTitulo.setText(title);
                    txtLibroAutor.setText(authors);
                    txtLibroEditorial.setText(publisher);
                } else {
                    txtLibroTitulo.setText("No se encontró un libro con todos los campos");
                    txtLibroAutor.setText("");
                    txtLibroEditorial.setText("");
                }

            } catch (JSONException e) {
                //Si onPostExecute no recibe el JSON con la respuesta de la consulta,
                //indico que no se encontro el libro buscado
                txtLibroTitulo.setText("No se encontró el libro");
                txtLibroAutor.setText("");
                txtLibroEditorial.setText("");
                e.printStackTrace();
            }
        }
    }
}
