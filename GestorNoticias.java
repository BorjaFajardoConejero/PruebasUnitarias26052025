import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorNoticias {
    private List<Noticia> listaNoticias;

    public GestorNoticias() {
        this.listaNoticias = new ArrayList<>();
    }

    public void anadirNoticia(Noticia noticia) {
        this.listaNoticias.add(noticia);
        System.out.println("Noticia '" + noticia.getTitulo() + "' anadida al gestor.");
    }

    public Noticia buscarNoticiaPorTitulo(String titulo) {
        for (Noticia noticia : listaNoticias) {
            if (noticia.getTitulo().equalsIgnoreCase(titulo)) {
                return noticia;
            }
        }
        return null; // Noticia no encontrada
    }

    public List<Noticia> getNoticiasPorCategoria(Noticia.Categoria categoria) {
        return listaNoticias.stream()
                .filter(n -> n.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    public List<Noticia> getNoticiasPublicadas() {
        return listaNoticias.stream()
                .filter(Noticia::isPublicada)
                .collect(Collectors.toList());
    }

    public int getTotalNoticias() {
        return listaNoticias.size();
    }

    public boolean eliminarNoticia(String titulo) {
        Noticia noticiaAEliminar = buscarNoticiaPorTitulo(titulo);
        if (noticiaAEliminar != null) {
            this.listaNoticias.remove(noticiaAEliminar);
            System.out.println("Noticia '" + titulo + "' eliminada del gestor.");
            return true;
        }
        System.out.println("No se pudo eliminar la noticia '" + titulo + "': no encontrada.");
        return false;
    }
}