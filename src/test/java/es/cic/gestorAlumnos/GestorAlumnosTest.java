package es.cic.gestorAlumnos;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;


class GestorAlumnosTest {
    GestorAlumnos gestor = new GestorAlumnos();

    @BeforeEach
    public void setup() {
        gestor.cargarLista();
    }

    @Test
    void addAlumnoTest() {
        int numAlumnos = gestor.getGestorAlumnos().size();
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
        Assertions.assertEquals(0, alumnos.size());
    }

    @Test
    public void generarFicheroSerializableTest() throws IOException {
        long pesoFichero = gestor.generarFicheroSerializable("src/test/listaAlumnos.txt");
        long pesoEsperado = gestor.pesoFicheroSerializable("src/test/listaAlumnos.txt");
        Assertions.assertEquals(pesoEsperado,pesoFichero);
    }

    @Test
    public void cargarFicheroSerializableTest() throws IOException {
        gestor.setGestorAlumnos(gestor.cargarFicheroSerializable("src/test/listaAlumnos.txt"));

        Assertions.assertEquals(3, gestor.getGestorAlumnos().size());
    }


}