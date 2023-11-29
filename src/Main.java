import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        BinaryTree bst = new BinaryTree();
        AVL_Tree avl = new AVL_Tree();
        RedBlackTree rbt = new RedBlackTree();

        System.out.println("Выберите тип дерева: \n1. BinaryTree \n2. AVLTree \n3. RedBlackTree ");
        int treeType = scanner.nextInt();

        System.out.println("Выберите количество элементов в дереве: ");
        int nodeCount = scanner.nextInt();

        for (int i = 0; i < nodeCount; i++) {
            int value = rand.nextInt(100);
            switch (treeType) {
                case 1:
                    bst.insert(value);
                    break;
                case 2:
                    avl.insert(value);
                    break;
                case 3:
                    rbt.insert(value);
                    break;
                default:
                    System.out.println("Неверный выбор типа дерева");
                    return;
            }
        }

        System.out.println("Выберите действие, которое хотите выполнить: \n1. Delete \n2. Search \n3. Get Height: ");
        int action = scanner.nextInt();

        int value;

        switch (action) {
            case 1:
                switch (treeType) {
                    case 1:
                        System.out.println("Введите значение: ");
                        value = scanner.nextInt();
                        bst.delete(value);
                        break;
                    case 2:
                        System.out.println("Введите значение: ");
                        value = scanner.nextInt();
                        avl.delete(value);
                        break;
                    case 3:
                        System.out.println("Введите значение: ");
                        value = scanner.nextInt();
                        rbt.delete(value);
                        break;
                }
                break;
            case 2:
                switch (treeType) {
                    case 1:
                        System.out.println("Введите значение: ");
                        value = scanner.nextInt();
                        System.out.println(bst.search(value));
                        break;
                    case 2:
                        System.out.println("Введите значение: ");
                        value = scanner.nextInt();
                        System.out.println(avl.search(value));
                        break;
                    case 3:
                        System.out.println("Введите значение: ");
                        value = scanner.nextInt();
                        System.out.println(rbt.search(value));
                        break;
                }
                break;
            case 3:
                switch (treeType) {
                    case 1:
                        System.out.println(bst.getHeight(bst.root));
                        break;
                    case 2:
                        System.out.println(avl.getHeight());
                        break;
                    case 3:
                        System.out.println(rbt.getHeight());
                        break;
                }
                break;
            default:
                System.out.println("Неверный выбор действия");
        }
    }
}
