import java.util.ArrayList;
import java.util.List;

public class GestorInventario {
    private List<Producto> listaProductos;

    public GestorInventario(){
        this.listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto p){
        listaProductos.add(p);
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}
