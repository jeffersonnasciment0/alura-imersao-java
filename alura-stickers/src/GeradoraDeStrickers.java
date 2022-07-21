import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeStrickers {
    
    void create() throws IOException {


        // 1. Ler imagem
        BufferedImage ImagemOriginal = ImageIO.read(new File("alura-stickers/entrada/TopMovies_1.jpg"));

        // 2. Criar nova imagem em memória com transparência e com tamanho novo 
        int largura = ImagemOriginal.getWidth();
        int altura = ImagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // 3. Copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(ImagemOriginal, 0, 0, null);

        // Configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(fonte);

        // 4. Escrever uma frase na nova imagem
        graphics.drawString("O melhor", 0, novaAltura - 100);

        // 5. Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("alura-stickers/saida/figurinha.png"));
    }
            public static void main(String[] args) throws IOException {
                var geradora = new GeradoraDeStrickers();
                geradora.create();
            }
}
