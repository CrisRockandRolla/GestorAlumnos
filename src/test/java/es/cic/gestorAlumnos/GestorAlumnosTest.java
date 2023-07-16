package es.cic.gestorAlumnos;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;


class GestorAlumnosTest {
    GestorAlumnos gestor = new GestorAlumnos();

//    @BeforeEach
//    public void setup() {
//        gestor.cargarLista();
//    }

    @Test
    void addAlumnoTest() {
        int numAlumnos = gestor.getGestorAlumnos().size();
        gestor.addAlumno(FactoriaDatos.ALUMNO_CREATE.get());
        Assertions.assertEquals(1, numAlumnos + 1);

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

        Assertions.assertEquals(0, gestor.getGestorAlumnos().size());

    }

    @Test
    void showAllTest() {
        List<Alumno> alumnos = gestor.showAll();
        Assertions.assertEquals(0, alumnos.size());
    }
//
//    @Test
//    public void generarFicheroTest() throws IOException {
//        gestor.generarFichero(gestor.getGestorAlumnos(), "src/test/listaAlumnos.txt");
//        long linesFichero = gestor.lineasFichero("src/test/listaAlumnos.txt");
//
//        Assertions.assertEquals(gestor.getGestorAlumnos().size(), linesFichero);
//    }
//
//    @Test
//    public void cargarFicheroTest()  {
//        gestor.setGestorAlumnos(gestor.cargarFichero("src/test/listaAlumnos.txt"));
//
//        Assertions.assertEquals(0, gestor.getGestorAlumnos().size());
//    }


    @Test
    public void generarFichero2Test() throws IOException {
        long pesoFichero = gestor.generarFichero2("src/test/listaAlumnos.txt");
        long pesoEsperado = gestor.pesoFichero("src/test/listaAlumnos.txt");
        Assertions.assertEquals(pesoEsperado,pesoFichero);
    }

    @Test
    public void cargarFichero2Test() throws IOException {
        gestor.setGestorAlumnos(gestor.cargarFichero2("src/test/listaAlumnos.txt"));

        Assertions.assertEquals(3, gestor.getGestorAlumnos().size());
    }

//    @Test
//    public void generarFichero2Test() throws IOException {
//        gestor.generarFichero("src/test/listaAlumnos1.txt");
//        long peso = gestor.pesoFichero("src/test/listaAlumnos1.txt");
//        Assertions.assertTrue(peso>0);
//    }
//
//    @Order(Integer.MAX_VALUE)
//    @Test
//    public void cargarFichero2Test() throws IOException {
//        gestor.setGestorAlumnos(gestor.cargarFichero2("src/test/listaAlumnos.txt"));
//
//        Assertions.assertEquals(3, gestor.getGestorAlumnos().size());
//    }


}