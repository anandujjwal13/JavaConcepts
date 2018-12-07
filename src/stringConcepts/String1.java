package stringConcepts;

// In Java 6 all interned strings were stored in the PermGen – the fixed size part of heap mainly used for storing loaded classes and string pool.
// PermGen has a fixed size and can not be expanded at runtime.
// In Java 7 – the string pool was relocated to the heap. It means that you are no longer limited by a separate fixed size memory area.
// String pool values are garbage collected


public class String1 {

    private void compareStringReferences( String message ,String a , String b) {
        System.out.println(message + (a == b));
    }

    private void compareWithEquals( String message ,String a , String b) {
        System.out.println(message + a.equals(b));
    }


    /*
    Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
The general contract of hashCode is:

a) Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method must consistently return the same integer, provided no information used in equals comparisons on the object is modified. This integer need not remain consistent from one execution of an application to another execution of the same application.
b) If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same integer result.
c) It is not required that if two objects are unequal according to the equals(java.lang.Object) method, then calling the hashCode method on each of the two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.
d) i.e two unequal objects may have same hashCode
     */
    private void compareHashCodes( String message ,String a , String b) {
        System.out.println(message + "HashCodes (" + (a.hashCode() +","+ b.hashCode()) + ") : " +(a.hashCode() == b.hashCode()));
    }

    public static void main(String args[]) {
        String1 obj = new String1();
        String first = "sampleString"; // created in the String Pool
        String second = "sampleString"; // created in the String Pool
        String third = "anotherString";// created in the String Pool
        String fourth = new String("sampleString");// created in the heap memory
        String fifth = new String("sampleString");// created in the heap memory
        String sixth = "sample" + "String";// created in the String Pool
        System.out.println("Comparing string's references : ");
        System.out.println();
        obj.compareStringReferences("first,second: ",first,second);// => true
        obj.compareStringReferences("first,third : ",first,third);// => false
        obj.compareStringReferences("first,fourth : ",first,fourth);// => false
        obj.compareStringReferences("first,sixth : ",first,sixth);// => true (concatenation also watches for existing strings in the String pool)
        obj.compareStringReferences("fifth,sixth : ",fifth,sixth);// => false
        obj.compareStringReferences("first, sixth.intern() : ", first, sixth.intern()); // Interning the String in the String pool
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Comparing string's with equals : ");
        System.out.println();
        obj.compareWithEquals("first,second: ",first,second);// => true
        obj.compareWithEquals("first,third : ",first,third);// => false
        obj.compareWithEquals("first,fourth : ",first,fourth);// => true
        obj.compareWithEquals("first,sixth : ",first,sixth);// => true
        obj.compareWithEquals("fifth,sixth : ",fifth,sixth);// => true
        obj.compareWithEquals("first, sixth.intern() : ", first, sixth.intern()); // => true
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println("Comparing string's hashCodes : ");
        System.out.println();
        obj.compareHashCodes("first,second: ",first,second);// => true
        obj.compareHashCodes("first,third : ",first,third);// => false
        obj.compareHashCodes("first,fourth : ",first,fourth);// => true
        obj.compareHashCodes("first,sixth : ",first,sixth);// => true
        obj.compareHashCodes("fifth,sixth : ",fifth,sixth);// => true
        obj.compareHashCodes("first, sixth.intern() : ", first, sixth.intern()); // => true
    }

}
