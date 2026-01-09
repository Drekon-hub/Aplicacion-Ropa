public class Producto {
    private int id;
    private String nombre;
    private String talle;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, String talle, double precio, int stock){
        this.id = id;
        this.nombre = nombre;
        this.talle = talle;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getTalle(){return talle;}
    public double getPrecio(){return precio;}
    public int getStock(){return stock;}

    @Override
    public String toString(){
        return "ID: " + id + " | " + nombre + " (" + talle + ") - $" + precio + " | Stock: " + stock;
    }
}
