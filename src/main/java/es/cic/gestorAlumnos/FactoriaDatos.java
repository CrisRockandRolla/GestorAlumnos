package es.cic.gestorAlumnos;

public enum FactoriaDatos {

    ALUMNO1(new Alumno(1, "Juan", "Garcia Lopez", 20)),
    ALUMNO2(new Alumno(2, "María", "Moreno Romero", 22)),
    ALUMNO3(new Alumno(3, "Pedro", "Rodriguez Pardo", 19)),
    ALUMNO_CREATE(new Alumno(4,"Africa","Jimenez Cantero",27));

    final Alumno alumno;

    FactoriaDatos(Alumno alumno) {
        this.alumno = alumno;
    }

    public Alumno get() {
        return alumno;
    }
}
