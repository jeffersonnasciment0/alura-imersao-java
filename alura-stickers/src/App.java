import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        
        // 1. Fazer uma conexão HTTP e buscar os filmes
        // String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        // 2. Extrair só os dados que interessam (titulo, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        

        // 3. Exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            String titulo = filme.get("title");
            InputStream inputStream = new URL(urlImage).openStream();

            String nomeArquivo = titulo + ".png";

            GeradoraDeStrickers geradora = new GeradoraDeStrickers();
            geradora.create(inputStream, nomeArquivo);


            System.out.printf("TITULO:.\t\u001b[37m \u001b[41m\u001b[3m\u001b[1m %s \u001b[m \n", titulo);

            System.out.printf("\u001b[1mLINK IMAGEM:.\t\u001b[34m \u001b[3m\u001b[1m%s \u001b[m \n", urlImage);


            double imdbRating = Double.parseDouble(filme.get("imDbRating"));
            System.out.printf("\u001b[1mIMDBRATING:.\t");
            for( int i = 0 ; i < imdbRating ; i++ ){
                System.out.printf(" \u001b[1m\u2B50");
            }

            System.out.println();
        }
    }
}
