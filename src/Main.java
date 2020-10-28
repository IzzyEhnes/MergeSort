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



    void split(LList list1, LList list2)
    {
        LList inList = this;

        int splitSize = inList.getListSize() / 2;

        int nodeCount = 1;
        for (Node current = inList.head; current != null; current = current.getNext())
        {
            if (nodeCount > splitSize)
            {
                list2.add(current.getValue());
            }

            else
            {
                list1.add(current.getValue());
            }

            nodeCount++;
        }
    }



    LList merge(LList left, LList right)
    {
        System.out.println("\nleft: ");
        left.traverse();

        System.out.println("\nright: ");
        right.traverse();

        LList result = new LList();

        if (left.head == null)
        {
            return right;
        }

        else if (right.head == null)
        {
            return left;
        }

        Node leftCurrent = left.head;
        Node rightCurrent = right.head;
        while (leftCurrent != null && rightCurrent != null)
        {
            System.out.println("leftCurrent: " + leftCurrent.getValue());
            System.out.println("rightCurrent: " + rightCurrent.getValue());

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

            if (leftCurrent == null || rightCurrent == null)
            {
                System.out.println("NULL");
            }
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
        if (inList.head == null)
        {
            return null;
        }

        if (inList.head.getNext() == null)
        {
            return inList;
        }

        LList left = new LList();
        LList right = new LList();

        inList.split(left, right);

        left = mergeSort(left);
        right = mergeSort(right);

        inList = merge(left, right);

        System.out.println("\nMerge result: ");
        inList.traverse();

        System.out.println("**********");

        return inList;
    }
}






class Driver
{

    public static void main(String[] args)
    {
	    //int[] intArray = {32, 17, 109, 78, 2, 93, 60, 51, 176, 24, 88, 13, 120, 49, 158};
        //int[] intArray = {5, 1, 4, 2, 6, 3};
        int[] intArray = {5, 1, 8, 3};

	    LList list = new LList();


	    for (int i = 0; i < intArray.length; i++)
        {
            list.add(intArray[i]);
        }

        LList list2 = new LList();
	    LList list1 = new LList();

	    /*
        list.split(list1, list2);
        System.out.println("list1:");
        list1.traverse();
        System.out.println("list2:");
        list2.traverse();
	     */

	    list.mergeSort(list);
    }
}