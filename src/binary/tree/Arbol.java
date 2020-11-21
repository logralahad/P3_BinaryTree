/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary.tree;

/**
 *
 * @author logra
 */
public class Arbol{
    
    NodoArbol raiz = null;
    public boolean bandera = false;
    
    public void insertar(int dato){
        NodoArbol nuevo = crearNodo(dato);
        
        if(raiz == null){
            raiz = nuevo;
        }else{
            NodoArbol aux  = raiz;
            NodoArbol ant = null;
            while(aux!=null){
                ant = aux;
                if(nuevo.dato > aux.dato){
                    aux = aux.der;
                }else{
                    aux = aux.izq;
                }    
            }
            if(nuevo.dato > ant.dato){
                ant.der = nuevo;
            }else{
                ant.izq = nuevo;
            }
        }
    }
    
    public void insertarUnico(int dato){
        NodoArbol nuevo = crearNodo(dato);
        
        if(raiz == null){
            raiz = nuevo;
        }else{
            NodoArbol aux  = raiz;
            NodoArbol ant = null;
            while(aux != null && aux.dato != dato){
                ant = aux;
                if(nuevo.dato > aux.dato){
                    aux = aux.der;
                }else{
                    aux = aux.izq;
                }    
            }
            if(aux == null){
                if(nuevo.dato > ant.dato){
                    ant.der = nuevo;
                }else{
                    ant.izq = nuevo;
                }
            }
        }
    }
    
    public void recorrer(Recorrido tipo){
        switch(tipo){
            case INORDER:
                inOrden(this.raiz);
                System.out.print("\n");
                break;
            case POSTORDER:
                postOrden(this.raiz);
                System.out.print("\n");
                break;
            case PREORDER:
                preOrden(this.raiz);
                System.out.print("\n");
                break;
            case HOJAS:
                soloHojas(this.raiz);
                System.out.print("\n");
                break;
            case NIVELES:
                niveles(this.raiz);
                System.out.print("\n");
                break;
        }
    }

    public void eliminar(int dato){
        NodoArbol aux = encontrar(raiz, dato);
        if(aux != null){
            raiz = eliminarRecur(raiz, dato);
            System.out.print("\nSe elimino el numero " + dato + "\n");
        }else{
            System.out.print("\nNo se elimino el numero " + dato + " porque no existe\n");
        }
    }
    
    public NodoArbol eliminarRecur(NodoArbol inicio, int dato){
        if(inicio == null) return inicio;
        
        if(dato < inicio.dato){
            inicio.izq = eliminarRecur(inicio.izq, dato);
        }else if(dato > inicio.dato){
            inicio.der = eliminarRecur(inicio.der, dato);
        }else{
            if(inicio.izq == null){
                return inicio.der;
            }else if(inicio.der == null){
                return inicio.izq;
            }
            
            NodoArbol aux = minimo(inicio.der);
            inicio.dato = aux.dato;
            inicio.der = eliminarRecur(inicio.der, inicio.dato);
        }
        
        return inicio;      
    }
    
    public NodoArbol minimo(NodoArbol aux){
        while(aux.izq != null){
            aux = aux.izq;
        }
        return aux;
    }
    
    public void niveles(NodoArbol aux){
        Cola colita = new Cola();
        
        if(aux != null){
            colita.push(aux);
            while(!colita.isEmpty()){
                NodoArbol hoja = colita.pop();
                System.out.print(hoja.dato + " ");
                if(hoja.izq != null){
                    colita.push(aux.izq);
                }
                if(hoja.der != null){
                    colita.push(aux.der);
                }
            }
        }
    }
    
    public void soloHojas(NodoArbol aux){
        if(aux != null){
            soloHojas(aux.izq);
            
            if(aux.izq == null && aux.der == null){
                System.out.print(aux.dato + " ");
            }
            
            soloHojas(aux.der);
        }
    }
       
    public void preOrden(NodoArbol aux){
        if (aux != null) {
            System.out.print(aux.dato + " ");
            preOrden(aux.izq);
            preOrden(aux.der);
        } 
    }

    public void inOrden(NodoArbol aux){
        if (aux != null) {
            inOrden(aux.izq);
            System.out.print(aux.dato + " ");
            inOrden(aux.der);
        } 
    }

    public void postOrden(NodoArbol aux){
        if (aux != null) {
            postOrden(aux.izq);
            postOrden(aux.der);
            System.out.print(aux.dato + " ");
        } 
    }

    public NodoArbol encontrar(NodoArbol aux, int dato){
        if (aux == null || dato == aux.dato) return aux;
        if (dato < aux.dato) return encontrar(aux.izq, dato);
        return encontrar(aux.der, dato);
    }
    
    public void buscarNumero(int dato){
	NodoArbol aux = encontrar(raiz, dato);
        if(aux == null){
            System.out.println("No se encontro el numero " + dato);
        }else{
            System.out.println("Se encontro el numero " + aux.dato);
        }
    }
    
    public NodoArbol crearNodo(int dato){
        NodoArbol nuevo = new NodoArbol();
        nuevo.dato = dato;
        nuevo.izq = null;
        nuevo.der = null;
        
        return nuevo;
    }
    
}
