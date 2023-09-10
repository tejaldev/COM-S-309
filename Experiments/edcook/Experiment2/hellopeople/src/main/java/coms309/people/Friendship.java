package coms309.people;



public class Friendship {
    private Person Ella;
    private Person Tejal;
    private Person Raghuram;
    private Person Tanvi;

    public Friendship(Person Ella, Person Tejal, Person Raghuram, Person Tanvi){
        this.Ella = Ella;
        this.Tejal = Tejal;
        this.Tanvi = Tanvi;
        this.Raghuram = Raghuram;
    }

    public Person getElla(){
        return Ella;
    }

    public Person getTejal(){
        return Tejal;
    }

    public Person getRaghuram(){
        return Raghuram;
    }

    public Person getTanvi(){
        return Tanvi;
    }
}
