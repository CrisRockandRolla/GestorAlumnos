package es.cic.gestorAlumnos;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorAlumnosTest {
    GestorAlumnos gestor = new GestorAlumnos();

    @BeforeEach
    public void setup() {
        gestor.cargarLista();
    }

    @Test
    void addAlumno() {
        int numAlumnos = gestor.gestorAlumnos.size();
        gestor.addAlumno(FactoriaDatos.ALUMNO_CREATE.get());
        Assertions.assertEquals(4, numAlumnos + 1);

        Alumno alumnoCreado = gestor.readAlumno(4L);
        Assertions.assertNotNull(alumnoCreado);
    }

    @Test
    void readAlumnoExistente() {
        Alumno alumno = gestor.readAlumno(1L);

        Assertions.assertNotNull(alumno);
        Assertions.assertEquals(1, alumno.getId());
        Assertions.assertEquals("Juan", alumno.getNombre());
        Assertions.assertEquals("Garcia Lopez", alumno.getApellidos());
        Assertions.assertEquals(20, alumno.getEdad());
    }

    @Test
    public void readAlumnoNoExistente() {
        Long id = 257L;
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            gestor.readAlumno(id);
        });
    }


    @Test
    void updateAlumno() {
        Alumno alumno = gestor.readAlumno(2L);
        Assertions.assertNotNull(alumno);//casca si es null

        alumno.setNombre("MariCarmen");
        gestor.updateAlumno(alumno);

        Alumno alumnoActualizado = gestor.readAlumno(2L);

        Assertions.assertEquals("MariCarmen", alumnoActualizado.getNombre());
    }

    @Test
    public void updateAlumnoNoExistente() {

        Long id = 257L;
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            gestor.readAlumno(id);
        });

    }

    @Test
    void deleteAlumnoExistente() {
        Alumno alumno = gestor.readAlumno(1L);

        int numAlumnos = gestor.getGestorAlumnos().size();
        gestor.deleteAlumno(1L);

        Assertions.assertEquals(2, gestor.getGestorAlumnos().size());

    }

    @Test
    public void deleteAlumnoNoExistente() {
        long id = 257;
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            gestor.readAlumno(id);
        });
    }

    @Test
    void showAll() {
        List<Alumno> alumnos = gestor.showAll();
        Assertions.assertEquals(3, alumnos.size());
    }

    @Test
    public void generarFichero() throws IOException {
        gestor.generarFichero(gestor.gestorAlumnos, "src/test/resources/listaAlumnos.txt");
        long lineCount = Files.lines(Path.of("src/test/resources/listaAlumnos.txt")).count();
        Assertions.assertEquals(gestor.gestorAlumnos.size(), lineCount);
    }

    @Order(Integer.MAX_VALUE)
    @Test
    public void cargarFichero() throws IOException {
        gestor.gestorAlumnos = gestor.cargarFichero("src/test/resources/listaAlumnos.txt");

        Assertions.assertEquals(3, gestor.gestorAlumnos.size());
    }
}