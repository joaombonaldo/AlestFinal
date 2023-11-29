public class AVLTreeOfInteger {

    private class Node {
        int element;
        int height;
        Node left;
        Node right;

        Node(int element) {
            this.element = element;
            this.height = 1;
        }
    }

    private Node root;

    // Método para adicionar elementos na árvore
    public void add(int element) {
        root = insert(root, element);
    }

    private Node insert(Node node, int element) {
        if (node == null) {
            return new Node(element);
        }

        if (element < node.element) {
            node.left = insert(node.left, element);
        } else if (element > node.element) {
            node.right = insert(node.right, element);
        } else {
            return node; // Duplicatas não são permitidas
        }

        // Atualizar altura deste nó ancestral
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Obter o fator de equilíbrio
        int balance = getBalance(node);

        // Se o nó está desequilibrado, então são quatro casos

        // Rotação simples à direita
        if (balance > 1 && element < node.left.element) {
            return rightRotate(node);
        }

        // Rotação simples à esquerda
        if (balance < -1 && element > node.right.element) {
            return leftRotate(node);
        }

        // Rotação esquerda-direita
        if (balance > 1 && element > node.left.element) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Rotação direita-esquerda
        if (balance < -1 && element < node.right.element) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Retorna o nó sem alterações
        return node;
    }

    // Método para calcular a altura de um nó
    private int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    public void clear() {
        root = null;
    }

    public boolean contains(int element) {
        return contains(root, element);
    }
    private boolean contains(Node node, int element) {
        if (node == null) {
            return false;
        }
        if (element < node.element) {
            return contains(node.left, element);
        } else if (element > node.element) {
            return contains(node.right, element);
        } else {
            return true;
        }
    }

    public int heightTree() {
        return heightTree(root);
    }

    private int heightTree(Node node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node getParent(Node n){
        Node parent = null;
        Node current = root;
        while(current != null){
            if(current.element == n.element){
                return parent;
            }
            if(current.element > n.element){
                parent = current;
                current = current.left;
            }else{
                parent = current;
                current = current.right;
            }
        }
        return null;
    }

    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger list = new LinkedListOfInteger();
        positionsCentral(root, list);
        return list;
    }


    private void positionsCentral(Node node, LinkedListOfInteger list) {
        if (node == null) {
            return;
        }
        positionsCentral(node.left, list);
        list.add(node.element);
        positionsCentral(node.right, list);
    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger list = new LinkedListOfInteger();
        positionsPos(root, list);
        return list;
    }

    private void positionsPos(Node node, LinkedListOfInteger list) {
        if (node == null) {
            return;
        }
        positionsCentral(node.left, list);
        positionsCentral(node.right, list);
        list.add(node.element);
    }

    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger list = new LinkedListOfInteger();
        positionsPre(root, list);
        return list;
    }

    private void positionsPre(Node node, LinkedListOfInteger list) {
        if (node == null) {
            return;
        }
        list.add(node.element);
        positionsCentral(node.left, list);
        positionsCentral(node.right, list);
    }

    // Método para obter o fator de equilíbrio de um nó
    private int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    // Rotação à direita
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Realiza a rotação
        x.right = y;
        y.left = T2;

        // Atualiza alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Retorna a nova raiz após a rotação
        return x;
    }

    public AVLTreeOfInteger cloneTree() {
            Queue<Node> fila = new Queue<>();
            Node atual = null;
            AVLTreeOfInteger res = new AVLTreeOfInteger();

            if (root != null) {
                fila.enqueue(root);
                while (!fila.isEmpty()) {
                    atual = fila.dequeue();
                    Node nodeclone = new Node(atual.element);
                    if (atual.left != null) {
                        fila.enqueue(atual.left);
                    }
                    if (atual.right != null) {
                        fila.enqueue(atual.right);
                    }
                    res.add(nodeclone.element);
                }
            }
            return res;
        }

    // Rotação à esquerda
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Realiza a rotação
        y.left = x;
        x.right = T2;

        // Atualiza alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Retorna a nova raiz após a rotação
        return y;
    }

    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" " + "\n");
        }
        //"[label = " << nodo->hDir << "]" <<endl;
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline 
    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }    
    
}
