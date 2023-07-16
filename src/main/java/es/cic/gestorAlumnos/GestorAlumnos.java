package es.cic.gestorAlumnos;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GestorAlumnos {
    private List<Alumno> gestorAlumnos = new ArrayList<>();


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

    public void setGestorAlumnos(List<Alumno> gestorAlumnos) {
        this.gestorAlumnos = gestorAlumnos;
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
                            throw new RuntimeException("No se ha podido generar el fichero");
                        }
                    });
        }
    }

    public List<Alumno> cargarFichero(String filename)  {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return gestorAlumnos = reader.lines().map(alumno -> {
                String[] split = alumno.split(",");
                return new Alumno(Long.parseLong(split[0]), split[1], split[2], Integer.parseInt(split[3]));
            }).collect(Collectors.toList());
        }catch (IOException e){
            throw new RuntimeException("El fichero que quiere cargar aun no existe. Introduzca alumnos");
        }
    }


public long generarFichero2(String filename) {
    long length = 0;
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(bos)) {
        oos.writeObject(gestorAlumnos);
        byte[] data = bos.toByteArray();
        length = data.length;
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(data);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return length;
}

    public List<Alumno> cargarFichero2(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Alumno>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException("No se puede encontrar el archivo");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public long lineasFichero(String path) throws IOException {
        long lineCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }
        return lineCount;
    }

    public long pesoFichero(String path) throws IOException {
        long peso = 0;
        File file = new File(path);
        return file.length();
    }

}