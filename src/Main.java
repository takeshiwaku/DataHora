import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        int segundos1 = (int) (Math.random() * 40 + 11);
        int segundos2 = (int) (Math.random() * 40 + 11);
        int minutos1 = (int) (Math.random() * 40 + 11);
        int minutos2 = (int) (Math.random() * 40 + 11);

        System.out.println("DataHora <Pasta arquivo> <Arquivo> <ddmmYYYYHHMM>" );
        System.out.println("Exemplo: java -jar DataHora.jar C:\temp teste 020220211030" );
        System.out.println("Quantidade de Argumentos: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argumento[" + i + "]: " + args[i]);
        }

        //String myDate = "010120211810" + segundos1;
        String myDate = args[2] + segundos1;

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date = sdf.parse(myDate);
        long millis = date.getTime();

        /* Step  -1: Access the file in Path object */
        Path path = Paths.get(args[0], args[1]);
        /* Get System time to set against created timestamp */
        long time = System.currentTimeMillis();
        /* Get FileTime value */
        FileTime fileTime = FileTime.fromMillis(millis);
        /* Change Created Time Stamp */
        Files.setAttribute(path, "basic:creationTime", fileTime, NOFOLLOW_LINKS);
        Files.setAttribute(path, "basic:lastModifiedTime", fileTime, NOFOLLOW_LINKS);

    }
}