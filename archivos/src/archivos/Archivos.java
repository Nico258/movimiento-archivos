package archivos;

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
        System.out.println("Archivos a pasar: " + aPasar.length);
        JOptionPane.showMessageDialog(null, "Al oprimir el botón se copiarán todos los archivos anteriores a la carpeta de destino, confirmar antes de pasar");
        for (int i = 0; i < aPasar.length; i++) {
            try {
                String ruta = ruta1 + "\\" + aPasar[i];
                String rutaDest = ruta2 + "\\" + aPasar[i];
                esta.moverArchivo(ruta, rutaDest);
                correcto += 1;
            } catch (Exception e) {
                System.out.println(e);
                incorrecto += 1;
            }
        }

        JOptionPane.showMessageDialog(null, "Se han movido correctamente " + correcto + " archivos");
        System.out.println("Errores presentados: " + incorrecto);

        //Validar contenido de las carpetas al terminar
        //esta.contenidoCarpetas(carpetaOrigen, carpetaDestino);

    }

    public boolean moverArchivo(String rutaOrigen, String rutaDestino) throws IOException {

        Path temp = Files.move(Paths.get(rutaOrigen),
                Paths.get(rutaDestino));

        System.out.println("Archivo movido de forma exitosa");

        return true;

    }

    public void contenidoCarpetas(File carp1, File carp2) {

        String[] listado = carp1.list();
        System.out.println("Carpeta Origen ----------------->");
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        } else {
            for (int i = 0; i < listado.length; i++) {
                System.out.println(listado[i]);
            }
        }

        String[] listado2 = carp2.list();
        System.out.println("Carpeta Destino ----------------->");
        if (listado2 == null || listado2.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        } else {
            for (int i = 0; i < listado2.length; i++) {
                System.out.println(listado2[i]);
            }
        }

    }

    public String[] archivosIguales(File carp1, File carp2) {

        ArrayList<String> listaRepe = new ArrayList<String>();
        String[] listado = carp1.list();
        String[] listado2 = carp2.list();
        for (int i = 0; i < listado.length; i++) {
            String[] nombre = listado[i].split("\\.");
            for (int j = 0; j < listado2.length; j++){
                String[] nombres = listado2[j].split("\\.");
                if (nombre[0].equalsIgnoreCase(nombres[0]) && !listaRepe.contains(listado[i])){
                    listaRepe.add(listado[i]);
                }
            };
        }

        String[] listaRepetidos = listaRepe.toArray(new String[listaRepe.size()]);
        for (int i = 0; i < listaRepetidos.length; i++) {
            System.out.println(listaRepetidos[i]);
        }

        return listaRepetidos;
    }

}
