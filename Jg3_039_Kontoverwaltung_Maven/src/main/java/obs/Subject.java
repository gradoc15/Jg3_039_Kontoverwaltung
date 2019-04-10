package obs;

public interface Subject
{
    public void register(Observer obs);
    public void deregister(Observer obs);
    public void inform();
}
