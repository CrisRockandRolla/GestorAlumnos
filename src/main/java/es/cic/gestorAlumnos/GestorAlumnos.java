package es.cic.gestorAlumnos;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
                .orElse(null);
    }

    public void updateAlumno(Alumno alumno) {
        IntStream.range(0, gestorAlumnos.size())
                .filter(i -> gestorAlumnos.get(i).getId() == alumno.getId())
                .findFirst()
                .ifPresent(i -> gestorAlumnos.set(i, alumno));
    }

    public void deleteAlumno(long id) {
        gestorAlumnos.removeIf(alumno -> alumno.getId() == id);
    }

    public List<Alumno> showAll() {
        return gestorAlumnos;
    }

    public List<Alumno> getGestorAlumnos() {
        return gestorAlumnos;
    }

    public void cargarLista() {
//        addAlumno(new Alumno(1, "Juan", "Garcia Lopez", 20));
//        addAlumno(new Alumno(2, "María", "Moreno Romero", 22));
//        addAlumno(new Alumno(3, "Pedro", "Rodriguez Pardo", 19));


        addAlumno(FactoriaAlumnos.ALUMNO1.get());
        addAlumno(FactoriaAlumnos.ALUMNO2.get());
        addAlumno(FactoriaAlumnos.ALUMNO3.get());
    }

    public void generarFichero(List<Alumno> alumnos, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            alumnos.stream() .map(alumno -> alumno.getId() + "," + alumno.getNombre() + "," + alumno.getApellidos() + "," + alumno.getEdad() + "\n")
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
               return new Alumno (Long.parseLong(split[0]), split[1], split[2], 13);
           }).collect(Collectors.toList());
        }
    }
}