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



    public LList()
    {

    }



    void add(int inValue)
    {
        Node nn = new Node(inValue);

        if (head == null)
        {
            head = nn;
            head.setNext(null);
        }

        else
        {
            for (Node current = head; current != null; current = current.getNext())
            {
                if (current.getNext() == null)
                {
                    current.setNext(nn);
                    nn.setNext(null);

                    break;
                }
            }
        }
    }



    void traverse()
    {
        for (Node current = head; current != null; current = current.getNext())
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



    void split(LList inList1, LList inList2)
    {
        int splitSize = inList1.getListSize() / 2;

        int nodeCount = 1;
        for (Node current = inList1.head; current != null; current = current.getNext())
        {
            System.out.println(current.getValue());

            if (nodeCount == splitSize)
            {
                inList2.head = current.getNext();
                current.setNext(null);
            }

            nodeCount++;
        }

        System.out.println("\nlist1: ");
        inList1.traverse();

        System.out.println("\nlist2: ");
        inList2.traverse();

        System.out.println("\n**********");
    }



    LList merge(LList left, LList right)
    {
        LList result = new LList();

        if (left.head == null)
        {
            return right;
        }

        else if (right.head == null)
        {
            return left;
        }

        Node leftCurrent = new Node(right.head.getValue());
        Node rightCurrent = new Node(left.head.getValue());

        while (leftCurrent != null && rightCurrent != null)
        {
            if (leftCurrent.getValue() < rightCurrent.getValue())
            {
                result.add(leftCurrent.getValue());
                result.add(rightCurrent.getValue());
            }

            else
            {
                result.add(rightCurrent.getValue());
                result.add(leftCurrent.getValue());
            }

            rightCurrent = rightCurrent.getNext();
            leftCurrent = leftCurrent.getNext();
        }


        while (leftCurrent != null)
        {
            result.add(leftCurrent.getValue());
            leftCurrent = leftCurrent.getNext();
        }

        while (rightCurrent != null)
        {
            result.add(rightCurrent.getValue());
            rightCurrent = rightCurrent.getNext();
        }

        return result;
    }



    public LList mergeSort(LList inList)
    {
        if (inList.head == null || inList.head.getNext() == null)
        {
            return inList;
        }

        LList result = new LList();

        LList left = new LList();
        left = inList;
        LList right = new LList();

        split(left, right);

        left = mergeSort(left);
        right = mergeSort(right);

        result = merge(left, right);

        return result;
    }
}






class Driver
{

    public static void main(String[] args)
    {
	    //int[] intArray = {32, 17, 109, 78, 2, 93, 60, 51, 176, 24, 88, 13, 120, 49, 158};
        int[] intArray = {5, 1, 8, 3};

	    LList list1 = new LList();


	    for (int i = 0; i < intArray.length; i++)
        {
            list1.add(intArray[i]);
        }

	    list1.traverse();
        LList list2 = new LList();
        //list1.split(list1, list2);
	    list1 = list1.mergeSort(list1);

	    list1.traverse();
    }
}