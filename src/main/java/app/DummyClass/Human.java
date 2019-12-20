package app.DummyClass;

public class Human {
    private String name;
    private String surname;

    public Human(String name,String surname){
    this.name=name;
    this.surname=surname;
    }

    public String get_name(){
        return name;
    }
    public String get_surname(){
        return surname;
    }

    public void set_name(String name){
        this.name=name;
    }
    public void set_surname(String surname){
        this.surname=surname;
    }
}
