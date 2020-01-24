#Librerias para Grafos
import graphviz as gv
import numpy as np


#Declaramos las variables de filas y de columnas para la matriz de adyacencia
filas = int(input("Introduce las filas de la matriz de adyacencia"))
columnas = int(input("Introduce las columnas de la matriz de adyacencia"))

matrizAdyacencia=np.array([[filas], [columnas]])
for i in range(filas):
    for j in range(columnas):
        matrizAdyacencia[i][j]=int(input("Introduce el valor de {i}x{j}"))