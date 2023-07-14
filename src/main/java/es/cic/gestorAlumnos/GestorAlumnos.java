package es.cic.gestorAlumnos;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GestorAlumnos {
    List<Alumno> gestorAlumnos = new ArrayList<>();


    public void addAlumno(Alumno alumno) {
        gestorAlumnos.add(alumno);
    }

    public Alumno readAlumno(Long id) {

        return gestorAlumnos.stream()
                .filter(alumno -> alumno.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Alumno no encontrado con el ID: " + id));
    }

    public void updateAlumno(Alumno alumno) {
        IntStream.range(0, gestorAlumnos.size())
                .filter(i -> gestorAlumnos.get(i).getId() == alumno.getId())
                .findFirst()
                .ifPresent(i -> gestorAlumnos.set(i, alumno));
    }

    public void deleteAlumno(long id) {
        gestorAlumnos.removeIf(alumno -> alumno.getId()==id);
    }

    public List<Alumno> showAll() {
        return gestorAlumnos;
    }

    public List<Alumno> getGestorAlumnos() {
        return gestorAlumnos;
    }

    public void cargarLista() {
        addAlumno(FactoriaDatos.ALUMNO1.get());
        addAlumno(FactoriaDatos.ALUMNO2.get());
        addAlumno(FactoriaDatos.ALUMNO3.get());
    }

    public void generarFichero(List<Alumno> alumnos, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            alumnos.stream().map(alumno -> alumno.getId() + "," + alumno.getNombre() + "," + alumno.getApellidos() + "," + alumno.getEdad() + "\n")
                    .forEach(alumno -> {
                        try {
                            writer.write(alumno);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    public List<Alumno> cargarFichero(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return gestorAlumnos = reader.lines().map(alumno -> {
                String[] split = alumno.split(",");
                return new Alumno(Long.parseLong(split[0]), split[1], split[2], 13);
            }).collect(Collectors.toList());
        }
    }
}