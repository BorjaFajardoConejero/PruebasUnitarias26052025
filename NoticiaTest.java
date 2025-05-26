import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class NoticiaTest {

    private Noticia noticiaFutbol;
    private Noticia noticiaBaloncesto;

    @Before // Anotación para métodos de inicialización
    public void setUp() { // El método debe ser público y no estático
        noticiaFutbol = new Noticia("Messi ficha por el PSG", "Detalles del traspaso de Messi al PSG.", Noticia.Categoria.FUTBOL, Noticia.Relevancia.ALTA);
        noticiaBaloncesto = new Noticia("Los Lakers ganan el anillo", "Analisis de la victoria de los Lakers en la final de la NBA.", Noticia.Categoria.BALONCESTO, Noticia.Relevancia.MEDIA);
    }

    @Test // Anotación para métodos de prueba
    public void testConstructorAndGetters() {
        assertEquals("El titulo de la noticia de futbol es incorrecto.", "Messi ficha por el PSG", noticiaFutbol.getTitulo());
        assertEquals("La categoria de la noticia de futbol es incorrecta.", Noticia.Categoria.FUTBOL, noticiaFutbol.getCategoria());
        assertEquals("La relevancia de la noticia de futbol es incorrecta.", Noticia.Relevancia.ALTA, noticiaFutbol.getRelevancia());
        assertFalse("La noticia no deberia estar publicada inicialmente.", noticiaFutbol.isPublicada());
        assertNull("La fecha de publicacion deberia ser nula inicialmente.", noticiaFutbol.getFechaPublicacion());
    }

    @Test
    public void testPublicarNoticia() {
        noticiaFutbol.publicar();
        assertTrue("La noticia deberia estar publicada.", noticiaFutbol.isPublicada());
        assertNotNull("La fecha de publicacion no deberia ser nula despues de publicar.", noticiaFutbol.getFechaPublicacion());
        assertTrue("La fecha de publicacion debe ser actual.", noticiaFutbol.getFechaPublicacion().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    public void testDespublicarNoticiaPublicada() {
        noticiaBaloncesto.publicar();
        assertTrue("La noticia de baloncesto deberia estar publicada antes de despublicar.", noticiaBaloncesto.isPublicada());
        noticiaBaloncesto.despublicar();
        assertFalse("La noticia de baloncesto deberia estar despublicada.", noticiaBaloncesto.isPublicada());
    }

    @Test
    public void testPublicarNoticiaYaPublicada() {
        noticiaFutbol.publicar();
        LocalDateTime primeraPublicacion = noticiaFutbol.getFechaPublicacion();
        noticiaFutbol.publicar(); // Intentar publicar de nuevo
        assertTrue("La noticia debe seguir publicada.", noticiaFutbol.isPublicada());
        assertEquals("La fecha de publicacion no deberia cambiar al intentar republicar.", primeraPublicacion, noticiaFutbol.getFechaPublicacion());
    }

    @Test
    public void testSetRelevancia() {
        assertEquals("La relevancia inicial de noticiaFutbol debe ser ALTA.", Noticia.Relevancia.ALTA, noticiaFutbol.getRelevancia());
        noticiaFutbol.setRelevancia(Noticia.Relevancia.URGENTE);
        assertEquals("La relevancia de noticiaFutbol deberia ser URGENTE.", Noticia.Relevancia.URGENTE, noticiaFutbol.getRelevancia());
    }
}