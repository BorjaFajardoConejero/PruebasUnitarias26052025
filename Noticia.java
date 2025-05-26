import java.time.LocalDateTime;

public class Noticia {
    public enum Categoria {
        FUTBOL, BALONCESTO, MOTOR, TENIS, OTROS
    }

    public enum Relevancia {
        BAJA, MEDIA, ALTA, URGENTE
    }

    private String titulo;
    private String contenido;
    private Categoria categoria;
    private Relevancia relevancia;
    private LocalDateTime fechaPublicacion;
    private boolean publicada;

    public Noticia(String titulo, String contenido, Categoria categoria, Relevancia relevancia) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.relevancia = relevancia;
        this.fechaPublicacion = null; // Se establecerá al publicar
        this.publicada = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Relevancia getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(Relevancia relevancia) {
        this.relevancia = relevancia;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public boolean isPublicada() {
        return publicada;
    }

    public void publicar() {
        if (!this.publicada) {
            this.publicada = true;
            this.fechaPublicacion = LocalDateTime.now();
            System.out.println("Noticia '" + titulo + "' publicada a las " + fechaPublicacion);
        } else {
            System.out.println("La noticia '" + titulo + "' ya esta publicada.");
        }
    }

    public void despublicar() {
        if (this.publicada) {
            this.publicada = false;
            System.out.println("Noticia '" + titulo + "' despublicada.");
        } else {
            System.out.println("La noticia '" + titulo + "' no esta publicada.");
        }
    }
}