import java.util.*;
public class Crypto{
    static Scanner scan= new Scanner(System.in);
    static String input=scan.nextLine();
    static int length=input.length();
    public static void main(String[] args) {
        int [] test=new int[length];
        ArrayList<Set<Integer>> output;
        for (int j=0; j<length/2+1; j++) {
            output=split(test, j);
        }
        //printing out the subsets;
        System.out.println(collect(7, 3));
    }

    //finding the best way to construct the space generated by the code
    //num specifies the number of subsets;
    /*
    in the output array, every row is the subset of index+0's+subset
    size at the end; the number of columns is the size of the largest
    subset plus one
    */
    //Seeing if the collection of subsets is elligible;
    public static boolean test (ArrayList<Set<Integer>> input) {
        for (int r=0; r<input.size(); r++) {
            if (single(input.get(r), input)==false)
            return false;
        }
        return true;
    }

//detecting the common elements of two strings;
    public static Set<Integer> commonEle (Set<Integer> a, Set<Integer> b) {
        Set <Integer> output=new HashSet<Integer>();
        for (Integer i:a) {
            if (b.contains(i)) output.add(i);
        }
        return output;
    }
//Seeing if an element is the only intersection of sets;
    public static boolean isOnly (Integer a, ArrayList<Set<Integer>>b) {
        Set<Integer> intersection=new HashSet<Integer>();
        for (int p=0; p<b.size(); p++) {
            intersection=commonEle(intersection, b.get(p));
        }
        return (intersection.contains(a)&&intersection.size()==1);
    }
//Seeing if a single subset is eligible given the array of subsets;
//Here, note that /a/ must be one of the sets in /b/;;
    public static boolean single (Set<Integer>a, ArrayList<Set<Integer>>b) {
        //num: recording the elements that belong only to subset a;;
        int num=0;  
        /*sum: recording the number of elements that is the only
        intersection of sets;; */
        int sum=0;
        List<Integer> list=new ArrayList<Integer>(a);
        for (int q=0; q<list.size(); q++) {
            for (int j=0; j<b.size(); j++) {
                if (b.get(j).contains(list.get(q))) {
                    num++;
                    Integer target=list.get(q);
                    //index indicates which subsets in /b/ contain target;
                    List<Integer> index=new ArrayList<Integer>();
                    index.add(Integer(j));
                    for (int i=j+1; i<list.size(); i++) {
                        if (b.get(i).contains(target)) 
                        index.add(Integer(i));
                    }
                    ArrayList<Set<Integer>> c=new ArrayList<Set<Integer>>();
                    c.add(a);
                    for (int s=1; s<index.size()+1; s++) {
                        c.add(b.get(index.get(s-1)));
                    }
                    if (isOnly(target, c)==true) sum++;
                    break;
                }
            }
        }
        if (num>1) return false;
        else{
            if (num==0) {
                if (sum>a.size()-2) return true;
                else return false;
            }
            else {
                if (sum>a.size()-3) return true;
                else return false;
            }
        }
    //calculating the efficiency of a method;
        
    }
    /*
    Conditions that each subset must satisfy:
        1. At least one element belongs to another set;;
        2. At most one element that belongs only to this subset;;
        3. Every element except for the one in (2), except for at most
           one, has to be the only intersection of other subsets;;
    */

    public static Integer[][] split(int[] input, int num) {
        Integer[][] result=new Integer[input.length][num];
        //exploring different partitions of the original set;
        ArrayList<Integer[][]> collection=collect(input.length, num);
        /*
        remember to calculate the efficiency / specify which part is the
        error-correcting code; number of operations and data points and percentages
        */
        for (int v=0; v<collection.size()-1; v++) {
            Integer[][] a=collection.get(v);
            Integer[][] b=collection.get(v+1);
        }
        return result;
    }
    //generating the colletion(s) of subsets
    public static ArrayList<Integer[][]> collect (int count, int num) {
        Set<Integer> whole = new HashSet<Integer>();
        for (int u=1; u<count+1; u++)
        whole.add(Integer(u));
        //Count is the number of elements;
        //num is the number of subsets;
        ArrayList<Integer[][]> output=new ArrayList<Integer[][]>();
        //elements is the collection of all subsets of different lengths;
        ArrayList<Set<Integer>> elements=new ArrayList<Set<Integer>>();
        for (int j=2; j<count+1; j++) {
            ArrayList<Set<Integer>> addon = generate(count, j);
            for (Set<Integer> q:addon)
            elements.add(q);
        }
        //now we select a certain number of subsets from Elements to cover all elements
        //It is in the form of eg. {(1, 2, 3), (1, 2, 4)...}
        ArrayList<Set<Integer>> select=generate(elements.size(), num);
        for (int i=0; i<select.size(); i++) {
            Set<Integer> target=select.get(i);
            ArrayList<Set<Integer>> first=new ArrayList<Set<Integer>>();
            for (Integer j:target)
            first.add(elements.get(j));
            if (covers(first, whole)) {
                Integer [][] component = new Integer[count][num];
                for (int s=0; s<num; s++) {
                    for (int t=0; t<first.get(s).size(); t++) {
                        ArrayList<Integer> e = new ArrayList<Integer>(first.get(s));
                        component[t][s]=e.get(t);
                    }
                }
                output.add(component);
            }
        }
        return output;
        //In this function we need to reduce the redundant part as much as possible.
    }
    //Generating all the subsets of a certain size;
    public static ArrayList<Set<Integer>> generate (int count, int size) {
        Set<Integer> total=new HashSet<Integer>();
        for (int i=1; i<count+1; i++) 
        total.add(Integer(i));
        ArrayList<Set<Integer>> result=new ArrayList<Set<Integer>>();
        ArrayList<Set<Integer>> firstStep=generate(count, num-1);
        for (int i=0; i<firstStep.size(); i++) {
            //adding one element to the subset whose size is one less;
            ArrayList<Set<Integer>> addin=new ArrayList<Set<Integer>>();
            List<Integer> exception;
            exception=new ArrayList<Integer>(supplement(firstStep.get(i), total));
            for (int p=0; p<exception.size(); p++) {
                Set<Integer> inter=firstStep.get(i);
                inter.add(exception.get(p));
                addin.add(inter);
            }
            Set<Set<Integer>> relay1=new HashSet<Set<Integer>>(addin);
            Set<Set<Integer>> relay2=new HashSet<Set<Integer>>(result);
            result=new ArrayList<Set<Integer>>(Set.union(relay1, relay2));
        }
        return result;
    }

    //this function is to calculate C_ab;
    public static Set<Integer> supplement (Set<Integer> a, Set<Integer> b) {
        Set <Integer> output= new HashSet<Integer>();
        List <Integer> bb = new ArrayList<Integer>(b);
        for (int c=0; c<bb.size(); c++) {
            if (a.contains(bb.get(c))&&(!b.contains(bb.get(c))))
            output.add(bb.get(c));
        }
        return output;
    }
    //seeing if the union of sets cover another set;
    public static boolean covers (ArrayList<Set<Integer>>a, Set<Integer>b) {
        Set<Integer> sum = new HashSet<Integer>();
        for (Set<Integer> c:a)
        sum=Set.union(sum, c);
        for (Integer d:b)
        if (!sum.contains(d)) return false;
        return true;
    }
    //The following function assigns error-correcting codes to the network;
    public static void assign(Integer[][] a) {
        
    }

    public static double efficiency (ArrayList<Set<Integer>>d) {
        return 0.0;
    }
    /*
    Constructing the Network of Error-Correcting Bits;
    Then defining the success of a method;
    */
}