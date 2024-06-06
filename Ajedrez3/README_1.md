# AppointScheduler
Customer Appointment Management Scheduling Software

# Documentacion de Proyecto

# Anexos
### _Creación de un ejecutable JAR_

Para crear un JAR de un proyecto `Maven` en `NetBeans` puede ser un poco tedioso, ya que puede ocurrir errores como ubicar en dónde se encuentra el archivo [Manifest] (porque no se creó), es posible que también haya documentación obsoleta o mal documentada.

Tantos errores que pueden ocurrir, pues llegamos con un método sencillo para exportar el proyecto en un solo ejecutable.

Desde la página oficial de Oracle, se encuentra una documentación explicando cómo ponemos integrar el plugin de [Apache Maven Assembly Plugin] en el archivo POM de nuestro proyecto y de esa forma hacerle un `build` para que se cree el ejecutable en nuestra carpeta `target` del proyecto. 

A continuación el código que debería tener el POM:
```xml
<project>
 [...]
  <build>
    [...]
    <plugins>
        <plugin>
            <artifactId>maven-assembly-plugin</artifactId>
            <version>3.7.1</version>
            <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
                <archive>
                    <manifest>
                    <mainClass>[Main.Class.Name]</mainClass>
                </manifest>
                </archive>
            </configuration>
            <executions>
                <execution>
                    <id>make-assembly</id>
                    <phase>package</phase>
                    <goals>
                        <goal>single</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
  [...]
</project>
```
Si no se entendió muy bien la explicación de la documentación, aquí se provee un video tutorial de [cómo generar un ejecutable Jar en Netbeans].

## _Envío de un correo con JavaMail_
Para proyectos en Maven, cuando se utiliza librerías, estas no las podemos descargar e importar cómo se hace con proyectos en Java Ant. Para este caso, tenemos que entrar al [Repositorio de Maven] y buscar la libreria que queremos usar en nuestro proyecto. Al encontrar la librería, nos va a aparecer una tabla con las versiones que tiene, escogemos la que queremos y nos va aparecer un código XML para agregarlo como una [dependencia] al archivo POM del proyecto. 

Durante el proceso de pruebas para entender como funciona el [envio de un correo con JavaMail], se presentaron varios problemas.

El primero es que se necesita ademas de la dependencia de [JavaMail], tambien se necesita la dependencia de [Sun.Mail]

*Error que se presento con Sun.Mail:* `java.lang.noclassdeffounderror: com/sun/mail/util/maillogger`

Para eso, se busco en el repositorio de Maven la dependencia e incluirlo al proyecto.

Luego, el segundo problema que se presentó fue que Google no permite el uso de la contraseña de la cuenta, si no, que se debe de utilizar una contraseña de app, para eso hay que ir a la configuración de cuenta de Google y activar verificación en 2 pasos. Luego de eso, hay que entrar al enlace de https://myaccount.google.com/apppasswords 
Para generar una [contraseña de 16 digitos] y de esa forma poder logguearse.

A continuación se presenta el código para el envio de un correo simple:
```java
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailing {

    public static void main(String[] args) {

        final String username = "senderEmail@gmail.com";
        final String password = "(Generar un App Password atraves de Google Security)";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); // el host que se usa sera el de google
        prop.put("mail.smtp.port", "587"); // Se debe conectar al puerto 587 
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("senderEmail@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("receiverEmail@gmail.com, receiver2Email@gmail.com")
            );
            message.setSubject("Titulo del correo");
            message.setText("Mensaje del correo");

            Transport.send(message);

            System.out.println("Correo enviado");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
```
[Google Console] Provee una documentacion de como se puede enviar un correo simple, pero no explica [cómo autenticarse a la cuenta].

[Manifest]: <https://docs.oracle.com/javase/tutorial/deployment/jar/manifestindex.html>
[Apache Maven Assembly Plugin]: <https://maven.apache.org/plugins/maven-assembly-plugin/usage.html>
[cómo generar un ejecutable Jar en Netbeans]: <https://www.youtube.com/watch?v=P-4OH4wrQNs>
[Repositorio de Maven]: <https://mvnrepository.com/>
[dependencia]: <https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html>
[JavaMail]: <https://javaee.github.io/javamail/>
[Google Console]: <https://cloud.google.com/appengine/docs/legacy/standard/java/mail/sending-mail-with-mail-api?hl=es-419>
[envio de un correo con JavaMail]: <https://mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/>
[contraseña de 16 digitos]: <https://es.stackoverflow.com/a/500544>
[Sun.Mail]: <https://javaee.github.io/javamail/docs/api/com/sun/mail/smtp/package-summary.html>
[cómo autenticarse a la cuenta]: <https://www.baeldung.com/java-email#:~:text=To%20format%20and%20style%20our,implement%20the%20tag>
