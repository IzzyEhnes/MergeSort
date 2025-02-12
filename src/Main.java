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
        if (head != null)
        {
            for (Node current = head; current != null; current = current.getNext())
            {
                System.out.println(current.getValue());
            }
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



    boolean isEmpty()
    {
        return this.head == null;
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

        if (inList.getListSize() % 2 == 1)
        {
            splitSize++;
        }

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
            if (leftCurrent.getValue() > rightCurrent.getValue())
            {
                result.add(rightCurrent.getValue());
                right.delete(rightCurrent.getValue());
            }

            else
            {
                result.add(leftCurrent.getValue());
                left.delete(leftCurrent.getValue());
            }

            rightCurrent = rightCurrent.getNext();
            leftCurrent = leftCurrent.getNext();
        }



        while (!left.isEmpty())
        {
            for (Node current = result.head; current != null; current = current.getNext())
            {
                if (current.getNext() == null ||
                        left.head.getValue() < current.getNext().getValue())
                {
                    Node nn = new Node(left.head.getValue());
                    nn.setNext(current.getNext());
                    current.setNext(nn);

                    break;
                }
            }

            left.delete(left.head.getValue());
        }



        while (!right.isEmpty())
        {
            for (Node current = result.head; current != null; current = current.getNext())
            {
                if (current.getNext() == null ||
                        right.head.getValue() < current.getNext().getValue())
                {
                    Node nn = new Node(right.head.getValue());
                    nn.setNext(current.getNext());
                    current.setNext(nn);

                    break;
                }
            }

            right.delete(right.head.getValue());
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

        return inList;
    }
}






class Driver
{

    public static void main(String[] args)
    {
	    int[] intArray = {32, 17, 109, 78, 2, 93, 60, 51, 176, 24, 88, 13, 120, 49, 158};
        //int[] intArray = {5, 1, 4, 2, 6, 3};
        //int[] intArray = {2, 3, 1};
        //int[] intArray = {38, 27, 43, 3, 9, 82, 10};
        //int[] intArray = {7, 3, 2, 16, 24, 4, 11, 9};
        //int[] intArray = {12};
        //int[] intArray = {}
        //int[] intArray = {};


        LList list = new LList();

        if (intArray.length > 0)
        {
            for (int i = 0; i < intArray.length; i++)
            {
                list.add(intArray[i]);
            }

            list = list.mergeSort(list);

            System.out.println("Sorted list: ");
            list.traverse();
        }

        else
        {
            System.out.println("intArray is empty.");
        }
    }
}