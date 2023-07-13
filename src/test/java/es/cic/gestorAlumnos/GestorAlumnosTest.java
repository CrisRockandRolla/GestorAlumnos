package es.cic.gestorAlumnos;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorAlumnosTest {
    GestorAlumnos gestor = new GestorAlumnos();

    @BeforeEach
    public void setup() {
        gestor.cargarLista();
    }

    @Test
    void createAlumno() {
        int numAlumnos = gestor.gestorAlumnos.size();
        gestor.addAlumno(FactoriaAlumnos.ALUMNO_CREATE.get());
        Assertions.assertEquals(1, numAlumnos + 1);

        Alumno alumnoCreado = gestor.readAlumno(4L);
        Assertions.assertNotNull(alumnoCreado);
    }

    @Test
    void readAlumno() {
        Alumno alumno = gestor.readAlumno(1L);
        Assertions.assertNotNull(alumno);
        Assertions.assertEquals(1, alumno.getId());
        Assertions.assertEquals("Juan", alumno.getNombre());
        Assertions.assertEquals("Garcia Lopez", alumno.getApellidos());
        Assertions.assertEquals(20, alumno.getEdad());
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
    void deleteAlumno() {
        int numAlumnos = gestor.getGestorAlumnos().size();
        gestor.deleteAlumno(1L);

        Assertions.assertEquals(0, gestor.getGestorAlumnos().size());

        Alumno alumno = gestor.readAlumno(1L);
        Assertions.assertNull(alumno);
    }

    @Test
    void showAll() {
        List<Alumno> alumnos = gestor.showAll();
        Assertions.assertEquals(3,alumnos.size());
    }

    @Test
    void showAllVacio() {
        List<Alumno> alumnos = gestor.showAll();
        Assertions.assertEquals(0,alumnos.size());
    }

    @Test
    public void generarFichero() throws IOException{
        gestor.generarFichero(gestor.gestorAlumnos,"listaAlumnos.txt");
        long lineCount = Files.lines(Path.of("listaAlumnos.txt")).count();
        Assertions.assertEquals(gestor.gestorAlumnos.size(),lineCount);
    }

    @Order(Integer.MAX_VALUE)
    @Test
    public void cargarFichero() throws IOException{
        gestor.gestorAlumnos = gestor.cargarFichero("listaAlumnos.txt");

        Assertions.assertEquals(0,gestor.gestorAlumnos.size());
    }
}