import java.util.Scanner;

class Empleado {
    String nombre;
    int edad;
    int salario;

    public Empleado(String nombre, int edad, int salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }

    public void mostrar() {
        System.out.println(nombre + " - Edad: " + edad + " - Salario: $" + salario);
    }
}

public class OrdenamientoEmpleados {

    public static void main(String[] args) {
        Empleado[] empleados = {
                new Empleado("Carlos", 29, 3000),
                new Empleado("Ana", 25, 3000),
                new Empleado("Luis", 32, 4000),
                new Empleado("Marta", 28, 3200),
                new Empleado("Pedro", 26, 4200),
                new Empleado("Elena", 27, 2000),
                new Empleado("Sofia", 30, 3100),
                new Empleado("Javier", 26, 3000)
        };

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHola, bienvenido a mi aplicación Empresarial, ¿Qué quieres hacer?:");
            System.out.println("1. Ordenar por edad");
            System.out.println("2. Ordenar por nombre");
            System.out.println("3. Ordenar por salario");
            System.out.println("4. Buscar por edad");
            System.out.println("5. Buscar por salario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            if (opcion == 6) break;

            switch (opcion) {
                case 1:
                    heapSort(empleados);
                    imprimirEmpleados(empleados);
                    break;
                case 2:
                    quickSort(empleados, 0, empleados.length - 1);
                    imprimirEmpleados(empleados);
                    break;
                case 3:
                    empleados = mergeSort(empleados);
                    imprimirEmpleados(empleados);
                    break;
                case 4:
                    System.out.print("Ingrese la edad a buscar: ");
                    int edad = scanner.nextInt();
                    heapSort(empleados);
                    int index = busquedaBinaria(empleados, edad, true);
                    if (index != -1) System.out.println("Empleado encontrado: " + empleados[index].nombre);
                    else System.out.println("No se encontró.");
                    break;
                case 5:
                    System.out.print("Ingrese el salario a buscar: ");
                    int salario = scanner.nextInt();
                    empleados = mergeSort(empleados);
                    index = busquedaBinaria(empleados, salario, false);
                    if (index != -1) System.out.println("Empleado encontrado: " + empleados[index].nombre);
                    else System.out.println("No se encontró.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }

    // Quick Sort para ordenar por nombre
    static void quickSort(Empleado[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Empleado[] arr, int low, int high) {
        String pivot = arr[high].nombre;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].nombre.compareTo(pivot) < 0) {
                i++;
                Empleado temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Empleado temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Merge Sort para ordenar por salario
    static Empleado[] mergeSort(Empleado[] arr) {
        if (arr.length <= 1) return arr;
        int mid = arr.length / 2;
        Empleado[] left = new Empleado[mid];
        Empleado[] right = new Empleado[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    static Empleado[] merge(Empleado[] left, Empleado[] right) {
        Empleado[] merged = new Empleado[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].salario < right[j].salario) merged[k++] = left[i++];
            else merged[k++] = right[j++];
        }
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];
        return merged;
    }

    // Heap Sort para ordenar por edad
    static void heapSort(Empleado[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            Empleado temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    static void heapify(Empleado[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].edad > arr[largest].edad) largest = left;
        if (right < n && arr[right].edad > arr[largest].edad) largest = right;
        if (largest != i) {
            Empleado temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    // Búsqueda binaria para encontrar empleados por edad o salario
    static int busquedaBinaria(Empleado[] arr, int clave, boolean buscarPorEdad) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int valor = buscarPorEdad ? arr[mid].edad : arr[mid].salario;
            if (valor == clave) return mid;
            else if (valor < clave) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    static void imprimirEmpleados(Empleado[] empleados) {
        for (Empleado e : empleados) e.mostrar();
    }
}
