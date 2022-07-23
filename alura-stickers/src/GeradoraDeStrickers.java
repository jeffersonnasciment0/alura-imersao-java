import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeStrickers {
    
    void create(InputStream inputStream, String nomeArquivo) throws IOException {

        // 1. Ler imagem
        BufferedImage ImagemOriginal = ImageIO.read(inputStream);

        // 2. Criar nova imagem em memória com transparência e com tamanho novo 
        int largura = ImagemOriginal.getWidth();
        int altura = ImagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // 3. Copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(ImagemOriginal, 0, 0, null);

        // Configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 100);
        graphics.setColor(Color.RED);
        graphics.setFont(fonte);

        // 4. Escrever uma frase na nova imagem
        String frase = "Massa demais !";
        int tamanhoFrase = graphics.getFontMetrics().stringWidth(frase);
        int localFrase = (novaImagem.getWidth() - tamanhoFrase) / 2;
        graphics.drawString(frase, localFrase, novaAltura - 80);

        // 5. Escrever a nova imagem em um arquivo
        String path = "alura-stickers/saida/" + nomeArquivo;
        ImageIO.write(novaImagem, "png", new File(path));
    }

}
