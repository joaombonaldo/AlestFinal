public class Main {
    public static void main(String[] args) {
        AVLTreeOfInteger tree = new AVLTreeOfInteger();

        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.add(8);
        tree.add(9);
        System.out.println("1 ATE 9");
        System.out.println("Altura: " + tree.heightTree());
        tree.GeraDOT();
        
        tree.clear();
        
        tree.add(9);
        tree.add(8);
        tree.add(7);
        tree.add(6);
        tree.add(5);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        System.out.println("9 ATE 1");
        System.out.println("Altura: " + tree.heightTree());
        System.out.println("Caminhamento Central: " + tree.positionsCentral());
        tree.GeraDOT();


        AVLTreeOfInteger treeClone = new AVLTreeOfInteger();
        treeClone = tree.cloneTree();
        
        System.out.println("Clone");
        treeClone.GeraDOT();
        



    }
}