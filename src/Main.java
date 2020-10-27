/**
 * Author: Izzy Ehnes
 * Class: CS130, Section 5
 * Date completed: 28 October 2020
 */



class Node
{
    private int value;
    private Node next;
    private Node previous;



    public Node()
    {
    }



    public Node(int inValue)
    {
        this.value = inValue;
    }



    int getValue()
    {
        return value;
    }



    void setValue(int inValue)
    {
        this.value = inValue;
    }



    Node getPrevious()
    {
        return previous;
    }



    Node getNext()
    {
        return next;
    }



    void setPrevious(Node inNode)
    {
        previous = inNode;
    }



    void setNext(Node inNode)
    {
        next = inNode;
    }
}






class LList
{
    private Node head =  null;

    private LList left;
    private LList right;


    public LList()
    {

    }



    void add(int inValue)
    {
        Node nn = new Node(inValue);

        if (head == null)
        {
            nn.setNext(null);

            head = nn;
        }

        else
        {
            nn.setNext(head);

            head = nn;
        }
    }



    void traverse()
    {
        Node current = new Node();

        for (current = head; current != null; current = current.getNext())
        {
            System.out.println(current.getValue());
        }
    }



    int getListSize()
    {
        int size = 0;

        Node current = new Node();

        for (current = head; current != null; current = current.getNext())
        {
            size++;
        }

        return size;
    }



    void delete(int inValue)
    {
        Node current = new Node();

        if (head != null)
        {
            if (head.getValue() == inValue)
            {
                head = head.getNext();
            }

            else
            {
                current = head;

                while (current.getNext() != null &&
                        current.getNext().getValue() != inValue)
                {
                    current = current.getNext();
                }

                if (current.getNext() != null)
                {
                    current.setNext(current.getNext().getNext());
                }
            }
        }
    }
}






class Driver
{

    public static void main(String[] args)
    {
	    int[] intArray = {32, 17, 109, 78, 2, 93, 60, 51, 176, 24, 88, 13, 120, 49, 158};

	    LList intList = new LList();

	    /*
	    for (int i = 0; i < intArray.length; i++)
        {
            intList.add(intArray[i]);
        }
	     */

	    intList.traverse();
    }
}