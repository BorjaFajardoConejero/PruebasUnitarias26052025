import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class GestorNoticiasTest {

    private GestorNoticias gestor;
    private Noticia n1Futbol;
    private Noticia n2Baloncesto;
    private Noticia n3Futbol;

    @Before
    public void setUp() {
        gestor = new GestorNoticias();
        n1Futbol = new Noticia("Real Madrid gana la Liga", "El Real Madrid se proclama campeon.", Noticia.Categoria.FUTBOL, Noticia.Relevancia.ALTA);
        n2Baloncesto = new Noticia("Ricky Rubio vuelve a las canchas", "El base espanol regresa al baloncesto.", Noticia.Categoria.BALONCESTO, Noticia.Relevancia.MEDIA);
        n3Futbol = new Noticia("Mbappe renueva con el PSG", "Sorprendente renovacion del delantero.", Noticia.Categoria.FUTBOL, Noticia.Relevancia.URGENTE);

        gestor.anadirNoticia(n1Futbol);
        gestor.anadirNoticia(n2Baloncesto);
        gestor.anadirNoticia(n3Futbol);
    }

    @Test
    public void testAnadirNoticiaYTotal() {
        assertEquals("Deberia haber 3 noticias en el gestor.", 3, gestor.getTotalNoticias());
        Noticia n4Tenis = new Noticia("Nadal avanza en Roland Garros", "Rafa Nadal sigue su camino en el torneo.", Noticia.Categoria.TENIS, Noticia.Relevancia.MEDIA);
        gestor.anadirNoticia(n4Tenis);
        assertEquals("Deberia haber 4 noticias despues de anadir una mas.", 4, gestor.getTotalNoticias());
    }

    @Test
    public void testBuscarNoticiaExistente() {
        Noticia encontrada = gestor.buscarNoticiaPorTitulo("Ricky Rubio vuelve a las canchas");
        assertNotNull("La noticia de Ricky Rubio deberia ser encontrada.", encontrada);
        assertEquals("La noticia encontrada deberia ser la misma que la anadida.", n2Baloncesto, encontrada);
    }

    @Test
    public void testBuscarNoticiaNoExistente() {
        Noticia encontrada = gestor.buscarNoticiaPorTitulo("Noticia de Formula 1");
        assertNull("La noticia de Formula 1 no deberia ser encontrada.", encontrada);
    }

    @Test
    public void testGetNoticiasPorCategoria() {
        List<Noticia> noticiasFutbol = gestor.getNoticiasPorCategoria(Noticia.Categoria.FUTBOL);
        assertNotNull("La lista de noticias de futbol no deberia ser nula.", noticiasFutbol);
        assertEquals("Deberia haber 2 noticias de futbol.", 2, noticiasFutbol.size());
        assertTrue("La lista deberia contener la noticia 'Real Madrid gana la Liga'.", noticiasFutbol.contains(n1Futbol));
        assertTrue("La lista deberia contener la noticia 'Mbappe renueva con el PSG'.", noticiasFutbol.contains(n3Futbol));
    }

    @Test
    public void testGetNoticiasPublicadas() {
        n1Futbol.publicar();
        n3Futbol.publicar();

        List<Noticia> publicadas = gestor.getNoticiasPublicadas();
        assertNotNull("La lista de noticias publicadas no deberia ser nula.", publicadas);
        assertEquals("Deberia haber 2 noticias publicadas.", 2, publicadas.size());
        assertTrue("La lista deberia contener la noticia publicada 'Real Madrid gana la Liga'.", publicadas.contains(n1Futbol));
        assertTrue("La lista deberia contener la noticia publicada 'Mbappe renueva con el PSG'.", publicadas.contains(n3Futbol));
        assertFalse("La lista no deberia contener la noticia no publicada de baloncesto.", publicadas.contains(n2Baloncesto));
    }

    @Test
    public void testEliminarNoticiaExistente() {
        assertTrue("Deberia poder eliminar la noticia de Ricky Rubio.", gestor.eliminarNoticia("Ricky Rubio vuelve a las canchas"));
        assertEquals("Deberia haber 2 noticias despues de la eliminacion.", 2, gestor.getTotalNoticias());
        assertNull("La noticia de Ricky Rubio no deberia estar en el gestor.", gestor.buscarNoticiaPorTitulo("Ricky Rubio vuelve a las canchas"));
    }

    @Test
    public void testEliminarNoticiaNoExistente() {
        assertFalse("No deberia poder eliminar una noticia que no existe.", gestor.eliminarNoticia("Noticia de golf"));
        assertEquals("El total de noticias no deberia cambiar.", 3, gestor.getTotalNoticias());
    }
}