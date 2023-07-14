package es.cic.gestorAlumnos;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestorAlumnosTest {
    GestorAlumnos gestor = new GestorAlumnos();

    @BeforeEach
    public void setup() {
        gestor.cargarLista();
    }

    @Test
    void addAlumnoTest() {
        int numAlumnos = gestor.gestorAlumnos.size();
        gestor.addAlumno(FactoriaDatos.ALUMNO_CREATE.get());
        Assertions.assertEquals(4, numAlumnos + 1);

        Alumno alumnoCreado = gestor.readAlumno(4L);
        Assertions.assertNotNull(alumnoCreado);
    }

    @Test
    void readAlumnoTest() {
        Alumno alumno = gestor.readAlumno(1L);

        Assertions.assertNotNull(alumno);
        Assertions.assertEquals(1, alumno.getId());
        Assertions.assertEquals("Pablo", alumno.getNombre());
        Assertions.assertEquals("Garcia Navarro", alumno.getApellidos());
        Assertions.assertEquals(20, alumno.getEdad());
    }

    @Test
    void updateAlumnoTest() {
        Alumno alumno = gestor.readAlumno(2L);
        Assertions.assertNotNull(alumno);//casca si es null

        alumno.setNombre("MariCarmen");
        gestor.updateAlumno(alumno);

        Alumno alumnoActualizado = gestor.readAlumno(2L);

        Assertions.assertEquals("MariCarmen", alumnoActualizado.getNombre());
    }

    @Test
    void deleteAlumnoTest() {
        Alumno alumno = gestor.readAlumno(1L);

        int numAlumnos = gestor.getGestorAlumnos().size();
        gestor.deleteAlumno(1L);

        Assertions.assertEquals(2, gestor.getGestorAlumnos().size());

    }

    @Test
    void showAllTest() {
        List<Alumno> alumnos = gestor.showAll();
        Assertions.assertEquals(3, alumnos.size());
    }

    @Test
    public void generarFicheroTest() throws IOException {
        gestor.generarFichero(gestor.gestorAlumnos, "src/test/listaAlumnos.txt");
        long linesFichero = gestor.lineasFichero("src/test/listaAlumnos.txt");

        Assertions.assertEquals(gestor.gestorAlumnos.size(), linesFichero);
    }

    @Order(Integer.MAX_VALUE)
    @Test
    public void cargarFicheroTest() throws IOException {
        gestor.gestorAlumnos = gestor.cargarFichero("src/test/listaAlumnos.txt");

        Assertions.assertEquals(3, gestor.gestorAlumnos.size());
    }
}