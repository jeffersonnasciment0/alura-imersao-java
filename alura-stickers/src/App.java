import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        
        // 1. Fazer uma conexão HTTP e buscar os filmes
        // String url = "https://api.mocki.io/v2/549a5d8b/MostPopularMovies";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();



        // String url = "https://api.nasa.gov/planetary/apod?api_key=dxy2CsQteqxlZ1Vbvy9ulrM3BHNKgIIUHRfqDcnR&start_date=2022-06-12&end_date=2022-06-14";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "https://linguagens-ap1-alura.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();
       
        ClienteHttp http = new ClienteHttp();
        String json = http.BuscaDados(url);
        
        // 3. Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        GeradoraDeStrickers geradora = new GeradoraDeStrickers();

        for (Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();

            String nomeArquivo = conteudo.titulo() + ".png";

            geradora.create(inputStream, nomeArquivo);


            System.out.printf("TITULO:.\t\u001b[37m \u001b[41m\u001b[3m\u001b[1m %s \u001b[m \n", conteudo.titulo());
            System.out.printf("\u001b[1mLINK IMAGEM:.\t\u001b[34m \u001b[3m\u001b[1m%s \u001b[m \n", conteudo.urlImagem());

            System.out.println();
        }
    }
}
