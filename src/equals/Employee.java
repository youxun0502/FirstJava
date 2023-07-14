package equals;

import java.util.Objects;

public class Employee extends Object{
    private String id;

    public Employee(String id) {
        this.id = id;
    }


     @Override
    public boolean equals(Object obj) {
        if ( obj instanceof Employee){
            Employee e1 = (Employee) obj;
            if ( id != null ){
                return id.equals(e1.id);
            }
        }
        return false;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("gc");
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "員工編號="+this.id;
    }
}
