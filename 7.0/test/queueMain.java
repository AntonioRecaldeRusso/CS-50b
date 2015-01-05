class queueMain
{
    public static void main (String [] args)
    {
        Queue q = new Queue (13);

        q.add ("Now");
        q.add ("is the time");
        q.add ("for all good e50b students");
        q.add ("To start worrying about term projects!");

        for (int i = 1; i < q.size(); i++)
        {
            System.out.println (q.delete());
        }
    }
}
       