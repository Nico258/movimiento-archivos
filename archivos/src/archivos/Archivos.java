package archivos;

//Importaciones
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Niko
 */
public class Archivos {

    public static void main(String[] args) {

        //Método de la propia clase para poder llamar métodos propios.
        Archivos esta = new Archivos();

        //Rutas de las carpetas que se moverán
        String ruta1 = "C:\\...\\Carpeta1",
                ruta2 = "C:\\...\\Carpeta2";
        File carpetaOrigen = new File(ruta1);
        File carpetaDestino = new File(ruta2);

        //Mostar el contenido de las dos carpetas
        //esta.contenidoCarpetas(carpetaOrigen, carpetaDestino);

        //Contador de archivos pasados correctamente y errores
        int correcto = 0, incorrecto = 0;

        //Sacar el nombre y extención de los archivos a pasar
        String[] aPasar = esta.archivosIguales(carpetaOrigen, carpetaDestino);

        //Proceso
        
        System.out.println("Inicia el proceso: ");
        //Se imprime el número de archivos que se moveran de una carpeta a otra
        System.out.println("Archivos a pasar: " + aPasar.length);

        //Una pausa en el sistema para se sepa cuantos archivos se moveran antes de continuar
        JOptionPane.showMessageDialog(null, "Al oprimir el botón se copiarán todos los archivos anteriores a la carpeta de destino, confirmar antes de pasar");
        
        for (int i = 0; i < aPasar.length; i++) {
            try {
                //Se debe crear una cadena con la ruta completa incluyendo el nombre y extensión del archivos que se va a mover
                String ruta = ruta1 + "\\" + aPasar[i];
                String rutaDest = ruta2 + "\\" + aPasar[i];
                //Ejecución del movimiento del archivo
                esta.moverArchivo(ruta, rutaDest);
                correcto += 1;
            } catch (Exception e) {
                System.out.println(e);
                incorrecto += 1;
            }
        }

        //Al finalizar el proceso se imprime el total de archivos correctos para que se pueda comparar con el número del inicio
        JOptionPane.showMessageDialog(null, "Se han movido correctamente " + correcto + " archivos");
        System.out.println("Errores presentados: " + incorrecto);

        //Validar contenido de las carpetas al terminar
        //esta.contenidoCarpetas(carpetaOrigen, carpetaDestino);

    }

    /*
    **Método usado para mover los archivos, se recibe la ruta completa del origen y del destino (Incluye el nombre del archivo en ambos)
    */
    public boolean moverArchivo(String rutaOrigen, String rutaDestino) throws IOException {
        
        //Este Path basicamente es el que hace el movimeinto del archivo
        Path temp = Files.move(Paths.get(rutaOrigen),
                Paths.get(rutaDestino));

        System.out.println("Archivo movido de forma exitosa");

        return true;

    }

    /*
    **Método para imprimir el contenido de ambas carpetas, recibe ambas carpetas como tipo File
    */
    public void contenidoCarpetas(File carp1, File carp2) {

        //Se convierte de File a lista el contenido de la carpeta 1
        String[] listado = carp1.list();
        System.out.println("Carpeta Origen ----------------->");
        //Se valida que la carpeta no esté vacía
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        } else {
            //Se imprime el nombre de cada uno de los archivos de la carpeta 1
            for (int i = 0; i < listado.length; i++) {
                System.out.println(listado[i]);
            }
        }

        //Se convierte de File a lista el contenido de la carpeta 2
        String[] listado2 = carp2.list();
        System.out.println("Carpeta Destino ----------------->");
        //Se valida que la carpeta no esté vacía
        if (listado2 == null || listado2.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        } else {
            //Se imprime el nombre de cada uno de los archivos de la carpeta 2
            for (int i = 0; i < listado2.length; i++) {
                System.out.println(listado2[i]);
            }
        }

    }

    /*
    **Método para encontrar aquellos archivos que tienen el mismo nombre (independiente de la extención)
    **Recibe ambas carpetas como tipo File
    **Devuelve una lista con el nombre y extensión de los archivos que se encontraron con nombres iguales de la carpeta 1 u origen
    */
    public String[] archivosIguales(File carp1, File carp2) {

        //Creación de un ArrayList para almacenar los nombres de arhivos repetidos
        ArrayList<String> listaRepe = new ArrayList<String>();

        //Se obtiene una lista con todos los archivos de ambas carpetas
        String[] listado = carp1.list();
        String[] listado2 = carp2.list();

        //Se hace un doble for para comparar cada archivo de la carpeta de origen con todos los de la carpeta de destino
        for (int i = 0; i < listado.length; i++) {
            //Se crea una sublista para obtener ÚNICAMENTE el nombre del archivo
            String[] nombre = listado[i].split("\\.");
            for (int j = 0; j < listado2.length; j++){
                //Se crea una sublista para obtener ÚNICAMENTE el nombre del archivo
                String[] nombres = listado2[j].split("\\.");
                //Se realiza la validación para que si el nombre de el arhivo1 existe en la carpetaDestino, se agrege a la lista de Repetidos
                //Además se valida que el item no esté ya en la lista de repetidos
                if (nombre[0].equalsIgnoreCase(nombres[0]) && !listaRepe.contains(listado[i])){
                    listaRepe.add(listado[i]);
                }
            };
        }

        //Se convierte el ArrayList en una lista tipo String[]
        String[] listaRepetidos = listaRepe.toArray(new String[listaRepe.size()]);

        //Se imprime toda la lista de repetidos en caso que se necesiten validar
        //for (int i = 0; i < listaRepetidos.length; i++) {
        //    System.out.println(listaRepetidos[i]);
        //}

        //Se devuelve la lista de los archivos repetidos que se quieren mover
        return listaRepetidos;
    }

}
