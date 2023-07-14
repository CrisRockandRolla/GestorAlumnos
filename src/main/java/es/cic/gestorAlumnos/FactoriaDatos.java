package es.cic.gestorAlumnos;

public enum FactoriaDatos {

    ALUMNO1(new Alumno(1, "Pablo", "Garcia Navarro", 20)),
    ALUMNO2(new Alumno(2, "Lorena", "Moreno Romero", 22)),
    ALUMNO3(new Alumno(3, "Fernando", "Rodriguez Pardo", 19)),
    ALUMNO_CREATE(new Alumno(4,"Africa","Jimenez Cantero",27));

    final Alumno alumno;

    FactoriaDatos(Alumno alumno) {
        this.alumno = alumno;
    }

    public Alumno get() {
        return alumno;
    }
}
