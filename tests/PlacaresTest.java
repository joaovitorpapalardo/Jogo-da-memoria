package v1;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlacaresTest {

    private static final String ARQUIVO = "placares.csv";

    @BeforeEach
    public void limparArquivo() throws IOException {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            new PrintWriter(arquivo).close(); // limpa conteúdo do arquivo
        }
    }

    @Test
    public void testSalvarPlacarCriaArquivoComConteudoEsperado() throws IOException {
        Placares.salvarPlacar("Alice", 7);
        Placares.salvarPlacar("Bob", 4);

        File arquivo = new File(ARQUIVO);
        assertTrue(arquivo.exists());

        List<String> linhas = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
        }

        assertEquals(2, linhas.size());
        assertTrue(linhas.get(0).startsWith("Alice,7"));
        assertTrue(linhas.get(1).startsWith("Bob,4"));
    }

    @Test
    public void testCarregarTop10OrdenaPorTentativas() {
        Placares.salvarPlacar("Jogador1", 12);
        Placares.salvarPlacar("Jogador2", 5);
        Placares.salvarPlacar("Jogador3", 8);

        List<String[]> placares = Placares.carregarTop10Placares();

        assertEquals(3, placares.size());
        assertEquals("Jogador2", placares.get(0)[0]);
        assertEquals("Jogador3", placares.get(1)[0]);
        assertEquals("Jogador1", placares.get(2)[0]);
    }

    @Test
    public void testCarregarTop10LimitaADezResultados() {
        for (int i = 1; i <= 15; i++) {
            Placares.salvarPlacar("J" + i, i);
        }

        List<String[]> placares = Placares.carregarTop10Placares();
        assertEquals(10, placares.size());
        assertEquals("J1", placares.get(0)[0]);
        assertEquals("J10", placares.get(9)[0]);
    }

    @AfterEach
    public void apagarArquivo() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }
}
