package br.edu.ufabc.aedII.dicionario.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

@Service
public class Scraper {
    private static final Random random = new Random();
    private static final String url = "https://dle.rae.es/";
    public String getDefinicao(String word) {

        List<String> userAgent = List.of("Mozilla/5.0 (iPad; CPU OS 12_2 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.83 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Firefox/89.0",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Edge/91.0.864.59 Safari/537.36",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Firefox/89.0",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Safari/605.1.15");

        int indiceAleatorio = random.nextInt(userAgent.size() - 1);
        try {
            StringBuilder htmlPage = new StringBuilder();
            URL urlc = new URL(url + word);
            URLConnection conn = urlc.openConnection();
            conn.setRequestProperty("User-Agent", userAgent.get(indiceAleatorio));

            try(
                    InputStream is = conn.getInputStream();
                    Scanner leitor = new Scanner(is)
            ) {
                while (leitor.hasNext()) htmlPage.append(leitor.nextLine());
            }

            Document doc = Jsoup.parse(htmlPage.toString());

            Element resultados = doc.getElementById("resultados");
            assert resultados != null;
            Element article = resultados.getElementsByTag("article").first();
            assert article != null;

            List<String> definicao = Stream.of(article.text().split("\n")).filter(s -> s.length() > 0).toList();

            return definicao.toString();
        } catch (IOException e) {
            System.err.println("Exceção lançada ao enviar requisição HTTP à aplicação web de dicionario em espanhol");
            e.printStackTrace();
        }
        return "A palavra foi encontrada no dicionario e esta correta, porém não foi possível consultar seu significado";
    }
}
