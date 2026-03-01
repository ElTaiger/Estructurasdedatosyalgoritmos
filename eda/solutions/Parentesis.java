package eda.solutions;

import eda.ds.PilaArray;
import eda.exceptions.PilaVacia;

public class Parentesis {

    public static boolean estanEquilibrados(String cadena) {
        // aqui guardamos los simbolos que quedan abiertos
        PilaArray<Character> pila = new PilaArray<>();

        for (int i = 0; i < cadena.length(); i++) {
            char simbolo = cadena.charAt(i);

            if (simbolo == '(' || simbolo == '[' || simbolo == '{') {
                pila.apilar(simbolo);
            }
            else if (simbolo == ')' || simbolo == ']' || simbolo == '}') {
                
                if (pila.esVacia()) {
                    return false;
                }

                try {
                    char tope = pila.tope();

                    if ((simbolo == ')' && tope == '(') ||
                        (simbolo == ']' && tope == '[') ||
                        (simbolo == '}' && tope == '{')) {
                        
                        pila.desapilar();
                        
                    } else {
                        return false;
                    }
                } catch (PilaVacia e) {
                    return false;
                }
            }
        }

        // si queda algo en la pila, faltan cierres
        return pila.esVacia();
    }

    public static void main(String[] args) {
        System.out.println("¡HOLA! ESTOY FUNCIONANDO");
        String[] pruebas = {
            "(()()()())",
            "((((((())",
            "(()((())()))",
            "()))",
            "{[()]}",
            "([)]"
        };

        System.out.println("--- PRUEBA DE PARÉNTESIS ---");
        for (String p : pruebas) {
            System.out.println(p + " -> " + estanEquilibrados(p));
        }
    }
}
