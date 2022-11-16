import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //TODO 3: Creamos el Scanner y etc.
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime la frase ");

        try {
            ArrayList<String> palabras = cambiarLetra(sc.nextLine());
            String frase = reconstruir(palabras);
            System.out.println(frase);
        } catch (IOException e) {
            System.out.println("Algo se ha ido a la mierda");
        }

    }

    //TODO 1: Hacemos la función para cambiar la letra -> 1.01: Extraemos los métodos
    private static ArrayList<String> cambiarLetra(String cambiarLetra) throws IOException {
        Process process = crearProceso("PSP_Repaso_Ejercicio1_LetraCambiada.jar");

        BufferedReader br = getBufferedReader(process);

        PrintStream prs = getPrintStream(process);

        ArrayList<String> palabras = new ArrayList<>();
        prs.println(cambiarLetra);
        prs.flush();

        String palabra;
        while (!(palabra = br.readLine()).equalsIgnoreCase("fin")) {
            palabras.add(palabra);
        }
        return palabras;
    }

    //TODO 2: Creamos la función del hijo Construir
    //Esta función recoge lo que ha printeado la función de cambiar letras (Array palabras) y lo concatena
    private static String reconstruir(ArrayList<String> palabras) throws IOException {
        Process process = crearProceso("PSP_Repaso_Ejercicio1_Construir.jar");

        BufferedReader br = getBufferedReader(process);
        PrintStream ps = getPrintStream(process);

        //Esta función monta el array palabras creado en la función de cambiar letras
        for (String palabra : palabras) {
            ps.println(palabra);
            ps.flush();
        }
        ps.println("fin");
        ps.flush();
        return br.readLine();
    }

    //Esta función printea lo que especificamos que ha de hacer en los hijos
    private static PrintStream getPrintStream(Process process) {
        OutputStream os = process.getOutputStream();
        PrintStream prs = new PrintStream(os);
        return prs;
    }

    //Esta función lee lo que hemos pasado
    private static BufferedReader getBufferedReader(Process process) {
        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        return br;
    }

    //Esta función crea el proceso
    private static Process crearProceso(String proceso) throws IOException {
        ProcessBuilder ps = new ProcessBuilder("java", "-jar", proceso);
        ps.redirectErrorStream(true);
        Process process = ps.start();
        return process;
    }

    //TODO: Aquí está la función cambiarLetra sin extraer ningún método
    /*private static ArrayList<String> cambiarLetra(String cambiarLetra) throws IOException {
        ProcessBuilder ps = new ProcessBuilder("java", "-jar", "PSP_Repaso_Ejercicio1_LetraCambiada.jar");
        ps.redirectErrorStream(true);
        Process process = ps.start();

        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader br = new BufferedReader(isr);

        OutputStream os = process.getOutputStream();

        PrintStream prs = new PrintStream(os);

        ArrayList<String> palabras = new ArrayList<>();
        prs.println(cambiarLetra);
        prs.flush();

        String palabra;
        while (!(palabra = br.readLine()).equalsIgnoreCase("fin")) {
            palabras.add(palabra);
        }
        return palabras;
    }*/

}